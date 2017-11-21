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
import org.xjtusicd3.database.helper.EquipmentHelper;
import org.xjtusicd3.database.model.BasicConfigurePersistence;
import org.xjtusicd3.database.model.ComputerPersistence;
import org.xjtusicd3.database.model.ConfigurePersistence;
import org.xjtusicd3.database.model.EquipmentPersistence;
import org.xjtusicd3.database.model.ServerPersistence;
import org.xjtusicd3.portal.service.ComputerService;
import org.xjtusicd3.portal.service.ConfigureService;
import org.xjtusicd3.portal.service.EquipmentService;
import org.xjtusicd3.portal.service.ServerService;
import org.xjtusicd3.portal.view.ChangeIndexView;
import org.xjtusicd3.portal.view.ConfigureDriverView;
import org.xjtusicd3.portal.view.ConfigurePatchView;
import org.xjtusicd3.portal.view.ConfigureSoftView;
import org.xjtusicd3.portal.view.EquipmentComputerView;
import org.xjtusicd3.portal.view.EquipmentServerView;

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
 	   
 	   //获取所有驱动信息 -- tbl_driver表
 	   List<ConfigureDriverView> driverList = ConfigureService.getAllDrivers();
 	   
 	   //获取驱动总数量
 	   int driverSize = ConfigureHelper.getAllDriverCounts();
 	   
 	  //获取所有补丁信息  -- tbl_patch表
 	  List<ConfigurePatchView> patchList = ConfigureService.getAllPatchs();
 	   
 	  //获取补丁总数量
 	  int patchSize = ConfigureHelper.getAllPatchCounts();
 	   
 	   mv.addObject("softList", softList);
 	   mv.addObject("softSize", softSize);
 	   
 	   mv.addObject("driverList", driverList);
	   mv.addObject("driverSize", driverSize);
 	   
 	   
 	   mv.addObject("patchList", patchList);
	   mv.addObject("patchSize", patchSize);
 	   return mv;
    }

	
	//查看软件更多信息
	@ResponseBody
	@RequestMapping(value="/lookMoreSoftInfo",method={org.springframework.web.bind.annotation.RequestMethod.POST},produces="application/json;charset=UTF-8")
	public String lookMoreSoftInfo(HttpServletRequest request,HttpSession session){
		//获取ajax传来数据
		String configureId = request.getParameter("configureId");
    	String[] str = configureId.split("_");
    	
    	System.out.println("切分后的值为："+str[1]);    	
		//获取ID对应软件信息
		ConfigureSoftView list = ConfigureService.getSoftInfoById(str[1]);
		
		
		JSONObject jsonObject = new JSONObject();
		
		jsonObject.put("moreSoftInfo", list);
				
		String result = JsonUtil.toJsonString(jsonObject);
		
		return result;
	}
	
	
	//将软件添加至标准库
	@ResponseBody
	@RequestMapping(value="/SoftBasicCfg",method={org.springframework.web.bind.annotation.RequestMethod.POST},produces="application/json;charset=UTF-8")
	public String SoftBasicCfg(HttpServletRequest request,HttpSession session){
		//获取ajax传来数据
		String configureId = request.getParameter("configureId");
		
		//查看该软件是否已添加至标准配置库
		List<BasicConfigurePersistence> basicConfigurePersistences = BasicConfigureHelper.getCfgInfoFromBasic(configureId);
		
		JSONObject jsonObject = new JSONObject();
		
		if (basicConfigurePersistences.size() == 0) {
			//将软件添加至标准配置库
			ConfigureService.addToBasicCfg(configureId);
			
			//更新tbl_configure表中ISCONFIGURE字段为1
			ConfigureService.updateCfgStatus(configureId,1);
			
			jsonObject.put("value", "1");
			jsonObject.put("configureId", configureId);
			String result = JsonUtil.toJsonString(jsonObject); 
			return result;
		}else {
			//将软件从标准配置库移除
			ConfigureService.deleteFromBasicCfg(configureId);
			
			//更新tbl_configure表中ISCONFIGURE字段为0
			ConfigureService.updateCfgStatus(configureId,0);
			
			jsonObject.put("value", "2");
			jsonObject.put("configureId", configureId);
			String result = JsonUtil.toJsonString(jsonObject); 
			return result;
		}
	}
	
	
	//查看驱动更多信息
	@ResponseBody
	@RequestMapping(value="/lookMoreDriverInfo",method={org.springframework.web.bind.annotation.RequestMethod.POST},produces="application/json;charset=UTF-8")
	public String lookMoreDriverInfo(HttpServletRequest request,HttpSession session){
		//获取ajax传来数据
		String configureId = request.getParameter("configureId");
    	String[] str = configureId.split("_");
    	
    	System.out.println("切分后的值为："+str[1]);    	
		
    	//获取ID对应驱动信息
		ConfigureDriverView list = ConfigureService.getDriverInfoById(str[1]);
		
		
		JSONObject jsonObject = new JSONObject();
		
		jsonObject.put("moreDriverInfo", list);
				
		String result = JsonUtil.toJsonString(jsonObject);
		
		return result;
	}
	
	
	
	//将驱动添加至标准库 OR 从标准库删除
	@ResponseBody
	@RequestMapping(value="/driverBasicCfg",method={org.springframework.web.bind.annotation.RequestMethod.POST},produces="application/json;charset=UTF-8")
	public String driverBasicCfg(HttpServletRequest request,HttpSession session){
		//获取ajax传来数据
		String configureid = request.getParameter("configureId");
		String[] str = configureid.split("_");
		String configureId = str[1];
		
		
		//查看该驱动是否已添加至标准配置库
		List<BasicConfigurePersistence> basicConfigurePersistences = BasicConfigureHelper.getCfgInfoFromBasic(configureId);
		
		JSONObject jsonObject = new JSONObject();
		
		if (basicConfigurePersistences.size() == 0) {
			//将驱动添加至标准配置库
			ConfigureService.addToBasicCfg(configureId);
			
			//更新tbl_configure表中ISCONFIGURE字段为1
			ConfigureService.updateCfgStatus(configureId,1);
			
			jsonObject.put("value", "1");
			jsonObject.put("configureId", configureId);
			String result = JsonUtil.toJsonString(jsonObject); 
			return result;
		}else {
			//将驱动从标准配置库移除
			ConfigureService.deleteFromBasicCfg(configureId);
			
			//更新tbl_configure表中ISCONFIGURE字段为0
			ConfigureService.updateCfgStatus(configureId,0);
			
			jsonObject.put("value", "2");
			jsonObject.put("configureId", configureId);
			String result = JsonUtil.toJsonString(jsonObject); 
			return result;
		}
	}
	
		
	//查看补丁更多信息
	@ResponseBody
	@RequestMapping(value="/lookMorePatchInfo",method={org.springframework.web.bind.annotation.RequestMethod.POST},produces="application/json;charset=UTF-8")
	public String lookMorePatchInfo(HttpServletRequest request,HttpSession session){
		//获取ajax传来数据
		String configureid = request.getParameter("configureId");
    	String[] str = configureid.split("_");
    	String configureId = str[1];
    	
    	System.out.println("切分后的值为："+str[1]);    	
		
    	//获取ID对应补丁信息
		ConfigurePatchView list = ConfigureService.getPatchInfoById(configureId);
		
		
		JSONObject jsonObject = new JSONObject();
		
		jsonObject.put("morePatchInfo", list);
				
		String result = JsonUtil.toJsonString(jsonObject);
		
		return result;
	}
	
	
	//将补丁添加至标准库 OR 从标准库删除
	@ResponseBody
	@RequestMapping(value="/patchBasicCfg",method={org.springframework.web.bind.annotation.RequestMethod.POST},produces="application/json;charset=UTF-8")
	public String patchBasicCfg(HttpServletRequest request,HttpSession session){
		//获取ajax传来数据
		String configureid = request.getParameter("configureId");
		String[] str = configureid.split("_");
		String configureId = str[1];
				
		//查看该补丁是否已添加至标准配置库
		List<BasicConfigurePersistence> basicConfigurePersistences = BasicConfigureHelper.getCfgInfoFromBasic(configureId);
		
		JSONObject jsonObject = new JSONObject();
		
		if (basicConfigurePersistences.size() == 0) {
			//将补丁添加至标准配置库
			ConfigureService.addToBasicCfg(configureId);
			
			//更新tbl_configure表中ISCONFIGURE字段为1
			ConfigureService.updateCfgStatus(configureId,1);
			
			jsonObject.put("value", "1");
			jsonObject.put("configureId", configureId);
			String result = JsonUtil.toJsonString(jsonObject); 
			return result;
		}else {
			//将补丁从标准配置库移除
			ConfigureService.deleteFromBasicCfg(configureId);
			
			//更新tbl_configure表中ISCONFIGURE字段为0
			ConfigureService.updateCfgStatus(configureId,0);
			
			jsonObject.put("value", "2");
			jsonObject.put("configureId", configureId);
			String result = JsonUtil.toJsonString(jsonObject); 
			return result;
		}
	}
	
	
	
	

	
	
	/********************        configureEquipmentPage  BEGIN            ************************************/
	
	//设备资料库
	@RequestMapping(value="configureEquipmentPage",method=RequestMethod.GET)
    public ModelAndView  configureEquipmentPage(){
 	   ModelAndView mv=new ModelAndView("configureEquipmentPage");
 	   
 	   //获取所有计算机信息 -- tbl_computer表
 	   List<EquipmentComputerView> computerList = EquipmentService.getAllComputers();
 	   
 	   //获取计算机总数
 	   int computerSize = EquipmentHelper.getAllComputerCounts();
 	   
 	   //获取服务器信息 -- tbl_server表
 	   List<EquipmentServerView> serverList = EquipmentService.getAllServers();
 	   
 	   //获取服务器总数
 	   int serverSize = EquipmentHelper.getAllServerCounts();
 	   
 	   
 	   
 	   
 	   mv.addObject("computerList", computerList);
 	   mv.addObject("computerSize", computerSize);
 	   
 	   mv.addObject("serverList", serverList);
	   mv.addObject("serverSize", serverSize);
 	   
 	   
 	   
 	   return mv;
    }
	
	
	//增加用户设备
	@ResponseBody
	@RequestMapping(value="/addUserEquipment",method=RequestMethod.POST)
	public String addUserEquipment(HttpServletRequest request,HttpSession session){
		//获取AJAX数据
		String macAddress = request.getParameter("macAddress");
		String equipmentModel = request.getParameter("equipmentModel");
		String CPU = request.getParameter("CPU");
		String RAM = request.getParameter("RAM");
		String storage = request.getParameter("storage");
		String IP = request.getParameter("IP");
		String buytime = request.getParameter("buytime");
		String graphicCard = request.getParameter("graphicCard");
		String audioCard = request.getParameter("audioCard");
		String networkCard = request.getParameter("networkCard");
		String motherboard = request.getParameter("motherboard");
		String OSName = request.getParameter("OSName");
		String OSID = request.getParameter("OSID");
	
		//新增权限
		//PermissionManagerService.addPermission(logicName,physicalName);
		
		
		//查找设备表中是否已存在该设备
		List<EquipmentPersistence> eList = EquipmentHelper.getEquipmentList(macAddress);
		if (eList.size() == 0) {
			//添加用户设备
			EquipmentService.addUserEquipment(macAddress,equipmentModel,buytime,CPU,RAM,storage,IP,graphicCard,audioCard,networkCard,motherboard,OSName,OSID);
		}
				
		return "1";
	}
	
	
	//查看计算机更多信息
	@ResponseBody
	@RequestMapping(value="/lookMoreComputerInfo",method={org.springframework.web.bind.annotation.RequestMethod.POST},produces="application/json;charset=UTF-8")
	public String lookMoreComputerInfo(HttpServletRequest request,HttpSession session){
		//获取ajax传来数据
		String equipmentid = request.getParameter("equipmentId");
    	String[] str = equipmentid.split("_");
    	String equipmentId = str[1];
    	 	
    	System.out.println("切分后的值为："+str[1]);    	
		//获取ID对应计算机信息
		EquipmentComputerView list  = EquipmentService.getComputerInfoById(equipmentId);
		
		JSONObject jsonObject = new JSONObject();
		
		jsonObject.put("moreComputerInfo", list);
				
		String result = JsonUtil.toJsonString(jsonObject);
		
		return result;
	}
	
	
	//获取要编辑的计算机信息
	@ResponseBody
	@RequestMapping(value="/editComputerInfo",method={org.springframework.web.bind.annotation.RequestMethod.POST},produces="application/json;charset=UTF-8")
	public String editComputerInfo(HttpServletRequest request,HttpSession session){
		//获取ajax传来数据
		String equipmentid = request.getParameter("equipmentId");
    	String[] str = equipmentid.split("_");
    	String equipmentId = str[1];
    	 	
    	System.out.println("切分后的值为："+str[1]);    	
		
    	//获取要编辑的计算机信息
		EquipmentComputerView list = EquipmentService.getEquipmentInfoById(equipmentId);
    	
    	
		JSONObject jsonObject = new JSONObject();
		
		jsonObject.put("editComputerInfo", list);
				
		String result = JsonUtil.toJsonString(jsonObject);
		
		return result;
	}
	
	
	@ResponseBody
	@RequestMapping(value="/updateComputer",method=RequestMethod.POST)
	public String updateComputer(HttpServletRequest request,HttpSession session){
		//获取AJAX数据
		String equipmentId = request.getParameter("equipmentId");
		String macAddress = request.getParameter("macAddress");
		String equipmentModel = request.getParameter("equipmentModel");
		String CPU = request.getParameter("CPU");
		String RAM = request.getParameter("RAM");
		String storage = request.getParameter("storage");
		String IP = request.getParameter("IP");
		String buytime = request.getParameter("buytime");
		String graphicCard = request.getParameter("graphicCard");
		String audioCard = request.getParameter("audioCard");
		String networkCard = request.getParameter("networkCard");
		String motherboard = request.getParameter("motherboard");
		String OSName = request.getParameter("OSName");
		String OSID = request.getParameter("OSID");
	
		if (equipmentId==null) {
			
			return "0";
		}else {			
			//更改用户计算机信息
			EquipmentService.updateComputer(equipmentId,macAddress,equipmentModel,CPU,RAM,storage,IP,buytime,graphicCard,audioCard,networkCard,motherboard,OSName,OSID);	
			
			return "1";
		}	
	}
	
	
	//删除计算机信息		
	@ResponseBody
	@RequestMapping(value="/deleteComputer",method=RequestMethod.POST)
	public String deletePermission(HttpServletRequest request,HttpSession session){
		
		//获取ajax传值
		String equipmentid = request.getParameter("equipmentId");
		String[] str = equipmentid.split("_");
    	String equipmentId = str[1];
    	
    	
		if (equipmentId==null) {
			
			return "0";
		}else {			
			//更改计算机状态
			//PermissionManagerService.deletePermission(permissionId);
			EquipmentService.updateEquipmentState(equipmentId,0);			
			return "1";
		}		
	}
	
	
	//增加用户设备
	@ResponseBody
	@RequestMapping(value="/addServerEquipment",method=RequestMethod.POST)
	public String addServerEquipment(HttpServletRequest request,HttpSession session){
		//获取AJAX数据
		String macAddress = request.getParameter("macAddress");
		String equipmentModel = request.getParameter("equipmentModel");
		String CPU = request.getParameter("CPU");
		String RAM = request.getParameter("RAM");
		String storage = request.getParameter("storage");
		String IP = request.getParameter("IP");
		String buytime = request.getParameter("buytime");
		
		String osVersion = request.getParameter("osVersion");
		String computerName = request.getParameter("computerName");
		String PCI = request.getParameter("PCI");
		String USB = request.getParameter("USB");
		String path = request.getParameter("path");
		String RAM_EXCHANGEAREAUSE = request.getParameter("RAM_EXCHANGEAREAUSE");
		String PARTATIONUSE = request.getParameter("PARTATIONUSE");
		String IDLERAM = request.getParameter("IDLERAM");
		String OS_TIME_USERNUM_LOAD = request.getParameter("OS_TIME_USERNUM_LOAD");
		String OSLOAD = request.getParameter("OSLOAD");
		String FIREWALL = request.getParameter("FIREWALL");
		String ROUTINGTABLE = request.getParameter("ROUTINGTABLE");
		String HASCONTACT = request.getParameter("HASCONTACT");
		String NETWORK = request.getParameter("NETWORK");
		String PROCESS = request.getParameter("PROCESS");
		String REALTIMEPROCESS = request.getParameter("REALTIMEPROCESS");
		String ACTIVEUSER = request.getParameter("ACTIVEUSER");
		String BIOS = request.getParameter("BIOS");
		String NETWORKCARD = request.getParameter("NETWORKCARD");
	
		
		//查找设备表中是否已存在该设备
		List<EquipmentPersistence> eList = EquipmentHelper.getEquipmentList(macAddress);
		if (eList.size() == 0) {
			//添加服务器信息
			EquipmentService.addServerEquipment(macAddress,equipmentModel,buytime,CPU,RAM,storage,IP,osVersion,computerName,PCI,USB,path,RAM_EXCHANGEAREAUSE,PARTATIONUSE,
					IDLERAM,OS_TIME_USERNUM_LOAD,OSLOAD,FIREWALL,ROUTINGTABLE,HASCONTACT,NETWORK,PROCESS,REALTIMEPROCESS,ACTIVEUSER,BIOS,NETWORKCARD);
		}
				
		return "1";
	}
		
	
	//查看服务器更多信息
	@ResponseBody
	@RequestMapping(value="/lookMoreServerInfo",method={org.springframework.web.bind.annotation.RequestMethod.POST},produces="application/json;charset=UTF-8")
	public String lookMoreServerInfo(HttpServletRequest request,HttpSession session){
		//获取ajax传来数据
		String equipmentid = request.getParameter("equipmentId");
    	String[] str = equipmentid.split("_");
    	String equipmentId = str[1];
    	 	
    	System.out.println("切分后的值为："+str[1]);    	
		//获取ID对应服务器信息
		EquipmentServerView list  = EquipmentService.getServerInfoById(equipmentId);
		
		JSONObject jsonObject = new JSONObject();
		
		jsonObject.put("moreServerInfo", list);
				
		String result = JsonUtil.toJsonString(jsonObject);
		
		return result;
	}
	
	
	//获取要编辑的服务器信息
	@ResponseBody
	@RequestMapping(value="/editServerInfo",method={org.springframework.web.bind.annotation.RequestMethod.POST},produces="application/json;charset=UTF-8")
	public String editServerInfo(HttpServletRequest request,HttpSession session){
		//获取ajax传来数据
		String equipmentid = request.getParameter("equipmentId");
    	String[] str = equipmentid.split("_");
    	String equipmentId = str[1];
    	 	
    	System.out.println("切分后的值为："+str[1]);    	
		
    	//获取要编辑的服务器信息
    	EquipmentServerView list = EquipmentService.getServiceInfoById(equipmentId);
    	
		JSONObject jsonObject = new JSONObject();
		
		jsonObject.put("editServerInfo", list);
				
		String result = JsonUtil.toJsonString(jsonObject);
		
		return result;
	}
	
	
	@ResponseBody
	@RequestMapping(value="/updateServer",method=RequestMethod.POST)
	public String updateServer(HttpServletRequest request,HttpSession session){
		//获取AJAX数据
		String equipmentId = request.getParameter("equipmentId");
		String macAddress = request.getParameter("macAddress");
		String equipmentModel = request.getParameter("equipmentModel");
		String CPU = request.getParameter("CPU");
		String RAM = request.getParameter("RAM");
		String storage = request.getParameter("storage");
		String IP = request.getParameter("IP");
		String buytime = request.getParameter("buytime");
		
		String osVersion = request.getParameter("osVersion");
		String computerName = request.getParameter("computerName");
		String PCI = request.getParameter("PCI");
		String USB = request.getParameter("USB");
		String path = request.getParameter("path");
		String RAM_EXCHANGEAREAUSE = request.getParameter("RAM_EXCHANGEAREAUSE");
		String PARTATIONUSE = request.getParameter("PARTATIONUSE");
		String IDLERAM = request.getParameter("IDLERAM");
		String OS_TIME_USERNUM_LOAD = request.getParameter("OS_TIME_USERNUM_LOAD");
		String OSLOAD = request.getParameter("OSLOAD");
		String FIREWALL = request.getParameter("FIREWALL");
		String ROUTINGTABLE = request.getParameter("ROUTINGTABLE");
		String HASCONTACT = request.getParameter("HASCONTACT");
		String NETWORK = request.getParameter("NETWORK");
		String PROCESS = request.getParameter("PROCESS");
		String REALTIMEPROCESS = request.getParameter("REALTIMEPROCESS");
		String ACTIVEUSER = request.getParameter("ACTIVEUSER");
		String BIOS = request.getParameter("BIOS");
		String NETWORKCARD = request.getParameter("NETWORKCARD");
	
		if (equipmentId==null) {
			
			return "0";
		}else {			
			//更改用户计算机信息
		//	EquipmentService.updateComputer(equipmentId,macAddress,equipmentModel,CPU,RAM,storage,IP,buytime,graphicCard,audioCard,networkCard,motherboard,OSName,OSID);	
			
			return "1";
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
