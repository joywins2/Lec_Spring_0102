<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- 
	...169, 625p.
	   Bootstrap sample file download URL :
	   http://cafe.naver.com/gugucoding/798 
-->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@include file="../include/header.jsp"%>


<style>
.fileDrop {
  width: 80%;
  height: 100px;
  border: 1px dotted gray;
  background-color: lightslategrey;
  margin: auto;
  
}
</style>

<!-- Main content -->
<section class="content">
	<div class="row">
		<!-- left column -->
		<div class="col-md-12">
			<!-- general form elements -->

			<div class="box">
				<div class="box-header with-border">
					<h3 class="box-title">create Posting Page</h3>
				</div>
				<!-- /.box-header -->

				<!-- ...595p. 
				     업로드된 파일의 이름을 form 태그에 포함시키는 jQuery 처리로
				     form 태그 id 를 부여함. -->
				<form id='registerForm'  
				role="form" method="post">
					<div class="box-body">
						<div class="form-group">
							<label for="exampleInputEmail1">Title</label> 
							<input type="text"
								name='title' class="form-control" placeholder="Enter Title">
						</div>
						<div class="form-group">
							<label for="exampleInputPassword1">Content</label>
							<textarea class="form-control" name="content" rows="3"
								placeholder="Enter ..."></textarea>
						</div>
						<div class="form-group">
							<label for="exampleInputEmail1">Writer</label> 
							<input type="text"
								name="writer" class="form-control" placeholder="Enter Writer">
						</div>
						<!-- ...S.590p. 첨부파일 추가영역과 업로드된 파일 보여주는 ul태그.-->	
						<div class="form-group">
							<label for="exampleInputEmail1">
								File DROP Here
							</label>
							<div class="fileDrop"></div>
						</div>
						<!-- ...E.590p. 첨부파일 추가영역과 업로드된 파일 보여주는 ul태그.-->
					</div>
					<!-- /.box-body -->
				
					<div class="box-footer">
					<!-- ...S.590p. 첨부파일 추가영역과 업로드된 파일 보여주는 ul태그.-->
						<div>
							<hr>
						</div>
						
						<ul class="mailbox-attachments clearfix uploadedList">
						</ul>
					<!-- ...E.590p. 첨부파일 추가영역과 업로드된 파일 보여주는 ul태그.-->
					
						<button type="submit" class="btn btn-primary">Submit</button>
					</div>
				</form>
				
			</div>
			<!-- /.box -->
		</div>
		<!--/.col (left) -->
	</div>
	<!-- /.row -->
</section>
<!-- /.content -->
<%-- 
	...591p.첨부파일을 보여주는 HTML코드가 복잡하므로 handlebars를 이용하여
	첨부된 각각의 파일을 보여주는 화면을 템플릿으로 작성함.  
	li태그를 구성할 때 'imgsrc'속성을 보여주는데, 'imgsrc'는 이미지 파일인 경우
	썸네일 파일의 경로이고, 일반 파일인 경우는 단순히 파일모양의 이미지(file.png)를
	보여주는 경로임.
--%>
<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/3.0.1/handlebars.js"></script>

<%-- 
	...595p.getFileInfo()는 별도의 JavaScript 파일로 작성함.
	별도의 파일로 하고 실행하니까 브라우저 검사에서 getFileInfo()를 인식할 수 없다고
	...오류가 나서 같은 소스에 두고 실행하니까 되었다.
	[Solved]WEB-INF폴더를 기준으로 resources폴더로 상대경로를 이용하여 위치를 표기하면 된다.
	script type="text/javascript" src="/resources/zjs/upload.js"		
	정상실행된 모습은 이미지를 Drop구역에 놓으면, submit버튼 위에 썸네일이미지와 파일명이
	링크형태로 뜬다.
 --%>
<script type="text/javascript" src="../resources/zjs/upload.js"></script>

<script id="template" type="text/x-handlebars-template">
<li>
  <span class="mailbox-attachment-icon has-img">
	<img src="{{imgsrc}}" alt="Attachment">
  </span>
  <div class="mailbox-attachment-info">
	<a href="{{getLink}}" class="mailbox-attachment-name">{{fileName}}</a>
	<a href="{{fullName}}" class="btn btn-default btn-xs pull-right delbtn">
		<i class="fa fa-fw fa-remove"></i>
	</a>
	</span>
  </div>
</li>        
</script>  


<!-- 
	...593p.
	Drag&Drop으로 파일이 업로드 완료후 처리 과정.
	
	1. 서버에 업로드된 파일의 이름.
	↓
	2. getFileInfo()를 통한 정보 추출.
	↓
	3. JavaScript객체 생성.
	↓
	4. handlebars적용.
	↓
	5. HTML생성.
	↓
	6. 화면적용.
 --> 
<script>
	var template = Handlebars.compile($("#template").html());
	
	$(".fileDrop").on("dragenter dragover", function(event){
		event.preventDefault();
	});
	
	
	$(".fileDrop").on("drop", function(event){
		event.preventDefault();
		
		var files = event.originalEvent.dataTransfer.files;
		
		var file = files[0];
	
		var formData = new FormData();
		
		formData.append("file", file);	
		
		
		$.ajax({
			  url: '/uploadAjax', //...UploadController 참조.
			  data: formData,
			  dataType:'text',
			  processData: false,
			  contentType: false,
			  type: 'POST',
			  success: function(data){
				  //...593p.
				  //getFileInfo()를 이용해서 템플릿에 필요한 객체를 생성함.
				  //이후 템플릿을 적용해서 온전한 HTML을 구성한 후 첨부된 파일이
				  //보여지는 $(.'uploadedList')의 일부로 추가됨.
				  //getFileInfo()는 별도의 자바스크립트 파일로 구성함.
				  var fileInfo = getFileInfo(data);
				  
				  var html = template(fileInfo);
				  
				  $(".uploadedList").append(html);
			  }
			});	
	});
	

	//...595p.사용자가 업로드한 파일명 정보를 form 태그 내부로 포함시켜서 함께 전송하게 함. 
	$("#registerForm").submit(function(event){
		event.preventDefault();
		
		var that = $(this);
		
		var str ="";
		$(".uploadedList .delbtn").each(function(index){
			 str += "<input type='hidden' name='files["+index+"]' value='"+$(this).attr("href") +"'> ";
		});
		
		that.append(str);

		that.get(0).submit();
	});
	
</script>

<%@include file="../include/footer.jsp"%>