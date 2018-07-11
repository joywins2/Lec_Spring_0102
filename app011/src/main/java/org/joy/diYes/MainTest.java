package org.joy.diYes;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class MainTest {

	public static void main(String[] args) {
		
		String confLoc = "classpath:beanDiYes.xml";
		AbstractApplicationContext ctx = new GenericXmlApplicationContext(confLoc);
		TestDAO testDao = ctx.getBean("TestDAOImp",TestDAOImp.class);
		
		
		System.out.println("===========");
		//TestDAO testDao = new TestDAOImp(); 코드를 사용하지 않았음에도 불구하고
		testDao.printMsg();
		System.out.println("===========");
	}
}
