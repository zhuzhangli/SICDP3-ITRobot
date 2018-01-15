package org.xjtusicd3.database.helper;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.xjtusicd3.database.logic.SqlSessionManager;
import org.xjtusicd3.database.mapper.PermissionPersistenceMapper;
import org.xjtusicd3.database.model.PermissionPersistence;


public class PermissionHelper
{
	//获取所有权限
	public static List<PermissionPersistence> getAllPermission() {
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		PermissionPersistenceMapper mapper = session.getMapper(PermissionPersistenceMapper.class);
		List<PermissionPersistence> list = mapper.getAllPermission();
		session.close();
		return list;
	}

	//根据物理名查看权限ID
	public static String getPermissionIdByPhysicalName(String physicalName) {
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		PermissionPersistenceMapper mapper = session.getMapper(PermissionPersistenceMapper.class);
		String permissionId = mapper.getPermissionIdByPhysicalName(physicalName);
		session.close();
		return permissionId;
	}
	
	//根据逻辑名查看权限ID
	public static String getPermissionIdByLogicName(String logicName) {
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		PermissionPersistenceMapper mapper = session.getMapper(PermissionPersistenceMapper.class);
		String permissionId = mapper.getPermissionIdByLogicName(logicName);
		session.close();
		return permissionId;
	}
		
	//增加权限
	public static void save(PermissionPersistence permissionPersistence) {
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		PermissionPersistenceMapper mapper = session.getMapper(PermissionPersistenceMapper.class);
		mapper.save(permissionPersistence);
		session.close();		
	}
	
	//更改权限
	public static void update(PermissionPersistence permissionPersistence) {
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		PermissionPersistenceMapper mapper = session.getMapper(PermissionPersistenceMapper.class);
		mapper.update(permissionPersistence);
		session.close();		
	}
	
	//删除权限
	public static void deletePermission(String permissionId) {
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		PermissionPersistenceMapper mapper = session.getMapper(PermissionPersistenceMapper.class);
		mapper.deletePermission(permissionId);
		session.close();		
	}
	
	//获取角色还未得到的权限
	public static List<PermissionPersistence> notObtainRolePermission(String roleId) {
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		PermissionPersistenceMapper mapper = session.getMapper(PermissionPersistenceMapper.class);
		List<PermissionPersistence> list = mapper.notObtainRolePermission(roleId);
		session.close();
		return list;
		
	}
	
	//获取角色已得到的权限
	public static List<PermissionPersistence> obtainRolePermission(String roleId) {
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		PermissionPersistenceMapper mapper = session.getMapper(PermissionPersistenceMapper.class);
		List<PermissionPersistence> list = mapper.obtainRolePermission(roleId);
		session.close();
		return list;
	}
	
}
