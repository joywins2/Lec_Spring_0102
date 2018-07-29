package org.joy.aop;
//...473p.

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


/*
 * ...474p.
 * ...@Component : 스프링의 빈으로 인식하기 위한 설정.
 * ...@Aspect : AOP기능을 하는 클래스 선언에 추가함.
 */
@Component
@Aspect
public class SampleAdvice {

  private static final Logger logger = LoggerFactory.getLogger(SampleAdvice.class);

  /*
   * ...474p.@Before : @Before가 붙은 메서드를 target메서드보다 먼저 실행시킴.
   * ...execution~구문 : Pointcut을 지정하며 AspectJ언어 문법을 사용함.
   * ...'org.joy.service.MsgService~'로 시작하는 모든 클래스의 '*'(모든)메서드를 지정함.
   *    설정이 올바르게 되면 메서드 앞에 화살표 아이콘이 적용됨.
   */
  @Before("execution(* org.joy.service.MsgService*.*(..))")
  public void startLog() {

    logger.info("----------------------------");
    logger.info("S.startLog.----------------------------");
    //logger.info(Arrays.toString(jp.getArgs()));  
    logger.info("E.startLog.----------------------------");
  }    
  
}
