package org.joy.beanLifeEx042.Profile;

import java.util.Scanner;

import org.springframework.context.support.GenericXmlApplicationContext;

public class MainProfile {
	
	public static void main(String[] args){
		String profile=null;
		Scanner scanner = new Scanner(System.in);
		String str = scanner.next();
		
		if(str.equals("develop")){
			profile="develop";
		}else if(str.equals("service")){
			profile="service";
		}
		
		scanner.close();
		
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
		ctx.getEnvironment().setActiveProfiles(profile); //설정파일을 결정
		//ctx.load("dev.xml","service.xml");
		ctx.load("beanLifeEx042_ProfileDev.xml","beanLifeEx042_ProfileService.xml");
		
		ProfileEx prof = ctx.getBean("profileEx", ProfileEx.class);
		System.out.println("ip : " + prof.getIp());
		System.out.println("port : "+prof.getPort());
		
		ctx.close();
	}
}
