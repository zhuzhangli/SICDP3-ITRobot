package org.xjtusicd3.portal.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.xjtusicd3.database.helper.PermissionHelper;
import org.xjtusicd3.database.model.PermissionPersistence;
import org.xjtusicd3.portal.view.PermissionView;

/**
 * 
 * @author zzl
 *
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
		String permissionId = UUID.randomUUID().toString();
		
		Date date=new Date();
    	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	String time = format.format(date);
		
    	PermissionHelper.addPermission(permissionId,physicalName,logicName,time);
	}

	
	//更改权限
	public static void updatePermission(String permissionId, String physicalName, String logicName) {
		Date date=new Date();
    	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	String time = format.format(date);
    	
    	PermissionHelper.updatePermission(permissionId,physicalName,logicName,time);
		
	}

	
	//删除权限
	public static void deletePermission(String permissionId) {
		
		PermissionHelper.deletePermission(permissionId);
		
	}



	
	
	
	
	
	
	
	
//	//获取该permissionId对应的权限信息
//	public static PermissionView getPermissionById(String permissionId) {
//		PermissionView permissionView = new PermissionView();
//		
//		PermissionPersistence permissionPersistence = PermissionHelper.getPermissionById(permissionId);
//		
//		permissionView.setPermissionId(permissionId);
//		permissionView.setPermissionPhysicalName(permissionPersistence.getPERMISSIONPHYSICALNAME());
//		permissionView.setPermissionLogicName(permissionPersistence.getPERMISSIONLOGICNAME());
//		
//		return permissionView;
//	}

	
	/*
	 * zpz_rbacindex展示
	 */
//	public static List<PermissionView> getRolePer(){
//		List<PermissionView> rolePerViews = new ArrayList<PermissionView>();
//		List<String> roleId = RolePermissionHelper.getAllRoleId(); 
//		for(String roleIds:roleId){
//			PermissionView rolePerView = new PermissionView();
//			//List<PermissionPersistence> permissionPersistences = new ArrayList<PermissionPersistence>();
//			List<PermissionPersistence> list = RolePermissionHelper.getRolePermissionByRId(roleIds);
//			String string = "";
//			for (PermissionPersistence permissionPersistence:list){
//				string = string+permissionPersistence.getPERMISSIONPHYSICALNAME()+" ";
//			}
//			rolePerView.setPermission(string);
//			rolePerView.setRoleName(RoleHelper.getRoleName(roleIds));
//			rolePerViews.add(rolePerView);
//		}
//		return rolePerViews;
//	}

	
}
