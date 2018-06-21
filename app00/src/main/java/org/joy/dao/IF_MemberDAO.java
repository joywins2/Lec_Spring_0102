package org.joy.dao;
/*
 * ...129p.
 * ...DAO = Persistence 패키지.
 */

import org.joy.domain.MemberVO;

public interface IF_MemberDAO {

	public String getTime();
	
	public void insertMember(MemberVO vo);
	
	public MemberVO selectMember(String user_id)throws Exception;
	  
	public MemberVO selectWithPw(String user_id, String user_pw)throws Exception;
	

}
