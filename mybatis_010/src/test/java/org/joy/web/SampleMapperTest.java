package org.joy.web;

import static org.junit.Assert.fail;

import javax.inject.Inject;

import org.joy.dao.IF_SampleMapper;
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
public class SampleMapperTest {
	
	@Inject
	private IF_SampleMapper mapper;

	@Test
	public void testTime() {
		//fail("Not yet implemented");
		System.out.println(mapper.getClass().getName());
		System.out.println("getTime : " + mapper.getTime());
	}

	@Test
	public void testIfAnnotationGetEmail() {
		//fail("Not yet implemented");
		String email = mapper.IfAnnotation_GetEmail("user123", "user123");
		System.out.println("getEmail : " + email);
	}

	@Test
	public void testMixXmlGetUserName() {
		//fail("Not yet implemented");
		String userName = mapper.MixXml_GetUserName("user123", "user123");
		System.out.println("getUserName : " + userName);
	}

	@Test
	public void testSearchUserNameDynamicSql() {
		//fail("Not yet implemented");
		String userName = mapper.searchUserNameDynamicSql("id", "user123");
		System.out.println("testSearchUserNameDynamicSql::getUserName : " + userName);
	}
	
	

}
