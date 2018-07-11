package org.joy.diNo;

import org.joy.diEx090.Player;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class Main {
	

	public static void main(String[] args) {
		
		// TODO Auto-generated method stub
		/*...AA has BB : AA 는 BB 를 의존한다.
		 *   의존도가 높음.
		 *   예) 메소드명이나 변수가 바뀐다면...? 
		 */
/*		BB b = new BB();				
		b.bb();		*/
		

		MyGetSum myGetSum = new MyGetSum();		
		myGetSum.setGetsum(new GetSum());
		
		myGetSum.setA(10);
		myGetSum.setB(100);
				
	}

}


/*
스프링 특징
	가벼운 프레임워크.
	IoC : 직접 만들지 않고 컨테이너에서 만들어서 주입해줌.
		  객체의 생명주기(생성, 소멸)를 관리함.

	
AOP(Aspect Oriented Programming)
	공통사항(트랜잭션, 로깅, 보안)을 한곳에서 모아놓고 적용함.
	
POJO(Plain Old Java Object) 지원 :
	상속받지 않는 자바 객체를 사용함.
	
다양한 영속성관련 API 를 제공함.
	JDBC, IBatis, MyBatis, JPA, Hibernate.

	
DI(Dependency Injection, 의존성주입)
	A ---> B : A가 B를 의존함.
	객체 사이의 의존 관계를 객체 자신이 아닌 스프링컨테이너가
	정해줌 : 
		설정파일(bean.xml)이나 어노테이션을 이용하여 의존관계를
		설정함.
		완전히 같지는 않지만 콜백처럼 필요할때 불려짐???
	예제 : joy.di01
	의존하는 객체에 대한 획득을 클래스가 직접 생성하지 않고
	스프링 컨테이너가 제공(주입)해줌.
	new 할 필요가 없음.
	의존성을 낮춤.
	의존성 주입 방법 :
		setter 메서드를 이용.
		Constructor 이용.
	의존성 주입 장점 :
		인터페이스의 장점 이해 :
			대규모 프로젝트 작업시 장점이 있음.
			ex) /app011/src/main/java/org/joy/diEx050/MainCarDrive.java
	의존관계 설정 방법 :
		XML 파일을 이용.
		Java 파일을 이용(어노테이션, 메타데이타 이용).
			어노테이션 : JDK5 부터 등장함.
			ex) exem.java34.annotation
			@Configuration : 클래스 앞에 사용.
				스프링 설정 클래스로 지정함.
			@Bean : 메서드 앞에 사용.
				객체를 생성함.
		XML 과 Java 파일을 혼용.
			ex) org.joy.diEx090
		
Spring 컨테이너 생명 주기.
	스프링 컨테이너 생성 
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
	설정 :
		ctx.load("classpath:beanDiEx.xml");
		ctx.refresh();//...load 하고 반드시 초기화해줌.
		//GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("classpath:beanDiEx.xml");
	사용 :
		Player player1 = ctx.getBean("player1", Player.class);
	종료 :
		ctx.close();
	
	
*/