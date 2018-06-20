package org.joy.web;

import static org.junit.Assert.*;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;


/*
 *...89p.@RunWith, @ContextConfiguration 어노테이션은 테스트코드 실행시 스프링을 로딩시킴.
 */
@RunWith(SpringJUnit4ClassRunner.class)
/*
*...java.lang.IllegalStateException: Failed to load ApplicationContext
	https://stackoverflow.com/questions/40565064/junit-test-whats-wrong
*/
@WebAppConfiguration
@ContextConfiguration(
		locations ={"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
public class Junit093p_MyBatisTest {

	//...94p.스프링이 정상 작동하면 root-context.xml의 sqlSessionFactory가 주입됨.
	@Inject 
	private SqlSessionFactory sqlFactory;
	

	@Test
	public void testFactory(){
		System.out.println("testFactory");
		System.out.println(sqlFactory);
		
	}	
	
	@Test
	public void testSession()throws Exception{
		
		try(SqlSession session = sqlFactory.openSession()){
			System.out.println("Okay!");
			System.out.println(session);
			
		}catch(Exception e){
			System.out.println("Error");
			e.printStackTrace();
		}
		
	}

}
