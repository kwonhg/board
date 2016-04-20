package first.sample.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import first.common.common.CommandMap;
import first.sample.service.Sample2Service;

/*ajax, jquery 게시판 */

@Controller
public class Sample2Controller {
	Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name="sample2Service")
	private Sample2Service sampleService;
	
	@RequestMapping(value="/sample2/openBoardList.do")
	public ModelAndView openBoardList(CommandMap commandMap) throws Exception{
	    ModelAndView mv = new ModelAndView("/sample2/boardList");
	     
	    return mv;
	}
	 
	@RequestMapping(value="/sample2/selectBoardList.do")
	public ModelAndView selectBoardList(CommandMap commandMap) throws Exception{
	    ModelAndView mv = new ModelAndView("jsonView");
	     
	    List<Map<String,Object>> list = sampleService.selectBoardList(commandMap.getMap());
	    mv.addObject("list", list);
	    if(list.size() > 0){
	        mv.addObject("TOTAL", list.get(0).get("TOTAL_COUNT"));
	    }
	    else{
	        mv.addObject("TOTAL", 0);
	    }
	     
	    return mv;
	}

	
	@RequestMapping(value="/sample2/openBoardWrite.do")
	public ModelAndView openBoardWrite(CommandMap commandMap) throws Exception{
		ModelAndView mv = new ModelAndView("/sample2/boardWrite");
		
		return mv;
	}
	
	@RequestMapping(value="/sample2/insertBoard.do")
	public ModelAndView insertBoard(CommandMap commandMap, HttpServletRequest request) throws Exception{
		ModelAndView mv = new ModelAndView("redirect:/sample2/openBoardList.do");
		
		sampleService.insertBoard(commandMap.getMap(), request);
		
		return mv;
	}
	
	@RequestMapping(value="/sample2/openBoardDetail.do")
	public ModelAndView openBoardDetail(CommandMap commandMap) throws Exception{
		ModelAndView mv = new ModelAndView("/sample2/boardDetail");
		
		Map<String,Object> map = sampleService.selectBoardDetail(commandMap.getMap());
		mv.addObject("map", map.get("map"));
		mv.addObject("list", map.get("list"));
		
		return mv;
	}
	
	@RequestMapping(value="/sample2/openBoardUpdate.do")
	public ModelAndView openBoardUpdate(CommandMap commandMap) throws Exception{
		ModelAndView mv = new ModelAndView("/sample2/boardUpdate");
		
		Map<String,Object> map = sampleService.selectBoardDetail(commandMap.getMap());
		mv.addObject("map", map.get("map"));
		mv.addObject("list", map.get("list"));
		
		return mv;
	}
	
	@RequestMapping(value="/sample2/updateBoard.do")
	public ModelAndView updateBoard(CommandMap commandMap, HttpServletRequest request) throws Exception{
		ModelAndView mv = new ModelAndView("redirect:/sample2/openBoardDetail.do");
		
		sampleService.updateBoard(commandMap.getMap(), request);
		
		mv.addObject("IDX", commandMap.get("IDX"));
		return mv;
	}
	
	@RequestMapping(value="/sample2/deleteBoard.do")
	public ModelAndView deleteBoard(CommandMap commandMap) throws Exception{
		ModelAndView mv = new ModelAndView("redirect:/sample2/openBoardList.do");
		
		sampleService.deleteBoard(commandMap.getMap());
		
		return mv;
	}
}
