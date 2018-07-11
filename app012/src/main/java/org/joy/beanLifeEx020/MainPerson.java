package org.joy.beanLifeEx020;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class MainPerson {
	public static void main(String[] args){
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:beanLifeEx020.xml");
		
		Person person1 = ctx.getBean("person", Person.class);
		System.out.println("이름 :"+person1.getName());
		System.out.println("나이 :"+person1.getAge());
		
		System.out.println("person1 : "+person1);
		System.out.println("--------------------");
		
		Person person2 = ctx.getBean("person", Person.class);
		person2.setName("이문세");
		person2.setAge(50);
		System.out.println("이름 :"+person2.getName());
		System.out.println("나이 :"+person2.getAge());
		System.out.println("person2 : "+person2);
		
		if(person1.equals(person2)){
			System.out.println("person1 == person2");
		}else{
			System.out.println("person1 !=person2");
		}
		
		ctx.close();
	}
}
