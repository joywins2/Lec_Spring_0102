<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<html>
<head>
	<title>...523p.uploadForm</title>
<!-- 
	...533p
	업로드된 결과 역시 현재 창에서 바로 확인할 수 있도록 함.
	form태그의 전송은 기본적으로 화면전환을 피할 수 없는데, 
	form태그에 target속성을 주고 iframe을 이용하면 화면 전환효과를 없앨 수 있음.
	form태그의 전송은 iframe을 이용하고, iframe내의 결과페이지는 다시 바깥쪽
	parent의 Javascript를 호출함. 
 -->
<style>
iframe {
	width: 0.5px;
	height: 0.5px;
	border: 0.5px
}
</style>
</head>
<body>
<h1>
	...523p.uploadForm
</h1>
	 
	<form id='form1' action="uploadForm" method="post"
		  enctype="multipart/form-data"
		  target="zFrame">
		<input type='file' name='file'> 
		<input type='submit'>
	</form>
	
	<iframe name="zFrame"></iframe>
	
	<script>
		function addFilePath(msg) {
			alert(msg);
			document.getElementById("form1").reset();
		}
	</script>
	 
	
</body>
</html>
