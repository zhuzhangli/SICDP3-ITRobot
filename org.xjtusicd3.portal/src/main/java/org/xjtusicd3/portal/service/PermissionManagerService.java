package org.xjtusicd3.portal.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.xjtusicd3.database.helper.PermissionHelper;
import org.xjtusicd3.database.helper.RolePermissionHelper;
import org.xjtusicd3.database.model.PermissionPersistence;
import org.xjtusicd3.database.model.RolePermissionPersistence;
import org.xjtusicd3.portal.view.PermissionView;
import org.xjtusicd3.portal.view.Permission_RoleView;

/**
 * @author zzl
 */
public class PermissionManagerService
{
	//获取所有权限列表
	public static List<PermissionView> getPermissionList() {
		//所有权限视图
		List<PermissionView> permissionViews = new ArrayList<PermissionView>();
		
		//获取所有权限
		List<PermissionPersistence> permissionPersistences = PermissionHelper.getAllPermission();
		
		for(PermissionPersistence permissionPersistence:permissionPersistences){
			//新建单个权限视图
			PermissionView permissionView = new PermissionView();
			permissionView.setPermissionId(permissionPersistence.getPERMISSIONID());
			permissionView.setPermissionPhysicalName(permissionPersistence.getPERMISSIONPHYSICALNAME());
			permissionView.setPermissionLogicName(permissionPersistence.getPERMISSIONLOGICNAME());
			permissionView.setTime(permissionPersistence.getTIME());
			permissionViews.add(permissionView);
		}
		return permissionViews;
	}
	
	//增加权限
	public static void addPermission(String logicName, String physicalName) {
		PermissionPersistence permissionPersistence = new PermissionPersistence();
		permissionPersistence.setPERMISSIONID(UUID.randomUUID().toString());
		permissionPersistence.setPERMISSIONPHYSICALNAME(physicalName);
		permissionPersistence.setPERMISSIONLOGICNAME(logicName);
		
		Date date=new Date();
    	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	String time = format.format(date);
		permissionPersistence.setTIME(time);
    	PermissionHelper.save(permissionPersistence);
	}
	
	//更改权限
	public static void updatePermission(String permissionId, String physicalName, String logicName) {
		PermissionPersistence permissionPersistence = new PermissionPersistence();
		permissionPersistence.setPERMISSIONID(permissionId);
		permissionPersistence.setPERMISSIONPHYSICALNAME(physicalName);
		permissionPersistence.setPERMISSIONLOGICNAME(logicName);		
		Date date=new Date();
    	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	String time = format.format(date);   
    	permissionPersistence.setTIME(time);
    	PermissionHelper.update(permissionPersistence);
	}
	
	//删除权限
	public static void deletePermission(String permissionId) {
		
		PermissionHelper.deletePermission(permissionId);
		
	}
	
	//获取角色还未得到的权限
	public static List<Permission_RoleView> notObtainRolePermission(String roleId) {
		List<Permission_RoleView> permission_RoleViews = new ArrayList<Permission_RoleView>();
		
		List<PermissionPersistence> lists = PermissionHelper.notObtainRolePermission(roleId);
		
		System.out.println("未获得权限条数："+lists.size());
		
		for(PermissionPersistence list:lists){
			Permission_RoleView permission_RoleView = new Permission_RoleView();
			
			permission_RoleView.setPERMISSIONID(list.getPERMISSIONID());
			permission_RoleView.setPERMISSIONPHYSICALNAME(list.getPERMISSIONPHYSICALNAME());
			permission_RoleView.setPERMISSIONLOGICNAME(list.getPERMISSIONLOGICNAME());

			permission_RoleViews.add(permission_RoleView);
		}
		
		return permission_RoleViews;
		
	}
	
	//获取角色已得到的权限
	public static List<Permission_RoleView> obtainRolePermission(String roleId) {
		List<Permission_RoleView> permission_RoleViews = new ArrayList<Permission_RoleView>();
		
		List<PermissionPersistence> lists = PermissionHelper.obtainRolePermission(roleId);		
		System.out.println("已获得权限条数："+lists.size());		
		for(PermissionPersistence list:lists){
			Permission_RoleView permission_RoleView = new Permission_RoleView();
			
			permission_RoleView.setPERMISSIONID(list.getPERMISSIONID());
			permission_RoleView.setPERMISSIONPHYSICALNAME(list.getPERMISSIONPHYSICALNAME());
			permission_RoleView.setPERMISSIONLOGICNAME(list.getPERMISSIONLOGICNAME());

			permission_RoleViews.add(permission_RoleView);
		}	
		return permission_RoleViews;
	}
	
	// 将选中权限插入到角色-权限表中  -- 逐条插入
	public static void addPermissionToRole(String roleId, String permissionId) {
		
		//判断用户权限表中是否已存在此权限
		List<RolePermissionPersistence> list = RolePermissionHelper.isExist(roleId,permissionId);
		if (list.size()==0) {
			//为角色增加权限
			RolePermissionHelper.addPermissionToRole(UUID.randomUUID().toString(),roleId,permissionId);
		}
	}
	
	// 将选中权限从角色-权限表中移除
	public static void deletePermissionToRole(String roleId, String permissionId) {
		//判断用户权限表中是否已存在此权限
		List<RolePermissionPersistence> list = RolePermissionHelper.isExist(roleId,permissionId);
		if (list.size()>0) {
			//移除角色已获取的权限
			RolePermissionHelper.deletePermissionToRole(roleId,permissionId);
		}		
	}	
}
