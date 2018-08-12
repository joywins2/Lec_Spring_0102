package org.joy.aop040;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class AdviceLog {
	
	
	//@Pointcut("within(org.joy.aop040.*)")
	//@Pointcut("execution(* org.joy.aop040.*.*())")
	@Pointcut("bean(student)")
	private void pointcutMethod(){}
	
	
	@Around("pointcutMethod()")
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
	}//profile()
	
	
	//@Before("within(org.joy.aop040.*)")
	@Before("pointcutMethod()")
	public void beforeAdvice(){
		System.out.println("beforeAdvice()........");
	}
	
//	public void afterReturningAdvice(){
//		System.out.println("afterReturningAdvice().........");
//	}
//	
//	public void afterThrowingAdvice(){
//		System.out.println("afterThrowingAdvice()..........");
//	}
//	
//	public void afterAdvice(){
//		System.out.println("afterAdvice().........");
//	}
}
