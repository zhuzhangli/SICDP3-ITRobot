package org.xjtusicd3.portal.service;

import java.util.ArrayList;
import java.util.List;

import org.xjtusicd3.database.helper.AnswerHelper;
import org.xjtusicd3.database.helper.UserHelper;
import org.xjtusicd3.database.helper.UserQuestionHelper;
import org.xjtusicd3.database.model.UserQuestionPersistence;
import org.xjtusicd3.portal.view.EventView;
import org.xjtusicd3.portal.view.Event_AnswerView;

public class EventManagerService {
	/**
	 * @abstract:待处理事件_当用户对系统回复的答案不满意时，即tbl_robotAnswer问答表中满意字段置为0
	 */
	public static List<EventView> eventUnresolved() {
		//未处理视图
		List<EventView> eventUnresolved = new ArrayList<EventView>();
		
		//事件就是处理用户前台问题_获取所有有满意度且满意度为0的前台问题
		List<UserQuestionPersistence> userQuestionPersistences = UserQuestionHelper.resolvedEvent(0,0);
				
		for(UserQuestionPersistence userQuestionPersistence:userQuestionPersistences){
			EventView eventView = new EventView();			
			eventView.setUserQuestionTitle(userQuestionPersistence.getQUESTIONTITLE());
			String username = UserHelper.getUserNameById(userQuestionPersistence.getUSERID());			
			eventView.setUserName(username);
			eventView.setUserQuestionTime(userQuestionPersistence.getQUESTIONTIME());
			eventView.setUserQuestionId(userQuestionPersistence.getUSERQUESTIONID());
			eventUnresolved.add(eventView);
		}
		return eventUnresolved;
	}
	
	/**
	 * abstract:事件未处理_忽略用户问题 2是忽略
	 */
	public static void updateQuestionState(String userQuestionId, int questionState) {
		UserQuestionHelper.updateRobotAnswerState(userQuestionId, questionState);
		
	}
	
	/**
	 * abstract:已处理事件_当用户对系统回复的答案满意时，即tbl_robotAnswer问答表中满意字段置为1
	 */
	public static List<Event_AnswerView> eventResolved() {
		//已处理视图
		List<Event_AnswerView> eventResolved = new ArrayList<Event_AnswerView>();
		
		//事件就是处理用户前台问题_获取所有有满意度且满意度为1的前台问题
		List<UserQuestionPersistence> userQuestionPersistences = UserQuestionHelper.resolvedEvent(1,0);
		
		for(UserQuestionPersistence userQuestionPersistence:userQuestionPersistences){
			Event_AnswerView eventView = new Event_AnswerView();			
			eventView.setQUESTIONTITLE(userQuestionPersistence.getQUESTIONTITLE());
			String username = UserHelper.getUserNameById(userQuestionPersistence.getUSERID());			
			eventView.setUSERNAME(username);
			eventView.setQUESTIONTIME(userQuestionPersistence.getQUESTIONTIME());
			eventView.setUSERQUESTIONID(userQuestionPersistence.getUSERQUESTIONID());			
			//获取应答表中问题对应的知识库答案id
			String faqAnswerId = UserQuestionHelper.getFaqAnswerIdByQuestionId(userQuestionPersistence.getUSERQUESTIONID());			
			//获取faqanswerId相对应的内容
			String faqContent = AnswerHelper.getContentById(faqAnswerId);
			eventView.setFAQANSWERID(faqAnswerId);
			eventView.setFAQANSWER(faqContent);			
			eventResolved.add(eventView);								
		}
		return eventResolved;
	}
	
	/**
	 * abstract:事件已处理_查看事件详情_showResolvedEvent.ftl
	 */
	public static Event_AnswerView getResolvedEventDetail(String userQuestionId) {
		//已处理事件视图
		Event_AnswerView event_AnswerView = new Event_AnswerView();
		//获取查看的用户问题
		List<UserQuestionPersistence> userQuestionPersistences = UserQuestionHelper.getUserQuestion(userQuestionId);
				
		event_AnswerView.setUSERQUESTIONID(userQuestionId);
		event_AnswerView.setQUESTIONTITLE(userQuestionPersistences.get(0).getQUESTIONTITLE());		
		//获取应答表中问题对应的知识库答案id
		String faqAnswerId = UserQuestionHelper.getFaqAnswerIdByQuestionId(userQuestionId);		
		//获取faqanswerId相对应的内容
		String faqContent = AnswerHelper.getContentById(faqAnswerId);
		event_AnswerView.setFAQANSWER(faqContent);
		//获取用户名信息
		String username = UserHelper.getUserNameById(userQuestionPersistences.get(0).getUSERID());
		event_AnswerView.setUSERNAME(username);
		event_AnswerView.setQUESTIONTIME(userQuestionPersistences.get(0).getQUESTIONTIME());
		return event_AnswerView;
	}
	
	/**
	 * author:zzl
	 * abstract:事件待处理_查看事件详情
	 * data:2017年11月4日21:26:07
	 */
	public static EventView getUnResolvedEventDetail(String userQuestionId) {
		
		//获取查看的问题信息
		List<UserQuestionPersistence> userQuestionPersistences = UserQuestionHelper.getUserQuestion(userQuestionId);
		
		EventView eventView = new EventView();
		
		eventView.setUserQuestionId(userQuestionId);
		eventView.setUserQuestionTime(userQuestionPersistences.get(0).getQUESTIONTIME());
		
		eventView.setUserQuestionTitle(userQuestionPersistences.get(0).getQUESTIONTITLE());
		String username = UserHelper.getUserNameById(userQuestionPersistences.get(0).getUSERID());
		eventView.setUserName(username);	
	
		return eventView;
	}
	
}
