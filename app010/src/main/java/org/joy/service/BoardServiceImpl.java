package org.joy.service;

import java.util.List;

import javax.inject.Inject;

import org.joy.dao.IF_BoardDAO;
import org.joy.domain.BoardVO;
import org.springframework.stereotype.Service;


//...187, 376, 470p.@Service가 스프링의 빈으로 인식하게 함. root-context.xml::Beans Graph 확인할 것.
@Service
public class BoardServiceImpl implements IF_BoardService {

	@Inject
	private IF_BoardDAO dao;

	@Override
	public void create(BoardVO board) throws Exception {
		dao.insert(board);
	}

	@Override
	public BoardVO read(Integer bno) throws Exception {
	    return dao.select(bno);
	}

	@Override
	public void update(BoardVO board) throws Exception {
	    dao.update(board);
	}

	@Override
	public void delete(Integer bno) throws Exception {
	    dao.delete(bno);
	}

	@Override
	public List<BoardVO> listAll() throws Exception {
		return dao.selectAll();
	}

}
