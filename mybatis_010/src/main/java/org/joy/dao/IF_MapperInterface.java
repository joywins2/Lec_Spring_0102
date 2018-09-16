package org.joy.dao;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.joy.domain.MemberVO;

/*
 * ...683p.mybatis-spring이 Mapper인터페이스의 구현을자동으로 생성함. 
 * ...How use @Select MyBatis annotation :
 *    https://examples.javacodegeeks.com/enterprise-java/mybatis/how-use-select-mybatis-annotation/
 */
public interface IF_MapperInterface {
	
	@Select("select now()")
	public String getTime();
	
	/*
	 * ...686p.2개 이상의 파라미터를 DAO에서 처리하는 방법들 : 
	 *    방법01.별도의 Map을 구성해서 처리하거나 클래스를 구성해서 처리함.
	 *    방법02.Mybatis어노테이션을 사용하여 두개 이상의 파라미터 각각에
	 *         @Param 이름의 어노테이션을 붙여서 처리함.
	 */
	@Select("select user_id, user_name, email "
			+ "from ztbl_member "
			+ "where user_id = #{id} and user_pw = #{pw}")
	public String getEmail_AnnotationedInterface(
			@Param("id") String id,
			@Param("pw") String pw);	
	
	public MemberVO getEmail_MixedIfAndXml(
			@Param("id") String id,
			@Param("pw") String pw);	
	
	/*
	 * ...692p.@SelectProvider 속성 :
	 *    type : SQL문을 만들어 내는 클래스.
	 *    method : SQL문이 반환되는 메서드 이름.
	 */
	@SelectProvider(type=MemberSelectProvider.class, method="getUserName_SelectProvider")
	public MemberVO getUserName_SelectProvider(
			@Param("type") String type,
			@Param("keyword") String keyword);
		
}