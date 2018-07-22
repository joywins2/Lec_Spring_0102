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
				
					<!-- 299p. 페이징 처리에 대한 정보를 유지하도록 hidden 사용. -->
					<input type='hidden' name='page' value="${cri.page}"> 
					<input type='hidden' name='perPageNum' value="${cri.perPageNum}">
				
					<!--  338p. 검색 처리에 대한 정보를 유지하도록 hidden 사용. -->
					<input type='hidden' name='searchType' value="${cri.searchType}">
					<input type='hidden' name='keyword' value="${cri.keyword}">
				
					<div class="box-body">

						<div class="form-group">
							<label for="exampleInputEmail1">BNO</label> <input type="text"
								name='bno' class="form-control" value="${boardVO.bno}"
								readonly="readonly">
						</div>

						<div class="form-group">
							<label for="exampleInputEmail1">Title</label> <input type="text"
								name='title' class="form-control" value="${boardVO.title}">
						</div>

						<div class="form-group">
							<label for="exampleInputPassword1">Content</label>
							<textarea class="form-control" name="content" rows="3">${boardVO.content}</textarea>
						</div>

						<div class="form-group">
							<label for="exampleInputEmail1">Writer</label> <input type="text"
								name="writer" class="form-control" value="${boardVO.writer}">
						</div>
					</div>
					<!-- /.box-body -->

				</form>

				<div class="box-footer">
					<button type="submit" class="btn btn-primary">SAVE</button>
					<button type="submit" class="btn btn-warning">CANCEL</button>
				</div>
				<!-- /.box-footer-->


				<!-- 
					...228p.$("form[role='form']")로 선언된 formObj는 <form>태그를 의미함.
					$(".btn-warning")이벤트 처리는 지정한 페이지로 이동하도록 <form>태그 속성을
					수정하고 전송하게 함. 
				 -->
				<script>
					$(document).ready(function() {
						var formObj = $("form[role='form']");
						console.log(formObj);
						//...338p. 취소버튼을 클릭하면 원래 목록 페이지로 이동하게 함.
						$(".btn-warning").on("click",
							function() {
								self.location = "/sboard/list?page=${cri.page}&perPageNum=${cri.perPageNum}"
												+ "&searchType=${cri.searchType}&keyword=${cri.keyword}";
						});
						$(".btn-primary").on("click", function() {
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

	if (result == 'SUCCESS') {
		alert("처리가 완료되었습니다.");
	}
</script>



<%@include file="../include/footer.jsp"%>