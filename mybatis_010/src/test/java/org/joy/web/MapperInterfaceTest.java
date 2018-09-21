package org.joy.web;

import javax.inject.Inject;

import org.joy.dao.IF_MapperInterface;
import org.joy.domain.MemberVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
/*
 * ...java.lang.IllegalStateException: Failed to load ApplicationContext
 * https://stackoverflow.com/questions/40565064/junit-test-whats-wrong
 */
@WebAppConfiguration
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/**/root-context.xml" })
public class MapperInterfaceTest {
	
	@Inject
	private IF_MapperInterface mapper;

	@Test
	public void testTime() {
		//fail("Not yet implemented");
		System.out.println(mapper.getClass().getName());
		System.out.println("testGetTime : " + mapper.getTime());
	}	

	@Test
	public void testGetMember_AnnotationedInterface() {
		//fail("Not yet implemented");
		String email = mapper.getMember_AnnotationedInterface("user123", "user123");
		System.out.println("testGetMember_AnnotationedInterface : " + email);
	}

	@Test
	public void testGetMember_MixedIfAndXml() {
		//fail("Not yet implemented");
		MemberVO member = mapper.getMember_MixedIfAndXml("user123", "user123");
		System.out.println("testGetMember_MixedIfAndXml : " + member.toString());
	}

	@Test
	public void testGetMember_SelectProvider() {
		//fail("Not yet implemented");
		MemberVO member = mapper.getMember_SelectProvider("id", "user123");
		System.out.println("testGetUserName_SelectProvider : " + member.toString());
	}

}
