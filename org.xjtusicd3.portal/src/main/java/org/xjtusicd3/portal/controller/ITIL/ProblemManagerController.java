package org.xjtusicd3.portal.controller.ITIL;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.xjtusicd3.common.util.JsonUtil;
import org.xjtusicd3.portal.service.ProblemManagerService;
import org.xjtusicd3.portal.view.ProblemView;
import org.xjtusicd3.portal.view.Problem_AnswerView;

import com.alibaba.fastjson.JSONObject;

@Controller
/**
 * @author zzl
 */
public class ProblemManagerController {

	@RequestMapping(value="problemPage",method=RequestMethod.GET)
	public ModelAndView problemPage()
	{
		ModelAndView mv = new ModelAndView("problemPage");
		
		//获取问题中心没有最佳答案的问题
		List<ProblemView> problemUnresolved = ProblemManagerService.problemUnresolved();
						
		//获取已处理问题总数
		int problemResolvedCount = ProblemManagerService.problemResolvedCount();
		
		mv.addObject("problemUnresolved",problemUnresolved);
		mv.addObject("unResolvedCounts", problemUnresolved.size());
		mv.addObject("resolvedCounts", problemResolvedCount);		
				
		return mv;		
	}
	
	/**
	 * 更新社区问题状态
	 */
	@ResponseBody
	@RequestMapping(value="/ignoreQuestion",method=RequestMethod.POST)
	public String ignoreQuestion(HttpServletRequest request,HttpSession session){
		String username = (String) session.getAttribute("UserName");
		JSONObject jsonObject = new JSONObject();
		if (username==null) {
			jsonObject.put("value", "0");
			String result = JsonUtil.toJsonString(jsonObject); 			
			return result;
		}else{
			String questionId = request.getParameter("questionId");
			
			//更新tbl_communityquestion表问题状态 -- 2是忽略
			ProblemManagerService.updateCommunityQuestionState(questionId,2);
			jsonObject.put("value", "1");
			String result = JsonUtil.toJsonString(jsonObject);
			return result;
		}

	}
	
	//已处理问题
	@ResponseBody
	@RequestMapping(value={"/getResolvedProblem"},method={org.springframework.web.bind.annotation.RequestMethod.POST},produces="application/json;charset=UTF-8")
	public String getResolvedProblem(HttpServletRequest request,HttpSession session){			
		String username = (String) session.getAttribute("UserName");
		JSONObject jsonObject = new JSONObject();
		if (username==null) {
			jsonObject.put("value", "0");
			String result = JsonUtil.toJsonString(jsonObject); 			
			return result;
		}else{
			//获取问题中心有最佳答案的问题	
			List<Problem_AnswerView> problemResolved = ProblemManagerService.problemResolved();
			jsonObject.put("problemResolved", problemResolved);
			jsonObject.put("value", "1");
			String result = JsonUtil.toJsonString(jsonObject);
			return result;
		}
	}
	
	//待处理问题
	@ResponseBody
	@RequestMapping(value={"/getUnResolvedProblem"},method={org.springframework.web.bind.annotation.RequestMethod.POST},produces="application/json;charset=UTF-8")
	public String getUnResolvedProblem(HttpServletRequest request,HttpSession session){			
		String username = (String) session.getAttribute("UserName");
		JSONObject jsonObject = new JSONObject();
		if (username==null) {
			jsonObject.put("value", "0");
			String result = JsonUtil.toJsonString(jsonObject); 			
			return result;
		}else{
			//获取待处理问题
			List<ProblemView> problemUnresolved = ProblemManagerService.problemUnresolved();
			jsonObject.put("problemUnresolved", problemUnresolved);
			jsonObject.put("value", "1");
			String result = JsonUtil.toJsonString(jsonObject);
			return result;
		}
	}
	
	/**
	 * @abstract 获取未处理问题详情
	 */
	@RequestMapping(value="showUnResolvedProblem",method=RequestMethod.GET)
	public ModelAndView showUnResolvedProblem(String p){
		ModelAndView mv = new ModelAndView("showUnResolvedProblem");
		
		//获取未处理问题详情
		ProblemView unResolvedProblemDetail = ProblemManagerService.getUnResolvedEventDetail(p);
		
		mv.addObject("unResolvedProblemDetail", unResolvedProblemDetail);
		return mv;
	}
	
	/**
	 * @abstract 获取已处理问题详情
	 */
	@RequestMapping(value="showResolvedProblem",method=RequestMethod.GET)
    public ModelAndView  showResolvedProblem(String p){
		ModelAndView mv = new ModelAndView("showResolvedProblem");

		//获取已处理问题详情
		Problem_AnswerView resolvedProblemDetail = ProblemManagerService.getResolvedEventDetail(p);
		
		mv.addObject("resolvedProblemDetail", resolvedProblemDetail);
		return mv;
    }
	
}
