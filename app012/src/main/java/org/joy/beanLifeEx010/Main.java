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
		
외부파일을 이용한 Bean 설정 :
		EnvironmentAware 인터페이스 이용하여 Environment 객체 얻기 :
			Context --> getEnvironment() --> Environment 객체 얻기.
			Environment : getPropertySources 객체를 얻기.
			PropertySources : 외부 프로퍼티를 추가(addLast)하거나 추출(getProperty)함.
			
		XML 파일에 외부프로퍼티 파일을 명시하는 방법 :
			ex) org.joy.beanLifeEx04.EnvXML
		
		Java 파일에 외부프로퍼티 파일을 명시하는 방법 :
			ex) /app012/src/main/java/org/joy/beanLifeEx04/EnvXML/ExtConfig.java
			
		XML 에 프로파일(profile)을 이용한 빈 설정 :
			ex) org.joy.beanLifeEx042.Profile
			
IoC(Inversion of Control : 제어의 역전) :
	IoC 는 일반적인 프로그램의 흐름과 다름.
		모든 제어 권한을 자신이 아닌 다른 대상(컨테이너)에게 위임함.
		객체가 자신이 사용할 객체를 생성하거나 선택하지 않음.
		Object 는 자신이 어떻게 생성되고 사용되는지 알 수 없음.
	
	DI 는 IoC 구현 방법의 한가지임.
		DL (Dependency Lookup) : 의존성 검색 방식.
			저장소에 저장된 Bean 에 접근하기 위해 개발자들이 컨테이너에서 제공하는 API 를 이용하여 Bean 을 검색함.
			컨테이너에 대한 종속성이 커짐.
		DI (Dependency Injection) : 의존성 주입 방식.
			컨테이너에 대한 종속성이 큰 DL 방식 대신, DI 를 사용함.
			각 클래스 사이의 의존 관계를 빈설정(Bean Definition) 정보를 바탕으로 컨테이너가 자동으로 연결해줌.
				setter Injection,
				constructor Injection.
		
		일반적인 프로그램의 흐름 구조 :
			main() 과 같은 시작 프로그램이 사용할 오브젝트를 결정, 생성, 호출의 반복.
			모든 종류의 작업을 사용하는 쪽에서 제어함.
			
			
	IoC 용어 :
		Bean : 스프링에서는 제어권을 가지고 직접 만들고 관계를 부여하는 오브젝트.
				자바빈, EJB 빈과 비슷한 오브젝트 단위의 어플리케이션 컴포넌트를 의미함.
				스프링 빈은 스프링 컨테이너가 생성, 관계설정, 사용등을 제어해주는 오브젝트임.
				
		Bean Factory : 스프링의 IoC 를 담당하는 핵심 컨테이너.
				빈을 등록/생성/조회/반환/관리하는 기능.
				Bean Factory 를 바로 사용하지 않고, 이를 확장한 Application Context 를 이용함.
				Bean Factory 는 bean factory 가 구현하는 Interface 임(getBean()... 메서드가 정의).
				
		Application Context : bean factory 를 확장한 IoC 컨테이너.
				빈을 등록/생성/조회/반환/관리하는 기능 + 스프링의 부가서비스 제공.
				ApplicationContext 는 인터페이스이며 BeanFactory 를 상속함.
		
			Configuration Metadata : 설정정보/ 설정 메타정보.
					Application Context 혹은 Bean Factory 가 IoC 를 적용하기 위해 사용하는 메타 정보임.
					스프링의 설정 정보는 컨테이너에 어떤 기능을 설정하거나 조정하는 경우에 사용되기도 하고,
					주로 bean 을 생성/ 구현하는 용도로 사용함.
					
			Container (IoC Container) :
				IoC 방식으로 bean 을 관리한다는 의미에서 bean factory 또는 application context 를 가리킴.
				Spring Container = Application Context.
				Application Context 는 그 자체로는 Application Context 인터페이스를 구현한 오브젝트를
				의미함.
				하나의 어플리케이션에서는 보통 여러개의 Application Context Object 가 만들어 짐.
				객체를 관리하는 컨테이너로서, 이를 통칭해서 Spring Container 라고 함.
				
스프링 프레임워크 : IoC Container, Application Context 를 포함해서 스프링에서 제공하는 모든 기능을 통칭함.
		
*/









