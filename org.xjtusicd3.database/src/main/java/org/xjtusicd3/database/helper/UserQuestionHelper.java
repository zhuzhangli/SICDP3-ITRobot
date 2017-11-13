package org.xjtusicd3.database.helper;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.xjtusicd3.database.logic.SqlSessionManager;
import org.xjtusicd3.database.mapper.RobotAnswerPersistenceMapper;
import org.xjtusicd3.database.mapper.UserPersistenceMapper;
import org.xjtusicd3.database.mapper.UserQuestionPersistenceMapper;
import org.xjtusicd3.database.model.ClassifyPersistence;
import org.xjtusicd3.database.model.RobotAnswerPersistence;
import org.xjtusicd3.database.model.UserPersistence;
import org.xjtusicd3.database.model.UserQuestionPersistence;

public class UserQuestionHelper
{
	/**
	 * author:zzl
	 * abstract:获取满意度为0的前台问题
	 * data:2017年11月4日20:03:01
	 */
	public static List<UserQuestionPersistence> unResolvedEvent() {
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		UserQuestionPersistenceMapper mapper = session.getMapper(UserQuestionPersistenceMapper.class);
		List<UserQuestionPersistence> eventList = mapper.unResolvedEvent();
		session.close();
		return eventList;
	}
	
	/**
	 * author:zzl
	 * abstract:获取满意度为1的前台问题
	 * data:2017年11月4日20:46:40
	 */
	public static List<UserQuestionPersistence> resolvedEvent() {
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		UserQuestionPersistenceMapper mapper = session.getMapper(UserQuestionPersistenceMapper.class);
		List<UserQuestionPersistence> eventList = mapper.resolvedEvent();
		session.close();
		return eventList;
	}
	
	/**
	 * author:zzl
	 * abstract:获取应答表中问题对应的知识库答案id
	 * data:2017年11月4日20:54:35
	 */
	public static String getFaqAnswerIdByQuestionId(String userQuestionId) {
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		UserQuestionPersistenceMapper mapper = session.getMapper(UserQuestionPersistenceMapper.class);
		String faqAnswerId = mapper.getFaqAnswerIdByQuestionId(userQuestionId);
		session.close();
		return faqAnswerId;
	}
	
	/**
	 * author:zzl
	 * abstract:获取用户问题信息
	 * data:2017年11月4日21:30:32
	 */
	public static List<UserQuestionPersistence> getUserQuestion(String UserQuestionId){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		UserQuestionPersistenceMapper mapper = session.getMapper(UserQuestionPersistenceMapper.class);
		List<UserQuestionPersistence> userlist = mapper.getUserQuestionById(UserQuestionId);
		session.close();
		return userlist;
	}
	
	/**
	 * author:zzl
	 * abstract:更新已处理的状态
	 * data:2017年11月12日16:36:02
	 */
	public static void updateRobotAnswerState(String questionId, int questionState) {
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		RobotAnswerPersistenceMapper mapper = session.getMapper(RobotAnswerPersistenceMapper.class);
		mapper.updateRobotAnswerState(questionId,questionState);
		session.close();
		
	}
	
	
	
	
	
	
	
	
	
	//zpz
	public static List<UserQuestionPersistence> getUserQuestion()
	{
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		UserQuestionPersistenceMapper mapper = session.getMapper(UserQuestionPersistenceMapper.class);
		List<UserQuestionPersistence> userlist = mapper.getUserQuestion();
		session.close();
		return userlist;
	}
	

				
		//删除用户问题信息
		public static void deleteUserQuestion(String userquestionid) 
		{
			SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
			UserQuestionPersistenceMapper mapper = session.getMapper(UserQuestionPersistenceMapper.class);
			mapper.deleteUserQuestion(userquestionid);  
			session.close();
			
		}
		
		/**
		 * author:zzl
		 * abstract:记录用户提问记录
		 * data:2017年10月22日18:43:47
		 */
		public static void addUserQuestion(String userQuestionId, String userQuestionTitle, String time, int isFaq, String userId) {
			SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
			UserQuestionPersistenceMapper mapper = session.getMapper(UserQuestionPersistenceMapper.class);
			mapper.addUserQuestion(userQuestionId,userQuestionTitle,time,isFaq,userId);
			session.close();
			
		}
		
		//获取用户提问问题Id
		public static String queationUserId(String userId, String comment) {
			SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
			UserQuestionPersistenceMapper mapper = session.getMapper(UserQuestionPersistenceMapper.class);
			String questionId = mapper.queationUserId(userId, comment);
			session.close();
			return questionId;
		}
		
		//用户满意度问答表
		public static void addUserSaticfaction(String robotAnswerId, int saticfaction, String questionId, String answerId, int questionState) {
			SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
			RobotAnswerPersistenceMapper mapper = session.getMapper(RobotAnswerPersistenceMapper.class);
			mapper.addUserSaticfaction(robotAnswerId,saticfaction,questionId,answerId,questionState);
			session.close();
			
		}
		
		//查看用户提问问题标题
		public static String getNameById(String questionId) {
			SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
			UserQuestionPersistenceMapper mapper = session.getMapper(UserQuestionPersistenceMapper.class);
			String title = mapper.getNameById(questionId);
			session.close();
			return title;
		}

		
		
	
}
