package org.joy.aop020;

import org.aspectj.lang.ProceedingJoinPoint;

//...대상메소드를 실행할 수 있는 기능을 가진 ProceedingJoinPoint를 전달 받는다.
public class TimeCheckAdvice {
	public Object check(ProceedingJoinPoint joinPoint) throws Throwable{
		System.out.println("method start.....");
		
		//proceed()를 호출하지 않으면 비즈니스 로직을 수행 할 수 없다.
		Object returnValue = joinPoint.proceed();
		
		System.out.println("method end.....");
		System.out.println("---------------------------------------------");
		
		//대상메소드의 실행결과 얻어진 반환 값을 반환 해야 한다.
		return returnValue;
	}
}
