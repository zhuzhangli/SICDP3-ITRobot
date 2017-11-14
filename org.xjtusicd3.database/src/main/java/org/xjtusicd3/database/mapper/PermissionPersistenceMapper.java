package org.xjtusicd3.database.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.xjtusicd3.database.model.PermissionPersistence;

public interface PermissionPersistenceMapper
{
	//获取所有权限
	@Select("SELECT * FROM TBL_Permission ORDER BY TIME DESC")
	public List<PermissionPersistence> getAllPermission();
	
	//根据权限ID获取相对应的权限信息
	@Select("SELECT * FROM TBL_Permission WHERE PERMISSIONID=#{0}")
	public PermissionPersistence getPermissionById(String permissionId);
	
	//增加权限
	@Insert("INSERT INTO TBL_Permission  VALUES (#{0},#{1},#{2},#{3})")
	public void addPermission(String permissionId, String physicalName, String logicName, String time);
	
	
	
	
	
	
	
	
	
	
	











	

	
}
