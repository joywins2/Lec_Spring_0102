package org.joy.dao;

import java.util.Date;

import org.joy.domain.UserVO;
import org.joy.dto.LoginDTO;

//...631p.
public interface IF_UserDAO {

	public UserVO login(LoginDTO dto) throws Exception;
	
}
