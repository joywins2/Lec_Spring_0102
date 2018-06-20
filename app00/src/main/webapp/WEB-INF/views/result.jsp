<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

 <span>...109p.zweb.Welcome to ${msg}</span>
 <span>환영합니다! ${msg}</span>
 <h5>
 span태그를 보면 JSP의 EL 을 사용하여 \${msg}를 출력합니다.
 스프링 MVC 의 @ModelAttribute 는 자동으로 해당 객체를 뷰까지 전달합니다.
 </h5>
 <hr/>

</body>
</html>