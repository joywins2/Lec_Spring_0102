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
					<h3 class="box-title">read Posting Page</h3>
				</div>
				<!-- /.box-header -->
				
				<form role="form" action="modifyPage" method="post">
				
					<input type='hidden' name='bno' value="${boardVO.bno}">
					bno = ${boardVO.bno}		
				    <input type='hidden' name='page' value ="${cri.page}">
				    page = ${cri.page}
				    <input type='hidden' name='perPageNum' value ="${cri.perPageNum}">
				    perPageNum = ${cri.perPageNum}
				<!-- ...335p. -->
				    <input type='hidden' name='searchType' value="${cri.searchType}">
					<input type='hidden' name='keyword' value="${cri.keyword}">
					
				</form>			
				
				<div class="box-body">
					<div class="form-group">
						<label for="exampleInputEmail1">Title</label> <input type="text"
							name='title' class="form-control" value="${boardVO.title}"
							readonly="readonly">
					</div>
					<div class="form-group">
						<label for="exampleInputPassword1">Content</label>
						<textarea class="form-control" name="content" rows="3"
							readonly="readonly">${boardVO.content}</textarea>
					</div>
					<div class="form-group">
						<label for="exampleInputEmail1">Writer</label> <input type="text"
							name="writer" class="form-control" value="${boardVO.writer}"
							readonly="readonly">
					</div>
				</div>
				<!-- /.box-body -->
				
				<div class="box-footer">
					<button type="submit" class="btn btn-warning">Modify</button>
					<button type="submit" class="btn btn-danger">REMOVE</button>
					<button type="submit" class="btn btn-primary">LIST ALL</button>
				</div>
				<!-- /.box-footer-->
				
				<script>				
					$(document).ready(function(){
						
						var formObj = $("form[role='form']");
						
						console.log('formObj: ', formObj);
						
						$(".btn-warning").on("click", function(){
							formObj.attr("action", "/sboard/modifyPage");
							formObj.attr("method", "get");		
							formObj.submit();
						});					
					
						$(".btn-danger").on("click", function(){
							alert("Delete Button clicked...");
							formObj.attr("action", "/sboard/removePage");
							formObj.submit();
						});
						
						$(".btn-primary").on("click", function(){
							formObj.attr("method", "get");
							formObj.attr("action", "/sboard/list");
							formObj.submit();		
						});
						
					});
				</script>
				
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