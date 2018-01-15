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
import org.xjtusicd3.database.helper.ClassifyHelper;
import org.xjtusicd3.database.helper.UserQuestionHelper;
import org.xjtusicd3.database.model.ClassifyPersistence;
import org.xjtusicd3.portal.service.EventManagerService;
import org.xjtusicd3.portal.view.EventView;
import org.xjtusicd3.portal.view.Event_AnswerView;

import com.alibaba.fastjson.JSONObject;

@Controller
/**
 * @abstract:事件管理_eventPage.ftl
 */
public class EventManagerController{	
	/*
	 * 事件管理begin
	 */
	/**
	 * @abstract:事件管理_eventPage.ftl
	 */
	@RequestMapping(value="eventPage",method=RequestMethod.GET)
	public ModelAndView eventPage()
	{		
		ModelAndView mv = new ModelAndView("eventPage");			
		 
		//待处理事件_只显示满意度表中满意度为0的记录
		List<EventView> eventUnresolved = EventManagerService.eventUnresolved();
							
		//获取已处理事件总数
		int eventResolvedCount = UserQuestionHelper.getResolvedCount(1,0);
		
		mv.addObject("eventUnresolved",eventUnresolved);
		mv.addObject("UnresolvedCounts", eventUnresolved.size());
		mv.addObject("ResolvedCounts",eventResolvedCount);
		
		return mv;		
	}
	
	//已处理事件
	@ResponseBody
	@RequestMapping(value={"/getResolvedEvent"},method={org.springframework.web.bind.annotation.RequestMethod.POST},produces="application/json;charset=UTF-8")
	public String getResolvedEvent(HttpServletRequest request,HttpSession session){			
		String username = (String) session.getAttribute("UserName");
		JSONObject jsonObject = new JSONObject();
		if (username==null) {
			jsonObject.put("value", "0");
			String result = JsonUtil.toJsonString(jsonObject); 			
			return result;
		}else{
			// 已处理事件_只显示满意度表中满意度为1的记录
			List<Event_AnswerView> eventResolved = EventManagerService.eventResolved();			
			jsonObject.put("eventResolvedView", eventResolved);
			jsonObject.put("value", "1");
			String result = JsonUtil.toJsonString(jsonObject);
			return result;
		}
	}
	
	//待处理事件
	@ResponseBody
	@RequestMapping(value={"/getUnResolvedEvent"},method={org.springframework.web.bind.annotation.RequestMethod.POST},produces="application/json;charset=UTF-8")
	public String getUnResolvedEvent(HttpServletRequest request,HttpSession session){			
		String username = (String) session.getAttribute("UserName");
		JSONObject jsonObject = new JSONObject();
		if (username==null) {
			jsonObject.put("value", "0");
			String result = JsonUtil.toJsonString(jsonObject); 			
			return result;
		}else{
			// 待处理事件_只显示满意度表中满意度为0的记录
			List<EventView> eventUnresolved = EventManagerService.eventUnresolved();
			jsonObject.put("eventUnresolved", eventUnresolved);
			jsonObject.put("value", "1");
			String result = JsonUtil.toJsonString(jsonObject);
			return result;
		}
	}
	
	/**
	 * 忽略用户提问
	 */
	@ResponseBody
	@RequestMapping(value="/ignoreUserQuestion",method=RequestMethod.POST)
	public String ignoreUserQuestion(HttpServletRequest request,HttpSession session){	
		String username = (String) session.getAttribute("UserName");
		JSONObject jsonObject = new JSONObject();
		if (username==null) {
			jsonObject.put("value", "0");
			String result = JsonUtil.toJsonString(jsonObject); 			
			return result;
		}else{
			String userQuestionId = request.getParameter("userQuestionId");	
			//更新tbl_robotanswer表用户问题状态 -- 2是忽略
			EventManagerService.updateQuestionState(userQuestionId,2);
			jsonObject.put("value", "1");
			String result = JsonUtil.toJsonString(jsonObject);
			return result;
		}
	}
	
	/**
	 * @abstract:事件已处理_查看事件详情_showResolvedEvent.ftl
	 */
	@RequestMapping(value="showResolvedEvent",method=RequestMethod.GET)
	public ModelAndView showResolvedEvent(String q){
		ModelAndView modelAndView = new ModelAndView("showResolvedEvent");
		
		//获取已处理事件详情
		Event_AnswerView resolvedEventDetail = EventManagerService.getResolvedEventDetail(q);		
		//获取一级分类名
		List<ClassifyPersistence> classifyPersistences = ClassifyHelper.classifyName();
		if (classifyPersistences == null || classifyPersistences.size()==0) {
			return null;
		}				
		modelAndView.addObject("resolvedEventDetail", resolvedEventDetail);
		modelAndView.addObject("FirstLevel", classifyPersistences);		
		return modelAndView;
	}
	
	/**
	 * @abstract:事件待处理_查看事件详情_showUnResolvedEvent.ftl
	 */
	@RequestMapping(value="showUnResolvedEvent",method=RequestMethod.GET)
	public ModelAndView showUnResolvedEvent(String q){
		ModelAndView modelAndView = new ModelAndView("showUnResolvedEvent");
		
		//获取待处理事件详情
		EventView unResolvedEventDetail = EventManagerService.getUnResolvedEventDetail(q);
		
		modelAndView.addObject("UnResolvedEventDetail", unResolvedEventDetail);
		return modelAndView;
	}
}
