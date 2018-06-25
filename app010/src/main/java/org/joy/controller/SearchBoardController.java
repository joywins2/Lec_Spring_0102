package org.joy.controller;

import javax.inject.Inject;

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


}
