package org.joy.dao;
import org.apache.ibatis.annotations.*;

/*
 * ...How use @Select MyBatis annotation :
 *    https://examples.javacodegeeks.com/enterprise-java/mybatis/how-use-select-mybatis-annotation/
 */
public interface IF_SampleMapper {
	
	@Select("select now()")
	public String getTime();

	
	@Select("select user_id, user_name, email "
			+ "from ztbl_member "
			+ "where user_id = #{id} and user_pw = #{pw}")
	public String IfAnnotation_GetEmail(
			@Param("id") String id,
			@Param("pw") String pw);
	

	public String MixXml_GetUserName(
			@Param("id") String id,
			@Param("pw") String pw);
	
	@SelectProvider(type=SampleProvider.class, method="searchUserNameDynamicSql")
	public String searchUserNameDynamicSql(
			@Param("type") String type,
			@Param("keyword") String keyword);
	
}