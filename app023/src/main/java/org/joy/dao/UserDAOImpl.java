package org.joy.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.joy.domain.UserVO;
import org.joy.dto.LoginDTO;
import org.springframework.stereotype.Repository;

//...632p.
@Repository
public class UserDAOImpl implements IF_UserDAO {
	
	@Inject
	private SqlSession session;
	
	private static String namespace ="org.joy.mapper.UserMapper";	

	@Override
	public UserVO login(LoginDTO dto) throws Exception {
		
		return session.selectOne(namespace +".login", dto);
	}

	//...664p.
	@Override
	public void keepLogin(String uid, String sessionId, Date next) {
	
	    Map<String, Object> paramMap = new HashMap<String, Object>();
	    paramMap.put("uid", uid);
	    paramMap.put("sessionId", sessionId);
	    paramMap.put("next", next);
	    
	    session.update(namespace+".keepLogin", paramMap);
	    
	  }

	@Override
	public UserVO checkUserWithSessionKey(String value) {

	    return session.selectOne(namespace +".checkUserWithSessionKey", value);
	}
}
