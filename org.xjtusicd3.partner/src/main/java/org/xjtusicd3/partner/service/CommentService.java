package org.xjtusicd3.partner.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.xjtusicd3.database.helper.AnswerHelper;
import org.xjtusicd3.database.helper.CommentHelper;
import org.xjtusicd3.database.helper.CommunityAnswerHelper;
import org.xjtusicd3.database.helper.CommunityQuestionHelper;
import org.xjtusicd3.database.helper.ITHelper;
import org.xjtusicd3.database.helper.UserHelper;
import org.xjtusicd3.database.model.CommentPersistence;
import org.xjtusicd3.database.model.ITPersistence;
import org.xjtusicd3.database.model.UserPersistence;
import org.xjtusicd3.partner.view.Faq1_UserActive;
import org.xjtusicd3.partner.view.Faq2_faqUserView;
import org.xjtusicd3.partner.view.Faq3_CommentReplyView;
import org.xjtusicd3.partner.view.Faq3_CommentView;
import org.xjtusicd3.partner.view.Question2_CommunityReplayView;


public class CommentService {
	/**
	 * zyq_question2_获得更多的回复
	 */
	public static List<Question2_CommunityReplayView> question2_CommunityReplayViews(String questionId,String answerId,Integer startnumber){
		List<Question2_CommunityReplayView> question2_CommunityReplayViews = new ArrayList<Question2_CommunityReplayView>();
		List<CommentPersistence> commentPersistences = CommentHelper.question2_getMoreComment(questionId, answerId, startnumber);
		for(CommentPersistence commentPersistence:commentPersistences){
			Question2_CommunityReplayView question2_CommunityReplayView = new Question2_CommunityReplayView();
			question2_CommunityReplayView.setCommunity(commentPersistence.getCOMMENTCONTENT());
			question2_CommunityReplayView.setTime(commentPersistence.getCOMMENTTIME());
			List<UserPersistence> userPersistences = UserHelper.getUserInfoById(commentPersistence.getUSERID());
			question2_CommunityReplayView.setUserImage(userPersistences.get(0).getAVATAR());
			question2_CommunityReplayView.setUserName(userPersistences.get(0).getUSERNAME());
			question2_CommunityReplayViews.add(question2_CommunityReplayView);
		}
		return question2_CommunityReplayViews;
	}
	
	/**
	 * zyq_question2_设为最佳答案
	 */
	public static void saveBestAnswer(String questionId,String answerId) {
		//更新社区答案ISBESTANSWER字段
		CommunityAnswerHelper.saveBestAnswer(answerId,1);
		//更新社区问题ISANSWER字段 = 1
		CommunityQuestionHelper.updateBestAnswer(questionId,1);
	}
	
	/**
	 * zyq_faq1_查看活跃用户
	 */
	public static List<Faq1_UserActive> faq1_userActive() {
		List<Faq1_UserActive> faq1_UserActives = new ArrayList<Faq1_UserActive>();
  	    Date date=new Date();
  	    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
  	    String time = format.format(date);
		List<CommentPersistence> commentPersistences = CommentHelper.faq1_userActive(time);
		for(CommentPersistence commentPersistence:commentPersistences){
			Faq1_UserActive faq1_UserActive = new Faq1_UserActive();
			faq1_UserActive.setUserId(commentPersistence.getUSERID());
			List<UserPersistence> userPersistences = UserHelper.getUserInfoById(commentPersistence.getUSERID());
			faq1_UserActive.setUserImage(userPersistences.get(0).getAVATAR());
			List<ITPersistence> itPersistences = ITHelper.IT(commentPersistence.getUSERID());
			if (itPersistences.size()!=0) {
				faq1_UserActive.setWork(itPersistences.get(0).getGOODWORK());
			}
			faq1_UserActive.setUserName(userPersistences.get(0).getUSERNAME());
			faq1_UserActive.setFaqNumber(commentPersistence.getNUM());
			faq1_UserActives.add(faq1_UserActive);
		}
		return faq1_UserActives;
	}
	
	/**
	 * zyq_faq1_查看活跃用户_按周查询
	 */
	public static List<Faq1_UserActive> faq1_userActive_week() {
		List<Faq1_UserActive> faq1_UserActives = new ArrayList<Faq1_UserActive>();
  	    Date date=new Date();

  	    Calendar c = Calendar.getInstance();
  	    c.setTime(date);
  	    c.add(Calendar.DAY_OF_MONTH, 1);
  	    
  	    Date tomorrow = c.getTime();
  	    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
  	    String time = format.format(tomorrow);  	    
  	    String time2 = getdate(-7);
		List<CommentPersistence> commentPersistences = CommentHelper.faq1_userActiveWeek(time,time2);
		for(CommentPersistence commentPersistence:commentPersistences){
			Faq1_UserActive faq1_UserActive = new Faq1_UserActive();
			faq1_UserActive.setUserId(commentPersistence.getUSERID());
			List<UserPersistence> userPersistences = UserHelper.getUserInfoById(commentPersistence.getUSERID());
			faq1_UserActive.setUserImage(userPersistences.get(0).getAVATAR());
			List<ITPersistence> itPersistences = ITHelper.IT(commentPersistence.getUSERID());
			if (itPersistences.size()!=0) {
				faq1_UserActive.setWork(itPersistences.get(0).getGOODWORK());
			}
			faq1_UserActive.setUserName(userPersistences.get(0).getUSERNAME());
			faq1_UserActive.setFaqNumber(commentPersistence.getNUM());
			faq1_UserActives.add(faq1_UserActive);
		}
		return faq1_UserActives;
	}
	
	//获取日期
	public static String getdate(int i){ // //获取前后日期 i为正数 向后推迟i天，负数时向前提前i天
		 Date dat = null;
		 Calendar cd = Calendar.getInstance();
		 cd.add(Calendar.DATE, i);
		 dat = cd.getTime();
		 SimpleDateFormat dformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		 String time = dformat.format(dat);
		 return time;
	 }
	
	/**
	 * zyq_faq3_获得评论列表
	 */
	public static List<Faq3_CommentView> faq3_comment(String questionId,int startnumber) {
		List<Faq3_CommentView> faq3_CommentViews = new ArrayList<Faq3_CommentView>();
		List<CommentPersistence> commentPersistences = CommentHelper.getCommentMore(questionId,startnumber,"0");
		for(CommentPersistence commentPersistence : commentPersistences){
			//获取用户信息
			List<Faq2_faqUserView> faq2_faqUserViews = new ArrayList<Faq2_faqUserView>();
			List<UserPersistence> userPersistences = UserHelper.getUserInfoById(commentPersistence.getUSERID());
			for(UserPersistence userPersistence:userPersistences){
				Faq2_faqUserView userView = new Faq2_faqUserView(userPersistence);
				faq2_faqUserViews.add(userView);
			}
			
			//获取faq3评论回复信息
			List<Faq3_CommentReplyView> faq3_CommentReplyViews = new ArrayList<Faq3_CommentReplyView>();			
			List<CommentPersistence> commentPersistences2 = CommentHelper.faq3_getCommentReply_Limit(commentPersistence.getCOMMENTID(),0);
			for(CommentPersistence commentPersistence2:commentPersistences2){
				Faq3_CommentReplyView faq3_CommentReplyView = new Faq3_CommentReplyView();
				faq3_CommentReplyView.setCommentId(commentPersistence2.getCOMMENTID());
				faq3_CommentReplyView.setParrentId(commentPersistence2.getCOMMENTPARENTID());				
				String username = UserHelper.getUserNameById(commentPersistence2.getUSERID());
				faq3_CommentReplyView.setUserName(username);
				//获取父评论信息
				List<CommentPersistence> commentPersistences3 = CommentHelper.faq3_getCommentInfoById(commentPersistence2.getCOMMENTPARENTID());
				if (commentPersistences3.get(0).getCOMMENTPARENTID().equals("0")&&commentPersistence2.getTOUSERID()==null) {
					faq3_CommentReplyView.setToUserName(null);
				}else {
					String toUsername = UserHelper.getUserNameById(commentPersistence2.getTOUSERID());					
					faq3_CommentReplyView.setToUserName(toUsername);
				}								
				faq3_CommentReplyView.setTime(commentPersistence2.getCOMMENTTIME());
				faq3_CommentReplyView.setComment(commentPersistence2.getCOMMENTCONTENT());						
				faq3_CommentReplyViews.add(faq3_CommentReplyView);
			}		
			int commentTotalnumber = CommentHelper.faq3_getCommentReply(commentPersistence.getCOMMENTID());			
			Faq3_CommentView faq3_CommentView = new Faq3_CommentView(commentPersistence);
			faq3_CommentView.setCommentId(commentPersistence.getCOMMENTID());
			faq3_CommentView.setCommentNumber(Integer.toString(commentTotalnumber));
			faq3_CommentView.setUserViews(faq2_faqUserViews);
			faq3_CommentView.setReplyViews(faq3_CommentReplyViews);			
			faq3_CommentViews.add(faq3_CommentView);
		}
		return faq3_CommentViews;
	}
	
	/**
	 * zyq_faq3_ajax_添加评论
	 */
	public static void addComment(String userid, String faqquestionid, String comment,String faquserid) {		
		Date date=new Date();
	    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    String time = format.format(date);
	    //查看是否回答自己的FAQ
	    String answerUserId = AnswerHelper.findUserIdByFAQQuestionId(faqquestionid);
	    int isnotice = 0;
	    if (userid.equals(answerUserId)) {
			isnotice = 0;
		}else {
			isnotice = 1;
		}
		CommentHelper.saveComment(UUID.randomUUID().toString(),faqquestionid,null,userid,comment,time,"0",isnotice,faquserid);
	}
	
	/**
	 * zyq_question2_ajax_添加评论的回复
	 */
	public static void saveCommunityComment(String userid,String communityquestionId,String comment,String answerId){
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    String time = format.format(date);
	    //查看是否回复了自己的评论
	    String answerUserId = CommunityAnswerHelper.getUserIdByAnswerId(answerId);
	    int isnotice = 0;
	    if (userid.equals(answerUserId)) {
			isnotice = 0;
		}else {
			isnotice = 1;
		}
	    CommentHelper.saveComment(UUID.randomUUID().toString(), null, communityquestionId, userid, comment, time, answerId,isnotice,null);
	}
	
	/**
	 * zyq_faq3_ajax_添加评论的回复
	 */
	public static void saveFaqComment(String userid,String faqquestionId,String comment,String commentId,String duo){
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    String time = format.format(date);
	    //查看是否回复了自己的评论
	    String commentUserId = CommentHelper.faq3_getUserIdByCommentId(commentId);
	    int isnotice = 0;
	    if (userid.equals(commentUserId)) {
			isnotice = 0;
		}else {
			isnotice = 1;
		}
	    if (duo.equals("1")) {
	    	//二级回复记录回复用户commentUserId
	    	CommentHelper.saveComment(UUID.randomUUID().toString(), faqquestionId, null, userid, comment, time, commentId,isnotice,commentUserId);
		}else {
			CommentHelper.saveComment(UUID.randomUUID().toString(), faqquestionId, null, userid, comment, time, commentId,isnotice,null);
		}
	}
	
	/**
	 * zyq_faq3_获得更多的回复
	 */
	public static List<Faq3_CommentReplyView> faq3_CommentReplyViews(String commentId,int startnumber){
		List<Faq3_CommentReplyView> faq3_CommentReplyViews = new ArrayList<Faq3_CommentReplyView>();
		List<CommentPersistence> commentPersistences = CommentHelper.faq3_getCommentReply_Limit(commentId, startnumber);
		for(CommentPersistence commentPersistence:commentPersistences){
			Faq3_CommentReplyView faq3_CommentReplyView = new Faq3_CommentReplyView();
			faq3_CommentReplyView.setCommentId(commentPersistence.getCOMMENTID());
			faq3_CommentReplyView.setParrentId(commentPersistence.getCOMMENTPARENTID());
			String username = UserHelper.getUserNameById(commentPersistence.getUSERID());
			faq3_CommentReplyView.setUserName(username);
			if (commentPersistence.getTOUSERID()!=null) {
				String toUsername = UserHelper.getUserNameById(commentPersistence.getTOUSERID());
				faq3_CommentReplyView.setToUserName(toUsername);
			}			
			faq3_CommentReplyView.setTime(commentPersistence.getCOMMENTTIME());
			faq3_CommentReplyView.setComment(commentPersistence.getCOMMENTCONTENT());			
			faq3_CommentReplyViews.add(faq3_CommentReplyView);
		}
		return faq3_CommentReplyViews;
	}

}
