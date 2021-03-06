package org.xjtusicd3.database.helper;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import org.apache.ibatis.session.SqlSession;
import org.xjtusicd3.database.logic.SqlSessionManager;
import org.xjtusicd3.database.mapper.CollectionPersistenceMapper;
import org.xjtusicd3.database.model.CollectionPersistence;

public class CollectionHelper {
	/**
	 * abstract:question2_收藏
	 */
	public static String getCollection(String username,String answerId){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		CollectionPersistenceMapper mapper = session.getMapper(CollectionPersistenceMapper.class);
		String userId = UserHelper.getUserIdByName(username);
		String list = mapper.getCollection(userId,answerId);
		session.close();
		return list;
	}
	
	
	/**
	 * zyq_faq3_ajxa_收藏
	 */
	public static String getCollection2(String userid,String questionId){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		CollectionPersistenceMapper mapper = session.getMapper(CollectionPersistenceMapper.class);
		String collectionId = mapper.getCollection2(userid,questionId);
		session.close();
		return collectionId ;
	}
	
	
	/**
	 * zyq_question2_ajax_添加收藏
	 */
	public static void saveCollection(String username,String communityanswerId){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		CollectionPersistenceMapper mapper = session.getMapper(CollectionPersistenceMapper.class);
		String userId = UserHelper.getUserIdByName(username);
		Date date=new Date();
	    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    String time = format.format(date);
	    //判断是否为自己收藏
	    String communityAnswerUserId = CommunityAnswerHelper.getUserIdByAnswerId(communityanswerId);
	    int isnotice = 0;
	    if(userId.equals(communityAnswerUserId)){
	    	isnotice = 0;
	    }else {
			isnotice = 1;
		}
		mapper.saveCollection(UUID.randomUUID().toString(),communityanswerId,userId,time,isnotice);
		session.close();
	}
	
	
	/**
	 * zyq_faq3_ajax_添加收藏
	 */
	public static void saveCollection2(String userId,String questionId){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		CollectionPersistenceMapper mapper = session.getMapper(CollectionPersistenceMapper.class);
		Date date=new Date();
	    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    String time = format.format(date);
	    //判断是否为自己收藏
	    String faqAnswerUserId = AnswerHelper.findUserIdByFAQQuestionId(questionId);
	    int isnotice = 0;
	    if(userId.equals(faqAnswerUserId)){
	    	isnotice = 0;
	    	mapper.saveCollection2(UUID.randomUUID().toString(),questionId,userId,time,isnotice);
	    }else {
			isnotice = 1;
			mapper.saveCollection2(UUID.randomUUID().toString(),questionId,userId,time,isnotice);
		}
		session.close();
	}
	
	/**
	 * zyq_question2_faq3_删除收藏
	 */
	public static void deleteCollection(String collectionid) {
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		CollectionPersistenceMapper mapper = session.getMapper(CollectionPersistenceMapper.class);
		mapper.deleteCollection(collectionid);
		session.close();
	}
	
	/**
	 * 获取faq问题收藏总数
	 */
	public static int getCollectionFaqCount(String faqquestionid) {
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		CollectionPersistenceMapper mapper = session.getMapper(CollectionPersistenceMapper.class);
		int list = mapper.getCollectionFaqCount(faqquestionid);
		session.close();
		return list;
	}
	
	/**
	 * zyq_personal2_ajax_获取收藏FAQ
	 */
	public static List<CollectionPersistence> getCollectionFaq(String userid,int startNumber,int number) {
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		CollectionPersistenceMapper mapper = session.getMapper(CollectionPersistenceMapper.class);
		List<CollectionPersistence> list = mapper.getCollectionFaq(userid,startNumber,number);
		session.close();
		return list;
	}
	
	/**
	 * zyq_personal2_ajax_获取问吧的关注答案
	 */
	public static List<CollectionPersistence> personal2_PayCommunity_Limit(String userId, int startNumber, int number) {
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		CollectionPersistenceMapper mapper = session.getMapper(CollectionPersistenceMapper.class);
		List<CollectionPersistence> list = mapper.personal2_PayCommunity_Limit(userId,startNumber,number);
		session.close();
		return list;
	}	
}
