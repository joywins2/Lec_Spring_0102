<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>@RequestParam를 사용한 페이지 입니다!!!!</h3>
	
	@RequestParam을 이용하여 파라미터를 각각 받는 대신,<br>
	Model 필요없이 Person Domain 객체를 이용하여 한번에<br> 
	파라미터를 받을 수 있어서 같은 결과임.<br><br>

	이름 : ${person.name}<br/>
	아이디 :${person.id}<br/>
	주소 : ${person.address}<br/>
	이메일 : ${person.email}
	
</body>
</html>