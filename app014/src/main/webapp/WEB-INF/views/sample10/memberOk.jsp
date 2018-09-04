<%@ page language="java" contentType="text/html; charset=UTF-8"
  						 pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원 가입 성공</title>
</head>
<body>
<h2>회원 가입 성공</h2>
<hr />
회원 정보 <br /><br />

이름 :${member.name} <br />
아이디 :${member.id} <br />
주소 :${member.address.address1} <br />
${member.address.address2}
 

</body>
</html>