package org.joy.controller;

import javax.inject.Inject;

import org.joy.domain.BoardVO;
import org.joy.domain.Criteria;
import org.joy.domain.PageMaker;
import org.joy.service.IF_BoardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/board/*")
public class BoardController {
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);

	@Inject
	private IF_BoardService service;

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public void createGET(BoardVO board, Model model) throws Exception {
		logger.info("createGet called ...........");

	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String createPOST(BoardVO board, RedirectAttributes rattr) throws Exception {

		logger.info("create post addFlashAttribute 도배방지...........");
		logger.info(board.toString());

		service.create(board);

		// ...http://cafe.naver.com/gugucoding/1697
		rattr.addFlashAttribute("msg", "SUCCESS");

		return "redirect:/board/listAll";

	}

	@RequestMapping(value = "/listAll", method = RequestMethod.GET)
	public void listAll(Model model, @ModelAttribute("msg") String msg) throws Exception {
		logger.info("show all list1......................");
		model.addAttribute("list", service.listAll());
	}

	/*
	 * ...219p.@ReqeustParam("bno")는 Servlet::request.getParam("bno")처럼 동작함.
	 * 
	 * @RequestParam을 사용하여 외부에서 전달될 bno값을 파라미터로 받는 것을 더욱 명확하게 표현함.
	 * Servlet.HttpServletRequest와 다른 점은 문자열, 숫자, 날짜 등의 형변환이 가능함.
	 * Model.addAttribute()작업할때 아무런 이름 없이 데이터를 넣으면 자동으로 클래스의 이름을 소문자로 시작해서 사용한다.
	 * BoardVO org.joywins.service.IF_BoardService.read(Integer bno) throws
	 * Exception처럼 여기서 들어가는 데이터는 BoardVO객체이므로 boardVO라는 이름으로 저장됨.
	 */
	@RequestMapping(value = "/read", method = RequestMethod.GET)
	public void read(@RequestParam("bno") int bno, Model model) throws Exception {
		model.addAttribute(service.read(bno));
	}

	/*
	 * ...225p.@RequestParam("bno")를 제외해도 bno에 값이 들어옴. 
	 *    219p, 195p, 334p. @RequestParam 은 외부에서 전달받는 값임을 명확하게 표현하기 위해 사용.
	 */
	@RequestMapping(value = "/remove", method = RequestMethod.POST)
	public String delete(@RequestParam("bno") int bno, RedirectAttributes rttr) throws Exception {

		service.delete(bno);

		rttr.addFlashAttribute("msg", "SUCCESS");

		logger.info("삭제처리, bno = " + bno);

		return "redirect:/board/listAll";
	}

	@RequestMapping(value = "/modify", method = RequestMethod.GET)
	public void modifyGET(@RequestParam("bno") int bno, Model model) throws Exception {
		logger.info("227p.수정페이지로 이동, modify/ get/ bno = " + bno);
		model.addAttribute(service.read(bno));
	}

	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String modifyPOST(BoardVO board, RedirectAttributes rttr) throws Exception {

		logger.info("수정처리, modify post BoardVO = " + board.toString());

		service.update(board);
		rttr.addFlashAttribute("msg", "SUCCESS");

		return "redirect:/board/listAll";
	}

	/*
	 * ...262p. 스프링MVC의 컨트롤러는 특정 URL에 해당하는 메서드를 실행할 때, 
	 *    파라미터의 타입을 보고, 해당 객체를 자동으로 생성해냄. 
	 *    파라미터가 자동으로 수집되므로, 바로 이전에 만든 Criteria 클래스를 
	 *    그대로 사용함. 
	 *    모든 조회는 GET방식을 사용함. 
	 *    http://localhost:8080/board/listCriteria
	 *    http://localhost:8080/board/listCri?page=2
	 *    http://localhost:8080/board/listCri?page=2&perPageNum=20
	 */
	@RequestMapping(value = "/listCri", method = RequestMethod.GET)
	public void listCriteria(Criteria cri, Model model) throws Exception {

		logger.info("show list Page with Criteria......................");

		model.addAttribute("list", service.listCriteria(cri));
	}
	
	/*
	 * ...275p. listPage()에서는 크게 목록 데이터를 Model에 저장하는 작업과 
	 * ...PageMaker를 구성해서 Model에 담는 작업을 함.
	 * ...282p. http://localhost:8080/board/listPage?page=256
	 */
	@RequestMapping(value = "/listPage", method = RequestMethod.GET)
	//public void listPage(Criteria cri, Model model) throws Exception {//...275p.
	//...108p, 234p, 275p, 281p. ModelAttribute
	public void listPage(@ModelAttribute("cri") Criteria cri, Model model) throws Exception {//...281p.

		logger.info(cri.toString());

		model.addAttribute("list", service.listCriteria(cri));
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);

		//...총데이터수를 60으로 정하고, calcData()에서 보여주는 페이지수(displayPageNum)에 의해 계산됨.
		//pageMaker.setTotalCount(60); //...274p.
		pageMaker.setTotalCount(service.countBno(cri));// ...281p.

		model.addAttribute("pageMaker", pageMaker);
	}


}
