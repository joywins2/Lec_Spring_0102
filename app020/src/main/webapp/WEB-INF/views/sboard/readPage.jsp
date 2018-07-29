<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page session="false"%>


<%@include file="../include/header.jsp"%>
<!-- ...https://cdnjs.com/libraries/handlebars.js -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/4.0.11/handlebars.js"></script>

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

					<input type='hidden' name='bno' value="${boardVO.bno}"> bno
					= ${boardVO.bno} <input type='hidden' name='page'
						value="${cri.page}"> page = ${cri.page} <input
						type='hidden' name='perPageNum' value="${cri.perPageNum}">
					perPageNum = ${cri.perPageNum}
					<!-- ...335p. -->
					<input type='hidden' name='searchType' value="${cri.searchType}">
					searchTeype = ${cri.searchType} <input type='hidden' name='keyword'
						value="${cri.keyword}"> keyword = ${cri.keyword}
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
					<button type="submit" class="btn btn-warning" id="modifyBtn">Modify</button>
					<button type="submit" class="btn btn-danger" id="deleteBtn">REMOVE</button>
					<button type="submit" class="btn btn-primary" id="listBtn">LIST
						ALL</button>
				</div>
				<!-- /.box-footer-->

				<!-- 
					...426p. 댓글 등록, 댓글 목록, 댓글 페이징처리에 필요한 div
					...428p. 템플릿 :
							 JSRender http://www.jsviews.com/#jsrender
							 Mustache http://mustache.github.io/
							 handlebars https://handlebarsjs.com/
				-->
				<div class="row">
					<div class="col-md-12">

						<!-- ...426p. 댓글 등록에 필요한 div -->
						<div class="box box-success">
							<div class="box-header">
								<h3 class="box-title">ADD NEW REPLY</h3>
							</div>

							<div class="box-body">
								<label for="exampleInputEmail1">Writer</label> <input
									class="form-control" type="text" placeholder="USER ID"
									id="newReplyWriter"> <label for="exampleInputEmail1">Reply
									Text</label> <input class="form-control" type="text"
									placeholder="REPLY TEXT" id="newReplyText">
							</div>

							<!-- /.box-body -->
							<div class="box-footer">
								<button type="button" class="btn btn-primary" id="replyAddBtn">
									ADD REPLY</button>
							</div>
						</div>

						<!-- ...426p. 댓글 목록과 페이징 처리에 필요한 div -->
						<!-- The time line -->
						<ul class="timeline">
							<!-- timeline time label -->
							<li class="time-label" id="repliesDiv"><span
								class="bg-green"> Replies List </span></li>
						</ul>

						<div class='text-center'>
							<ul id="pagination" class="pagination pagination-sm no-margin ">
							</ul>
						</div>

					</div>
					<!-- /.col -->
				</div>
				<!-- /.row -->
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
	$(document).ready(function() {

		var formObj = $("form[role='form']");

		console.log('formObj: ', formObj);

		//$(".btn-warning").on("click", function(){//...440p.댓글추가랑 class가 겹쳐서 주석처리함.
		$("#modifyBtn").on("click", function() {
			formObj.attr("action", "/sboard/modifyPage");
			formObj.attr("method", "get");
			formObj.submit();
		});

		//$(".btn-danger").on("click", function(){//...440p.댓글추가랑 class가 겹쳐서 주석처리함.
		$("#deleteBtn").on("click", function() {
			alert("Delete Button clicked...");
			formObj.attr("action", "/sboard/removePage");
			formObj.submit();
		});

		//$(".btn-primary").on("click", function(){//...440p.댓글추가랑 class가 겹쳐서 주석처리함.
		$("#listBtn").on("click", function() {
			formObj.attr("method", "get");
			//...294p.내용조회후 다시 원래 목록 위치로 돌아가기 위한 정보추가.
			formObj.attr("action", "/sboard/list");
			formObj.submit();
		});

	});
</script>


<script>
	var result = '${msg}';

	if (result == 'SUCCESS') {
		alert("처리가 완료되었습니다.");
	}
</script>

<!-- 
	...435p. template코드는 화면상에서 하나의 댓글을 구성하는 부분임.
	   prettifyDate regdate 
-->
<script id="template" type="text/x-handlebars-template">
	{{#each .}}
		<li class="replyLi" data-rno={{rno}}>
		<i class="fa fa-comments bg-blue"></i>
		 <div class="timeline-item" >
		  	<span class="time">
		    	<i class="fa fa-clock-o"></i>{{prettifyDate regdate}}
		  	</span>

		  	<h3 class="timeline-header">
				<strong>{{rno}}</strong> -{{replyer}}
		  	</h3>

			  <div class="timeline-body">
				{{replytext}} 
			  </div>
		      <div class="timeline-footer">
		     	<a class="btn btn-primary btn-xs" 
			       data-toggle="modal" data-target="#modifyModal">
					Modify
				</a>
		      </div>
		  </div>			
		</li>
	{{/each}}
</script>


<%-- 
	...435p.prettifyDate에 대한 자바스크립트 처리. 
	   handlebars는 helper라는 기능을 이용해서 데이터의 상세한 처리에 필요한 기능들을 처리함.
	   만일 원하는 기능이 없는 경우에는 registerHelper()를 이용해서 사용자가 새로운 기능을
	   추가할 수 있음.
 --%>
<script>
	Handlebars.registerHelper("prettifyDate", function(timeValue) {
		var dateObj = new Date(timeValue);
		var year = dateObj.getFullYear();
		var month = dateObj.getMonth() + 1;
		var date = dateObj.getDate();
		return year + "/" + month + "/" + date;
	});

	var printData = function(replyArr, target, templateObject) {
		var template = Handlebars.compile(templateObject.html());
		var html = template(replyArr);
		$(".replyLi").remove();
		target.after(html);
	}
	

	//...436p. 해당 게시물에 대한 번호.
	var bno = ${boardVO.bno};
	
	//...436p. 수정이나 삭제작업 이후 사용자가 보던 댓글의 페이지 번호를 가지고 다시 목록을
	//   출력하기 위해 유지되는 데이터임.
	var replyPage = 1;

	//...436p. getPage() : 특정한 게시물에 대한 페이징 처리를 위해 호출되는 함수.
	//   페이지번호를 전달받고, 댓글의 목록 데이터를 처리함.
	//   /app020/src/main/java/org/joy/controller/ReplyController::selectPageReplies 가
	//   반환하는 data 의 list 와 pageMaker 를 받아서 댓글목록과 댓글페이징을 처리함.
	function getPage(pageInfo) {
		
		$.getJSON(pageInfo, function(data) {
			
			console.log("getPage(pageInfo) : ", pageInfo);
			console.log("getJSON(data) : ", data);
			
			printData(data.list, $("#repliesDiv"), $('#template'));
			printPaging(data.pageMaker, $(".pagination"));

			$("#modifyModal").modal('hide');

		});
	}

	var printPaging = function(pageMaker, target) {

		var str = "";

		if (pageMaker.prev) {
			str += "<li><a href='" + (pageMaker.startPage - 1)
					+ "'> << </a></li>";
		}

		for (var i = pageMaker.startPage, len = pageMaker.endPage; i <= len; i++) {
			var strClass = pageMaker.cri.page == i ? 'class=active' : '';
			str += "<li "+strClass+"><a href='"+i+"'>" + i + "</a></li>";
		}

		if (pageMaker.next) {
			str += "<li><a href='" + (pageMaker.endPage + 1)
					+ "'> >> </a></li>";
		}

		target.html(str);
	};	
	
	
	//...437p.화면상에서 'Replies List'라는 버튼을 클릭했을때 댓글 목록을 가져와서 보임.
	//   게시판 목록에서 조회글을 클릭하고 조회화면에 들어오면 댓글 목록이 'Replies List' 만
	//   보이는 상태임.
	//   목록의 size() 를 체크하는 코드는 목록을 가져오는 버튼만 있는 경우 1 페이지의 댓글 
	//   목록을 가져오게 하기 위함 : 최초로 'Replies List'를 클릭한 때는 댓글 목록이 접혀서
	//   없는 상태임.
	//...ajax를 사용해서 JSON으로 값을 받아와서 jQuery로 화면에 표시하므로 JSON으로 값을
	//   처리해주는 별도의 @RestController ReplyController selectPageReplies를 호출함.
	$("#repliesDiv").on("click", function() {

		if ($(".timeline li").size() > 1) {
			return;
		}
		getPage("/replies/" + bno + "/1");

	});
		
	
	//...438p.페이징 처리의 코드는 ul class = 'pagination' 에서 이뤄짐.
	$(".pagination").on("click", "li a", function(event){
		
		event.preventDefault();
		
		replyPage = $(this).attr("href");
		
		getPage("/replies/"+bno+"/"+replyPage);
		
	});
	
</script>

<%@include file="../include/footer.jsp"%>