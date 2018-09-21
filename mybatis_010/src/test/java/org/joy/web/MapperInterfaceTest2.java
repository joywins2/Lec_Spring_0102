package org.joy.web;

import javax.inject.Inject;

import org.joy.dao2.IF_MapperInterface2;
import org.joy.dao2.MemberDao2;
import org.joy.domain.MemberVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
/*
 * ...MyBatis 3 Annotation Example with @Select, @Insert, @Update and @Delete :
 *    https://www.concretepage.com/mybatis-3/mybatis-3-annotation-example-with-select-insert-update-and-delete
 */
@WebAppConfiguration
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/**/root-context.xml" })
public class MapperInterfaceTest2 {
	
	@Inject
	private IF_MapperInterface2 mapper;

	@Test
	public void testTime() {
		//fail("Not yet implemented");
		System.out.println(mapper.getClass().getName());
		System.out.println("getTime : " + mapper.getTime());
	}
	public MemberVO selectMemberByUserId(String user_id) {
		//fail("Not yet implemented");
		MemberVO member = mapper.selectMemberByUserId(user_id);
		if (member != null) {
			System.out.println("selectMemberByUserId : " + member.toString());	
		}else {
			System.out.println(user_id + " not found ");
		}
		return member;		
	}
	@Test
	public void testSelectMemberByUserId() {
		selectMemberByUserId("user123");		
	}

	//@Test
	public void testInsertMember() {
		//fail("Not yet implemented");
		MemberVO member = new MemberVO("user888", "user888pw", "user888Name", "user888@email.com");
		mapper.insertMember(member);
		System.out.println("S.insertMember : " + member.getUser_id());
		selectMemberByUserId(member.getUser_id());
	}

	//@Test
	public void testUpdateMember() {
		//fail("Not yet implemented");
		MemberVO member = new MemberVO("user888", "user999pw", "user999Name", "user999@email.com");		
		mapper.updateMember(member);
		System.out.println("S.testDeleteMember : " + member.getUser_id());
		selectMemberByUserId(member.getUser_id());
	}

	//@Test
	public void testDeleteMember() {
		//fail("Not yet implemented");
		String user_id = "user888";		
		mapper.deleteMember(user_id);
		System.out.println("S.testDeleteMember : " + user_id);
		selectMemberByUserId(user_id);
	}
	
}
