package org.xjtusicd3.portal.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.xjtusicd3.portal.service.EventManagerService;
import org.xjtusicd3.portal.service.PermissionManagerService;
import org.xjtusicd3.portal.view.PermissionView;
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
	
	
	
	//修改权限信息		
	@ResponseBody
	@RequestMapping(value="/editPermission",method=RequestMethod.POST)
	public ModelAndView editPermission(HttpServletRequest request,HttpSession session){
		ModelAndView mv = new ModelAndView("editPermission");
		
		//获取ajax传过permissionId
		String permissionId = request.getParameter("permissionId");
	
		if (permissionId==null) {
			
			return mv;
		}else {			
			//获取此条权限信息
			PermissionView permissionInfo = PermissionManagerService.getPermissionById(permissionId);
			
			mv.addObject("permissionInfo", permissionInfo);
			
			return mv;
		}
		
	}
	
	
}
