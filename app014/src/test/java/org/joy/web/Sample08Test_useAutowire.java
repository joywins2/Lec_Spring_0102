package org.joy.web;

import org.joy.sample08.User_useAutowire;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class Sample08Test_useAutowire {
	
	public static void main(String[] args){
		
		ApplicationContext context = new FileSystemXmlApplicationContext("/src/main/resources/sample08_useAutowire.xml");
		
		User_useAutowire user = (User_useAutowire)context.getBean("user");
		
		System.out.println(user.getShoesBrand());
	}

}
