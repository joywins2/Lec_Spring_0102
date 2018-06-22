package org.joy.service;

import java.util.List;

import org.joy.domain.BoardVO;

public interface IF_BoardService {

	  public void create(BoardVO board) throws Exception;

	  public BoardVO read(Integer bno) throws Exception;

	  public void update(BoardVO board) throws Exception;

	  public void delete(Integer bno) throws Exception;

	  public List<BoardVO> listAll() throws Exception;

}
