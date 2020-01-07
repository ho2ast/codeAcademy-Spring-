<%@page import="java.util.List"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
	<%-- head area --%>
	<jsp:include page="../include/common_head.jsp" />
</head>

<body>
	<%-- header area --%>
	<jsp:include page="../include/header.jsp" />

	<%-- head area --%>
	<jsp:include page="../include/adminBoard_topMenu.jsp" />
	<%-- =========================================================== --%>
	
	<div class="col-md-11-content">
			
		<table id="notice">
<tr>
				<th class="cwrite" width="100px">글번호</th>
				<td class="left" width="200px">${num}</td>
				<th class="cwrite" width="100px">조회수</th>
				<td class="left" width="200px">${boardVO.readcount }</td>
			</tr>
			<tr>
				<th class="cwrite" width="100px">작성자명</th>
				<td class="left" width="200px">${boardVO.username }</td>
				<th class="cwrite" width="100px">작성일자</th>
				<td class="left" width="200px"><fmt:formatDate value="${board.regDate}" pattern="yyyy.MM.dd"/></td>
			</tr>
			<tr>
				<th class="cwrite">글제목</th>
				<td class="left" colspan="3"><pre>${boardVO.subject }</pre></td>
			</tr>
			<tr>
				<th class="cwrite">파일</th>
				<td class="left" colspan="3">
				<c:forEach var="attach" items="${attachList}">
					<a href="upload/${attach.filename }" download>
						${attach.filename }
					</a>
				</c:forEach>
				
				</td>
			</tr>
			<tr>
<!-- 				<th class="cwrite">글내용</th> -->
				<td class="left" colspan="4">
				<c:forEach var="attach" items="${attachList}">
					<c:choose>
						<c:when test="${attach.filetype eq 'I' }">
							<a href="upload/${attach.filename }" download>
							<img src="upload/${attach.filename }" width="500px" height="auto" style="margin: 10px 10px 10px 10px;">
							</a>
						</c:when>
						<c:otherwise>
							<a href="upload/${attach.filename }" download>
							${attach.filename }
							</a>
						</c:otherwise>
					</c:choose>
				</c:forEach>
					<br>
					<p style="font-size: 14px;">${boardVO.content }</p>
				</td>
			</tr>
		</table>
					
		<div>
			<button type="button" class="back_btn" onclick="location.href='adminWholeBoardForm.do'">목록가기</button>
			<button type="button" class="delete_btn" onclick="checkDelete()">삭제</button>
		</div>
	</div>
	
	
	<%-- footer area --%>
	<jsp:include page="../include/footer.jsp" />
</body>

<script>
	function checkDelete() {
		var result = confirm('${boardVO.num}번 글을 정말로 삭제하시겠습니까??');
		if (result == true) {
			var adminId = '${id}';
			if (adminId == 'admin'){
					location.href='adminContentDelete.do?num=${num}';
			} else {
				alert('권한이 없습니다.');
				location.href='main.do';
			}
		}
	}
</script>
</html>