package org.joy.diEx070;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainBaseBall {
	public static void main(String [] args){
/*
		<!-- 출처: http://wonsama.tistory.com/379 [WWW : World Wide Wonsama] -->
		<dependency>
			<groupId>org.sonatype.sisu.inject</groupId>
			<artifactId>cglib</artifactId>
			<version>2.2.2</version>
		</dependency>
		
 */
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(ConfigApp.class);
		
		Player player1 = ctx.getBean("player1", Player.class);
		System.out.println("선수이름 :"+player1.getName());
		System.out.println("나이 :"+player1.getAge());
		System.out.println("포지션 :"+player1.getPosition());
		System.out.println("신장 :"+player1.getHeight());
		System.out.println("몸무게 :"+player1.getWeight());
		
		System.out.println("=============================");
		
		Player player2 = ctx.getBean("player2", Player.class);
		System.out.println("선수이름 :"+player2.getName());
		System.out.println("나이 :"+player2.getAge());
		System.out.println("포지션 :"+player2.getPosition());
		System.out.println("신장 :"+player2.getHeight());
		System.out.println("몸무게 :"+player2.getWeight());
		
		ctx.close();
	}
}
