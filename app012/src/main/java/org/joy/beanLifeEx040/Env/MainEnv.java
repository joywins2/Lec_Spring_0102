package org.joy.beanLifeEx040.Env;

import java.io.IOException;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.io.support.ResourcePropertySource;

public class MainEnv {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		ConfigurableApplicationContext ctx = new GenericXmlApplicationContext();
		ConfigurableEnvironment env = ctx.getEnvironment();
		MutablePropertySources propertySources = env.getPropertySources();
		
		propertySources.addLast(new ResourcePropertySource("classpath:beanLifeEx040_Env.properties"));
		
		//env.properties 파일에서 가져온 정보를 출력
		System.out.println(env.getProperty("env.id"));
		System.out.println(env.getProperty("env.pwd"));
		
		GenericXmlApplicationContext gCtx = (GenericXmlApplicationContext)ctx;
		gCtx.load("beanLifeEx040_Env.xml");
		gCtx.refresh();
		
		EnvironmentEx envEx = gCtx.getBean("env", EnvironmentEx.class);
		System.out.println("env Id: " + envEx.getId());
		System.out.println("env Pwd: "+ envEx.getPwd());
		
		gCtx.close();
		ctx.close();		
	}//main
}
