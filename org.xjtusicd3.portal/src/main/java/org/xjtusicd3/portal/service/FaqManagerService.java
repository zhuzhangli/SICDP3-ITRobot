package org.xjtusicd3.portal.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.xjtusicd3.database.helper.AnswerHelper;
import org.xjtusicd3.database.helper.ClassifyHelper;
import org.xjtusicd3.database.helper.FaqPictureHelper;
import org.xjtusicd3.database.helper.QuestionHelper;
import org.xjtusicd3.database.helper.UserHelper;
import org.xjtusicd3.database.model.AnswerPersistence;
import org.xjtusicd3.database.model.FaqPicturePersistence;
import org.xjtusicd3.database.model.QuestionPersistence;
import org.xjtusicd3.portal.view.FaqView;
/**
 * 
 * @author zzl
 * @abstract faq管理_faqPage.ftl
 *
 */
public class FaqManagerService {
	/**
	 * @param FAQSTATE = 2 通过审核；FAQSTATE = 1待审核
	 */
	public static List<FaqView> faqPendingAudits() {
		//待审核faq视图
		List<FaqView> faqPendingAudits = new  ArrayList<FaqView>();		
		//获取所有待审核faq -- 即FAQSTATE字段为1
		List<QuestionPersistence> faqLists = QuestionHelper.faqPendingAudits(1,0,100);				
		for(QuestionPersistence faqList:faqLists){
			FaqView faqPendingAudit = new FaqView();
			
			faqPendingAudit.setFAQQUESTIONID(faqList.getFAQQUESTIONID());
			faqPendingAudit.setFAQTITLE(faqList.getFAQTITLE());
			faqPendingAudit.setFAQDESCRIPTION(faqList.getFAQDESCRIPTION());
			
			String classifyName = ClassifyHelper.getClassifyNameById(faqList.getFAQCLASSIFYID());
			faqPendingAudit.setFAQCLASSIFYNAME(classifyName);
			faqPendingAudit.setFAQKEYWORDS(faqList.getFAQKEYWORDS());
			faqPendingAudit.setMODIFYTIME(faqList.getMODIFYTIME());
			
			String username = UserHelper.getUserNameById(faqList.getUSERID());
			faqPendingAudit.setUSERID(faqList.getUSERID());
			faqPendingAudit.setUSERNAME(username);
			
			String faqContent = AnswerHelper.getAnswerContentByQuestionId(faqList.getFAQQUESTIONID());
			faqPendingAudit.setFAQCONTENT(faqContent);
			
			faqPendingAudits.add(faqPendingAudit);
		}	
		return faqPendingAudits;
	}

	/**
	 * 删除待审核faq
	 */
	public static void deleteFAQ(String questionId) {
		//删除待审核信息
		QuestionHelper.deleteFAQ(questionId,0);		
		
	}
	
	//获取已审核FAQ
	public static List<FaqView> faqAudited(String classifyId) {
		//待审核faq视图
		List<FaqView> faqAudited = new  ArrayList<FaqView>();
		
		//获取所有已审核faq -- 即FAQSTATE字段为2
		List<QuestionPersistence> faqLists = QuestionHelper.faqAudited(classifyId,2,0,25);
				
		for(QuestionPersistence faqList:faqLists){
			FaqView faqPendingAudit = new FaqView();
			
			faqPendingAudit.setFAQQUESTIONID(faqList.getFAQQUESTIONID());
			faqPendingAudit.setFAQTITLE(faqList.getFAQTITLE());
			faqPendingAudit.setFAQDESCRIPTION(faqList.getFAQDESCRIPTION());
			
			String classifyName = ClassifyHelper.getClassifyNameById(faqList.getFAQCLASSIFYID());
			faqPendingAudit.setFAQCLASSIFYNAME(classifyName);
			faqPendingAudit.setFAQKEYWORDS(faqList.getFAQKEYWORDS());
			faqPendingAudit.setMODIFYTIME(faqList.getMODIFYTIME());
			
			String username = UserHelper.getUserNameById(faqList.getUSERID());
			faqPendingAudit.setUSERID(faqList.getUSERID());
			faqPendingAudit.setUSERNAME(username);
			
			String content = AnswerHelper.getAnswerContentByQuestionId(faqList.getFAQQUESTIONID());
			faqPendingAudit.setFAQCONTENT(content);
			
			faqAudited.add(faqPendingAudit);
		}	
		return faqAudited;
	}

	/**
	 * 获取faq问题及答案信息
	 * @param q
	 * @return
	 */
	public static FaqView getAllFaqInfo(String faqQuestionId) {
		//待编辑faq视图
		FaqView faqEdit = new FaqView();		
		if (faqQuestionId!=null) {
			//获取faqQuestionId对应的问题列表
			List<QuestionPersistence> faqQuserionList = QuestionHelper.getFaqQuestionInfo(faqQuestionId);
			
			//获取faqQuestionId对应的答案列表
			List<AnswerPersistence> faqAnswerList = AnswerHelper.getAnswerByQuestionId(faqQuestionId);
			
			faqEdit.setFAQQUESTIONID(faqQuestionId);
			faqEdit.setFAQTITLE(faqQuserionList.get(0).getFAQTITLE());
			
			String classifyName = ClassifyHelper.getClassifyNameById(faqQuserionList.get(0).getFAQCLASSIFYID());
			faqEdit.setFAQCLASSIFYID(faqQuserionList.get(0).getFAQCLASSIFYID());
			faqEdit.setFAQCLASSIFYNAME(classifyName);
		
			faqEdit.setFAQKEYWORDS(faqQuserionList.get(0).getFAQKEYWORDS());
			faqEdit.setFAQDESCRIPTION(faqQuserionList.get(0).getFAQDESCRIPTION());
			faqEdit.setFAQCONTENT(faqAnswerList.get(0).getFAQCONTENT());
			
			String username = UserHelper.getUserNameById(faqQuserionList.get(0).getUSERID());
			faqEdit.setUSERNAME(username);
		}
		return faqEdit;
	}
	
	/**
	 * 查看username发表的title faq的信息
	 */
	public static List<QuestionPersistence> faqAdd(String title, String username) {
		//获取用户ID
		String userId = UserHelper.getUserIdByName(username);
		
		//判断是否重复添加
		List<QuestionPersistence> isExist = QuestionHelper.titleIsExist(title,userId);
		
		return isExist;
	}

	/**
	 * faq首页面推荐栏图片更新
	 */
	public static void addFaqPic(String username, String imgPath) {
		FaqPicturePersistence faqPicturePersistence = new FaqPicturePersistence();
		faqPicturePersistence.setFAQPICTUREID(UUID.randomUUID().toString());
		faqPicturePersistence.setIMGPATH(imgPath);
		String UserId = UserHelper.getUserIdByName(username);
		faqPicturePersistence.setUSERID(UserId);
		Date date=new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String createTime = format.format(date);
		faqPicturePersistence.setTIME(createTime);
		faqPicturePersistence.setSTATE(1);
		FaqPictureHelper.faqImgSave(faqPicturePersistence);		
	}

	/**
	 * faq首页面推荐栏信息
	 */
	public static List<FaqPicturePersistence> faqPicList(int state, int num) {
		List<FaqPicturePersistence> faqPicList = FaqPictureHelper.faqPicture(state, num);
		return faqPicList;
	}
}
