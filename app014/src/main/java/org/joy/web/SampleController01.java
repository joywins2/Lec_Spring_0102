package org.joy.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/sample01/*")
public class SampleController01 {
	
	@RequestMapping("/memberInput")	
	public String memberInputGET(){
		
		return "sample01/memberInput";
	}
	
	@RequestMapping("/memberInfo")
	public String memInfo(Model model){
		
		model.addAttribute("name", "이수징");
		
		return "sample01/memberInfo";
	}
	
	@RequestMapping("/model")
	public String testModel(Model model){
		
		model.addAttribute("address","내마음이 편한 곳");
		
		return "/sample01/model";
	}
	
	@RequestMapping("/modelandview")
	public ModelAndView modelAndVeiw(){
		ModelAndView mv = new ModelAndView();
		
		mv.addObject("email","yes@yes.com");
		mv.setViewName("/sample01/modelAndVeiw");
		
		return mv;
	}		

}
