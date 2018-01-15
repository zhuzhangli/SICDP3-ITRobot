package org.xjtusicd3.database.helper;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.xjtusicd3.database.logic.SqlSessionManager;
import org.xjtusicd3.database.mapper.AnswerPersistenceMapper;
import org.xjtusicd3.database.model.AnswerPersistence;

public class AnswerHelper {
	/**
	 * zyq_faq3_知识内容
	 */
	public static List<AnswerPersistence> getAnswerByQuestionId(String QuestionId){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		AnswerPersistenceMapper mapper  = session.getMapper(AnswerPersistenceMapper.class);
		List<AnswerPersistence> list = mapper.getAnswerByQuestionId(QuestionId);
		session.close();
		return list;
	}
	
	/**
	 * 根据faq问题id查找用户id
	 */
	public static String findUserIdByFAQQuestionId(String faqquestionid) {
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		AnswerPersistenceMapper mapper  = session.getMapper(AnswerPersistenceMapper.class);
		String userId = mapper.findUserIdByFAQQuestionId(faqquestionid);
		session.close();
		return userId;
	}
	
	/**
	 * zyq_spider_知识库答案的添加
	 */
	public static void save(AnswerPersistence answerPersistence){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		AnswerPersistenceMapper mapper = session.getMapper(AnswerPersistenceMapper.class);
		mapper.save(answerPersistence);
		session.close();
	}
	
	/*********************************************        后台                    ***************************************************************/
	//获取faqanswerId相对应的内容
	public static String getContentById(String faqAnswerId) {
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		AnswerPersistenceMapper mapper  = session.getMapper(AnswerPersistenceMapper.class);
		String content = mapper.getContentById(faqAnswerId);
		session.close();
		return content;
	}
	
	//根据问题id获取问题答案内容
	public static String getAnswerContentByQuestionId(String faqquestionid) {
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		AnswerPersistenceMapper mapper  = session.getMapper(AnswerPersistenceMapper.class);
		String content = mapper.getContentByQuestionId(faqquestionid);
		session.close();
		return content;
	}
	
	//更新faq信息
	public static void updateFaqAnswerInfo(String questionId, String faqcontent) {
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		AnswerPersistenceMapper mapper  = session.getMapper(AnswerPersistenceMapper.class);
		mapper.updateFaqAnswerInfo(questionId, faqcontent);
		session.close();		
	}
	

	/*
	 * zyq_notice_ajax_查询FAQ评论通知	
	 * !!!未使用
	 */
	public static List<AnswerPersistence> notice_faqanswerList(String userId){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		AnswerPersistenceMapper mapper  = session.getMapper(AnswerPersistenceMapper.class);
		List<AnswerPersistence> list = mapper.notice_faqanswerList(userId);
		session.close();
		return list;
	}
}
