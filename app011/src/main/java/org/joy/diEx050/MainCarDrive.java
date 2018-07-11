package org.joy.diEx050;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class MainCarDrive {
	public static void main(String[] args) {
		/*
		 * ...현대, 기아, 대우차를 설정파일에서 참조하는 클래스만 바꿔주기만 하면 차를 바꿔서 운전할 수 있음.
		 */
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:beanDiEx050.xml");
		Car car = ctx.getBean("car", Car.class);
		car.drive();
		
		ctx.close();
	}//main()
}
