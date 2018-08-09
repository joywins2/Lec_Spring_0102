package org.joy.service;

import java.util.Date;

import javax.inject.Inject;

import org.joy.dao.IF_UserDAO;
import org.joy.domain.UserVO;
import org.joy.dto.LoginDTO;
import org.springframework.stereotype.Service;

//...633p.
@Service
public class UserServiceImpl implements IF_UserService {

  @Inject
  private IF_UserDAO dao;

  @Override
  public UserVO login(LoginDTO dto) throws Exception {

    return dao.login(dto);
  }
}
