package org.joy.dao;

import java.util.Map;

public class SampleProvider {
	
	public static String searchUserNameDynamicSql(Map<String, Object> params) {
		String selectSql = "select user_name from ztbl_member where 1=1 ";
		if(params.get("type").equals("id")) {
			selectSql += " and user_id = #{keyword}";
		}
		return selectSql;
	}

}
