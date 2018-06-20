package org.joy.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class Sample114p_Controller {

	private static final Logger logger = 
			LoggerFactory.getLogger(Sample106p_Controller.class);

	/*
	 * ...115, 210p.
	 * ...http://localhost:8080/web/doE를 입력하면,
	 * ...http://localhost:8080/web/doF로 주소창이 바뀜.
	 * ...HTTP Status 404 - /web/WEB-INF/views/doF.jsp
	 * 
	 *...다른 경로를 호출하면서 데이터를 전달함.
	 *...redirect: 에서 ':'가 있음에 주의할 것.
	 *...RedirectAttributes클래스로 리다이렉트 시점에 데이터를 임시로 추가로 넘김.
	 *...addFlashAttribute(변수명, 값)을 이용해서 리다이렉트 시점에 임시 데이터를 전달함.
	 *... 이 경우, URI에 보이지 않게 데이터를 전달함.
	 */
	@RequestMapping("/doE")
	public String doE(RedirectAttributes rttr){
		logger.info("web.doE called but redirect to /doF.............");
		
		rttr.addFlashAttribute("msg", "This is the Message!! with redirected");
		return "redirect:/doF";
	}	
	
	@RequestMapping("/doF")
	//public void doF(String msg){
	//...http://cafe.naver.com/gugucoding/2450
	public void doF(@ModelAttribute("msg") String msg){
		System.out.println("msg = " + msg);	
		logger.info("doF called..............."+ msg);
	}
		
	
}
