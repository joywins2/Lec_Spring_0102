package org.joy.dao;

import org.joy.domain.MsgVO;

public interface IF_MsgDAO {

  public void insert(MsgVO vo) throws Exception;

  public MsgVO read(Integer mid) throws Exception;

  public void updateOpenDate(Integer mid) throws Exception;

	
}
