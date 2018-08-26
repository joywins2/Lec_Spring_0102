package org.joy.web;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.joy.domain.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping("/sample02/*")
public class SampleController02 {
	
	private static final Logger logger = LoggerFactory.getLogger(SampleController02.class);
		
	/*
	 * ...HttpServletRequest를 이용하면 파라미터 없이도 페이지가 열림.
	 * ...http://localhost:8080/web/sample02/testRequest?name=이수징&id=yes
	 */
	@RequestMapping("/testRequest")
	public String testRequest(HttpServletRequest httpServletRequest, 
					   					 Model model){
		
		String name = httpServletRequest.getParameter("name");
		String id = httpServletRequest.getParameter("id");
		
		model.addAttribute("name", name);
		model.addAttribute("id",id);
		
		return "sample02/testRequest";
	}//test()
	
	/*
	 * ...http://localhost:8080/web/sample02/testRequestParam로 파라미터 없이 요청하면 400 오류가 발생함.
	 *    http://localhost:8080/web/sample02/testRequestParam?id=이수징&name=yes 정상작동.
	 *    
	 * ...testRequest 메서드와 다르게 @RequestParam을 사용해서 요청된 파라미터를 변수에 바로 담음.
		  @RequestParam("id")로 파라미터를 받고 선언한 변수에 String id 바로 넣을 수 있음.
	 */
	@RequestMapping("/testRequestParam")
	public String testnRequestParam(@RequestParam("id") String id, 
			                                 @RequestParam("name") String name, 
			                                 Model model){
		model.addAttribute("id", id);
		model.addAttribute("name",name);
		
		return "sample02/testRequestParam";
	}//check()
	
	/*
	 * ...http://localhost:8080/web/sample02/testRequestParam2?id=yes&name=이수징&address=명당&email=yes@yes.com
	 * ...http://localhost:8080/web/sample02/testRequestParam2?name=이수징&id=yes&address=명당&email=yes@yes.com

	public String testnRequestParam2(@RequestParam("name") String name, 
			                 @RequestParam("id") String id,
			                 @RequestParam("address") String address, 
			                 @RequestParam("email") String email, 
			                 Model model){
		Person person = new Person();
		person.setName(name);
		person.setId(id);
		person.setAddress(address);
		person.setEmail(email);
		
		model.addAttribute("person", person);
					                 
		@RequestParam을 이용하여 파라미터를 각각 받는 대신,
		Model 필요없이 Person Domain 객체를 이용하여 한번에  
		파라미터를 받을 수 있어서 같은 결과임.
	 */
	@RequestMapping("testRequestParam2")
	public String testnRequestParam2(Person person){

		return "sample02/testRequestParam2";
	}
	
	/*
	 * ...http://localhost:8080/web/sample02/testKoreanPathVariable/이수징
	 * 
	 	  한글(UTF-8) 설정 방법 :
	 	  1. STS 내의 Tomcat :
	 	     /Servers/Tomcat v9.0 Server at localhost-config/server.xml
	 	        <Connector URIEncoding="UTF-8" 
	 	                   connectionTimeout="20000" 
	 	                   port="8080" 
	 	                   protocol="HTTP/1.1" redirectPort="8443"/>
	 	        
                <Connector URIEncoding="UTF-8" 
                           port="8009" 
                           protocol="AJP/1.3"  redirectPort="8443"/>
	 	                   
	 	  2. Controller 내의 메서드 :
			//URL 한글 입력 처리
		    //...ISO-8859(기본값) → UTF-8 → ISO-8859(기본값으로 디코딩) → UTF-8(스프링설정인코딩) 
			URLDecoder.decode(URLDecoder.decode(personName,"8859_1"),"utf-8");
			
	 	  3. JSP 내 인코딩 선언 :
	 	     <%@ page language="java" contentType="text/html; charset=UTF-8"
                      pageEncoding="UTF-8"%>
             <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
             <html>
             <head>
             <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 	 */
	
	@RequestMapping("/testKoreanPathVariable/{personName}")
	public String testKoreanPathVariable(@PathVariable String personName, 
			                Model model) throws UnsupportedEncodingException{
			//...URL 한글 입력 처리
		    //   ISO-8859(기본값) → UTF-8 → ISO-8859(기본값으로 디코딩) → UTF-8(스프링설정인코딩) 
			URLDecoder.decode(URLDecoder.decode(personName,"8859_1"),"utf-8");
			
			System.out.println(personName);
			
			model.addAttribute("personInfo", personName);
			
			return("sample02/testKoreanPathVariable");
			
	}//getPerson()
	
	//...http://localhost:8080/web/sample02/testPathVariable/one
	@RequestMapping("/testPathVariable/{var}")
	public String testPathVariable(@PathVariable String var){
		
		String viewName = "";
		
		if(var.equals("one")){
			viewName = "sample02/page01";
			
		}else if(var.equals("two")){
			viewName = "sample02/page02";
		}
		
		return viewName;
	}
	
	/*
	 * ...GET방식 컨트롤러
	 *    @param key1
	 *    @param key2  
	 * ...http://localhost:8080/web/sample02/testGet
	 * ...http://localhost:8080/web/sample02/testGet?key1=이수징&key2=yes
	 */
	@RequestMapping("/testGet")
	public void testGet(String key1, String key2){
		System.out.println("key1 : "+key1);
		System.out.println("key2 : "+key2);
	}
	
	//...http://localhost:8080/web/sample02/testPathVariable2/하하/이수징/좋아
	@RequestMapping("/testPathVariable2/{key1}/{key2}/{key3}")
	public void testPathVariable2(@PathVariable String key1,
			                      @PathVariable String key2,
			                      @PathVariable String key3){
		System.out.println("key1 :"+key1);
		System.out.println("key2 :"+key2);
		System.out.println("key3 :"+key3);
	}
	
	
}
