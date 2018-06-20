package org.joy.web;

import org.joy.domain.ProductVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Sample110p_Controller {

	private static final Logger logger = 
			LoggerFactory.getLogger(Sample106p_Controller.class);


	//...113p. http://localhost:8080/web/doD
	@RequestMapping("/doD")
	/*
	 * ...112p. Model 클래스 : 스프링 MVC 가 기본으로 제공함.
	 * 	  뷰에 원하는 데이터를 담아서 전달하는 상자 역할. 
	 */
	public String doD(Model model){
		
		//make sample data
		ProductVO product = new ProductVO("Sample Product 입니다.", 10000);
		System.out.println("product : " + product.toString());
		
		logger.info("doD");
		
	
		//...addAttribute(객체)처럼 객체이름을 별도로 지정하지 않은 경우, 자동으로 저장되는
		//...객체 클래스명의 앞글자를 소문자로 처리한 이름이 객체이름이 됨.
		//...여기서는 productVO가 객체명이 됨.
		model.addAttribute(product);
		
		return "productDetail";
		
	}	
	
}
