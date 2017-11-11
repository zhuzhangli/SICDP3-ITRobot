package org.xjtusicd3.portal.controller.ITIL;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.xjtusicd3.portal.service.ProblemManagerService;
import org.xjtusicd3.portal.view.ProblemView;
import org.xjtusicd3.portal.view.Problem_AnswerView;

@Controller
/**
 * @author zzl
 */
public class ProblemManagerController {
	
	/**
	 * @return mv
	 */
	@RequestMapping(value="problemPage",method=RequestMethod.GET)
	public ModelAndView problemPage()
	{
		ModelAndView mv = new ModelAndView("problemPage");
		
		//获取问题中心没有最佳答案的问题
		List<ProblemView> problemUnresolved = ProblemManagerService.problemUnresolved();
				
		//获取问题中心有最佳答案的问题
		List<Problem_AnswerView> problemResolved = ProblemManagerService.problemResolved();
		
		mv.addObject("problemUnresolved",problemUnresolved);
		mv.addObject("unResolvedCounts", problemUnresolved.size());
		
		mv.addObject("problemResolved",problemResolved);
		mv.addObject("resolvedCounts", problemResolved.size());		
		
		
		return mv;		
	}
	
	
	/**
	 * @abstract 获取未处理问题详情
	 * @param p problemId
	 * @return mv
	 */
	@RequestMapping(value="showUnResolvedProblem",method=RequestMethod.GET)
	public ModelAndView showUnResolvedProblem(String p){
		ModelAndView mv = new ModelAndView("showUnResolvedProblem");
		
		//获取未处理问题详情
		ProblemView unResolvedProblemDetail = ProblemManagerService.getUnResolvedEventDetail(p);
		
		mv.addObject("unResolvedProblemDetail", unResolvedProblemDetail);
		return mv;
	}
	
	/**
	 * @abstract 获取已处理问题详情
	 * @param p problemId
	 * @return mv
	 */
	@RequestMapping(value="showResolvedProblem",method=RequestMethod.GET)
    public ModelAndView  showResolvedProblem(String p){
		ModelAndView mv = new ModelAndView("showResolvedProblem");
		
		//获取已处理问题详情
		Problem_AnswerView resolvedProblemDetail = ProblemManagerService.getResolvedEventDetail(p);
		
		mv.addObject("resolvedProblemDetail", resolvedProblemDetail);
		return mv;
    }
	
}
