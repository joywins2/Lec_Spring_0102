package org.joy.web;

import org.joy.domain.ProductVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Sample116p_Controller {

	/*
	 * ...117, 347, 400, 419, 563p.http://localhost:8080/web/doJSON
	 * ...{"name":"web.샘플상품","price":30000.0}
	 * ...Advanced Rest Client 또는 크롬 검사::Network::doJson::Headers에 보면 아래와 같음.
	 * ...Request URL:http://localhost:8080/web/doJSON
		  Request Method:GET
		  Status Code:200 OK
		  Response Headers :: Content-Type:application/json;charset=UTF-8		  
	 */
	@RequestMapping("/doJSON")
	public @ResponseBody ProductVO doJSON(){
		
		ProductVO vo = new ProductVO("web.샘플상품",30000);
		
		return vo;
		
	}
	

}
