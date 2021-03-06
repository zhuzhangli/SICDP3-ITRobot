package org.xjtusicd3.database.helper;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.xjtusicd3.database.logic.SqlSessionManager;
import org.xjtusicd3.database.mapper.MessageHistoryPersistenceMapper;
import org.xjtusicd3.database.mapper.MessagePersistenceMapper;
import org.xjtusicd3.database.model.MessageHistoryPersistence;
import org.xjtusicd3.database.model.MessagePersistence;

public class MessageHelper {
	/*
	 * zyq_message_ajax_添加私信
	 */
	public static void save(MessagePersistence messagePersistence) {
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		MessagePersistenceMapper mapper = session.getMapper(MessagePersistenceMapper.class);
		mapper.save(messagePersistence);
		session.close();
	}
	/*
	 * zyq_message_查看用户的消息记录
	 */
	public static List<MessagePersistence> getMessage(String userId,int state){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		MessagePersistenceMapper mapper = session.getMapper(MessagePersistenceMapper.class);
		List<MessagePersistence> list = mapper.getMessage(userId,state);
		session.close();
		return list;
	}
	/*
	 * zyq_message_查看用户列表
	 */
	public static List<MessagePersistence> getUserList(String userId, int state,int state2) {
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		MessagePersistenceMapper mapper = session.getMapper(MessagePersistenceMapper.class);
		List<MessagePersistence> list = mapper.getUserList(userId,state,state2);
		session.close();
		return list;
	}
	public static List<MessagePersistence> getUserList_pushlet(String userId, int state) {
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		MessagePersistenceMapper mapper = session.getMapper(MessagePersistenceMapper.class);
		List<MessagePersistence> list = mapper.getUserList_pushlet(userId,state);
		session.close();
		return list;
	}
	public static List<MessagePersistence> getMessageUser(String userid, String userId2, int state) {
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		MessagePersistenceMapper mapper = session.getMapper(MessagePersistenceMapper.class);
		List<MessagePersistence> list = mapper.getMessageUser(userid,userId2,state);
		session.close();
		return list;
	}
	/*
	 * zyq_message_ajax_查询私信内容
	 */
	public static List<MessagePersistence> getMessageContent(String postuserId, String getuserId, int state) {
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		MessagePersistenceMapper mapper = session.getMapper(MessagePersistenceMapper.class);
		List<MessagePersistence> list = mapper.getMessageContent(postuserId,getuserId,state);
		session.close();
		return list;
	}
	public static List<MessagePersistence> getMessageContent_time(String postuserId, String getuserId, int state,String time) {
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		MessagePersistenceMapper mapper = session.getMapper(MessagePersistenceMapper.class);
		List<MessagePersistence> list = mapper.getMessageContent_time(postuserId,getuserId,state,time);
		session.close();
		return list;
	}
	public static void updateMessageState(String messageId,int state) {
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		MessagePersistenceMapper mapper = session.getMapper(MessagePersistenceMapper.class);
		mapper.updateMessageState(messageId,state);
		session.close();
	}
	/*
	 * zyq_message_ajax_查询历史记录
	 */
	public static List<MessagePersistence> getMessageContent1(String postuserId, String getuserId, int state) {
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		MessagePersistenceMapper mapper = session.getMapper(MessagePersistenceMapper.class);
		List<MessagePersistence> list = mapper.getMessageContent1(postuserId,getuserId,state);
		session.close();
		return list;
	}
	//比较时间
	public static List<MessagePersistence> getMessageContent1_time(String postuserId, String getuserId, int state,String time) {
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		MessagePersistenceMapper mapper = session.getMapper(MessagePersistenceMapper.class);
		List<MessagePersistence> list = mapper.getMessageContent1_time(postuserId,getuserId,state,time);
		session.close();
		return list;
	}
	public static List<MessagePersistence> getMessageContent2(String postuserId, String getuserId, int state,int startnumber) {
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		MessagePersistenceMapper mapper = session.getMapper(MessagePersistenceMapper.class);
		List<MessagePersistence> list = mapper.getMessageContent2(postuserId,getuserId,state,startnumber,1);
		session.close();
		return list;
	}
	//比较时间
	public static List<MessagePersistence> getMessageContent2_time(String postuserId, String getuserId, int state,int startnumber,String time) {
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		MessagePersistenceMapper mapper = session.getMapper(MessagePersistenceMapper.class);
		List<MessagePersistence> list = mapper.getMessageContent2_time(postuserId,getuserId,state,startnumber,time,1);
		session.close();
		return list;
	}
	/*
	 * zyq_message_ajax_获取更多历史私信
	 */
	public static List<MessagePersistence> getMessageContent11(String postuserId, String getuserId, int state,String date) {
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		MessagePersistenceMapper mapper = session.getMapper(MessagePersistenceMapper.class);
		List<MessagePersistence> list = mapper.getMessageContent11(postuserId,getuserId,state,date);
		session.close();
		return list;
	}
	public static List<MessagePersistence> getMessageContent21(String postuserId, String getuserId, int state,String date) {
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		MessagePersistenceMapper mapper = session.getMapper(MessagePersistenceMapper.class);
		List<MessagePersistence> list = mapper.getMessageContent21(postuserId,getuserId,state,date);
		session.close();
		return list;
	}
	/*
	 * zyq_message_查看私信历史列表
	 */
	public static List<MessageHistoryPersistence> getMessageHistoryList(String postuserId, String getuserId) {
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		MessageHistoryPersistenceMapper mapper = session.getMapper(MessageHistoryPersistenceMapper.class);
		List<MessageHistoryPersistence> list = mapper.getMessageHistoryList(postuserId,getuserId);
		session.close();
		return list;
	}
	/*
	 * zyq_message——记录私信历史
	 */
	public static void save(MessageHistoryPersistence messageHistoryPersistence){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		MessageHistoryPersistenceMapper mapper = session.getMapper(MessageHistoryPersistenceMapper.class);
		mapper.save(messageHistoryPersistence);
		session.close();
	}
	/*
	 * zyq_message_更改私信的时间
	 */
	public static void updateMessageHistory(String messagehistoryid, String time) {
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		MessageHistoryPersistenceMapper mapper = session.getMapper(MessageHistoryPersistenceMapper.class);
		mapper.updateMessageHistory(messagehistoryid,time);
		session.close();
	}
}
