package org.joy.service;
//...375p.
import java.util.List;

import org.joy.domain.ReplyVO;

public interface IF_ReplyService {

	public List<ReplyVO> selectReplies(Integer bno) throws Exception;
	
	public void insertReply(ReplyVO vo) throws Exception;

	public void updateReply(ReplyVO vo) throws Exception;

	public void deleteReply(Integer rno) throws Exception;	
	
}
