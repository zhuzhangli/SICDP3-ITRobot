package org.xjtusicd3.database.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.xjtusicd3.database.logic.IBaseDao;
import org.xjtusicd3.database.model.PermissionPersistence;
import org.xjtusicd3.database.model.RolePermissionPersistence;

public interface RolePermissionPersistenceMapper extends IBaseDao<RolePermissionPersistence, String>
{
	//判断用户权限表中是否已存在此权限
	@Select("SELECT * FROM TBL_Role_Permission WHERE ROLEID=#{0} AND PERMISSIONID=#{1}")
	public List<RolePermissionPersistence> isExist(String roleId, String permissionId);
	
	//为角色增加权限
	@Insert("INSERT INTO TBL_Role_Permission VALUES(#{0},#{1},#{2})")
	public void addPermissionToRole(String rolePermissionId, String roleId, String permissionId);

	//移除角色已获取的权限
	@Delete("DELETE FROM TBL_Role_Permission WHERE ROLEID=#{0} AND PERMISSIONID=#{1}")
	public void deletePermissionToRole(String roleId, String permissionId);
	
	// 多表联合查询获取到角色权限信息
	@Select("SELECT TBL_Permission.* FROM TBL_User, TBL_Role_Permission INNER JOIN TBL_Permission ON TBL_Role_Permission.PERMISSIONID = TBL_Permission.PERMISSIONID WHERE TBL_User.USERID = #{0} AND TBL_User.ROLEID = TBL_Role_Permission.ROLEID")
	public List<PermissionPersistence> getRolePermissionByUId(String Uid);
	
}
