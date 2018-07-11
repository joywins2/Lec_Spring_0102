package org.joy.diEx040;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class MainMyBatisController {

	public static void main(String[] args) {
		
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:beanDiEx040.xml");
		MyBatisServiceImpl service =ctx.getBean("service",MyBatisServiceImpl.class);
		
		service.myBatisTest();
		
		ctx.close();
	}

}
