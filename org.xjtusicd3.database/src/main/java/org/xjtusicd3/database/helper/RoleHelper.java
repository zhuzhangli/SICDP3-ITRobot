package org.xjtusicd3.database.helper;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.xjtusicd3.database.logic.SqlSessionManager;
import org.xjtusicd3.database.mapper.RolePersistenceMapper;
import org.xjtusicd3.database.model.RolePersistence;

public class RoleHelper
{
	//获取所有角色
	public static List<RolePersistence> getAllRoles() {
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		RolePersistenceMapper mapper = session.getMapper(RolePersistenceMapper.class);
		List<RolePersistence> rolelist = mapper.getAllRoles();
		session.close();
		return rolelist;
	}
	
	//获取该员工本身角色外的其他角色			！！！
	public static List<RolePersistence> getUnGotRoleList(String userId) {
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		RolePersistenceMapper mapper = session.getMapper(RolePersistenceMapper.class);
		List<RolePersistence> list = mapper.getUnGotRoleList(userId);
		session.close();
		return list;
	}
	
	//查询当前用户角色			！！！
	public static List<RolePersistence> getRoleInfoByUserId(String userId) {
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		RolePersistenceMapper mapper = session.getMapper(RolePersistenceMapper.class);
		List<RolePersistence> list = mapper.getRoleInfoByUserId(userId);
		session.close();
		return list;
	}
}
