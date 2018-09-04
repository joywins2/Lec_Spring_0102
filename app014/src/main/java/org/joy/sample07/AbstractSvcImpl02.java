package org.joy.sample07;

import org.springframework.stereotype.Component;

@Component("beanId_service02")
public class AbstractSvcImpl02 implements IF_AbstractSvc {
	private String name = "02";
	
	@Override
	public String getClassName(){
		return "AbstractSvcImpl - "+name;
	}
}
