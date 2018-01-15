package org.xjtusicd3.database.helper;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.xjtusicd3.database.logic.SqlSessionManager;
import org.xjtusicd3.database.mapper.RobotAnswerPersistenceMapper;
import org.xjtusicd3.database.mapper.UserQuestionPersistenceMapper;
import org.xjtusicd3.database.model.RobotAnswerPersistence;
import org.xjtusicd3.database.model.UserQuestionPersistence;

public class UserQuestionHelper
{
	/**
	 * abstract:记录用户提问记录
	 */
	public static void saveQuestion(UserQuestionPersistence userQuestionPersistence) {
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		UserQuestionPersistenceMapper mapper = session.getMapper(UserQuestionPersistenceMapper.class);
		mapper.save(userQuestionPersistence);
		session.close();			
	}

	/**
	 * 查看是否已填写过满意度
	 */
	public static String getQuertionInfo(String questionId) {
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		RobotAnswerPersistenceMapper mapper = session.getMapper(RobotAnswerPersistenceMapper.class);
		String robotAnswerId = mapper.getQuertionInfo( questionId);
		session.close();
		return robotAnswerId;
	}
	
	/**
	 * 用户满意度
	 */
	public static void saveSaticfaction(RobotAnswerPersistence robotAnswerPersistence) {
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		RobotAnswerPersistenceMapper mapper = session.getMapper(RobotAnswerPersistenceMapper.class);
		mapper.save(robotAnswerPersistence);
		session.close();		
	}
	
	/*************************************************	        后台		**************************************************************/
	
	/**
	 * abstract:获取满意度为1/0的前台问题
	 */
	public static List<UserQuestionPersistence> resolvedEvent(int saticfaction,int questionstate) {
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		UserQuestionPersistenceMapper mapper = session.getMapper(UserQuestionPersistenceMapper.class);
		List<UserQuestionPersistence> eventList = mapper.resolvedEvent(saticfaction,questionstate);
		session.close();
		return eventList;
	}
	
	/**
	 * 获取已处理事件总数
	 */
	public static int getResolvedCount(int saticfaction,int questionstate) {
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		UserQuestionPersistenceMapper mapper = session.getMapper(UserQuestionPersistenceMapper.class);
		int resolvedCount = mapper.getResolvedCount(saticfaction,questionstate);
		session.close();
		return resolvedCount;
	}
	
	/**
	 * abstract:获取应答表中问题对应的知识库答案id
	 */
	public static String getFaqAnswerIdByQuestionId(String userQuestionId) {
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		RobotAnswerPersistenceMapper mapper = session.getMapper(RobotAnswerPersistenceMapper.class);
		String faqAnswerId = mapper.getFaqAnswerIdByQuestionId(userQuestionId);
		session.close();
		return faqAnswerId;
	}
	
	/**
	 * abstract:获取用户问题信息
	 */
	public static List<UserQuestionPersistence> getUserQuestion(String UserQuestionId){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		UserQuestionPersistenceMapper mapper = session.getMapper(UserQuestionPersistenceMapper.class);
		List<UserQuestionPersistence> userlist = mapper.getUserQuestionById(UserQuestionId);
		session.close();
		return userlist;
	}
	
	/**
	 * abstract:更新已处理的状态
	 */
	public static void updateRobotAnswerState(String questionId, int questionState) {
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		RobotAnswerPersistenceMapper mapper = session.getMapper(RobotAnswerPersistenceMapper.class);
		mapper.updateRobotAnswerState(questionId,questionState);
		session.close();		
	}	
}
