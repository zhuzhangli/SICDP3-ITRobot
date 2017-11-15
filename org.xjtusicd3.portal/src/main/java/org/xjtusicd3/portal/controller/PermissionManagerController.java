package org.xjtusicd3.portal.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.xjtusicd3.common.util.JsonUtil;
import org.xjtusicd3.database.helper.ClassifyHelper;
import org.xjtusicd3.database.helper.RoleHelper;
import org.xjtusicd3.database.model.ClassifyPersistence;
import org.xjtusicd3.database.model.RolePermissionPersistence;
import org.xjtusicd3.database.model.RolePersistence;
import org.xjtusicd3.portal.service.EventManagerService;
import org.xjtusicd3.portal.service.PermissionManagerService;
import org.xjtusicd3.portal.service.RoleService;
import org.xjtusicd3.portal.view.PermissionView;
import org.xjtusicd3.portal.view.Permission_RoleView;
/**
 * 
 * @author zzl
 *
 */
@Controller
public class PermissionManagerController
{
	//权限列表页面_permissionPage.ftl	
	@RequestMapping(value="permissionPage",method=RequestMethod.GET)
	public  ModelAndView permissionPage()
	{
		ModelAndView mv = new ModelAndView("permissionPage");
		
		//获取所有权限
		List<PermissionView> permissionList = PermissionManagerService.getPermissionList();
		
		mv.addObject("permissionList",permissionList);
		
		return mv;	 
	}

	
	//增加权限
	@ResponseBody
	@RequestMapping(value="/addPermission",method=RequestMethod.POST)
	public String addPermission(HttpServletRequest request,HttpSession session){
	
		String logicName = request.getParameter("logicName");
		String physicalName = request.getParameter("physicalName");
	
		//新增权限
		PermissionManagerService.addPermission(logicName,physicalName);
		
		return "1";
	}
	
		
	//更改权限信息		
	@ResponseBody
	@RequestMapping(value="/updatePermission",method=RequestMethod.POST)
	public String updatePermission(HttpServletRequest request,HttpSession session){
		
		//获取ajax传值
		String permissionId = request.getParameter("permissionId");
		String logicName = request.getParameter("logicName");
		String physicalName = request.getParameter("physicalName");
	
		if (permissionId==null) {
			
			return "0";
		}else {			
			//更改权限信息
			PermissionManagerService.updatePermission(permissionId,physicalName,logicName);
						
			return "1";
		}		
	}
	
	
	//更改权限信息		
	@ResponseBody
	@RequestMapping(value="/deletePermission",method=RequestMethod.POST)
	public String deletePermission(HttpServletRequest request,HttpSession session){
		
		//获取ajax传值
		String permissionId = request.getParameter("permissionId");
	
		if (permissionId==null) {
			
			return "0";
		}else {			
			//更改权限信息
			PermissionManagerService.deletePermission(permissionId);
						
			return "1";
		}		
	}
	
	
	
	/************************权限分配部分****************************/
	@RequestMapping(value="premissionAssignPage",method=RequestMethod.GET)
    public ModelAndView  premissionAssignPage(){
 	   ModelAndView mv=new ModelAndView("premissionAssignPage");
 	   
 	   List<Permission_RoleView> roleList = RoleService.getAllRoles();
 	   
 	   System.out.println();
 	   
 	   mv.addObject("roleList", roleList);
 	   return mv;
    }
	
	
}
