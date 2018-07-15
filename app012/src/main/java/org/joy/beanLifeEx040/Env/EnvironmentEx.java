package org.joy.beanLifeEx040.Env;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;

public class EnvironmentEx implements EnvironmentAware, InitializingBean, DisposableBean {
	
	private Environment env;
	private String id;
	private String pwd;
	
	@Override
	public void setEnvironment(Environment env){
		System.out.println("setEnvrionment() 호출");
		this.env = env;
	}
	
	@Override
	public void afterPropertiesSet() throws Exception{
		System.out.println("afterPropertiesSet() 호출");
		setId(env.getProperty("env.id"));
		setPwd(env.getProperty("env.pwd"));
	}
	
	@Override
	public void destroy() throws Exception{
		System.out.println("destroy() 호출");
	}
	
	//setter, getter
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
	
}
