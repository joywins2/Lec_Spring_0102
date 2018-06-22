package org.joy.controller;

import static org.junit.Assert.*;

import javax.inject.Inject;

import org.joy.dao.IF_BoardDAO;
import org.joy.domain.BoardVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;


/*
 *...89p.@RunWith, @ContextConfiguration 어노테이션은 테스트코드 실행시 스프링을 로딩시킴.
 */
@RunWith(SpringJUnit4ClassRunner.class)
/*
*...java.lang.IllegalStateException: Failed to load ApplicationContext
	https://stackoverflow.com/questions/40565064/junit-test-whats-wrong
*/
@WebAppConfiguration
@ContextConfiguration(
		locations ={"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class Junit181p_BoardDAOTest {

	@Inject
	private IF_BoardDAO dao;

	private static Logger logger = LoggerFactory.getLogger(Junit181p_BoardDAOTest.class);	

	@Test
	public void testInsert() throws Exception {
		BoardVO board = new BoardVO();
		board.setTitle("새로운 글을 넣습니다. ");
		board.setContent("새로운 글을 넣습니다. ");
		board.setWriter("zuser00");
		dao.insert(board);
	}

	@Test
	public void testSelect() throws Exception {
		logger.info(dao.select(2).toString());
	}

	@Test
	public void testUpdate() throws Exception {
		BoardVO board = new BoardVO();
		board.setBno(1);
		board.setTitle("수정된 글입니다.");
		board.setContent("수정 테스트 ");
		dao.update(board);
	}

	@Test
	public void testDelete() throws Exception {
		dao.delete(1);
	}
	
	//@Test
	public void test() {
		fail("Not yet implemented");
	}

}
