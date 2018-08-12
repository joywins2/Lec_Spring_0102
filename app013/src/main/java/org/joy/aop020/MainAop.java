package org.joy.aop020;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;


public class MainAop {
	public static void main(String[] args){
		
		//AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:aopEx020.xml");		
		ApplicationContext ctx=new FileSystemXmlApplicationContext("/src/main/resources/aopEx020.xml");
		
		IF_Service service=(IF_Service)ctx.getBean("service");
		
		service.addBoard();
		service.getBoard();
	}
}
