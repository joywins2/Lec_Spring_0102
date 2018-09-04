package org.joy.web;

import org.joy.sample09.User;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/sample09/*")
public class SampleController09 {
	
	//@RequestMapping(value="/memInputPOST", method=RequestMethod.POST)
	@RequestMapping("/memInputPOST")
	public String memInputPOST(@ModelAttribute User user, BindingResult result){
		
		System.out.println("이름 :" + user.getName() + " 아이디 :"+user.getId());
		
		return "redirect:memInput";
	}
	
	//...form 태그의 path는 User객체를 참조함.
	@RequestMapping("/memInput")
	public ModelAndView memberInputGET(){
		return new ModelAndView("sample09/memberInput", "command", new User());
	}
}
