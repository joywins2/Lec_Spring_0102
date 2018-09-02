<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<div align="center">
	<h2>로그인</h2>
	<hr>
	<form action="submitId">
	<table border="1" cellpadding="2" cellspacing="0">
		<tr>
			<td>아이디</td>
			<td><input type="text" name="id"/></td>
		</tr>
	</table>
	<input type="submit" value="로그인" />
	</form>
</div>
</body>
</html>