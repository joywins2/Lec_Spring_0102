package org.joy.web;

import javax.servlet.http.HttpServletRequest;

import org.joy.domain.Person2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/sample03/*")
public class SampleController03 {
	
	@RequestMapping("/inputPersonGET")
	public String inputGET(){
		return "sample03/inputPersonGET";
	}
	
	@RequestMapping("/inputPersonPOST")
	public String inputPOST(){
		return "sample03/inputPersonPOST";
	}
	
	
	@RequestMapping(value="/personInfoGET", method=RequestMethod.GET)
	public String person(HttpServletRequest request, Model model){
		
		String name = request.getParameter("name");
		String id = request.getParameter("id");
		
		System.out.println("personGET name : "+name);
		System.out.println("personGET id : "+id);
		
		model.addAttribute("name",name);
		model.addAttribute("id", id);
		
		
		return "sample03/personInfoGET";
	}
	
//	@RequestMapping(value="/personPOST", method=RequestMethod.POST)
//	public String person(HttpServletRequest request, Model model){
//		
//		String name = request.getParameter("name");
//		String id = request.getParameter("id");
//		
//		System.out.println("name : "+name);
//		System.out.println("id : "+id);
//		
//		model.addAttribute("name",name);
//		model.addAttribute("id", id);
//		
//		return "sample03/personInfoPOST";
//	}
	
	
	//...GET 방식의 person 메서드가 있으므로 매개변수 조정한 오버로딩 사용.
	@RequestMapping(value="/personInfoPOST", method=RequestMethod.POST)
	public ModelAndView person(HttpServletRequest request){
		
		String name = request.getParameter("name");
		String id = request.getParameter("id");
		
		System.out.println("personPOST name : "+name);
		System.out.println("personPOST id : "+id);
		
		ModelAndView modelView = new ModelAndView();
		
		modelView.setViewName("sample03/personInfoPOST");
		modelView.addObject("name",name);
		modelView.addObject("id",id);
		
//		model.addAttribute("name",name);
//		model.addAttribute("id", id);
		
		return modelView;
	}	
	
}
