package org.xjtusicd3.partner.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.xjtusicd3.database.helper.QuestionHelper;
import org.xjtusicd3.database.helper.UserHelper;
import org.xjtusicd3.database.helper.UserQuestionHelper;
import org.xjtusicd3.database.model.RobotAnswerPersistence;
import org.xjtusicd3.database.model.UserQuestionPersistence;

public class UserQuestionService {
	/**
	 * abstract:记录用户提问记录
	 */
	public static void addUserQuestion(String questionId,String username, String comment, String from) {
		UserQuestionPersistence userQuestionPersistence = new UserQuestionPersistence();
		userQuestionPersistence.setUSERQUESTIONID(questionId);
		userQuestionPersistence.setQUESTIONTITLE(comment);
		Date date=new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = format.format(date);
        userQuestionPersistence.setQUESTIONTIME(time);        
        int isFaq;
        if (from.equals("fromFaq")) {
        	isFaq = 1;
		}else {
			boolean qList = QuestionHelper.getFaqQuestion(comment);		        
			    //isFaq = 0;不是faq
			if (qList== false) {
				isFaq = 0;
			}else {
				isFaq = 1;
			}
		}
        userQuestionPersistence.setISFAQ(isFaq);     
        String userId;
		if(username == null){
			userId = "00000000-0000-0000-0000-000000000000";
		}else {			
			userId = UserHelper.getUserIdByName(username);
		}
		userQuestionPersistence.setUSERID(userId);
		//添加用户问题记录
		UserQuestionHelper.saveQuestion(userQuestionPersistence);
	}

	/**
	 * 用户满意度
	 */
	public static void addUserSaticfaction(String questionId, String answerId, int saticfaction) {
		RobotAnswerPersistence robotAnswerPersistence = new RobotAnswerPersistence();
		robotAnswerPersistence.setROBOTANSWERID(UUID.randomUUID().toString());
		robotAnswerPersistence.setUSERQUESTIONID(questionId);
		robotAnswerPersistence.setFAQANSWERID(answerId);
		robotAnswerPersistence.setSATICFACTION(saticfaction);
		robotAnswerPersistence.setQUESTIONSTATE(0);
		UserQuestionHelper.saveSaticfaction(robotAnswerPersistence);
	}
}
