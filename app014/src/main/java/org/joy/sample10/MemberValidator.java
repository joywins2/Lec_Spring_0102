package org.joy.sample10;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class MemberValidator implements Validator{
	@Override
	public void validate(Object target, Errors errors){
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "id", "required", "필수 입력사항 입니다.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "required", "필수 입력사항 입니다.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address.address1", "required", "필수 입력사항 입니다.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address.address2", "required", "필수 입력사항 입니다.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "required", "필수 입력사항 입니다.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "job", "required", "필수 선택사항 입니다.");
		
		Member member = (Member) target;
		if(member.getHobbys() == null || member.getHobbys().length == 0){
			errors.rejectValue("hobbys", "select");
		}
	}//validate()
	
	
	@Override
	public boolean supports(Class<?> clazz){
		return Member.class.isAssignableFrom(clazz);
	}
	
	
	
	
}
