<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>testAjax...396p</title>
	
	<style>
		#modDiv {
			width: 300px;
			height: 100px;
			background-color: gray;
			position: absolute;
			top: 50%;
			left: 50%;
			margin-top: -50px;
			margin-left: -150px;
			padding: 10px;
			z-index: 1000;
		}
		
		.pagination {
		  width: 100%;
		}
		
		.pagination li{
		  list-style: none;
		  float: left; 
		  padding: 3px; 
		  border: 1px solid blue;
		  margin:3px;  
		}
		
		.pagination li a{
		  margin: 3px;
		  text-decoration: none;  
		}	
	</style>
</head>
<body>

	<h2>Ajax Test Page...396p</h2>

	<!-- 
		...402p. form태그를 이용할 수 있지만, 전송할 내용이 많지 않아
		...id 속성을 이용해서 처리함.
		...댓글은 조회 페이지에서 작성되므로 게시물 번호는 받지 않음.
	 -->
	<div>
		<div>
			REPLYER <input type='text' name='replyer' id='newReplyWriter'>
		</div>
		<div>
			REPLY TEXT <input type='text' name='replytext' id='newReplyText'>
		</div>
		<button id="insertReplyBtn">ADD REPLY</button>
		<button id="postReplyBtn">Post REPLY</button>
	</div>


	<!-- 
		...396p.댓글목록이 출력됨. 
	-->
	<ul id="replies">
	</ul>
	
	<!-- 
		...419p.댓글 페이지를 출력함.
	 -->
	<ul class='pagination'>
	</ul>	

	<!-- 
		...412p.
		...수정과 삭제작업을 하는 div.
		...평상시에는 안보이도록 하고, 각 댓글 항목의 'MOD'버튼을 누르는 경우에만
		...보이도록 함.
	 -->
	<div id='modDiv' style="display: none;">
		<div class='modal-title'></div>
		<div>
			<input type='text' id='replytext'>
		</div>
		<div>
			<button type="button" id="replyModBtn">Modify</button>
			<button type="button" id="replyDelBtn">DELETE</button>
			<button type="button" id='closeBtn'>Close</button>
		</div>
	</div>

	

	<!--
		...397p.jQuery 2.1.4
		...현재 프로젝트의 경우 '/'를 경로로 사용하므로 직접 '/resources/'로
		...시작하는 경로를 이용함.
	<script src="/resources/plugins/jQuery/jquery-2.2.0.js"></script>		 
	-->
	<script src="http://code.jquery.com/jquery-2.1.4.js"></script>


	<script>
		/*
		...398p.
		@RestController는 객체를 JSON으로 전달하므로 jQuery를 이용해서 호출할때
		getJSON()을 이용함.
		@RequestMapping(value = "/all/{bno}", method = RequestMethod.GET)
		public ResponseEntity<List<ReplyVO>> selectReplies(@PathVariable("bno") Integer bno)
		...실행결과를 JSON으로 받아서 data를 each하면 ReplyVO.rno, ReplyVO.replytext를 받아서
		   처리할 수 있음.
		...http://localhost:8080/testAjax를 실행하고 개발자도구 검사::Network::좌측에서 
		   게시글번호를 선택.
		   우측 Preview탭을 보면 해당 게시글에 등록된 댓글들이 JSON형태로 출력되고
		   아래쪽 Console에는 해당게시글의 댓글수가 출력됨.
		
		...400p.
		...li태그의 속성으로 사용한 'data-'로 시작하는 속성은 이름이나 개수에 관계없이 
		   태그 내에서 자유롭게 사용할 수 있는 속성으로, id나 name속성을 대신해서 
		   사용하기 편리함.	
		
		...댓글목록 갱신은 자주 사용되므로 selectReplies()로 만듦.	
		*/		
	
		selectReplies();//...398p.
		
		function selectReplies()
		{
			//...398p.
			var bno = '103'
			$.getJSON(
					"/replies/all/" + bno,
					function(data) {	
						console.log(data.length);
						
						//...400p.
						var str = "";
						$(data).each(
									function() {
											str += "<li data-rno='"+this.rno+"' class='replyLi'>"
													+ this.rno
													+ ":"
													+ this.replytext
													+ "<button>○MOD○</button></li>";//...409p.
										}
									);
	
						$("#replies").html(str);
						
					});			
		}	

		/* 
		...403p.jQuery를 이용하여 $.ajax()를 통해 서버를 호출함.
		...전송하는 데이터는 JSON으로 구성된 문자열을 사용하고,
		   전송받은 결과는 단순문자열임.
		...특이한 점은 jQuery가 제공하는 $.post()등을 사용하지 않고,
		   $.ajax()를 이용하여 다양한 옵션으로 구성됨. 
		...407p.전송할때 HTTP헤더정보에 'application/json'이라고 명시함.
		...전송되는 데이터는 JSON.stringify()를 이용해서 JSON데이터로
		   구성해서 전송함.
		*/
	
		var bno = 103;
		
		$("#insertReplyBtn").on("click", function() {
			console.log("#insertReplyBtn clicked...");
			var replyer = $("#newReplyWriter").val();
			var replytext = $("#newReplyText").val();
	
			$.ajax({
					type : 'post',
					url : '/replies',
					headers : {
						"Content-Type" : "application/json",
						"X-HTTP-Method-Override" : "POST" //...385p.
					},
					dataType : 'text',
					data : JSON.stringify({
						bno : bno,
						replyer : replyer,
						replytext : replytext
					}),
					success : function(result) {
		
						if (result == 'SUCCESS') {
		
							alert("등록 되었습니다.");
							selectReplies();
							//selectPageReplies(replyPage);
		
						}
					}
			});
		});	

	
	</script>	

</body>
</html>