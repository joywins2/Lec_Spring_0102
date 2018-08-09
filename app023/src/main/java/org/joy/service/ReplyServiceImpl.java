package org.joy.service;
//...376p.
import java.util.List;

import javax.inject.Inject;

import org.joy.dao.IF_BoardDAO;
import org.joy.dao.IF_ReplyDAO;
import org.joy.domain.Criteria;
import org.joy.domain.ReplyVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReplyServiceImpl implements IF_ReplyService {

	@Inject
	private IF_ReplyDAO dao;
	
	//...505p.
	@Inject
	private IF_BoardDAO boardDAO;	
	
	@Override
	public List<ReplyVO> selectReplies(Integer bno) throws Exception {
		return dao.selectReplies(bno);
	}

	@Transactional
	@Override
	public void insertReply(ReplyVO vo) throws Exception {
	    dao.insertReply(vo);
	    boardDAO.updateReplyCount(vo.getBno(), 1);//...506p.
	}

	@Override
	public void updateReply(ReplyVO vo) throws Exception {
		dao.updateReply(vo);
	}

	@Transactional
	@Override
	public void deleteReply(Integer rno) throws Exception {
		int bno = dao.selectBno(rno);
		dao.deleteReply(rno);
		boardDAO.updateReplyCount(bno, -1);
	}

	//...392p.
	@Override
	public List<ReplyVO> selectPageReplies(Integer bno, Criteria cri) throws Exception {
		return dao.selectPageReplies(bno, cri);
	}

	@Override
	public int countReplies(Integer bno) throws Exception {
		return dao.countReplies(bno);
	}

}
