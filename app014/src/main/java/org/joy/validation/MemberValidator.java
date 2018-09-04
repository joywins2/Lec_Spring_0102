package org.joy.validation;



import org.joy.domain.Member;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class MemberValidator implements Validator{
	
	@Override
	public boolean supports(Class<?> arg0){
		return Member.class.isAssignableFrom(arg0);
	}
	
	@Override
	public void validate(Object obj, Errors errors){
		System.out.println("--------validate() 호출 ------------");
		Member member = (Member)obj;
		System.out.println("memberName: "+ member.getName());
		
		/*
		String memName = member.getName();		
		if(memName == null || memName.trim().isEmpty()){
			System.out.println("회원 이름을 입력하세요..");
			errors.rejectValue("name","입력시 오류 발생");
		}//if()
		ValidationUtils 클래스 사용 : */
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "name 입력 오류");
		
		
		/*
		String memId = member.getId();
		if(memId == null || memId.trim().isEmpty()){
			System.out.println("회원 아이디를 입력하세요...");
			errors.rejectValue("id","입력시 오류 발생");
		}//if()
		ValidationUtils 클래스 사용 : */
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "id", "id 입력 오류");
		
		int memNo = member.getMemNo();
		Integer Int_memNo = new Integer(memNo);
		if(memNo == 0 || Int_memNo.toString().trim().isEmpty()){
			System.out.println("주민번호를 입력하세요...");
			errors.rejectValue("memNo", "입력시 오류 발생");
		}//if()
	}
}
