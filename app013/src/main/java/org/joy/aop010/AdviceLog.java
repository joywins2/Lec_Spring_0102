package org.joy.aop010;

import org.aspectj.lang.ProceedingJoinPoint;

public class AdviceLog {
	
	//...ProceedingJoinPoint : 객체는 원래 실행되어야 할 대상 메소드(핵심 기능)
	public Object profile(ProceedingJoinPoint joinPoint) throws Throwable{
		
		//...실행되는 메서드 정보를 구함.
		String signStr = joinPoint.getSignature().toShortString();
		System.out.println(signStr + " is start!!");
		
		long startTime = System.currentTimeMillis();//...현재시각.
		
		try{
			//...원래 실행되야 할 메서드를 실행시킴.
			Object obj = joinPoint.proceed();
			return obj;
		}finally{
			long endTime = System.currentTimeMillis();
			System.out.println(signStr + " is finished!!!");
			System.out.println(signStr + " 경과 시간 : "+(endTime - startTime));
			System.out.println("---------------------------------------------");	
		}
	}
}
