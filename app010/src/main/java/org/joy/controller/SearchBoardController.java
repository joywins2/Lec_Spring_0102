package org.joy.controller;

import javax.inject.Inject;

import org.joy.domain.BoardVO;
import org.joy.domain.PageMaker;
import org.joy.domain.SearchCriteria;
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

//...306p.

@Controller
@RequestMapping("/sboard/*")//...306p.
public class SearchBoardController {

	  private static final Logger logger = LoggerFactory.getLogger(SearchBoardController.class);

	  @Inject
	  private IF_BoardService service;
	  
	  //...306p.
	  //...108p. SearchCriteria를 @ModelAttribute를 사용하기 때문에 listPage.jsp에 자동으로 전달됨.
	  @RequestMapping(value = "/list", method = RequestMethod.GET)
	  public void listPage(@ModelAttribute("cri") SearchCriteria cri, Model model) throws Exception {

	    logger.info("list is called... cri = " + cri.toString());

	    //model.addAttribute("list", service.listCriteria(cri));//...261p.
	    model.addAttribute("list", service.listSearchCriteria(cri)); //...332p.

	    PageMaker pageMaker = new PageMaker();
	    pageMaker.setCri(cri);

	    //pageMaker.setTotalCount(service.countBno(cri));//...280p.service.countBno(cri)
	    pageMaker.setTotalCount(service.listSearchCount(cri));//...332p.

	    model.addAttribute("pageMaker", pageMaker);
	    
	  }

	  //...335p.
	  @RequestMapping(value = "/readPage", method = RequestMethod.GET)
	  public void read(@RequestParam("bno") int bno, 
			  			@ModelAttribute("cri") SearchCriteria cri, 
			  			Model model)
	      throws Exception {
		  logger.info("readPage GET called... bno = " + bno + " // cri = " + cri.toString() + " // model = " + model.toString());
		  model.addAttribute(service.read(bno));
	  }
	  
	  //...336p.
	  @RequestMapping(value = "/removePage", method = RequestMethod.POST)
	  public String delete(@RequestParam("bno") int bno, 
			  				SearchCriteria cri, 
			  				RedirectAttributes rttr) throws Exception {
		logger.info("removePage called... bno = " + bno + " // cri = " + cri.toString());
	    service.delete(bno);

	    rttr.addAttribute("page", cri.getPage());
	    rttr.addAttribute("perPageNum", cri.getPerPageNum());
	    rttr.addAttribute("searchType", cri.getSearchType());
	    rttr.addAttribute("keyword", cri.getKeyword());

	    rttr.addFlashAttribute("msg", "SUCCESS");

	    return "redirect:/sboard/list";
	  }
	  
	  //...337p.
	  @RequestMapping(value = "/modifyPage", method = RequestMethod.GET)
	  public void modifyPageGET(int bno, 
			  					@ModelAttribute("cri") SearchCriteria cri, 
			  					Model model) throws Exception {
		logger.info("updatePage GET called... bno = " + bno + " // cri = " + cri.toString() + " // model = " + model.toString());
	    model.addAttribute(service.read(bno));
	  }  

	  @RequestMapping(value = "/modifyPage", method = RequestMethod.POST)
	  public String modifyPagePOST(BoardVO board, 
			  						SearchCriteria cri, 
			  						RedirectAttributes rttr) throws Exception {

	    logger.info("modifyPagePOST called... board = " + board.toString() + " // cri = " + cri.toString());
	    service.update(board);

	    rttr.addAttribute("page", cri.getPage());
	    rttr.addAttribute("perPageNum", cri.getPerPageNum());
	    rttr.addAttribute("searchType", cri.getSearchType());
	    rttr.addAttribute("keyword", cri.getKeyword());

	    rttr.addFlashAttribute("msg", "SUCCESS");

	    logger.info(rttr.toString());

	    return "redirect:/sboard/list";
	  }

	  //...339p.
	  @RequestMapping(value = "/create", method = RequestMethod.GET)
	  public void registerGET() throws Exception {

	    logger.info("create get ...........");
	  }  
	  
	  @RequestMapping(value = "/create", method = RequestMethod.POST)
	  public String registerPOST(BoardVO board, RedirectAttributes rttr) throws Exception {

	    logger.info("create post ... board = " + board.toString());

	    service.create(board);

	    rttr.addFlashAttribute("msg", "SUCCESS");

	    return "redirect:/sboard/list";
	  }



}
