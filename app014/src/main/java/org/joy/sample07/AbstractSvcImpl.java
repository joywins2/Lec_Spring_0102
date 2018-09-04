package org.joy.sample07;

import org.springframework.stereotype.Component;

@Component("beanId_service01")
public class AbstractSvcImpl implements IF_AbstractSvc {
	
	private String name = "01";
	
	@Override
	public String getClassName(){
		return "AbstractSvcImpl - " + name;
	}
}
