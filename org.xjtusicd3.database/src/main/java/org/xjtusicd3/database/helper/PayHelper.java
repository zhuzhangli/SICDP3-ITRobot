package org.xjtusicd3.database.helper;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.xjtusicd3.database.logic.SqlSessionManager;
import org.xjtusicd3.database.mapper.PayPersistenceMapper;
import org.xjtusicd3.database.model.PayPersistence;

public class PayHelper {
	/**
	 * 查找关注的对象
	 * @param userid
	 * @param startNumber
	 * @param number
	 * @return
	 */
	public static List<PayPersistence> payList_Limit(String userid,int startNumber,int number){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		PayPersistenceMapper mapper = session.getMapper(PayPersistenceMapper.class);
		List<PayPersistence> list  = mapper.payList_Limit(userid,startNumber,number);
		session.close();
		return list;
	}
	
	/**
	 * 查找关注的对象_时间限制
	 * @param userid
	 * @param time
	 * @param startNumber
	 * @param number
	 * @return
	 */
	public static List<PayPersistence> payList_time_Limit(String userid, String time,int startNumber,int number) {
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		PayPersistenceMapper mapper = session.getMapper(PayPersistenceMapper.class);
		List<PayPersistence> list = mapper.payList_time_Limit(userid,time,startNumber,number);
		session.close();
		return list;
	}
	
/*	*//**
	 * 查找关注对象
	 *//*
	public static List<PayPersistence> payList_Limit_Time(String userid,int startNumber,int number,String time){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		PayPersistenceMapper mapper = session.getMapper(PayPersistenceMapper.class);
		List<PayPersistence> list  = mapper.payList_Limit_Time(userid,startNumber,number,time);
		session.close();
		return list;
	}*/
		
	/**
	 * zyq_personal2_查看关注列表
	 * @param userId
	 * @param touserId
	 * @return
	 */
	public static List<PayPersistence> getpayList(String userId,String touserId){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		PayPersistenceMapper mapper = session.getMapper(PayPersistenceMapper.class);
		List<PayPersistence> list  = mapper.getpayList(userId,touserId);
		session.close();
		return list;
	}
	
	/**
	 * zyq_personal3_关注、被关注
	 */
	public static List<PayPersistence> payList(String userid){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		PayPersistenceMapper mapper = session.getMapper(PayPersistenceMapper.class);
		List<PayPersistence> list  = mapper.payList(userid);
		session.close();
		return list;
	}
	
	/**
	 * 获取关注人数
	 * @param userid
	 * @return
	 */
	public static int payListSize(String userid) {
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		PayPersistenceMapper mapper = session.getMapper(PayPersistenceMapper.class);
		int list  = mapper.payListSize(userid);
		session.close();
		return list;
	}
	
	/**
	 * 查看被关注对象信息
	 * @param beuserid
	 * @return
	 */
	public static List<PayPersistence> bepayList(String beuserid){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		PayPersistenceMapper mapper = session.getMapper(PayPersistenceMapper.class);
		List<PayPersistence> list  = mapper.bepayList(beuserid);
		session.close();
		return list;
	}
	
	/**
	 * 粉丝数
	 * @param userid
	 * @return
	 */
	public static int bepayListSize(String beuserid) {
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		PayPersistenceMapper mapper = session.getMapper(PayPersistenceMapper.class);
		int size  = mapper.bepayListSize(beuserid);
		session.close();
		return size;
	}
	
	/**
	 * zyq_personal2_关注
	 */
	public static void savePay(PayPersistence payPersistence) {
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		PayPersistenceMapper mapper = session.getMapper(PayPersistenceMapper.class);
		mapper.save(payPersistence);
		session.close();
	}
	
	/**
	 * zyq_personal2_取消关注
	 */
	public static void deletePay(String userId, String touserId) {
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		PayPersistenceMapper mapper = session.getMapper(PayPersistenceMapper.class);
		mapper.deletePay(userId,touserId);
		session.close();
	}
	
	
	
	
	
	
	
	
	

	
	
	
	
	

	/*
	 * zyq_personal2_个人主页关注别人展示
	 */
	public static List<PayPersistence> payList_time(String userid, String time) {
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		PayPersistenceMapper mapper = session.getMapper(PayPersistenceMapper.class);
		List<PayPersistence> list = mapper.payList_time(userid,time);
		session.close();
		return list;
	}

	public static List<PayPersistence> payList_time_Limit_Time(String userid, String time,int startNumber,int number,String time2) {
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		PayPersistenceMapper mapper = session.getMapper(PayPersistenceMapper.class);
		List<PayPersistence> list = mapper.payList_time_Limit_Time(userid,time,startNumber,number,time2);
		session.close();
		return list;
	}
	/*public static void savePay(String payId, String userId, String touserId, String time) {
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		PayPersistenceMapper mapper = session.getMapper(PayPersistenceMapper.class);
		mapper.savePay(payId, userId,  touserId,  time);
		session.close();
		
	}*/

	

	


}
