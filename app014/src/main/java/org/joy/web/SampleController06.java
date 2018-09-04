package org.joy.web;

import javax.validation.Valid;

import org.joy.domain.Member;
import org.joy.validation.MemberValidator;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/valid/*")
public class SampleController06 {

	@RequestMapping("/memberInputGET")
	public String memberInputGET(){
		return "sample06/memberInput";
	}
	
	@RequestMapping("/memberInputOk")
	public String memberInputOk(@ModelAttribute("member")  Member member, //...## @Valid와 @InitBinder : @Valid Member member,  
	                      BindingResult result){
		
		String viewName = "sample06/memberInputOk";	
		
	
		/*...## @Valid와 @InitBinder : 
		 *   validate()를 직접 호출하지 않고 스프링 프레임 워크에서 호출하는 방법으로 아래 주석 처리함.
		 */		
		//Validator 생성
		MemberValidator mValidator = new MemberValidator();
		mValidator.validate(member, result);
		
		if(result.hasErrors()){
			System.out.println("result.hasErrors() :" + result.hasErrors());
			viewName="sample06/memberInput"; //...jsp파일 경로명.
			//return "redirect:memberInputGET"; //...매핑된 요청경로명.
		}
		
		System.out.println("viewName :" + viewName);
		return viewName;
	}
	

	@RequestMapping("/memberInputAnnotationGET")
	public String memberInputAnnotationGET(){
		return "sample06/memInputValidAnnotation";
	}
	
	//...@Valid와 @InitBinder 사용.
	@RequestMapping("/memberInputAnnotationOk")
	public String memberInputAnnotationOk(@ModelAttribute("member")  @Valid Member member,   
	                      BindingResult result){
		
		String viewName = "sample06/memberInputOk";	
		
	
		/*...@Valid와 @InitBinder : 
		 *   validate()를 직접 호출하지 않고 스프링 프레임 워크에서 호출하는 방법으로 아래 주석 처리함.
		     Validator 대신 @InitBinder 사용.
		MemberValidator mValidator = new MemberValidator();
		mValidator.validate(member, result);
		*/
		
		if(result.hasErrors()){
			System.out.println("result.hasErrors() :" + result.hasErrors());
			viewName="sample06/memberInput"; //...jsp파일 경로명.
			//return "redirect:memberInputGET"; //...매핑된 요청경로명.
		}
		
		System.out.println("viewName :" + viewName);
		return viewName;
	}
	
		
	
	@InitBinder
	protected void initBinder(WebDataBinder binder){
		binder.setValidator(new MemberValidator());
	}
	
	
}
