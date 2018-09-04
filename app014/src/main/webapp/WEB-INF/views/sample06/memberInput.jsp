<%@ page language="java" contentType="text/html; charset=UTF-8"
  						 pageEncoding="UTF-8"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원 가입</title>
</head>
<body>
<div align="center">
	<h1>Validate...</h1>
	<h2>회원 가입 페이지</h2>
	<hr>
	<%--
		...주의 : <form action="/inputOk"> 와 <form action="inputOk">를 
		          테스트해보고 경로의 차이를 발견할 것.
		          /inputOk 는 localhost:8080/inputOk로 경로가 됨.	
	 --%>
	<form action="memberInputOk">
		<table border="1" cellpadding="2" cellspacing="0" width="500">
				<tr>
					<td>이름</td>
					<td><input type="text" name="name" value=""/></td>		
				</tr>
				<tr>
					<td>아이디</td>
					<td><input type="text" name ="id" value=""/></td>		
				</tr>
				<tr>
					<td>주민번호</td>
					<td><input type="text" name="memNo" value=""/></td>		
				</tr>
		</table><br/>
		<input type="submit" value="전송" />
	</form>
</div>
</body>
</html>