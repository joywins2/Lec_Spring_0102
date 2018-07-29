package org.joy.service;
//...470p.

import javax.inject.Inject;

import org.joy.dao.IF_MsgDAO;
import org.joy.dao.IF_MsgPointDAO;
import org.joy.domain.MsgVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MsgServiceImpl implements IF_MsgService {

	@Inject
	private IF_MsgDAO msgDAO;
	
	@Inject
	private IF_MsgPointDAO msgPointDAO;

	@Override
	public void addMessage(MsgVO vo) throws Exception {
		msgDAO.insert(vo);
		msgPointDAO.update(vo.getSender_id(), 10);
	}

	@Override
	public MsgVO readMessage(String uid, Integer mid) throws Exception {
		
		msgDAO.updateOpenDate(mid);
		msgPointDAO.update(uid, 5);

		return msgDAO.read(mid);
	}

}
