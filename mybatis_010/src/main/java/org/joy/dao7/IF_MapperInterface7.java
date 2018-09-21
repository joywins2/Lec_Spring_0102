package org.joy.dao7;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.joy.domain.MemberVO;

public interface IF_MapperInterface7 {

	@Select("SELECT now()")
	public String getTime();

	@Select("SELECT VERSION()")
	public String getMySqlVersion();

	@Insert("INSERT INTO ztbl_member(user_id, user_pw, user_name, email)"
			+ " VALUES(#{user_id}, #{user_pw}, #{user_name}, #{email})")
	public void insertMember(MemberVO member);
	
	@Select("SELECT user_id, user_pw, user_name, email FROM ztbl_member"
			+ " WHERE user_id = #{user_id}")
	public MemberVO selectMemberByUserId(String user_id);
	
	@Select("SELECT user_id, user_pw, user_name, email FROM ztbl_member"
			+ " WHERE user_name = #{user_name}")
	public List<MemberVO> selectMembersByUserName(String user_name);
	
}
