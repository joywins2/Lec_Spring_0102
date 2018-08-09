<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<%@include file="../include/header.jsp"%>

<script type="text/javascript" src="../resources/zjs/upload.js"></script>
<!-- ...https://cdnjs.com/libraries/handlebars.js -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/4.0.11/handlebars.js"></script>

<!-- 
	...602p.
	일반파일의 경우 컨트롤러에서 자동으로 MIME타입을 다운로드함.
	원본 이미지의 경우, 현재 화면이 전환되면서 이미지가 보여지므로 별도 이벤트 처리가 필요함.
	원본이미지의 경우 CSS를 이용해서 화면 맨 앞쪽으로 보여지게 처리함.
	이미지파일명을 클릭하면 큰 크기로 보여줌.
-->
<style type="text/css">
.popup {position: absolute;}
.back { background-color: gray; opacity:0.5; width: 100%; height: 300%; overflow:hidden;  z-index:1101;}
.front {z-index:1110; opacity:1; boarder:1px; margin: auto;}
.show{
		position:relative;
		max-width: 1200px; 
		max-height: 800px; 
		overflow: auto;       
	}   	
</style>

<div class='popup back' style="display:none;"></div>
<div id="popup_front" class='popup front' style="display:none;">
	<img id="popup_img">
</div>
	

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
					bno= ${boardVO.bno} 
					<input type='hidden' name='page' value="${cri.page}"> 
					page = ${cri.page} 
					<input type='hidden' name='perPageNum' value="${cri.perPageNum}">
					perPageNum = ${cri.perPageNum}
					<!-- ...335p. -->
					<input type='hidden' name='searchType' value="${cri.searchType}">
					searchTeype = ${cri.searchType} 
					<input type='hidden' name='keyword' value="${cri.keyword}"> 
					keyword = ${cri.keyword}
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
				    
				    <div><hr></div>
				    
					<%--...601p.이미 업로드된 파일들이 보여지는 영역 --%>
				    <ul class="mailbox-attachments clearfix uploadedList"></ul>
				    
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
							<li class="time-label" id="repliesDiv">
								<span class="bg-green"> 
									Replies List
									<!-- ...510p.댓글목록갯수. -->  
									<small id='reply_countSmall'> [ ${boardVO.reply_cnt} ] </small> 
								</span>
							</li>
						</ul>

						<div class='text-center'>
							<ul id="pagination" class="pagination pagination-sm no-margin ">
							</ul>
						</div>

					</div>
					<!-- /.col -->
				</div>
				<!-- /.row -->

				<!-- ...442p. 수정과 삭제를 위한 Modal 창 -->
				<div id="modifyModal" class="modal modal-primary fade" role="dialog">
					<div class="modal-dialog">
						<!-- Modal content-->
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal">&times;</button>
								<h4 class="modal-title"></h4>
							</div>
							<div class="modal-body" data-rno>
								<p>
									<input type="text" id="replytext" class="form-control">
								</p>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-info" id="replyModBtn">Modify</button>
								<button type="button" class="btn btn-danger" id="replyDelBtn">DELETE</button>
								<%--
								...https://www.w3schools.com/bootstrap/bootstrap_modal.asp
								1. To trigger the modal window, you need to use a button or a link.
									Then include the two data-* attributes:									
									data-toggle="modal" opens the modal window
									data-target="#myModal" points to the id of the modal
								2. The <button> inside the header has a data-dismiss="modal" attribute
								   which closes the modal if you click on it.
								 --%>
								<button type="button" class="btn btn-default"
									data-dismiss="modal">Close</button>
							</div>
						</div>
					</div>
				</div>

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


<%--...602p.이미 업로드된 첨부파일을 보여주기 위한 handlebars의 템플릿. --%>
<script id="templateAttach" type="text/x-handlebars-template">
<li data-src='{{fullName}}'>
  <span class="mailbox-attachment-icon has-img">
	<img src="{{imgsrc}}" alt="Attachment">
  </span>

  <div class="mailbox-attachment-info">
	<a href="{{getLink}}" class="mailbox-attachment-name">{{fileName}}</a>
	</span>
  </div>
</li>                
</script>  


<script>
	$(document).ready(function() {

		var formObj = $("form[role='form']");

		//console.log('formObj: ', formObj);

		//$(".btn-warning").on("click", function(){//...440p.댓글추가랑 class가 겹쳐서 주석처리함.
		$("#modifyBtn").on("click", function() {
			formObj.attr("action", "/sboard/modifyPage");
			formObj.attr("method", "get");
			formObj.submit();
		});

		//$(".btn-danger").on("click", function(){//...440p.댓글추가랑 class가 겹쳐서 주석처리함.
		$("#deleteBtn").on("click", function() {
			alert("Delete Button clicked...");
			
			//...611p.S.게시물삭제와 첨부파일삭제의 화면처리.
			var replyCnt =  $("#reply_countSmall").html();			

			if(replyCnt > 0 ){
				alert("댓글이 달린 게시물을 삭제할 수 없습니다.");
				return;
			}	

			/*...611p.
			     현재 첨부파일의 이름을 배열로 작성해서, 
			     Ajax 로 /deleteAllFiles 호출해서 첨부파일을 삭제 처리한 후,
			     /sboard/removePage 로 DB 삭제 처리함.
			 */
			var arr = [];
			$(".uploadedList li").each(function(index){
				 arr.push($(this).attr("data-src"));
			});

			if(arr.length > 0){
				$.post("/deleteAllFiles",{files:arr}, function(){
					//...성공여부와 상관없이 다음 처리로 넘어감.
				});
			}
			//...611p.E.게시물삭제와 첨부파일삭제의 화면처리.
			
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
		
		//...S.601p.첨부파일에 대한 템플릿 처리.
		var bno = ${boardVO.bno};
		var template = Handlebars.compile($("#templateAttach").html());
		
		$.getJSON("/sboard/listAttach/"+bno, function(list){
			$(list).each(function(){
				
				var fileInfo = getFileInfo(this);
				
				var html = template(fileInfo);
				
				 $(".uploadedList").append(html);
				
			});
		});
		//...E.601p.첨부파일에 대한 템플릿 처리.

	});

	/*
	 * ...S.604p.첨부파일 이미지 클릭 이벤트로 이미지 확대해서 슬라이드로 보이기/숨기기.
	   첨부파일이 이미지인 경우, 원본파일의 경로를 특정한 div에 img 객체로
	   만들어서 넣은 후 해당 div를 맨 앞쪽으로 보여주게 처리함.
	   사용자가 첨부파일의 제목을 클릭한 경우 해당파일이 이미지인지 체크하면,
	   화면이동을 못하도록 event.preventDefault()로 막음.
	   현재 클릭한 이미지의 경로를 id속성값이 'popup_img'인 요소에 추가함.
	   추가된 뒤에 화면에 보이도록 jQuery.show()를 호출하고, 필요한 CSS를 추가함.
	   첨부파일을 클릭하면 원본파일을 천천히 화면이 열리면서 보여줌.
	   화면에 원본 이미지가 보여진 후 다시 한번 사용자가 클릭하면 이미지가 사라짐.
	 */
	$(".uploadedList").on("click", ".mailbox-attachment-info a", function(event){
		
		var fileLink = $(this).attr("href");
		
		if(checkImageType(fileLink)){
			
			event.preventDefault();
					
			var imgTag = $("#popup_img");
			imgTag.attr("src", fileLink);
			
			console.log(imgTag.attr("src"));
					
			$(".popup").show('slow');
			imgTag.addClass("show");		
		}	
	});

	$("#popup_img").on("click", function(){
		
		$(".popup").hide('slow');
		
	});
	// ...E.604p.첨부파일 이미지 클릭 이벤트로 이미지 확대해서 슬라이드로 보이기/숨기기.
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
	//   ReplyController::selectPageReplies 가 반환하는 data 의 list 와 pageMaker 를
	//   받아서 댓글목록과 댓글페이징을 처리함.
	function getPage(pageInfo) {

		$.getJSON(pageInfo, function(data) {

			console.log("getPage(pageInfo) : ", pageInfo);
			console.log("getJSON(data) : ", data);

			printData(data.list, $("#repliesDiv"), $('#template'));
			printPaging(data.pageMaker, $(".pagination"));
			
			/*
			...510p.
			   댓글 삭제 이벤트 에서$("#replyDelBtn").on("click",function()
			   다시 getPage() 할 때 댓글 갯수를 갱신시켜 줌.
			*/
			$("#reply_countSmall").html("[ " + data.pageMaker.totalCount +" ]");//...510p.

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
	$(".pagination").on("click", "li a", function(event) {

		event.preventDefault();

		replyPage = $(this).attr("href");

		getPage("/replies/" + bno + "/" + replyPage);

	});

	//...440p. 댓글추가 이벤트 처리.
	$("#replyAddBtn").on("click", function() {

		var replyerObj = $("#newReplyWriter");
		var replytextObj = $("#newReplyText");
		var replyer = replyerObj.val();
		var replytext = replytextObj.val();

		$.ajax({
			type : 'post',
			url : '/replies/',
			headers : {
				"Content-Type" : "application/json",
				"X-HTTP-Method-Override" : "POST"
			},
			dataType : 'text',
			data : JSON.stringify({
				bno : bno,
				replyer : replyer,
				replytext : replytext
			}),
			success : function(result) {
				//console.log("replyAddBtn clicked result: " + result);
				if (result == 'SUCCESS') {
					alert("등록 되었습니다.");
					replyPage = 1;
					getPage("/replies/" + bno + "/" + replyPage);
					replyerObj.val("");
					replytextObj.val("");
				}
			}
		});
	});

	//...댓글 수정 버튼 이벤트.
	$("#replyModBtn").on("click", function() {

		var rno = $(".modal-title").html().trim();
		var replytext = $("#replytext").val().trim();
		//console.log('수정이벤트 replytext :', replytext);

		$.ajax({
			type : 'put',
			url : '/replies/' + rno,
			headers : {
				"Content-Type" : "application/json",
				"X-HTTP-Method-Override" : "PUT"
			},
			data : JSON.stringify({
				replytext : replytext //...데이터 전달.
			}),
			dataType : 'text',
			success : function(result) {
				//console.log("result: " + result);
				if (result == 'SUCCESS') {
					alert("수정 되었습니다.");
					getPage("/replies/" + bno + "/" + replyPage);
				}
			}
		});
	});

	//...댓글 삭제 버튼 이벤트.
	$("#replyDelBtn").on("click",function(){
		  
		  var rno = $(".modal-title").html().trim();
		  var replytext = $("#replytext").val().trim();
		  
		  $.ajax({
				type:'delete',
				url:'/replies/'+rno,
				headers: { 
				      "Content-Type": "application/json",
				      "X-HTTP-Method-Override": "DELETE" },
				dataType:'text', 
				success:function(result){
					console.log("result: " + result);
					if(result == 'SUCCESS'){
						alert("삭제 되었습니다.");
						getPage("/replies/"+bno+"/"+replyPage );
					}
			}});
	});	

	
	//...443p. 각 댓글의 버튼 이벤트 처리.
	//...실제 댓글목록에 관한 소스에는 다음과 같은 부분이 대상이 된다.
	/*
		<div class = "timeline-footer">
			<a class="btn btn-primary btn-xs" data-toggle="modal" data-target="#modifyModal">
				Modify
			</a>
		</div>
		처럼 'data-'로 시작하는 커스텀 속성을 활용해서 'modifyModal'아이디에 속하는 
		div를 화면에 보이게 함.
	*/
	$(".timeline").on("click", ".replyLi", function(event){
		
		var reply = $(this);
		//console.log(reply.find('.timeline-body').text().trim());
		//...핸들바템플릿에서 댓글본문, 댓글번호.
		$("#replytext").val(reply.find('.timeline-body').text().trim());
		$(".modal-title").html(reply.attr("data-rno"));
		
	});

	
	
</script>

<%@include file="../include/footer.jsp"%>