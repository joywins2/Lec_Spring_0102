package org.joy.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.joy.sample07.IF_AbstractSvc;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


//@Component
@Controller
@RequestMapping("/sample07/*")
public class SampleController07 extends org.springframework.web.servlet.mvc.AbstractController{
	
	@Resource(name="beanId_service02")
	private IF_AbstractSvc service;
	
	
	@Override
	@RequestMapping("/comp")
	protected ModelAndView handleRequestInternal(HttpServletRequest req, HttpServletResponse res)
	throws Exception{
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("sample07/componentView");
		mv.addObject("msg", "Component Annotaion Test");
		
		System.out.println("----------------");
		System.out.println(service.getClassName());
		System.out.println("----------------");
		
		return mv;
	}
	
}
