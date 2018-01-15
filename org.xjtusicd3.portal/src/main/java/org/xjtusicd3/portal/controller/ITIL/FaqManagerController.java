package org.xjtusicd3.portal.controller.ITIL;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.xjtusicd3.common.util.JsonUtil;
import org.xjtusicd3.database.helper.ClassifyHelper;
import org.xjtusicd3.database.helper.FaqPictureHelper;
import org.xjtusicd3.database.helper.UserHelper;
import org.xjtusicd3.database.helper.UserQuestionHelper;
import org.xjtusicd3.database.model.ClassifyPersistence;
import org.xjtusicd3.database.model.FaqPicturePersistence;
import org.xjtusicd3.database.model.QuestionPersistence;
import org.xjtusicd3.portal.filter.CopyFile;
import org.xjtusicd3.portal.service.FaqManagerService;
import org.xjtusicd3.portal.service.QuestionService;
import org.xjtusicd3.portal.view.FaqView;
import com.alibaba.fastjson.JSONObject;

/**
 * @author zzl
 */
@Controller
public class FaqManagerController {
	@RequestMapping(value="faqPage",method=RequestMethod.GET)
    public ModelAndView  faqPage(HttpSession session,HttpServletRequest request){
 	   ModelAndView mv=new ModelAndView("faqPage");
 	   
 	   //获取待审核FAQ
 	   List<FaqView> faqPendingAudit = FaqManagerService.faqPendingAudits();
 	  
 	   //获取所有faq分类
 	   List<ClassifyPersistence> classifyList = ClassifyHelper.classifyName(); 
 	   
 	   //获取第一个分类已审核FAQ
 	   List<FaqView> faqAudited = FaqManagerService.faqAudited(classifyList.get(0).getFAQCLASSIFYID());
   	   
 	   mv.addObject("faqPendingAudit", faqPendingAudit);
 	   mv.addObject("pendCount",faqPendingAudit.size()); 	   
 	   mv.addObject("faqAudited", faqAudited);
 	   mv.addObject("classifyList", classifyList);
 	   return mv;
    }

	/**
	 * 编辑待审核问题
	 */
	@RequestMapping(value="faqEdit",method=RequestMethod.GET)
    public ModelAndView  faqEdit(HttpSession session,String q){
 	   ModelAndView mv=new ModelAndView("faqEdit");
 	   String username = (String) session.getAttribute("UserName");
 	   if (username == null) {
 		   return new ModelAndView("login");
 	   }else {
 		   //q是传过来的faq的问题ID，获取faq问题及答案信息
 		   FaqView faqList = FaqManagerService.getAllFaqInfo(q);
 		   mv.addObject("faqList", faqList);
 		  return mv;
 	   }	  
    }
	
	/**
	 * 更新信息及状态
	 */
	@ResponseBody
	@RequestMapping(value="/updateFaq",method=RequestMethod.POST)
	public String updateFaq(HttpServletRequest request,HttpSession session){
		String username = (String) session.getAttribute("UserName");
		JSONObject jsonObject = new JSONObject();
		if (username==null) {
			jsonObject.put("value", "0");
			String result = JsonUtil.toJsonString(jsonObject); 			
			return result;
		}else{
			String questionId = request.getParameter("questionId");
			String keywords = request.getParameter("keywords");
			String description = request.getParameter("description");
			String faqcontent = request.getParameter("faqcontent");
			
			//更新faq问题表信息及状态 -- "2"为管理员审核通过状态
			QuestionService.updateFaqInfo(questionId,keywords,description,2,faqcontent);		
			jsonObject.put("value", "1");
			String result = JsonUtil.toJsonString(jsonObject);
			return result;
		}
	}
	
	/**
	 * 删除待审核信息
	 */
	@ResponseBody
	@RequestMapping(value="/deleteFAQ",method=RequestMethod.POST)
	public String deleteFAQ(HttpServletRequest request,HttpSession session){
		String username = (String) session.getAttribute("UserName");
		JSONObject jsonObject = new JSONObject();
		if (username==null) {
			jsonObject.put("value", "0");
			String result = JsonUtil.toJsonString(jsonObject); 			
			return result;
		}else{
			String questionid = request.getParameter("faqQuestionId");
			String[] str = questionid.split("_");
			String questionId = str[1];		
			//删除待审核信息 -- faqstate状态为0时，表明删除
			FaqManagerService.deleteFAQ(questionId);			
			jsonObject.put("value", "1");
			String result = JsonUtil.toJsonString(jsonObject);
			return result;
		}
	}
	
	/**
	 * @abstract:保存faq
	 */
	@ResponseBody
	@RequestMapping(value={"/saveFAQ"},method={org.springframework.web.bind.annotation.RequestMethod.POST},produces="text/plain;charset=UTF-8")
	public String saveFAQ(HttpServletRequest request,HttpServletResponse response,HttpSession session){
		//获取登录的管理员信息
		String username = (String) session.getAttribute("UserName");		

		JSONObject jsonObject = new JSONObject();
		if (username==null) {
			jsonObject.put("value", "0");
			String result = JsonUtil.toJsonString(jsonObject); 			
			return result;
		}else{
			String questionId = request.getParameter("questionId");
			String title = request.getParameter("title");
			String keywords = request.getParameter("keywords");
			String subspecialCategoryId = request.getParameter("subspecialCategoryId");
			String description = request.getParameter("description");
			String faqcontent = request.getParameter("faqcontent");
			String from = request.getParameter("from");
			
			//zzl_faqAdd_校验知识是否重复增添
			List<QuestionPersistence> isExist = FaqManagerService.faqAdd(title,username);

			if (isExist.size()==0) {
				//zzl_保存知识
				QuestionService.saveFAQ(username,title,keywords,subspecialCategoryId,description,faqcontent,questionId);
				if (from.equals("event")) {
					
					UserQuestionHelper.updateRobotAnswerState(questionId,1);
					System.out.println("from event");
				}
				//保存知识成功返回1
				jsonObject.put("value", "1");
				String result = JsonUtil.toJsonString(jsonObject);
				return result;				
			}else {			
				//重复添加返回2
				if (from.equals("event")) {
					
					UserQuestionHelper.updateRobotAnswerState(questionId,1);
					System.out.println("from event");
				}
				jsonObject.put("value", "2");
				String result = JsonUtil.toJsonString(jsonObject);
				return result;
			}			
		}

	}
		
	//选择问题分类
	@ResponseBody
	@RequestMapping(value="/selectClassify",method={org.springframework.web.bind.annotation.RequestMethod.POST},produces="application/json;charset=UTF-8")
	public String selectClassify(HttpServletRequest request,HttpSession session){	
		//获取登录的管理员信息
		String username = (String) session.getAttribute("UserName");		
		JSONObject jsonObject = new JSONObject();
		if (username==null) {
			jsonObject.put("value", "0");
			String result = JsonUtil.toJsonString(jsonObject); 			
			return result;
		}else{
			String classifyId = request.getParameter("classifyId");
			List<FaqView> faqAudited = FaqManagerService.faqAudited(classifyId);
			jsonObject.put("faqAudited", faqAudited);
			jsonObject.put("value", "1");
			String result = JsonUtil.toJsonString(jsonObject); 			
			return result;
		}
	
	}
	
	//查看faq更多信息
	@ResponseBody
	@RequestMapping(value="/lookMoreFaqInfo",method={org.springframework.web.bind.annotation.RequestMethod.POST},produces="application/json;charset=UTF-8")
	public String lookMoreFaqInfo(HttpServletRequest request,HttpSession session){
		//获取ajax传来数据
		String faqQuestionId = request.getParameter("faqQuestionId"); 		
    	//获取ID对应faq信息		
		FaqView list = FaqManagerService.getAllFaqInfo(faqQuestionId);		
		JSONObject jsonObject = new JSONObject();		
		jsonObject.put("moreFaqInfo", list);				
		String result = JsonUtil.toJsonString(jsonObject);		
		return result;
	}
	
	/**
	 * @abstract:将已解决问题加入faq
	 */
	@ResponseBody
	@RequestMapping(value={"/saveCommunityQuestionToFAQ"},method={org.springframework.web.bind.annotation.RequestMethod.POST},produces="text/plain;charset=UTF-8")
	public String saveCommunityQuestionToFAQ(HttpServletRequest request,HttpServletResponse response,HttpSession session){
		//获取登录的管理员信息
		String username = (String) session.getAttribute("UserName");		
		
		//获取路径
		String url = (String) session.getAttribute("urlPath");
		
		if (username==null) {
			return "0";
		}else {			
			String communityQuestionId = request.getParameter("communityQuestionId");
			String faqTitle = request.getParameter("title");			
			String faqDescription = request.getParameter("content");
			String keywords = request.getParameter("keywords");
			String faqclassifyId = request.getParameter("classifyId");			
			String problemUser = request.getParameter("problemUser");
			String modifyTime = request.getParameter("problemTime");			
			String userId = request.getParameter("userId");			
			String answerContent = request.getParameter("answerContent");
			String answerUser = request.getParameter("answerUser");
			
			//zzl_faqAdd_校验知识是否重复增添
			List<QuestionPersistence> isExist = FaqManagerService.faqAdd(faqTitle,problemUser);

			JSONObject jsonObject = new JSONObject();
			if (isExist.size()==0) {
				//zzl_保存知识
				QuestionService.saveCommunityQuestionToFAQ(faqTitle,keywords,faqclassifyId,modifyTime,faqDescription,userId,answerContent,communityQuestionId,answerUser);
				//保存知识成功返回1
				jsonObject.put("value", "1");
				jsonObject.put("url", url);
				String result = JsonUtil.toJsonString(jsonObject);
				return result;				
			}else {			
				//重复添加返回2
				jsonObject.put("value", "2");
				jsonObject.put("url", url);
				String result = JsonUtil.toJsonString(jsonObject);
				return result;
			}
		}
	}	
	
	/**
	 * faq图片上传
	 * @throws IOException 
	 * @throws IllegalStateException 
	 */
	@ResponseBody
	@RequestMapping(value={"/webUploader"},method={org.springframework.web.bind.annotation.RequestMethod.POST},produces="text/plain;charset=UTF-8")
	public String webUploader(HttpServletRequest request,HttpServletResponse response,HttpSession session) throws IllegalStateException, IOException{
		String username = (String) session.getAttribute("UserName");		
		if (username==null) {
			return "redirect:login.html";
		}else {
			MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest) request;//request强制转换注意
			Iterator<String> iterator = mRequest.getFileNames();
	        String path  ="";
	        String fileName = "";
	        String suffix = "";
			String filename = "";
	        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	        String dir = "static/image/"+username +"/"+ sdf.format(new Date()) + "/";
	        String realPath = request.getSession().getServletContext().getRealPath("/");
	       	        
	        while(iterator.hasNext()){
	            MultipartFile multipartFile = mRequest.getFile(iterator.next());
	            if(multipartFile != null){
	                String fn = multipartFile.getOriginalFilename();
	                 suffix = fn.substring(fn.lastIndexOf("."));
	                 filename = RandomStringUtils.randomAlphanumeric(6);
	                fileName = dir + filename + suffix;
	                path = realPath + fileName;
	                path = path.replace("\\", "/");
	                File f = new File(path);
	                if(!f.mkdirs()){
	                    f.mkdir();
	                }
	                multipartFile.transferTo(f);
	            }
	        }
	        
	        CopyFile copyFile = new CopyFile();
	        String newPath = copyFile.copyFile(path, username, sdf.format(new Date()));

	        newPath = newPath.replace("\\", "/");
	        newPath = newPath.replace("E:/eclipse/workspace/robot-master/org.xjtusicd3.partner/src/main/webapp", "/org.xjtusicd3.partner");
	        UserHelper.updateUserImage(username, newPath);
			FaqManagerService.addFaqPic(username,newPath);
	        
	        try {
				Thread.sleep(3500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	        String aString = "{\"result\":\""+newPath+"\"}";
			return aString;
		}
    }
	
	//faq推荐栏
	 @RequestMapping(value="faqCommendPage",method=RequestMethod.GET)
     public ModelAndView  faqCommendPage(HttpSession session,HttpServletRequest request){
  	   ModelAndView mv=new ModelAndView("faqCommendPage");
  	   
  	   List<FaqPicturePersistence> faqPicList = FaqManagerService.faqPicList(1,10);
  	   
  	   mv.addObject("faqPicList", faqPicList);
  	   
  	   return mv;
     }
	 
	 
	@ResponseBody
	@RequestMapping(value={"/deletePic"},method={org.springframework.web.bind.annotation.RequestMethod.POST},produces="text/plain;charset=UTF-8")
	public String deletePic(HttpServletRequest request,HttpServletResponse response,HttpSession session){
		//获取登录的管理员信息
		String username = (String) session.getAttribute("UserName");		
		
		if (username==null) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("value", "0");
			return JsonUtil.toJsonString(jsonObject);			
		}else {			
			String faqPicId = request.getParameter("faqPicId");
			
			FaqPictureHelper.deletePic(faqPicId,0);
			
			JSONObject jsonObject = new JSONObject();

			//删除成功返回1
			jsonObject.put("value", "1");
			String result = JsonUtil.toJsonString(jsonObject);
			return result;				

		}
	}
	
	
	@ResponseBody
	@RequestMapping(value={"/updateTitle"},method={org.springframework.web.bind.annotation.RequestMethod.POST},produces="text/plain;charset=UTF-8")
	public String updateTitle(HttpServletRequest request,HttpServletResponse response,HttpSession session){
		//获取登录的管理员信息
		String username = (String) session.getAttribute("UserName");		
		
		if (username==null) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("value", "0");
			return JsonUtil.toJsonString(jsonObject);			
		}else {			
			String faqPicId = request.getParameter("faqPicId");
			String picTitle = request.getParameter("picTitle");
			
			FaqPictureHelper.updateTitle(faqPicId,picTitle);
			
			JSONObject jsonObject = new JSONObject();

			//保存知识成功返回1
			jsonObject.put("value", "1");
			String result = JsonUtil.toJsonString(jsonObject);
			return result;				

		}
	}
	 
}
