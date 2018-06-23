package org.joy.controller;

import javax.inject.Inject;

import org.joy.domain.BoardVO;
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

//...192p.

/*
 * @Controller로 설정하고, '/board/'로 오는 모든 요청을 처리한다. 
 * 
 * ...190p.Get방식 or Post방식 결정.
 * Get/Post방식, URI, 작동기능의 조합으로 스토리보드에 미리 URI와 Get/Post방식을 정해야 함.
 * 1. Get방식 : 외부나 다른 사람에게 메신저등으로 보낼 수 있게 하려면 반드시 Get방식 처리함.
 *              조회가 가능하도록 만들어야 하는 모든 경우 Get방식 처리해야 함.
 * 2. Post방식 : 현재 사용자가 스스로 작업하는 내용이 있는 경우 사용함.
 *              외부에 노출하는 것이 아닌 사용자 본인이 결정해서 어떤 작업을 진행하는 경우.
 * 
 * ...191p.리다이렉트 처리방식.
 * 등록/수정/삭제작업이 끝나면 어떻게 결과를 알려주고 페이지를 이동하는지에 대해서 
 * 우리가 사용하는 대부분의 페이지는 Ajax 또는 REST방식으로 처리해서 멋지게 작동하지만,
 * 경험이 없을수록 목표를 단순하게 잡고 최대한 단순한 페이지구성과 많은 화면 전환을 사용해야 함. 
 * 
 * ...193p. 스프링 MVC 메서드의 파라미터와 리턴타입 결정시 고려사항.
 * 1. 파라미터의 수집은 스프링MVC에서 자동으로 이뤄지므로, 파라미터 수집이 필요하면
 *    원하는 객체를 파라미터로 선언한다.
 * 2. DTO클래스를 파라미터로 사용하는 것이 편리하다.
 * 3. 브라우저에서 들어오는 요청(request)이 자동으로 파라미터로 지정한 클래스 객체 속성값으로
 *    처리되는데 이를 바인딩이라고 한다.
 * 4. 스프링MVC Model객체는 해당 메서드에서 뷰(jsp...)에 필요한 데이터를 전달하는 용도로
 *    사용되므로, 만일 메서드 내에서 뷰로 전달할 데이터가 있다면, Model을 파라미터로 선언
 *    해주는 것이 편리하다.
 */
@Controller
@RequestMapping("/board/*")
public class BoardController {
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);

	@Inject
	private IF_BoardService service;

	/*
	 * ...194p.등록작업은 등록페이지로 이동(Get방식)과 데이터를 처리(Post방식)으로 구분됨.
	 */
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public void createGET(BoardVO board, Model model) throws Exception {
		logger.info("createGet called ...........");

	}

	/*
	 * ...210p.위와 같이 Model을 이용한 경우 동일한 내용의 도배현상은 막을 수 있으나, ...새로고침할 때
	 * /zboard/listAll?result=success은 계속 남아있는 문제점이 남음.
	 * ...Spring.RedirectAttributes객체는 리다이렉트 시점에 한번만 사용되는 데이터를 ...전송할 수 있는
	 * addFlashAttribute()를 지원하여 페이지이동 및 데이터 전달후
	 * ...http://localhost:8080/board/listAll로 깔끔하게 정리됨.
	 */
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String createPOST(BoardVO board, RedirectAttributes rattr) throws Exception {

		logger.info("create post addFlashAttribute...........");
		logger.info(board.toString());

		service.create(board);

		// ...http://cafe.naver.com/gugucoding/1697
		rattr.addFlashAttribute("msg", "SUCCESS");

		return "redirect:/board/listAll";

	}

	/*
	 * 
	 * ...108p, 309p.http://localhost:8080/web/doC?msg=Hi5
	 * 
	 * @ModelAttribute 는 자동으로 해당 객체를 뷰까지 전달함.
	 * ...http://cafe.naver.com/gugucoding/134 ...112p. Model 클래스 : 스프링 MVC 가 기본으로
	 * 제공함. 뷰에 원하는 데이터를 담아서 전달하는 상자 역할.
	 */
	@RequestMapping(value = "/listAll", method = RequestMethod.GET)
	public void listAll(Model model, @ModelAttribute("msg") String msg) throws Exception {
		logger.info("show all list1......................");
		model.addAttribute("list", service.listAll());
	}

	/*
	 * ...219p.@ReqeustParam("bno")는 Servlet::request.getParam("bno")처럼 동작함.
	 * ...@RequestParam을 사용하여 외부에서 전달될 bno값을 파라미터로 받는 것을 더욱
	 * ...명확하게 표현함.
	 * ...Servlet.HttpServletRequest와 다른 점은 문자열, 숫자, 날짜 등의 형변환이 가능함.
	 * ...Model.addAttribute()작업할때 아무런 이름 없이 데이터를 넣으면 자동으로
	 * ...클래스의 이름을 소문자로 시작해서 사용한다.
	 * ...BoardVO org.joywins.service.IF_BoardService.read(Integer bno) throws Exception처럼
	 * ...여기서 들어가는 데이터는 BoardVO객체이므로 boardVO라는 이름으로 저장됨.
	 */
	@RequestMapping(value = "/read", method = RequestMethod.GET)
	public void read(@RequestParam("bno") int bno, Model model) throws Exception {
		model.addAttribute(service.read(bno));
	}

}
