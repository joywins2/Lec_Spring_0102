package org.joy.controller;

import static org.junit.Assert.*;

import java.util.List;

import javax.inject.Inject;

import org.joy.dao.IF_BoardDAO;
import org.joy.domain.BoardVO;
import org.joy.domain.Criteria;
import org.joy.domain.SearchCriteria;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

/*
 *...89p.@RunWith, @ContextConfiguration 어노테이션은 테스트코드 실행시 스프링을 로딩시킴.
 */
@RunWith(SpringJUnit4ClassRunner.class)
/*
 * ...java.lang.IllegalStateException: Failed to load ApplicationContext
 * https://stackoverflow.com/questions/40565064/junit-test-whats-wrong
 */
@WebAppConfiguration
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/**/root-context.xml" })
public class Junit181p_BoardDAOTest {

	@Inject
	private IF_BoardDAO dao;

	private static Logger logger = LoggerFactory.getLogger(Junit181p_BoardDAOTest.class);

	// @Test
	public void testInsert() throws Exception {
		BoardVO board = new BoardVO();
		board.setTitle("새로운 글을 넣습니다. ");
		board.setContent("새로운 글을 넣습니다. ");
		board.setWriter("zuser00");
		dao.insert(board);
	}

	// @Test
	public void testSelect() throws Exception {
		logger.info(dao.select(100).toString());
	}

	// @Test
	public void testUpdate() throws Exception {
		BoardVO board = new BoardVO();
		board.setBno(1);
		board.setTitle("수정된 글입니다.");
		board.setContent("수정 테스트 ");
		dao.update(board);
	}

	// @Test
	public void testDelete() throws Exception {
		dao.delete(1);
	}

	// @Test
	public void test() {
		fail("Not yet implemented");
	}

	// @Test
	public void testListPage() throws Exception { // ...250p.

		int page = 3;

		List<BoardVO> list = dao.listPage(page);

		for (BoardVO boardVO : list) {
			logger.info(boardVO.getBno() + ":" + boardVO.getTitle());
		}
	}

	// @Test
	public void testListCriteria() throws Exception {

		Criteria cri = new Criteria();
		cri.setPage(2);
		cri.setPerPageNum(20);
		// ...limit (시작페이지) (한페이지당 보여지는 개수)
		// ...limit 20 20 : 20개씩 2페이지의 결과.
		/*
		 * select bno, title, content, writer, regdate, view_cnt from ztbl_board where
		 * bno > 0 order by bno desc, regdate desc limit #{pageStart}, #{perPageNum}
		 */
		List<BoardVO> list = dao.listCriteria(cri);

		for (BoardVO boardVO : list) {
			logger.info("testListCriteria : " + boardVO.getBno() + ":" + boardVO.getTitle());
		}
	}

	// ...284p.UriComponents, UriComponentsBuilder는 path 또는 query에 해당하는 문자열들을
	// 추가해서 원하는 URI를 생성할 때 사용함.
	// 원하는 데이터를 계속 추가해서 처리할 수 있음.
	// queryParam()의 경우 GET방식의 '?' 뒤에 붙는 데이터가 됨.
	//@Test
	public void testURI() throws Exception {

		UriComponents uriComponents = UriComponentsBuilder.newInstance().path("/board/read").queryParam("bno", 168)
				.queryParam("perPageNum", 20).build();

		logger.info("testURI : /board/read?bno=12&perPageNum=20");
		logger.info("testURI : " + uriComponents.toString());

	}

	// ...285p.미리 경로를 지정해두고 '{module]'와 같은 경로를 'board'로
	//    '{page}'를 'read'로 변경할 수 있다.
	//@Test
	public void testURI2() throws Exception {

		UriComponents uriComponents = UriComponentsBuilder.newInstance().path("/{module}/{page}").queryParam("bno", 168)
				.queryParam("perPageNum", 20).build().expand("board", "read").encode();

		logger.info("testURI2 : /board/read?bno=168&perPageNum=20");
		logger.info("testURI2 : " + uriComponents.toString());
	}


	//...326p.log4jdbc-log4j2설정(139p, 160p)이 정상적이면 실행되는 SQL문장이 출력됨.
	//...예) INFO : jdbc.sqltiming - select count(bno) from ztbl_board where bno > 0 
	@Test
	public void testDynamic1() throws Exception {

		logger.info("...S.testDynamic1");
	    SearchCriteria cri = new SearchCriteria();
	    cri.setPage(1);
	    cri.setKeyword("글");
	    cri.setSearchType("t");

	    logger.info("=====================================");

	    List<BoardVO> list = dao.listSearch(cri);

	    for (BoardVO boardVO : list) {
	      logger.info("testDynamic1 : " + boardVO.getBno() + ": " + boardVO.getTitle());
	    }

	    logger.info("=====================================");

	    logger.info("COUNT: " + dao.listSearchCount(cri));
	    
	    logger.info("...E.testDynamic1");
	}
	

}
