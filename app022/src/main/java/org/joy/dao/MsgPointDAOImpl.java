package org.joy.dao;
//...468p.추가.

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
public class MsgPointDAOImpl implements IF_MsgPointDAO {

	@Inject
	private SqlSession session;
	
	private static String namespace ="org.joy.mapper.MsgPointMapper";	

	@Override
	public void update(String uid, int point) throws Exception {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("uid", uid);
		paramMap.put("point", point);
		session.update(namespace + ".updatePoint", paramMap);
	}


}
