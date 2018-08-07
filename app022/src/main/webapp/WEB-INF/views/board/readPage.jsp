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
				

				<form role="form" method="post">
					<!-- 
						...220p.나중에 수정, 삭제 작업에 사용되므로 반드시 원래 게시물 번호인 bno를
						...가지고 있도록 hidden속성으로 bno값을 처리함.
					 -->
					<input type='hidden' name='bno' value="${boardVO.bno}"> bno = ${boardVO.bno}
				<%--
					...294p.내용조회후 다시 원래 목록 위치로 돌아가기 위한 정보추가.
					  @RequestMapping(value = "/readPage", method = RequestMethod.GET)
					  public void read(@RequestParam("bno") int bno, 
							  		   @ModelAttribute("cri") Criteria cri, 
							  		   Model model)	
				 --%>	
				    <input type='hidden' name='page' value ="${cri.page}"> page = ${cri.page}
				    <input type='hidden' name='perPageNum' value ="${cri.perPageNum}"> perPageNum = ${cri.perPageNum}
				    
				
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
				<!-- 
					...222p.$("form[role='form']")로 선언된 formObj는 <form>태그를 의미함.
					   $(".btn-warning")이벤트 처리는 수정할 수 있는 페이지로 이동하도록 <form>태그 속성을
					   수정하고 전송하게 함.
					...버튼을 눌러도 반응이 없을때는 jQuery 경로 확인할 것.
					   /app010/src/main/webapp/resources/plugins/jQuery/jQuery-2.1.4.min.js
				 -->
				<script>				
					$(document).ready(function(){
						
						var formObj = $("form[role='form']");
						
						console.log('formObj: ', formObj);
						
						$(".btn-warning").on("click", function(){
							//formObj.attr("action", "/board/modify");//...read.jsp, 298p.주석.
							//...수정화면으로 이동(get).
							formObj.attr("action", "/board/modifyPage");
							formObj.attr("method", "get");		
							formObj.submit();
						});					
					
						$(".btn-danger").on("click", function(){
							alert("Delete Button clicked...");
							//formObj.attr("action", "/board/remove");; //...read.jsp, 298p.주석.
							formObj.attr("action", "/board/removePage");
							formObj.submit();
						});
						
						$(".btn-primary").on("click", function(){
							//self.location = "/board/listAll"; //...read.jsp, 294p 주석.
							
							//...294p.내용조회후 다시 원래 목록 위치로 돌아가기 위한 정보추가.
							formObj.attr("method", "get");
							formObj.attr("action", "/board/listPage");
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