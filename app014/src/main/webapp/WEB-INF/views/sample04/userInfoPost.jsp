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
<h2>${msg}</h2>
<hr />

<form action="testExtMdMap">
	<table border="1" cellpadding="2" cellspacing="0">
	  <tr>
	  	<td>이름</td>
	  	<td>${userVO.userName}</td>
	  </tr>
	  <tr>
	  	<td>주소</td>
	  	<td>${userVO.address}</td>
	  </tr>
	  <tr>
	  	<td>자동차</td>
		<td>
			<c:forEach var="maker" items="${mdaCarMaker}">
				${maker}, &nbsp;
			</c:forEach>
		</td>
	  </tr>
	</table>
	
	<input type="submit" value="뷰로 이동"/>
</form>
</div>
</body>
</html>