package org.joy.dao;

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
}
