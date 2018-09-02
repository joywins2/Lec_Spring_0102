package org.joy.web;

import javax.servlet.http.HttpServletRequest;

import org.joy.domain.UserVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/sample04/*")
public class SampleController04 {
	
	//@ModelAttribute 어노테이션은 뷰에 모델 전달
	@ModelAttribute("mdaCarMaker")
	public String[] CarMaker(){
		return new String[]{"현대-아이오닉","기아-모닝","쉬보레-스파크","쌍용-티볼리"};
	}
	
	
	@ModelAttribute("mdaUserVO")
	public UserVO mdaUserVO(HttpServletRequest request){
		System.out.println("@ModelAttribute().........");
		
		if(request.getMethod().equalsIgnoreCase("GET")){
			System.out.println("@ModelAttribute(): GET.......");
			
			UserVO userVO = new UserVO();
			System.out.println("객체 생성....");
			
			userVO.setUserName("이름이 모에요?");
			userVO.setAddress("집이 어디에요?");
			userVO.setCar("차이름은 모에요?");
			
			return userVO;
			
		}else{
			System.out.println("@ModelAttribute : POST...");
			return new UserVO();
			
		}
	}
	
	
	@RequestMapping(value = "/testMdA", method=RequestMethod.GET)
	public String testModelAttribute(){
		System.out.println("-----------testModelAttribute-----------");
		return "sample04/testMdA";
	}
	
	
	@RequestMapping(value = "/inputMdaUserVO", method=RequestMethod.GET)
	public String userFormGET(){
		System.out.println("-----------userForm-----------");
		return "sample04/inputMdaUserVO";
	}
	
	/*
	 * .../userInfoPost경로를 처리하는 userInfoPost 메서드 이전에
	 *    @ModelAttribute("mdaUserVO") UserVO userVO를 처리하는 
	 *    mdaUserVO 메서드부터 먼저 실행하고 userInfoPost 메서드를 처리함.
	 */
	@RequestMapping(value="/userInfoPost", method=RequestMethod.POST)
	public ModelAndView userInfoPost(@ModelAttribute("mdaUserVO") UserVO userVO, 
			                     Model model){
		System.out.println("-----userSave--------------");
		System.out.println("user : "+userVO.toString());
		
		model.addAttribute("msg" , "회원 정보 출력 성공");
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("sample04/userInfoPost");
		modelAndView.addObject("userVO", userVO);	
		
		return modelAndView;
	}//userSave()
	
	@RequestMapping(value="/testExtMdMap")
	public Model testExtendedModelMap(){
		System.out.println("---------userView---------");
		
		Model model = new ExtendedModelMap();
		model.addAttribute("msg", "ExtendedModelMap으로 보이는 jsp 파일 위치에 주의하세요!");
		
		return model;
	}//userView()
	
}
