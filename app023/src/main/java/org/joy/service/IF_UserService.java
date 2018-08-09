package org.joy.service;

import java.util.Date;

import org.joy.domain.UserVO;
import org.joy.dto.LoginDTO;

//...633p.
public interface IF_UserService {

  public UserVO login(LoginDTO dto) throws Exception;

  //...665p.
  public void keepLogin(String uid, String sessionId, Date next)throws Exception;
  
  public UserVO checkLoginBefore(String value);  
}
