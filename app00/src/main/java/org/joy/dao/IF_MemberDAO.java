package org.joy.dao;
/*
 * ...129p.
 * ...DAO = Persistence 패키지.
 */

import org.joy.domain.MemberVO;

public interface IF_MemberDAO {

	public String getTime();
	
	public void insertMember(MemberVO vo);

}
