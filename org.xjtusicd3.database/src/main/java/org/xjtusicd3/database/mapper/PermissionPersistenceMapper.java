package org.xjtusicd3.database.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.xjtusicd3.database.logic.IBaseDao;
import org.xjtusicd3.database.model.PermissionPersistence;

public interface PermissionPersistenceMapper extends IBaseDao<PermissionPersistence, String>
{
	//获取所有权限
	@Select("SELECT * FROM TBL_Permission ORDER BY TIME DESC")
	public List<PermissionPersistence> getAllPermission();
	
	//删除权限
	@Delete("DELETE FROM TBL_Permission WHERE PERMISSIONID=#{0}")
	public void deletePermission(String permissionId);
	
	//获取角色还未得到的权限
	@Select("SELECT * FROM TBL_Permission WHERE PERMISSIONID NOT IN (SELECT PERMISSIONID FROM tbl_role_permission WHERE ROLEID=#{0})")
	public List<PermissionPersistence> notObtainRolePermission(String roleId);
	
	//获取角色已得到的权限
	@Select("SELECT * FROM tbl_role_permission,TBL_Permission WHERE tbl_role_permission.PERMISSIONID = TBL_Permission.PERMISSIONID AND tbl_role_permission.ROLEID=#{0}")
	public List<PermissionPersistence> obtainRolePermission(String roleId);

	//根据物理名查看权限ID
	@Select("SELECT PERMISSIONID FROM TBL_Permission WHERE PERMISSIONPHYSICALNAME=#{0} ")
	public String getPermissionIdByPhysicalName(String physicalName);

	//根据逻辑名查看权限ID
	@Select("SELECT PERMISSIONID FROM TBL_Permission WHERE PERMISSIONLOGICNAME=#{0}")
	public String getPermissionIdByLogicName(String logicName);

}
