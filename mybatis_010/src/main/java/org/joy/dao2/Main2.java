package org.joy.dao2;

import javax.inject.Inject;

import org.joy.domain.MemberVO;

public class Main2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MemberDao2 dao = new MemberDao2();
		MemberVO member = dao.selectMemberByUserId("user123");
		System.out.println("S.testDao : " + member.toString());

	}

}
