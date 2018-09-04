package org.joy.web;

import org.joy.sample08.User_withoutAutowire;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class UserTest_withoutAutowire {
	
	public static void main(String[] args){
		
		ApplicationContext context = new FileSystemXmlApplicationContext("/src/main/resources/sample07_withoutAutowire.xml");
		
		User_withoutAutowire user = (User_withoutAutowire)context.getBean("user");
		
		System.out.println(user.getShoesBrand());
	}

}
