package org.joy.interceptor;

import javax.inject.Inject;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.joy.domain.UserVO;
import org.joy.service.IF_UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.WebUtils;

public class AuthInterceptor extends HandlerInterceptorAdapter {

  private static final Logger logger = LoggerFactory.getLogger(AuthInterceptor.class);
  
  @Inject
  private IF_UserService service;
  
  @Override
  public boolean preHandle(HttpServletRequest request,
      HttpServletResponse response, Object handler) throws Exception {
    
    HttpSession session = request.getSession();   


    if(session.getAttribute("login") == null){
    
      logger.info("current user is not logined");
      
      /*...645p.
       *   로그인하기 전 사용자가 이동하고자 한 페이지를 기억했다가
       *   로그인한 후 처음 이동하고자 한 페이지로 이동하게 함.
       */
      saveDest(request);

      response.sendRedirect("/user/login");
      return false;
    }
    return true;
  }
  

  private void saveDest(HttpServletRequest req) {

    String uri = req.getRequestURI();

    String query = req.getQueryString();

    if (query == null || query.equals("null")) {
      query = "";
    } else {
      query = "?" + query;
    }

    if (req.getMethod().equals("GET")) {
      logger.info("dest: " + (uri + query));
      req.getSession().setAttribute("dest", uri + query);
    }
  }

//  @Override
//  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//
//    HttpSession session = request.getSession();
//
//    if (session.getAttribute("login") == null) {
//
//      logger.info("current user is not logined");
//
//      saveDest(request);
//      
//      response.sendRedirect("/user/login");
//      return false;
//    }
//    return true;
//  }
}

// if(session.getAttribute("login") == null){
//
// logger.info("current user is not logined");
// response.sendRedirect("/user/login");
// return false;
// }
