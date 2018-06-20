package org.joy.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Sample108p_Controller {

	private static final Logger logger = 
			LoggerFactory.getLogger(Sample106p_Controller.class);

/***
 * ...108p.http://localhost:8080/web/doC?msg=Hi5
	   @ModelAttribute 는 자동으로 해당 객체를 뷰까지 전달함.
	   result.jsp
	   zweb.Hello "Hi5"
 */	
	@RequestMapping("doC")
	public String doC( @ModelAttribute("msg") String msg){
		
		logger.info("zweb.doC called..............................");
		
		return "result";
		
	}
	
}
