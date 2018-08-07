<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page session="false"%>


<%@include file="../include/header.jsp"%>

<!-- Main content -->
<section class="content">
	<div class="row">
		<!-- left column -->
		<div class="col-md-12">
			<!-- general form elements -->

			<div class="box">
				<div class="box-header with-border">
					<h3 class="box-title">listAll Posting Page</h3>
				</div>
				<!-- /.box-header -->
				
				<div class="box-body">
				
					<table class="table table-bordered">
						<tr>
							<th style="width: 10px">BNO</th>
							<th>TITLE</th>
							<th>WRITER</th>
							<th>REGDATE</th>
							<th style="width: 40px">VIEWCNT</th>
						</tr>

<!-- 
	...JSTL Reference :
		https://www.developer.com/java/ejb/article.php/1447551/An-Introduction-to-JSP-Standard-Template-Library-JSTL.htm
		https://www3.ntu.edu.sg/home/ehchua/programming/java/JavaServerPages.html#zz-10.
		https://svn.apache.org/repos/asf/tomcat/taglibs/standard/branches/bayern/doc/web/ExpressionLanguageSupport_FunctionalDescription_2_EA1.html		
		http://firedev.tistory.com/entry/JSTL-fmt-%EB%9D%BC%EC%9D%B4%EB%B8%8C%EB%9F%AC%EB%A6%AC-%EC%82%AC%EC%9A%A9-%EC%98%88%EC%A0%9C-%EC%88%AB%EC%9E%90-%ED%8F%AC%EB%A7%B7-%EB%82%A0%EC%A7%9C-%ED%8F%AC%EB%A7%B7
		http://blog.naver.com/PostView.nhn?blogId=lbiryu&logNo=30037958388		 
-->

					<c:forEach items="${list}" var="boardVO">
					
						<tr>
							<td>${boardVO.bno}</td>
							<td><a href='/board/read?bno=${boardVO.bno}'>${boardVO.title}</a></td>
							<td>${boardVO.writer}</td>
							<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm"
												value="${boardVO.regdate}" /></td>
							<td><span class="badge bg-red">${boardVO.view_count }</span></td>
						</tr>
					
					</c:forEach>					
					
					</table>				
				</div>
				<!-- /.box-body -->
				
				<div class="box-footer">Footer</div>
				<!-- /.box-footer-->
				
			</div>
			<!-- /.box -->
		</div>
		<!--/.col (left) -->
	</div>
	<!-- /.row -->
</section>
<!-- /.content -->
</div>
<!-- /.content-wrapper -->

<script>    
    var result = '${msg}';
    
    if(result == 'SUCCESS'){
    	alert("처리가 완료되었습니다.");
    }    
</script>



<%@include file="../include/footer.jsp"%>