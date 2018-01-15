package org.xjtusicd3.portal.service;

import java.util.List;

import org.xjtusicd3.database.helper.RolePermissionHelper;
import org.xjtusicd3.database.model.PermissionPersistence;

public class PermissionRoleService
{
	public static List<PermissionPersistence> getRolePermission(String uId)
	{
		List<PermissionPersistence> rolePermission = RolePermissionHelper.getRolePermission(uId);
		return rolePermission;
	}
	 
}
