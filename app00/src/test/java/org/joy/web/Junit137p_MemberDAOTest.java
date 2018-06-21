package org.joy.web;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.joy.dao.IF_MemberDAO;
import org.joy.domain.MemberVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
//...Spring4.3 부터 root-context.xml 명시하는 것으로 변경됨.
//...http://cafe.naver.com/gugucoding/2134
//변경전 : @ContextConfiguration(
//		locations ={"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
//.../app00/pom.xml log4jdbc-log4j2-jdbc4 의존성 추가.
@ContextConfiguration(
		locations ={"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class Junit137p_MemberDAOTest {
	
	@Inject
	private IF_MemberDAO dao;
	

	//@Test
	public void testTime()throws Exception{		
		System.out.println("getTime() = " + dao.getTime());
		
	}		
	
	//@Test
	public void testInsertMember()throws Exception{		
		MemberVO vo = new MemberVO();
		vo.setUser_id("user_id02");
		vo.setUser_pw("user_pw02");
		vo.setUser_name("USER02");
		vo.setEmail("user02@joy.org");
		
		dao.insertMember(vo);		
	}	
	
	//@Test
	public void selectMember()throws Exception{		
		MemberVO vo = new MemberVO();
		vo.setUser_id("user_id02");
		vo.setUser_pw("user_pw02");
		vo.setUser_name("USER02");
		vo.setEmail("user02@joy.org");
		
		dao.selectMember(vo.getUser_id());		
	}	

	@Test
	public void selectWithPw()throws Exception{		
		MemberVO vo = new MemberVO();
		vo.setUser_id("user_id02");
		vo.setUser_pw("user_pw02");
		vo.setUser_name("USER02");
		vo.setEmail("user02@joy.org");
		
		dao.selectWithPw(vo.getUser_id(), vo.getUser_pw());		
	}	



}
