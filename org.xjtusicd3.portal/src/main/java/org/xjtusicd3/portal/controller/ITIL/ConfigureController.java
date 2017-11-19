package org.xjtusicd3.portal.controller.ITIL;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.xjtusicd3.common.util.JsonUtil;
import org.xjtusicd3.database.helper.BasicConfigureHelper;
import org.xjtusicd3.database.helper.ConfigureHelper;
import org.xjtusicd3.database.model.BasicConfigurePersistence;
import org.xjtusicd3.database.model.ComputerPersistence;
import org.xjtusicd3.database.model.ConfigurePersistence;
import org.xjtusicd3.database.model.ServerPersistence;
import org.xjtusicd3.portal.service.ComputerService;
import org.xjtusicd3.portal.service.ConfigureService;
import org.xjtusicd3.portal.service.ServerService;
import org.xjtusicd3.portal.view.ChangeIndexView;
import org.xjtusicd3.portal.view.ConfigureSoftView;
import com.alibaba.fastjson.JSONObject;

@Controller
/**
 * 
 * @author zzl
 * @abstract:配置管理_configureSoftPage.ftl、configureEquipmentPage.ftl、configureBasicPage.ftl
 *
 */
public class ConfigureController {
		/********************        configureSoftPage  BEGIN            ************************************/

	//软件资料库
	@RequestMapping(value="configureSoftPage",method=RequestMethod.GET)
    public ModelAndView  configureSoftPage(){
 	   ModelAndView mv=new ModelAndView("configureSoftPage");
 	   
 	   //获取所有软件信息 -- tbl_soft表
 	   List<ConfigureSoftView> softList = ConfigureService.getAllSofts();
 	  
 	   //获取软件总数量
 	   int softSize = ConfigureHelper.getAllSoftCounts();
 	   
 	   mv.addObject("softList", softList);
 	   mv.addObject("softSize", softSize);
 	   return mv;
    }


	
	
	//查看软件更多信息
	@ResponseBody
	@RequestMapping(value="/lookMoreSoftInfo",method={org.springframework.web.bind.annotation.RequestMethod.POST},produces="application/json;charset=UTF-8")
	public String lookMoreSoftInfo(HttpServletRequest request,HttpSession session){
		//获取ajax传来数据
		String configureId = request.getParameter("configureId");
		
		//获取ID对应软件信息
		ConfigureSoftView list = ConfigureService.getSoftInfoById(configureId);
		
		
		JSONObject jsonObject = new JSONObject();
		
		jsonObject.put("moreSoftInfo", list);
				
		String result = JsonUtil.toJsonString(jsonObject);
		
		return result;
	}
	
	
	//查看软件更多信息
	@ResponseBody
	@RequestMapping(value="/addSoftToBasicCfg",method={org.springframework.web.bind.annotation.RequestMethod.POST},produces="application/json;charset=UTF-8")
	public String addSoftToBasicCfg(HttpServletRequest request,HttpSession session){
		//获取ajax传来数据
		String configureId = request.getParameter("configureId");
		
		//查看该软件是否已添加至标准配置库
		List<BasicConfigurePersistence> basicConfigurePersistences = BasicConfigureHelper.getCfgInfoFromBasic(configureId);
		
		JSONObject jsonObject = new JSONObject();
		
		if (basicConfigurePersistences.size() == 0) {
			//将软件添加至标准配置库
			ConfigureService.addToBasicCfg(configureId);
			jsonObject.put("value", "1");
			String result = JsonUtil.toJsonString(jsonObject); 
			return result;
		}else {
			//将软件从标准配置库移除
			jsonObject.put("value", "2");
			String result = JsonUtil.toJsonString(jsonObject); 
			return result;
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*
	 * zpz_�鿴configure
	 */
	@RequestMapping(value="cfgindex",method=RequestMethod.GET)
	public ModelAndView configure() {
		ModelAndView modelAndView = new ModelAndView("cfgindex");
		List<ComputerPersistence> computerList = ComputerService.getComputer();
		List<ServerPersistence> serverList = ServerService.getServer();
		List<ConfigurePersistence> cfgList = ConfigureService.getConfigure();
		modelAndView.addObject("computerList", computerList);
		modelAndView.addObject("serverList", serverList);
		modelAndView.addObject("cfgList", cfgList);
		return modelAndView;
	}
	
	/**
	 * author:
	 * abstract:变更列表
	 * data:2017年10月12日17:46:34
	 */
	@RequestMapping(value="changeindex",method=RequestMethod.GET)
    public ModelAndView  change(){
 	   ModelAndView mv=new ModelAndView("changeindex");
 	   int startNumber = 0;
 	   List<ChangeIndexView> changeViewsList = ConfigureService.getConfigureHistory(startNumber);
 	   mv.addObject("cfg_update_list", changeViewsList);
 	   return mv;
    }

}
