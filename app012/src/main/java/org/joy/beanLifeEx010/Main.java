package org.joy.beanLifeEx010;

import org.springframework.context.support.GenericXmlApplicationContext;

public class Main {
	public static void main(String[] args) {
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
		ctx.load("classpath:beanLifeEx010.xml");
		ctx.refresh();
		ctx.close();
	}
}

/*	
Bean 의 생명주기 :
	생성 :
		ctx.refresh() 에서 생성.
	초기화 및 소멸 처리 :
		InitializingBean, DisposableBean 인터페이스는 따로 구현하거나 동시에 구현해도 됨.
		InitializingBean 인터페이스.
			구현할 메서드 :
				public void afterPropertiesSet() throws Exception.
				ctx.refresh() 이후 호출됨.
		DisposableBean 인터페이스.
			스프링 컨테이너가 소멸할 때 ctx.close() 에서 빈도 소멸됨.
				public void destroy() throws Exception.
		어노테이션 활용 :
			@PostConstruct
			public void initMethod()...
			
			@PreDestroy
			public void destroy()...

Bean 의 범위(scope) :
	singleton : 기본값, static 같음.
				스프링 컨테이너에 의해 1개의 Bean 객체만 생성함.
	prototype : 빈을 사용할 때 마다(getBean) 객체를 생성함.
	
	request : HTTP Request 마다 Bean 객체를 생성함.
				(WebApplicationContext 에서만 해당함)
	session : HTTP Session 마다 Bean 객체를 생성함.
				(WebApplicationContext 에서만 해당함)
	global-session : 글로벌 HTTP 요청마다 Bean 객체를 생성함.
				(WebApplicationContext 에서만 해당함)
				
사용자 초기화 메서드/ 사용자 소멸 메서드 :
	사용자 초기화 메서드 : 
		설정파일에 init-method : Bean 태그의 속성으로 지정함.
	사용자 소멸 메서드 :
		설정파일에 destroy-method : Bean 태그의 속성으로 지정함.
		
	BeanNameAware 인터페이스 :
		Bean 객체가 자기 자신의 이름을 알아야 할 경우 사용됨.
		클래스가 BeanNameAware 인터페이스를 구현한 경우,
		컨테이너는 setBeanName() 메서드를 호출해서 Bean 객체의 이름을 전달함.
		setBeanName(String arg) : 객체가 생성될 때 해당 객체의 id 또는 name 값을 주입받음.
*/