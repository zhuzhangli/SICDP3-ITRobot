package org.xjtusicd3.database.helper;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.xjtusicd3.database.logic.SqlSessionManager;
import org.xjtusicd3.database.mapper.GeneraluserPersistenceMapper;
import org.xjtusicd3.database.mapper.UserPersistenceMapper;
import org.xjtusicd3.database.model.GeneraluserPersistence;
import org.xjtusicd3.database.model.UserPersistence;

public class UserHelper {
	//判断用户名是否已存在
	public static Boolean getUserInfoByName(String name) {
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		UserPersistenceMapper mapper = session.getMapper(UserPersistenceMapper.class);
		Boolean isExist = mapper.getUserInfoByName(name);
		session.close();
		return isExist;
	}
		
	//zyq_login_ajax_注册
	public static void login_register(String userid,String name,String password,int userstate,String createTime,String userimage){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		UserPersistenceMapper mapper = session.getMapper(UserPersistenceMapper.class);
		mapper.login_register(userid, name, password, userstate, createTime,userimage);
		session.close();
	}
	
	//判断用户是否登录	 
	public static boolean isLogin(String username, String password) {
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		UserPersistenceMapper mapper = session.getMapper(UserPersistenceMapper.class);
		boolean list = mapper.isLogin(username, password);
		session.close();
		return list;
	}

	//zyq_message_ajax_获得用户基本信息
	public static List<UserPersistence> getUserInfo(String username){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		UserPersistenceMapper mapper = session.getMapper(UserPersistenceMapper.class);
		List<UserPersistence> list = mapper.getUserInfo(username);
		session.close();
		return list;
	}

	//修改用户信息	 
	public static void updateUserInfo2(String userid, String usersex, String userbirthday,String address, String userbrief) {
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		UserPersistenceMapper mapper = session.getMapper(UserPersistenceMapper.class);
		mapper.updateUserInfo2(userid,usersex,userbirthday,address,userbrief);
		session.close();	
	}
	
	//修改密码	 
	public static void updateUserPassword(String username, String password) {
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		UserPersistenceMapper mapper = session.getMapper(UserPersistenceMapper.class);
		mapper.updateUserPassword(username,password);
		session.close();	
	}
	
	//通过username找userID
	public static String getUserIdByName(String username) {
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		UserPersistenceMapper mapper = session.getMapper(UserPersistenceMapper.class);
		String userId = mapper.getUserIdByName(username);
		session.close();
		return userId;
	}
	
	//zzl_通过用户ID获取到用户信息_2017年11月10日11:46:39
	public static List<UserPersistence> getUserInfoById(String userId) {
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		UserPersistenceMapper mapper = session.getMapper(UserPersistenceMapper.class);
		List<UserPersistence> list = mapper.getUserInfoById(userId);
		session.close();
		return list;
	}
	
	//通过用户Id获取用户名
	public static String getUserNameById(String userId) {
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		UserPersistenceMapper mapper = session.getMapper(UserPersistenceMapper.class);
		String username = mapper.getUserNameById(userId);
		session.close();
		return username;
	}
	
	//zzl_获取所有待审核用户_2017年11月9日21:28:48
	public static List<UserPersistence> getAllUsers(int userState) {
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		UserPersistenceMapper mapper = session.getMapper(UserPersistenceMapper.class);
		List<UserPersistence> list = mapper.getAllUsers(userState);
		session.close();
		return list;
	}
	
	//zzl_获取所有普通用户信息 -- 即USERSTATE = 2 且 角色名是普通用户_2017年11月9日22:33:54
	public static List<UserPersistence> getUserLists(int userState, String roleName) {
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		UserPersistenceMapper mapper = session.getMapper(UserPersistenceMapper.class);
		List<UserPersistence> list = mapper.getUserLists(userState,roleName);
		session.close();
		return list;
	}
	
	//zzl_更改用户状态_2017年11月10日10:33:30
	public static void updateUserState(String userId, int userState) {
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		UserPersistenceMapper mapper = session.getMapper(UserPersistenceMapper.class);
		mapper.updateUserState(userId, userState);
		session.close();		
	}
	
	//zyq_图片上传
	public static void updateUserImage(String username,String path){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		UserPersistenceMapper mapper = session.getMapper(UserPersistenceMapper.class);
		mapper.updateUserImage(username,path);
		session.close();
	}
	
	//导入用户信息
	public static void login_register2(String userid,String password,String username,int userstate,String userimage,String roleid){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		UserPersistenceMapper mapper = session.getMapper(UserPersistenceMapper.class);
		mapper.login_register2(userid, password, username, userstate,userimage,roleid);
		session.close();
	}

	//获取用户角色名
	public static String getRoleNameByUserId(String userId) {
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		UserPersistenceMapper mapper = session.getMapper(UserPersistenceMapper.class);
		String roleName = mapper.getRoleNameByUserId(userId);
		session.close();
		return roleName;
	}
	
	//更新员工角色
	public static void updateUserRole(String userId, String roleId) {
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		UserPersistenceMapper mapper = session.getMapper(UserPersistenceMapper.class);
		mapper.updateUserRole( userId,  roleId);
		session.close();				
	}
	
	public static void resetPass(String userid, String password) {
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		UserPersistenceMapper mapper = session.getMapper(UserPersistenceMapper.class);
		mapper.resetPass(userid,password);
		session.close();			
	}
	
	/**
	 * abstract:注册的用户信息同时添加到普通用户表		!!!
	 */
	public static void addGeneralUser(GeneraluserPersistence generaluserPersistence){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		GeneraluserPersistenceMapper mapper = session.getMapper(GeneraluserPersistenceMapper.class);
		mapper.save(generaluserPersistence);
		session.close();
	}

	
	
}