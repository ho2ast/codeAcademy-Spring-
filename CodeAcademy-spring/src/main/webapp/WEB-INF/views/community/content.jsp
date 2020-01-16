<%@page import="java.util.List"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<%-- head area --%>
<jsp:include page="../include/common_head.jsp" />
</head>



<body>
	<%-- header area --%>
	<jsp:include page="../include/header.jsp" />

	<%-- topMenu area --%>
	<jsp:include page="../include/community_topMenu.jsp" />
	<%-- =========================================================== --%>
	
	<div class="col-md-11-content">
		<table id="notice">
			<tr>
				<th class="cwrite" width="100px">글번호</th>
				<td class="left" width="200px">${boardVO.num}</td>
				<th class="cwrite" width="100px">조회수</th>
				<td class="left" width="200px">${boardVO.readcount}</td>
			</tr>
			<tr>
				<th class="cwrite" width="100px">작성자명</th>
				<td class="left" width="200px">${boardVO.username}</td>
				<th class="cwrite" width="100px">작성일자</th>
				<td class="left" width="200px"><fmt:formatDate value="${boardVO.regDate}" pattern="yyyy년 MM월 dd일" /></td>
			</tr>
			<tr>
				<th class="cwrite">글제목</th>
				<td class="left1" colspan="3" width="500px"><pre>${boardVO.subject}</pre></td>
			</tr>
			<tr>
				<th class="cwrite">파일</th>
				<td class="left1" colspan="3" width="500px">
				<c:forEach var="attach" items="${attachList}">
					<a style="font-size:12px;" href="/resources/upload/${attach.uploadpath}/${attach.uuid}_${attach.filename}" download>
					${attach.filename}
					</a><br>
				</c:forEach>
				</td>
			</tr>
			<tr>
				<td class="left1" colspan="4" width="600px">
					<c:forEach var="attach" items="${attachList}">
						<c:if test="${attach.filetype eq 'I'}">
							<img src="/resources/upload/${attach.uploadpath}/${attach.uuid}_${attach.filename}" width="450px" height="auto" style="margin: 10px 10px 10px 10px;">
						</c:if>
					</c:forEach>
					<br>
					<span style="font-size: 14px;">${boardVO.content}</span>
				</td>
			</tr>
		</table>
		
		<div>
		<c:choose>
			<c:when test="${empty id}">
				<c:choose>
					<c:when test="${prevPage eq 'board'}">
						<button type="button" class="back_btn" onclick="location.href='/community/board'">목록가기</button>
					</c:when>
					<c:when test="${prevPage eq 'qna'}">
						<button type="button" class="back_btn" onclick="location.href='/community/qna'">목록가기</button>
					</c:when>
					<c:otherwise>
						<button type="button" class="back_btn" onclick="location.href='/community/download'">목록가기</button>
					</c:otherwise>
				</c:choose>
			</c:when>
			<c:otherwise>
				<c:choose>
					<c:when test="${prevPage eq 'board'}">
						<button type="button" class="back_btn" onclick="location.href='/community/board'">목록가기</button>
					</c:when>
					<c:when test="${prevPage eq 'qna'}">
						<button type="button" class="back_btn" onclick="location.href='/community/qna'">목록가기</button>
					</c:when>
					<c:otherwise>
						<button type="button" class="back_btn" onclick="location.href='/community/downloado'">목록가기</button>
					</c:otherwise>
				</c:choose>
						<button type="button" class="delete_btn" onclick="checkDelete()">삭제</button>
				<c:choose>
					<c:when test="${prevPage eq 'board'}">
						<button type="button" class="update_sub" onclick="location.href='/community/modify?num=${boardVO.num}&pageNum=${pageNum}&prevPage=${prevPage}';">수정</button>
					</c:when>
					<c:when test="${prevPage eq 'qna'}">
						<button type="button" class="update_sub" onclick="location.href='/community/modify?num=${boardVO.num}&pageNum=${pageNum}&prevPage=${prevPage}';">수정</button>
					</c:when>
					<c:otherwise>
						<button type="button" class="update_sub" onclick="location.href='/community/modify?num=${boardVO.num}&pageNum=${pageNum}&prevPage=${prevPage}';">수정</button>
					</c:otherwise>
				</c:choose>
				
				<c:choose>
					<c:when test="${prevPage eq 'board'}">
						<button type="button" class="reWrite_sub" onclick="location.href='/community/rewrite?reRef=${boardVO.reRef}&reLev=${boardVO.reLev}&reSeq=${boardVO.reSeq}&subject=${boardVO.subject}&pageNum=${pageNum}&prevPage=${prevPage }&category=${boardVO.category }';">답글쓰기</button>
					</c:when>
					<c:when test="${prevPage eq 'qna'}">
						<button type="button" class="reWrite_sub" onclick="location.href='/community/rewrite?reRef=${boardVO.reRef}&reLev=${boardVO.reLev}&reSeq=${boardVO.reSeq}&subject=${boardVO.subject}&pageNum=${pageNum}&prevPage=${prevPage }&category=${boardVO.category }';">답글쓰기</button>
					</c:when>
					<c:otherwise>
						<button type="button" class="reWrite_sub" onclick="location.href='/community/rewrite?reRef=${boardVO.reRef}&reLev=${boardVO.reLev}&reSeq=${boardVO.reSeq}&subject=${boardVO.subject}&pageNum=${pageNum}&prevPage=${prevPage }&category=${boardVO.category }';">답글쓰기</button>
					</c:otherwise>
				</c:choose>
				
			</c:otherwise>
		</c:choose>

		</div><br>
		<%--
		<div class="commentContainer">
			<form action="commentProcess.jsp" method="post">
				<table class="commentTable">
					<tr>
						<th class="cwrite" style="text-align: center;">작성자</th>
						<td class="left"><input type="text" name="username" value="<%=id %>" readonly/></td>
					</tr>
					<tr>
						<th class="cwrite">댓글</th>
						<td class="left"><input type="text" name="comments" cols="50" rows="100"/><button type="submit">댓글쓰기</button></td>
					</tr>
				</table>
			</form>
		</div>
		
		<div>
			<%
			if (id == null) {
				if (prevPage.equals("board")) {
					%>
					<button type="button" class="back_btn" onclick="location.href='board.jsp'">목록가기</button>
					<%
				} else if (prevPage.equals("qna")) {
					%>
					<button type="button" class="back_btn" onclick="location.href='qna.jsp'">목록가기</button>
					<%
				} else {
					%>
					<button type="button" class="back_btn" onclick="location.href='download.jsp'">목록가기</button>
					<%
				}
			} else {
				if (prevPage.equals("board")) {
					%>
					<button type="button" class="back_btn" onclick="location.href='board.jsp'">목록가기</button>
					<%
				} else if (prevPage.equals("qna")) {
					%>
					<button type="button" class="back_btn" onclick="location.href='qna.jsp'">목록가기</button>
					<%
				} else {
					%>
					<button type="button" class="back_btn" onclick="location.href='download.jsp'">목록가기</button>
					<%
				}
					%>
					<button type="button" class="delete_btn" onclick="checkDelete()">삭제</button>
					<%
				if (prevPage.equals("board")) {
					%>
					<button type="button" class="update_sub" onclick="location.href='update.jsp?num=<%=boardVO.getNum() %>&pageNum=<%=pageNum %>&prevPage=<%=prevPage%>';">수정</button>
					<%
				} else if (prevPage.equals("qna")) {
					%>
					<button type="button" class="update_sub" onclick="location.href='update.jsp?num=<%=boardVO.getNum() %>&pageNum=<%=pageNum %>&prevPage=<%=prevPage%>';">수정</button>
					<%
				} else {
					%>
					<button type="button" class="update_sub" onclick="location.href='update.jsp?num=<%=boardVO.getNum() %>&pageNum=<%=pageNum %>&prevPage=<%=prevPage%>';">수정</button>
					<%
				}
				if (prevPage.equals("board")) {
					%>
					<button type="button" class="reWrite_sub" onclick="location.href='reWrite.jsp?reRef=<%=boardVO.getReRef() %>&reLev=<%=boardVO.getReLev() %>&reSeq=<%=boardVO.getReSeq() %>&Subject=<%=boardVO.getSubject() %>&pageNum=<%=pageNum%>&prevPage=<%=prevPage%>&Category=<%=boardVO.getCategory()%>';">답글쓰기</button>
					<%
				} else if (prevPage.equals("qna")) {
					%>
					<button type="button" class="reWrite_sub" onclick="location.href='reWrite.jsp?reRef=<%=boardVO.getReRef() %>&reLev=<%=boardVO.getReLev() %>&reSeq=<%=boardVO.getReSeq() %>&Subject=<%=boardVO.getSubject() %>&pageNum=<%=pageNum%>&prevPage=<%=prevPage%>&Category=<%=boardVO.getCategory()%>';">답글쓰기</button>
					<%
				} else {
					%>
					<button type="button" class="reWrite_sub" onclick="location.href='reWrite.jsp?reRef=<%=boardVO.getReRef() %>&reLev=<%=boardVO.getReLev() %>&reSeq=<%=boardVO.getReSeq() %>&Subject=<%=boardVO.getSubject() %>&pageNum=<%=pageNum%>&prevPage=<%=prevPage%>&Category=<%=boardVO.getCategory()%>';">답글쓰기</button>
					<%
				}
			}
			%>
		</div>
		 --%>
	</div>
	

	
	<%-- footer area --%>
	<jsp:include page="../include/footer.jsp" />
</body>
<script>
	function checkDelete() {
		var result = confirm('${boardVO.num}번 글을 정말로 삭제하시겠습니까??');
		if (result == true) {
			location.href='/community/delete?num=${boardVO.num}&pageNum=${pageNum}&prevPage=${prevPage}';
		}
	}
</script>
</html>