package org.joy.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Sample106p_Controller {

	private static final Logger logger = 
			LoggerFactory.getLogger(Sample106p_Controller.class);
	
	//...106p.http://localhost:8080/web/doA
	//...HTTP Status 404 - /web/WEB-INF/views/doA.jsp
	@RequestMapping("doA")
	public void doA(){
		
		logger.info("web.doA called....................");
		
	}

	//...106p.http://localhost:8080/web/doB
	//...HTTP Status 404 - /web/WEB-INF/views/doB.jsp
	@RequestMapping("doB")
	public void doB(){
		
		logger.info("web.doB called....................");
		
	}	
	

}
