package org.joy.dao;

import java.util.Map;

public class MemberSelectProvider {
	
	/*
	 * ...692p.메서드는 static으로 작성되어야 함.
	 *    메서드의 파라미터는 2개 이상의 데이터를 던질 수 있도록 Map<String, Object>로 설정함.
	 */
	
	public static String getMember_SelectProvider(Map<String, Object> params) {
		
		String selectSql = "select user_id, user_pw, user_name, email from ztbl_member where 1=1 ";
		
		if(params.get("type").equals("id")) {
			selectSql += " and user_id = #{keyword}";
		}
		
		return selectSql;
	}

}
