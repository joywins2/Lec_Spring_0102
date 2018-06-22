package org.joy.dao;

import java.util.List;

import org.joy.domain.BoardVO;

/***
 * ...176p.
 * DAO = Persistence 패키지.
 * @author Administrator
 *
 */
public interface IF_BoardDAO {

	  public void insert(BoardVO vo) throws Exception;

	  public BoardVO select(Integer bno) throws Exception;

	  public void update(BoardVO vo) throws Exception;

	  public void delete(Integer bno) throws Exception;

	  public List<BoardVO> selectAll() throws Exception;
	  
	  

}
