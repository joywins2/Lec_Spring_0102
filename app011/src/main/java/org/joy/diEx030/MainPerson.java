package org.joy.diEx030;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class MainPerson {

	public static void main(String[] args) {
		String confLoc = "classpath:beanDiEx030.xml";
		
		AbstractApplicationContext ctx = new GenericXmlApplicationContext(confLoc);
		PersonInfo personInfo = ctx.getBean("personInfo",PersonInfo.class);
		personInfo.getPersonInfo();
		
		Person person = ctx.getBean("person2",Person.class );
		personInfo.setPerson(person);
		personInfo.getPersonInfo();
		
		ctx.close();//...사용후 반납할 것.
	}
}
