package org.joy.beanLifeEx030;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class MainLifeBean {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:beanLifeEx030.xml");
		IF_LifeBean bean = ctx.getBean("lifeBean", LifeBeanImpl.class);
		
		bean.lifeMethod();
		
		ctx.close();
	}

}
