<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Input Form</title>
</head>
<body>
<div align="center">

	<hr width="400" color="red"/>
	<h2>개인 정보 입력 GET</h2>
	<hr width="400" color="red"/>
	
	<form action="person" method="GET">
		이름 : <input type="text" name="name"/><br/>
		아이디 : <input type="text" name="id" /><br/>
		<input type="submit" value="전송"/>
	</form>	
</div>
</body>
</html>