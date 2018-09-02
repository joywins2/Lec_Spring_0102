package org.joy.web2;

import org.joy.domain.Person2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/sample03A/*")
public class SampleController03A {
	
	
	/*
	 * ...Controller를 2개의 패키지에 배치할 경우 :
	 *    패키지 이름이 다른 것은 영향이 없음.
	 *    /Servers/Tomcat v9.0 Server at localhost-config/server.xml와
			<Context docBase="app014" path="/web" reloadable="true" source="org.eclipse.jst.jee.server:app014"/>
      		<Context docBase="app014" path="/web2" reloadable="true" source="org.eclipse.jst.jee.server:app014"/>
         
	 *    /app014/src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml에서
			<context:component-scan base-package="org.joy.web" />
			<context:component-scan base-package="org.joy.web2" />
	 *    Context를 같은 docBase이면서 별개의 path로 각각 선언해주고,
	 *    @RequestMapping("/서로다른경로명") 과
	 *    서로 다른 메서드명으로 정의해주면 오류가 발생하지 않음.
	 */
	@RequestMapping("/inputPerson2GET")
	public String inputPerson2(){
		
		return "sample03A/inputPerson2GET";
	}
	
	/*
	 * ...@ModelAttribute를 사용하면 jsp의 input 항목의 순서가
	 *    Person2와 달라도 input 항목의 name 속성값만 같으면 
	 *    자동으로 값을 담아줌.
	 */
	@RequestMapping("/person2InfoPOST")
	public String userInfo(@ModelAttribute("userInfo") Person2 person2){
		return "sample03A/person2InfoPOST";		
	}
	
}
