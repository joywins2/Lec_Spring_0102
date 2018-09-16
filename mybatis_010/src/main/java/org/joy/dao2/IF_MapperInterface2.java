package org.joy.dao2;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;
import org.joy.domain.MemberVO;

public interface IF_MapperInterface2 {

	@Select("select now()")
	public String getTime();

	@Results({
          @Result(property = "user_id", jdbcType=JdbcType.VARCHAR, id=true, column = "user_id"),
          @Result(property = "user_pw", jdbcType=JdbcType.VARCHAR, column = "user_pw"),
          @Result(property = "user_name", jdbcType=JdbcType.VARCHAR, column = "user_name"),
          @Result(property = "email", jdbcType=JdbcType.VARCHAR, column = "email")
          //@Result(property = "regdate", jdbcType=JdbcType.TIMESTAMP, column = "regdate"),
          //@Result(property = "updatedate", jdbcType=JdbcType.TIMESTAMP, column = "updatedate"),
        })
	@Select("SELECT user_id, user_pw, user_name, email FROM ztbl_member WHERE user_id = #{user_id}")
	public MemberVO selectMemberByUserId(String user_id);
	

/*	
 * ...http://blast21.kr/38
   @Insert("INSERT INTO ztbl_member(user_id, user_pw, user_name, email)"
			+ " VALUES(#{user_id}, #{user_pw}, #{user_name}, #{email})")
	public void insertMember(@Param("user_id") String user_id,
							 @Param("user_pw") String user_pw, 
							 @Param("user_name") String user_name, 
							 @Param("email") String email);*/
	@Insert("INSERT INTO ztbl_member(user_id, user_pw, user_name, email)"
			+ " VALUES(#{user_id}, #{user_pw}, #{user_name}, #{email})")
	public void insertMember(MemberVO member);	
	
	@Update("UPDATE ztbl_member"
			+ " SET user_pw=#{user_pw}, user_name=#{user_name}, email=#{email}, updatedate=now()"
			+ " WHERE user_id=#{user_id}")
	public void updateMember(MemberVO member);
	
	@Delete("DELETE FROM ztbl_member WHERE user_id=#{user_id}")
	public void deleteMember(String user_id);
	
}
