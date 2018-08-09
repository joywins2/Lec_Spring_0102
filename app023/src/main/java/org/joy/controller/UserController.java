package org.joy.controller;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.joy.domain.UserVO;
import org.joy.dto.LoginDTO;
import org.joy.service.IF_UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.WebUtils;

/*
 * ...635p.
 *    HttpSession 객체의 처리 장소? 컨트롤러 vs 인터셉터.
 *    컨트롤러 : 순수한 데이터 처리에 집중.
 *    인터셉터 : HttpSession 객체 처리.
 */
@Controller
@RequestMapping("/user")
public class UserController {
	
	private static Logger logger =  LoggerFactory.getLogger(UserController.class);

  @Inject
  private IF_UserService service;

  @RequestMapping(value = "/login", method = RequestMethod.GET)
  public void loginGET(@ModelAttribute("dto") LoginDTO dto) {

  }

  // @RequestMapping(value = "/loginPost", method = RequestMethod.POST)
  // public void loginPOST(LoginDTO dto, HttpSession session, Model model)
  // throws Exception {
  //
  // UserVO vo = service.login(dto);
  //
  // if (vo == null) {
  // return;
  // }
  //
  // model.addAttribute("userVO", vo);
  //
  // }

  @RequestMapping(value = "/loginPost", method = RequestMethod.POST)
  public void loginPOST(LoginDTO dto, HttpSession session, Model model) throws Exception {

	System.out.println("===============================");
	System.out.println("UserController: uid : "+ dto.getUid());
	System.out.println("UserController: upw : "+ dto.getUpw());
	System.out.println("===============================");
	
	  
    UserVO vo = service.login(dto);


    if (vo == null) {
      logger.info("loginPost faile... ");
      return;
    }else {
      logger.info("vo : " + vo.toString());
      model.addAttribute("userVO", vo);    	
    }


/*    if (dto.isUseCookie()) {

      int amount = 60 * 60 * 24 * 7;

      Date sessionLimit = new Date(System.currentTimeMillis() + (1000 * amount));

      service.keepLogin(vo.getUid(), session.getId(), sessionLimit);
    }*/

  }
  
}