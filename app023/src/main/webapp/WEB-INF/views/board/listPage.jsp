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
								<!-- 
								...216p.↓.
								...http://localhost:8080/board/listPage?page=256
								<td><a href='/board/read?bno=${boardVO.bno}'>${boardVO.title}</a></td>
								...286p.↓.페이지정보를 유지하게 함.
								...http://localhost:8080/board/listPage?page=19&perPageNum=10로 호출가능.
								 -->
								<td><a href='/board/readPage${pageMaker.makeQuery(pageMaker.cri.page)}&bno=${boardVO.bno}'>
										${boardVO.title}
									</a>
								</td>
							<td>${boardVO.writer}</td>
							<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm"
												value="${boardVO.regdate}" /></td>
							<td><span class="badge bg-red">${boardVO.view_count }</span></td>
						</tr>
					
					</c:forEach>					
					
					</table>				
				</div>
				<!-- /.box-body -->


				<div class="box-footer">

					<div class="text-center">
						<ul class="pagination">

							<c:if test="${pageMaker.blPrev}">
								<li>
								<!-- 
									...276p.↓.
									<a href="/board/listPage?page=250">&laquo;</a>
									<a href="/board/listPage?page=${pageMaker.startPage - 1}">&laquo;</a>
									
									...288p.↓.페이지정보를 유지하게 함.
									...★'?page='가 필요없음에 주의할 것.
									<a href="/board/listPage?page=?page=250&perPageNum=10">&laquo;</a>									
									<a href="/board/listPage?page=${pageMaker.makeQuery(pageMaker.startPage - 1)}">&laquo;</a>
								 -->
									<a href="/board/listPage${pageMaker.makeQuery(pageMaker.startPage - 1)}">&laquo;</a>
									<!-- startPage : ${pageMaker.makeQuery(pageMaker.startPage - 1)} -->
								</li>
							</c:if>

							<c:forEach begin="${pageMaker.startPage }"
									   end="${pageMaker.endPage }" 
									   var="idx">
								<!-- 
									...277p.↓.li태그내의 서식을 동적으로 표현함.
									${pageMaker.cri.page}를 이용해서 getCriteria(), getPage()를 호출하여
									현재 페이지 번호만을 강조시킴.
								 -->									   
								<li <c:out value="${pageMaker.cri.page == idx?'class =active':''}"/>>
								<!-- 
									...277p.↓.
									<a href="listPage?page=${idx}">${idx}</a>
									...287p.↓.
									<a href="/board/listPage?page=251&perPageNum=10">251</a>
									<a href="/board/listPage${pageMaker.makeQuery(idx)}">${idx}</a>
								 -->
									<a href="/board/listPage${pageMaker.makeQuery(idx)}">${idx}</a>
								</li>
							</c:forEach>

							<c:if test="${pageMaker.blNext && pageMaker.endPage > 0}">
								<li>
								<!-- 
									...276p.↓.
									<a href="/board/listPage?page=250">&laquo;</a>
									<a href="/board/listPage?page=${pageMaker.endPage + 1}">&laquo;</a>
									
									...288p.↓.페이지정보를 유지하게 함.
									...★'?page='가 필요없음에 주의할 것.
									<a href="/board/listPage?page=31&perPageNum=10">&raquo;</a>
									<a href="/board/listPage${pageMaker.makeQuery(pageMaker.endPage + 1)}">&raquo;</a>
								 -->								
									<a href="/board/listPage${pageMaker.makeQuery(pageMaker.endPage + 1)}">&raquo;</a>
									<!-- endPage : ${pageMaker.next}, ${pageMaker.endPage } -->
								</li>
							</c:if>

						</ul>
					</div>

				</div>
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


<script>    
    $(".pagination li a").on("click", function(event){
    //...290p.선택사항.
    //   http://cafe.naver.com/gugucoding/2634
    //   jQuery 로 처리하려면, 페이지목록을 276p 처럼 해야 함. 
    })    
</script>

<%@include file="../include/footer.jsp"%>