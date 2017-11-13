package org.xjtusicd3.portal.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.xjtusicd3.portal.service.LogService;
import org.xjtusicd3.portal.service.PermissionManagerService;
import org.xjtusicd3.portal.view.logindexView;
import org.xjtusicd3.portal.view.rolePerView;

@Controller
public class PermissionManagerController
{
	/*
	 * zpz_Log_查看Log
	 */
	@RequestMapping(value="permissionPage",method=RequestMethod.GET)
	public  ModelAndView permissionPage()
	{
		ModelAndView mv = new ModelAndView("permissionPage");
		List<rolePerView> rolePerViews = PermissionManagerService.getRolePer();
		mv.addObject("rolePer_list",rolePerViews);
		return mv;
	 
	}
}
