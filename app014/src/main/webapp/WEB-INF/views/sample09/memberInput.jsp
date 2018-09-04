<%@ page language="java" contentType="text/html; charset=UTF-8"
  						 pageEncoding="UTF-8"%>   
    
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<h2>Form tag 라이브러리 사용...</h2>

<h3>회원 정보 입력</h3>

<form:form action="memInputPOST" memthod="POST">
	<table>
		<tr>
			<!--
			<td><form:label path="name">이름</form:label></td> 
			 -->			
			<td>이름</td>
			<td><form:input path="name"/></td>
		</tr>
		<tr>
			<td>아이디</td>
			<td><form:input path="id"/></td>
		</tr>
		<tr>
			<td>이메일</td>
			<td><form:input path="email"/></td>
		</tr>
		<tr>
			<td>전화번호</td>
			<td><form:input path="hp" /></td>			
		</tr>
		
		<tr>
			<td colspan="2">
				<input type="submit" value="전송" />
			</td>
		</tr>
		
	</table>
</form:form> 

</body>
</html>