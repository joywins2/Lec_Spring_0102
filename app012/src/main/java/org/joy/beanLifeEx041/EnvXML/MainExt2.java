package org.joy.beanLifeEx041.EnvXML;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainExt2 {
	public static void main(String[] args){
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(ExtConfig.class);
		System.out.println("sucess!!");
		ExternalFileEx ext = ctx.getBean("ExtConfig" ,ExternalFileEx.class);
		
		System.out.println("envId : "+ext.getId());
		System.out.println("envPwd : "+ext.getPwd());
		System.out.println("extId : "+ext.getExtId());
		System.out.println("extPwd : "+ext.getExtPwd());
		
		ctx.close();
	}
}
