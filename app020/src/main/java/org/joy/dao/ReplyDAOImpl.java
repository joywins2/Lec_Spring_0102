package org.joy.dao;
//...374p.
import java.util.HashMap;
//...374p.
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.joy.domain.Criteria;
import org.joy.domain.ReplyVO;
import org.springframework.stereotype.Repository;

/*
 * ...374p.@Repository : DAO를 스프링에 인식시킴.135p.
 */
@Repository
public class ReplyDAOImpl implements IF_ReplyDAO {

	@Inject
	private SqlSession session;

	private static String namespace = "org.joy.mappers.ReplyMapper";

	@Override
	public List<ReplyVO> selectReplies(Integer bno) throws Exception {
		return session.selectList(namespace + ".selectReplies", bno);
	}

	@Override
	public void insertReply(ReplyVO vo) throws Exception {
		session.insert(namespace + ".insertReply", vo);		
	}

	@Override
	public void updateReply(ReplyVO vo) throws Exception {
		session.update(namespace + ".updateReply", vo);
	}

	@Override
	public void deleteReply(Integer rno) throws Exception {
		session.update(namespace + ".deleteReply", rno);
	}

	//...390p.
	@Override
	public List<ReplyVO> selectPageReplies(Integer bno, Criteria cri) throws Exception {
		
	    Map<String, Object> paramMap = new HashMap<>();//...143p, 145p.

	    paramMap.put("bno", bno);
	    paramMap.put("cri", cri);

	    return session.selectList(namespace + ".selectPageReplies", paramMap);
	    
	}

	@Override
	public int countReplies(Integer bno) throws Exception {
		return session.selectOne(namespace + ".countReplies", bno);
	}

	@Override
	public int selectBno(Integer rno) throws Exception {
		return session.selectOne(namespace + ".selectBno", rno);
	}
	
}






