package org.xjtusicd3.database.helper;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.xjtusicd3.database.logic.SqlSessionManager;
import org.xjtusicd3.database.mapper.RolePermissionPersistenceMapper;
import org.xjtusicd3.database.model.PermissionPersistence;
import org.xjtusicd3.database.model.RolePermissionPersistence;

public class RolePermissionHelper
{
	//判断用户权限表中是否已存在此权限
	public static List<RolePermissionPersistence> isExist(String roleId, String permissionId) {
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		RolePermissionPersistenceMapper mapper = session.getMapper(RolePermissionPersistenceMapper.class);
		List<RolePermissionPersistence> list = mapper.isExist(roleId,permissionId);
		session.close();
		return list;
	}
	
	//为角色增加权限
	public static void addPermissionToRole(String rolePermissionId, String roleId, String permissionId) {
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		RolePermissionPersistenceMapper mapper = session.getMapper(RolePermissionPersistenceMapper.class);
		mapper.addPermissionToRole(rolePermissionId,roleId,permissionId);
		session.close();		
	}
	
	//移除角色已获取的权限
	public static void deletePermissionToRole(String roleId, String permissionId) {
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		RolePermissionPersistenceMapper mapper = session.getMapper(RolePermissionPersistenceMapper.class);
		mapper.deletePermissionToRole(roleId,permissionId);
		session.close();		
	}
	
	/**
	 * 通过用户id获取权限
	 */
	public static List<PermissionPersistence> getRolePermission(String uId){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		RolePermissionPersistenceMapper mapper = session.getMapper(RolePermissionPersistenceMapper.class);
		List<PermissionPersistence> rolePermission = mapper.getRolePermissionByUId(uId);
		session.close();
		return rolePermission;
	}
	
}
