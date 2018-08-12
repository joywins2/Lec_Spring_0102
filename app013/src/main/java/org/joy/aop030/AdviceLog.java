package org.joy.aop030;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

public class AdviceLog {
	
	//ProceedingJoinPoint 객체는 원래 실행되어야 할 대상 메소드(핵심 기능)
	public Object profile(ProceedingJoinPoint joinPoint) throws Throwable{
		String signStr = joinPoint.getSignature().toShortString();
		System.out.println(signStr + " is start!!");
		long startTime = System.currentTimeMillis();
		
		try{
			Object obj = joinPoint.proceed();
			return obj;
		}finally{
			long endTime = System.currentTimeMillis();
			System.out.println(signStr + " is finished!!!");
			System.out.println(signStr + " 경과 시간 : "+(endTime - startTime));
			System.out.println("---------------------------------------------");		
		}
	}
	
	public void beforeAdvice(JoinPoint joinPoint){
		System.out.println("beforeAdvice()........");
	}
	
	public void afterReturningAdvice(){
		System.out.println("afterReturningAdvice().........");
	}
	
	public void afterThrowingAdvice(){
		System.out.println("afterThrowingAdvice()..........");
	}
	
	public void afterAdvice(){
		System.out.println("afterAdvice().........");
	}
}
