package org.joy.diEx010;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;


public class Main {

	public static void main(String[] args) {
		//...before :
//		MyGetSum myGetSum = new MyGetSum();		
//		myGetSum.setGetSum(new GetSum());
//		
//		myGetSum.setA(10);
//		myGetSum.setB(100);
//		myGetSum.sum();
		
		//...after :
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:beanDiEx010.xml");
		MyGetSum myGetSum = ctx.getBean("myGetSum", MyGetSum.class);
		
		myGetSum.sum();	
	}

}
