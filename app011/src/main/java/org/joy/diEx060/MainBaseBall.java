package org.joy.diEx060;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class MainBaseBall {

	public static void main(String[] args) {
		String confLoc = "classpath:beanDiEx060.xml";
		String confLoc2 = "classpath:beanDiEx061.xml";
		
		AbstractApplicationContext ctx = new GenericXmlApplicationContext(confLoc, confLoc2);

		System.out.println("-----------------------player1-----------------");
		Player player1 = ctx.getBean("player10", Player.class);
		System.out.println(player1.getName());
		System.out.println(player1.getPosition());
		
		System.out.println("-----------------------playerInfo-----------------");
		PlayerInfo playerInfo = ctx.getBean("playerInfo1",PlayerInfo.class);		
		Player player2 = playerInfo.getPlayer();
		System.out.println(player2.getName());
		System.out.println(player2.getPosition());
		
		if(player1.equals(player2)){
			System.out.println("equal player1과 player2는 같은 선수 입니다!!!");
		}else {
			System.out.println("equal player1과 player2는 다른 선수 입니다!!!");
		}
		/*
		 * ...== : 값만 비교 vs equal : 주소값을 찾아 같은 존재인지 비교.
		 */
		if(player1.getName() == player2.getName()){
			System.out.println("== player1과 player2는 같은 선수 입니다!!!");
		}else {
			System.out.println("== player1.name과 player2.name은 다른 선수 입니다!!!");
		}
		
		System.out.println("-----------------------player3-----------------");
		Player player3 = ctx.getBean("player3", Player.class);
		System.out.println(player3.getName());
		System.out.println(player3.getPosition());
		
		BaseBallTeam baseBallTeam = ctx.getBean("baseBallTeam",BaseBallTeam.class);
		System.out.println("-----------------------야구팀 구성-----------------");
		System.out.println("감독 :"+baseBallTeam.getManager());
		System.out.println("타격코치 :"+baseBallTeam.getBattingCoach());
		System.out.println("투수코치 :"+baseBallTeam.getPitchingCoach());
		System.out.println("타자 :"+baseBallTeam.getHitter());
		System.out.println("투수 :"+baseBallTeam.getPitcher());
	}

}
