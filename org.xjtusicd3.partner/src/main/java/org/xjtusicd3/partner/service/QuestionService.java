package org.xjtusicd3.partner.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.xjtusicd3.database.helper.AnswerHelper;
import org.xjtusicd3.database.helper.ClassifyHelper;
import org.xjtusicd3.database.helper.CommentHelper;
import org.xjtusicd3.database.helper.QuestionHelper;
import org.xjtusicd3.database.helper.ShareHelper;
import org.xjtusicd3.database.helper.UserHelper;
import org.xjtusicd3.database.model.AnswerPersistence;
import org.xjtusicd3.database.model.LogPersistence;
import org.xjtusicd3.database.model.QuestionPersistence;
import org.xjtusicd3.database.model.UserPersistence;
import org.xjtusicd3.partner.view.Faq2_faqContentView;
import org.xjtusicd3.partner.view.Faq3_faqContentView;
import org.xjtusicd3.partner.view.Faq_CommendView;
import org.xjtusicd3.partner.view.Faq_UserDynamics;
import org.xjtusicd3.partner.view.Faq2_faqUserView;
import org.xjtusicd3.partner.view.Faq3_faqAnswer;

public class QuestionService {
	/**
	 * zyq_FAQ页面_用户动态
	 */
	public static List<Faq_UserDynamics> userDynamics(){
		List<Faq_UserDynamics> userDynamics = new ArrayList<Faq_UserDynamics>();
		
		List<QuestionPersistence> questionPersistences = QuestionHelper.faq_userDynamics(2,5);		
		
		for(QuestionPersistence questionPersistence:questionPersistences){
			Faq_UserDynamics faq_UserDynamics = new Faq_UserDynamics();
						
			faq_UserDynamics.setFaqId(questionPersistence.getFAQQUESTIONID());
			faq_UserDynamics.setFaqTitle(questionPersistence.getFAQTITLE());
			faq_UserDynamics.setTime(questionPersistence.getMODIFYTIME());
			
			if (questionPersistence.getMODIFYNUMBER().equals("1")) {
				faq_UserDynamics.setHow("发布");
			}else {
				faq_UserDynamics.setHow("修改");
			}
			
			//根据用户Id获取用户信息			
			List<UserPersistence> userPersistences = UserHelper.getUserInfoById(questionPersistence.getUSERID());			
			faq_UserDynamics.setUserId(userPersistences.get(0).getUSERID());
			faq_UserDynamics.setUserName(userPersistences.get(0).getUSERNAME());
			userDynamics.add(faq_UserDynamics);
		}
		return userDynamics;
	}
	
	
	//有权限的角色分享社区问题
	public static void saveShare2(String userId,String faqquestionId){
    	Date date=new Date();
    	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	String time = format.format(date);
		ShareHelper.saveShare2(UUID.randomUUID().toString(),userId,time,faqquestionId);
	}
	
	
	/**
	 * abstract:获取未登录用户推荐列表
	 */
	public static List<Faq_CommendView> faq_recommend_Limit(int faqstate,int startnum,int number) {
		//Faq_CommendView中信息不全、后续可补充        !!!分类图片
		List<Faq_CommendView> faq_CommendViews = new ArrayList<Faq_CommendView>();
		List<QuestionPersistence> questionPersistences = QuestionHelper.faq_recommend_Limit(faqstate,startnum,number);
		for (QuestionPersistence questionPersistence:questionPersistences) {
			Faq_CommendView faq_CommendView = new Faq_CommendView();
			faq_CommendView.setUserId(questionPersistence.getUSERID());
			faq_CommendView.setFAQQUESTIONID(questionPersistence.getFAQQUESTIONID());			
			faq_CommendView.setFAQTITLE(questionPersistence.getFAQTITLE());
			faq_CommendView.setMODIFYTIME(questionPersistence.getMODIFYTIME());
			faq_CommendView.setFAQDESCRIPTION(questionPersistence.getFAQDESCRIPTION());
			faq_CommendView.setCOLLECTION(questionPersistence.getCOLLECTION());
			faq_CommendView.setSCAN(questionPersistence.getSCAN());			
			
			int commentCount = CommentHelper.commentInfo(questionPersistence.getFAQQUESTIONID());
			faq_CommendView.setCOMMENTSUM(commentCount);
			
			List<UserPersistence> userInfo = UserHelper.getUserInfoById(questionPersistence.getUSERID());
			faq_CommendView.setFAQUSERNAME(userInfo.get(0).getUSERNAME());
			faq_CommendView.setFAQUSERIMAGE(userInfo.get(0).getAVATAR());
			faq_CommendViews.add(faq_CommendView);			
		}
		return faq_CommendViews;
	}
	
	/**
	 * abstract:获取已登录用户推荐列表
	 * @param j 
	 * @param startnumber 
	 */
	public static List<Faq_CommendView> user_recommend_Limit(String userid, int state,int startnum,  int num) {
		//Faq_CommendView中信息不全、后续可补充
		List<Faq_CommendView> faq_CommendViews = new ArrayList<Faq_CommendView>();
		List<LogPersistence> logPersistences = LogService.getLogs(userid);
		int i;
		for ( i = 0; i < logPersistences.size(); i++) {
			String log = logPersistences.get(i).getUrl();
			if (log.indexOf("/faq3.html?q=") != -1) {
				String a[] = log.split("=");
				String questionId = a[a.length-1];
				String faq_classifyId = QuestionHelper.faqclassify(questionId);						
				String parentId = ClassifyHelper.faq_parentId(faq_classifyId);
				List<QuestionPersistence> questionPersistences = QuestionHelper.questionView(parentId,state,startnum,num);
				for (QuestionPersistence questionPersistence:questionPersistences) {
					Faq_CommendView faq_CommendView = new Faq_CommendView();
					faq_CommendView.setFAQQUESTIONID(questionPersistence.getFAQQUESTIONID());			
					faq_CommendView.setFAQTITLE(questionPersistence.getFAQTITLE());
					faq_CommendView.setMODIFYTIME(questionPersistence.getMODIFYTIME());;
					faq_CommendView.setFAQDESCRIPTION(questionPersistence.getFAQDESCRIPTION());
					faq_CommendView.setCOLLECTION(questionPersistence.getCOLLECTION());
					faq_CommendView.setSCAN(questionPersistence.getSCAN());					
					int commentCount = CommentHelper.commentInfo(questionPersistence.getFAQQUESTIONID());
					faq_CommendView.setCOMMENTSUM(commentCount);
					List<UserPersistence> userInfo = UserHelper.getUserInfoById(questionPersistence.getUSERID());
					faq_CommendView.setFAQUSERNAME(userInfo.get(0).getUSERNAME());
					faq_CommendView.setFAQUSERIMAGE(userInfo.get(0).getAVATAR());					
					faq_CommendViews.add(faq_CommendView);
				}
				break;
			}			
		}
		if (i == logPersistences.size()) {
			//zzl_用户首次登录时还没有log记录_依旧用faq_recommend_Limit推荐
			List<QuestionPersistence> questionPersistences = QuestionHelper.faq_recommend_Limit(2,startnum,5);
			for (QuestionPersistence questionPersistence:questionPersistences) {
				Faq_CommendView faq_CommendView = new Faq_CommendView();
				faq_CommendView.setFAQQUESTIONID(questionPersistence.getFAQQUESTIONID());			
				faq_CommendView.setFAQTITLE(questionPersistence.getFAQTITLE());
				faq_CommendView.setCOLLECTION(questionPersistence.getCOLLECTION());
				faq_CommendView.setSCAN(questionPersistence.getSCAN());
				faq_CommendView.setMODIFYTIME(questionPersistence.getMODIFYTIME());;
				faq_CommendView.setFAQDESCRIPTION(questionPersistence.getFAQDESCRIPTION());
				int commentCount = CommentHelper.commentInfo(questionPersistence.getFAQQUESTIONID());
				faq_CommendView.setCOMMENTSUM(commentCount);
				List<UserPersistence> userInfo = UserHelper.getUserInfoById(questionPersistence.getUSERID());
				faq_CommendView.setFAQUSERNAME(userInfo.get(0).getUSERNAME());
				faq_CommendView.setFAQUSERIMAGE(userInfo.get(0).getAVATAR());
				faq_CommendViews.add(faq_CommendView);	
			}
		}		
		return faq_CommendViews;
	}
	
	/**
	 * abstract:FAQ的增加
	 */
	public static void saveFAQ2(String userId, String title, String keywords, String subspecialCategoryId,String description, String faqcontent) {
		//保存faq问题
		QuestionPersistence questionPersistence = new QuestionPersistence();
		String questionid = UUID.randomUUID().toString();
		questionPersistence.setFAQQUESTIONID(questionid);
		questionPersistence.setFAQTITLE(title);
		questionPersistence.setFAQDESCRIPTION(description);
		questionPersistence.setFAQCLASSIFYID(subspecialCategoryId);
		questionPersistence.setFAQKEYWORDS(keywords);		
		questionPersistence.setCOLLECTION("0");
		questionPersistence.setSCAN("0");
    	Date date=new Date();
    	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	String time = format.format(date);
		questionPersistence.setMODIFYTIME(time);
		questionPersistence.setMODIFYNUMBER("1");
		//待审核为1
		questionPersistence.setFAQSTATE(1);
		questionPersistence.setUSERID(userId);
		QuestionHelper.save(questionPersistence);
		
		AnswerPersistence answerPersistence = new AnswerPersistence();
		answerPersistence.setFAQANSWERID(UUID.randomUUID().toString());
		answerPersistence.setFAQQUESTIONID(questionid);
		answerPersistence.setFAQCONTENT(faqcontent);	
		answerPersistence.setUSERID(userId);
		AnswerHelper.save(answerPersistence);
	}
		
	/**
	 * abstract:推荐知识_根据收藏量推荐前4个
	 */
	public static List<Faq_CommendView> faqInfo(String faqParentId) {
		List<Faq_CommendView> faq_CommendViews = new ArrayList<Faq_CommendView>();
		List<QuestionPersistence> questionPersistences = QuestionHelper.faqInfo_limit(faqParentId);
		for (QuestionPersistence questionPersistence:questionPersistences) {
			Faq_CommendView faq_CommendView = new Faq_CommendView();
			faq_CommendView.setFAQQUESTIONID(questionPersistence.getFAQQUESTIONID());
			faq_CommendView.setFAQTITLE(questionPersistence.getFAQTITLE());
			faq_CommendView.setFAQDESCRIPTION(questionPersistence.getFAQDESCRIPTION());
			faq_CommendViews.add(faq_CommendView);
		}		
		return faq_CommendViews;		
	}
	
	/**
	 * faq2_知识列表
	 */
	public static List<Faq2_faqContentView> faqlist_faq2(String ClassifyId,int pageNow){
		List<Faq2_faqContentView> faq2Views = new ArrayList<Faq2_faqContentView>();
		//每次获取一页5条信息
		List<QuestionPersistence> questionPersistences = QuestionHelper.faq2_faqlist(ClassifyId,2,pageNow,5);
		for(QuestionPersistence questionPersistence:questionPersistences){
			List<Faq2_faqUserView> user_faq2Views = new ArrayList<Faq2_faqUserView>();
			String userId = QuestionHelper.findUserIdByQuestionId(questionPersistence.getFAQQUESTIONID());
			List<UserPersistence> userPersistences = UserHelper.getUserInfoById(userId);
			for(UserPersistence userPersistence:userPersistences){
				Faq2_faqUserView user_faq2View = new Faq2_faqUserView(userPersistence);
				user_faq2Views.add(user_faq2View);
			}			
			Faq2_faqContentView faq2View  = new Faq2_faqContentView(questionPersistence);
			faq2View.setuList(user_faq2Views);
			int commentCount = CommentHelper.commentInfo(questionPersistence.getFAQQUESTIONID());
			faq2View.setCommentNumber(commentCount);
			faq2Views.add(faq2View);
		}
		return faq2Views;
	}
	
	/**
	 * faq3_知识内容
	 */
	public static List<Faq3_faqContentView> faq3_faqcontent(String QuestionId){
		List<Faq3_faqContentView> faq3Views = new ArrayList<Faq3_faqContentView>();
		List<QuestionPersistence> faqPersistences = QuestionHelper.faq3_faqcontent(QuestionId,2);
		for(QuestionPersistence faqPersistence:faqPersistences){
			List<Faq2_faqUserView> user_faq2Views = new ArrayList<Faq2_faqUserView>();
			List<Faq3_faqAnswer> faq3_faqAnswers = new ArrayList<Faq3_faqAnswer>();
			String userId = QuestionHelper.findUserIdByQuestionId(faqPersistence.getFAQQUESTIONID());
			List<UserPersistence> userPersistences = UserHelper.getUserInfoById(userId);
			List<AnswerPersistence> answerPersistences = AnswerHelper.getAnswerByQuestionId(QuestionId);
			for(UserPersistence userPersistence:userPersistences){
				Faq2_faqUserView user_faq2View = new Faq2_faqUserView(userPersistence);
				user_faq2Views.add(user_faq2View);
			}
			for(AnswerPersistence answerPersistence:answerPersistences){
				Faq3_faqAnswer faq3_faqAnswer = new Faq3_faqAnswer(answerPersistence);
				faq3_faqAnswers.add(faq3_faqAnswer);
			}
			Faq3_faqContentView faq3View = new Faq3_faqContentView(faqPersistence);
			faq3View.setQuestionId(QuestionId);
			faq3View.setuList(user_faq2Views);
			faq3View.setFaqAnswers(faq3_faqAnswers);
			faq3Views.add(faq3View);
		}
		return faq3Views;
	}
	
	/**
	 *	faq浏览量+1
	 */
	public static void updateFAQScan(String questionId) {
		String scan = QuestionHelper.getFaqScan(questionId);
		int faqScan = Integer.parseInt(scan);
		faqScan++;
		QuestionHelper.updateFAQScan(questionId,Integer.toString(faqScan));
	}
	
	/**
	 * zyq_faq3_ajax_分享的增加
	 */
	public static void saveShare(String userId,String faqquestionId){
    	Date date=new Date();
    	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	String time = format.format(date);
		ShareHelper.saveShare(UUID.randomUUID().toString(),userId,time,faqquestionId);
	}
	
}
