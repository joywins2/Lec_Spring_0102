package org.joy.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/sample05/*")
public class SampleController05 {
	
	/**
	 * redirect: 키를 이용 페이지 이동
	 * */
	
	@RequestMapping("/loginIdGET")
	public String loginform(){
		return "sample05/loginIdGET";
	}	
	
	@RequestMapping("/submitId")
	public String memberRedirect(HttpServletRequest req, Model model){
		String id = req.getParameter("id");
		
		if(id.equals("test")){
			return "redirect:submitIdOk";
		}
		
		return "redirect:submitIdFail";
	}//memberRedirect()
	
	
	@RequestMapping("/submitIdOk")
	public String memberOk(){
		return "sample05/submitIdOk";
	}
	
	@RequestMapping("/submitIdFail")
	public String memberFail(){
		
		return "sample05/submitIdFail";
	}
	
	//...보안에 취약하여 사용하지 않음.
	@RequestMapping("/decommendedURL")
	public String decommendedURL01(Model model){
		return "redirect:http://localhost:8080/web/decommendedURL.jsp";
		
	}
	
}
