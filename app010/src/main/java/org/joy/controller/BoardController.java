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
	 * ...292p.페이징 처리가 된 후, 조회 페이지는 다시 목록 페이지로 돌아가기 위해 다음 3가지 정보가 필요함.
	 *    1. 현재 목록 페이지의 페이지 번호(page).
	 *    2. 현재 목록 페이지의 페이지당 데이터 수(perPageNum).
	 *    3. 현재 조회하는 게시물의 번호(bno).
	 *    ★같은 메서드를 요청별로 오버로딩할 수 있다.
	 *    '/board/readPage?bno=xx&page=x&perPageNum=xxx'와 같은 식으로 요청을 받고,
	 *    http://localhost:8080/board/readPage?page=2&perPageNum=10&bno=192
	 *    ★page와 perPageNum 파라미터는 Criteria 타입의 객체로 처리함.
	 *    ★Criteria를 확장해서 bno값을 처리하지 않고, 별도의 bno 파라미터를 사용하는 이유는
	 *    숫자가 아닌 문자열을 처리하는 경우 다시 상속해야 하는 문제가 발생하고, Criteria는
	 *    페이징 처리를 위해 존재하는 객체이므로, 매번 의미없는 bno등을 유지할 필요가 없음.
	 */
	  @RequestMapping(value = "/readPage", method = RequestMethod.GET)
	  public void read(@RequestParam("bno") int bno, 
			  		   @ModelAttribute("cri") Criteria cri, 
			  		   Model model) throws Exception {
		logger.info("readPage called......................");
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

	/*
	 * ...296p.파라미터로 Criteria를 사용해서 JSP로 page, perPageNum을 전송해서 목록 복귀.
	 */
	@RequestMapping(value = "/removePage", method = RequestMethod.POST)
	public String delete(@RequestParam("bno") int bno, 
						 Criteria cri,
						 RedirectAttributes rttr) throws Exception {

		logger.info("deletePage POST called... bno = " + bno + cri.toString());
		
		service.delete(bno);

	    rttr.addAttribute("page", cri.getPage());
	    rttr.addAttribute("perPageNum", cri.getPerPageNum());
	    rttr.addFlashAttribute("msg", "SUCCESS");		
		
		logger.info("삭제처리, bno = " + bno);

		return "redirect:/board/listPage";
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

	//...298p.수정페이지로 이동.
	@RequestMapping(value = "/modifyPage", method = RequestMethod.GET)
	public void updatePageGET(@RequestParam("bno") int bno, 
							    @ModelAttribute("cri") Criteria cri, 
							    Model model) throws Exception {
		logger.info("수정페이지로 이동, updatePage get called.../ bno = " + bno + " / cri = " + cri.toString());
		model.addAttribute(service.read(bno));
	}
	
	//...300p.수정처리.
	@RequestMapping(value = "/modifyPage", method = RequestMethod.POST)
	public String updatePagePOST(BoardVO board, 
					  			Criteria cri, 
					  			RedirectAttributes rttr) throws Exception {
		
	  logger.info("수정처리, updatePage post called.../ board = " + board.toString() + " / cri = " + cri.toString());
	  
	  service.update(board);
	  rttr.addAttribute("page", cri.getPage());
	  rttr.addAttribute("perPageNum", cri.getPerPageNum());
	  rttr.addFlashAttribute("msg", "SUCCESS");
  
	  return "redirect:/board/listPage";
	  
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
