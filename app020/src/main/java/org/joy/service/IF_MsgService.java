package org.joy.service;

import org.joy.domain.MsgVO;

public interface IF_MsgService {

	  public void addMessage(MsgVO vo) throws Exception;

	  public MsgVO readMessage(String uid, Integer mid) throws Exception;

}
