<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.List"%>
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
	<div class="row">
		<h2 class="community_board">자유게시판</h2>
		<div class="col-md-12">
			<div class="table-responsive">
				<table class="board_table" style="width: 100%;">
					<tr id="boarder_head">
						<th scope="col" class="tno">번호</th>
						<th scope="col" class="tcate">카테고리</th>
						<th scope="col" class="ttitle">제목</th>
						<th scope="col" class="twrite">글쓴이</th>
						<th scope="col" class="tdate">작성일</th>
						<th scope="col" class="tread">조회수</th>
					</tr>
					<c:choose>
						<c:when test="${pageInfoMap.count gt 0 }">
							<c:forEach var="board" items="${boardList}">
								<tr onclick="location.href='/community/content?num=${ board.num }&pageNum=${ pageNum }&prevPage=${ curPage }';">
									<td style="font-size: 12px;">${board.num}</td>
									<td>${board.category}</td>
									<td id="title">
										<c:if test="${board.reLev gt 0 }">
											<c:set var="level" value="${board.reLev * 10 }" />
											<img src="/resources/imgs/level.gif" height="13" width="${level}">
											<img src="/resources/imgs/icon_re.gif" />
										</c:if>
			 							${board.subject}
									</td>
									<td>${board.username}</td>
									<td><fmt:formatDate value="${board.regDate}" pattern="yyyy.MM.dd"/></td>
									<td>${board.readcount }</td>
								</tr>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<tr>
								<td colspan="6">게시판 글이 없습니다.</td>
							</tr>
						</c:otherwise>
					</c:choose>
					
					
				</table>
			</div>
		</div>
		
		<div class="col-md-12">
			<c:if test="${pageInfoMap.startPage gt pageInfoMap.pageBlock }">
				<a href="/community/board?pageNum=${pageInfoMap.startPage - pageInfoMap.pageBlock + 4}&search=${search}">[이전]</a>
			</c:if>
			<c:forEach var="i" begin="${pageInfoMap.startPage}" end="${pageInfoMap.endPage}" step="1">
				<a href="/community/board?pageNum=${i}&search=${search}">
				<c:choose>
					<c:when test="${i eq pageNum }">
						<b style="color: blue;">  [${i}]  </b>
					</c:when>
					<c:otherwise>
						<b>${i}</b>
					</c:otherwise>
				</c:choose>
				</a>
			</c:forEach>
			<c:if test="${pageInfoMap.endPage lt pageInfoMap.pageCount}">
				<a href="/community/board?pageNum=${pageInfoMap.startPage - pageInfoMap.pageBlock + 4}&search=${search}">[다음]</a>
			</c:if>
		</div>
		
		<form action="/community/board" method="get">
			<div id="board_search">
				<c:choose>
					<c:when test="${!empty id}">
						<input type="text" placeholder="제목을 입력하세요" name="search" value="${search}" class="input_box"> 
						<input type="submit" value="제목검색" id="search_btn">
						<button type="button" value="글쓰기" id="write_btn" onclick="location.href='/community/write?prevPage=${curPage}';" >글쓰기</button>
					</c:when>
					<c:otherwise>
						<input type="text" placeholder="제목을 입력하세요" name="search" value="${search}" class="input_box"> 
						<input type="submit" value="제목검색" id="search_btn">
					</c:otherwise>
				</c:choose>
			</div>
		</form>
	</div>
	<%-- =========================================================== --%>
	<%-- footer area --%>
	<jsp:include page="../include/footer.jsp" />
</body>
</html>