<%@page import="java.util.List"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
	<%-- head Area --%>
	<jsp:include page="../include/common_head.jsp"/>
</head>


<body>
	<%-- header area --%>
	<jsp:include page="../include/header.jsp"/>
	<%-- top menu --%>
	<jsp:include page="../include/member_topMenu.jsp"/>
	
		<div class="row">
		<h2 class="community_board">전체멤버조회</h2>
		<div class="col-md-12">
			<div class="table-responsive">
			<form action="adminMemberDelete.do" method="post">
				<table class="board_table" style="width: 100%;">
					<tr id="boarder_head">
						<th scope="col" class="tid">아이디</th>
						<th scope="col" class="tname">이름</th>
						<th scope="col" class="temail">이메일</th>
						<th scope="col" class="ttel">전화번호</th>
						<th scope="col" class="tregdate">가입날짜</th>
						<th scope="col" class="tgender">성별</th>
						<th scope="col" class="tcheck">선택</th>
					</tr>
					<c:choose>
						<c:when test="${fn:length(memberList) gt 0}">
							<c:forEach var="memberList" items="${memberList}">
								<c:choose>
									<c:when test="${memberList.id ne 'admin' }">
										<tr>
											<td>${memberList.id}</td>
											<td>${memberList.name}</td>
											<td>${memberList.email}</td>
											<td>${memberList.tel}</td>
											<td><fmt:formatDate value="${boardVO.regDate}" pattern="yyyy.MM.dd"/></td>
											<td>${memberList.gender }</td>
											<td><input type="checkbox" name="delId" value="${ memberList.id }" /></td>
										</tr>				
									</c:when>
									<c:otherwise>
										<tr>
											<td>${memberList.id}</td>
											<td>${memberList.name}</td>
											<td><fmt:formatDate value="${boardVO.regDate}" pattern="yyyy.MM.dd"/></td>
											<td>${memberList.email}</td>
											<td>${memberList.tel}</td>
											<td>${memberList.gender}</td>
										</tr>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<tr>
								<td colspan="6">회원이 없습니다.</td>
							</tr>
						</c:otherwise>
					</c:choose>
				</table>
				<input id="wholeMemDel" type="submit" value="삭제"/>
				</form>
			</div>
		</div>
<%-- 
 		<div class="col-md-12"> -->
			<%
 				if (count > 0) {
 					// 총 페이지 개수 구하기
 					// 전체 글 개수 / 한페이지당 보여줄 글 개수 (나머지가 있을때는 +1)
 					int pageCount = count / pageSize + (count % pageSize == 0 ? 0 : 1); // 나머지 0이면 안더하고, 0 아니면 1을 더함

 					// 페이지블록 수 설정
 					int pageBlock = 5;

 					// 시작페이지 번호 startPage 구하기
 					// pageNum 값이 1~5 사이면 시작페이지는 항상 1이 나와야함

 					// (1 / 5) * 5 + 1 -> 1
 					// (2 / 5) * 5 + 1 -> 1
 					// (3 / 5) * 5 + 1 -> 1
 					// (4 / 5) * 5 + 1 -> 1
 					// ((5 / 5)-1) * 5 + 1 -> 1
 					// pageBlock의 배수가 될때는 1을 빼줘야 된다.

 					// (6 - 1 / 5) * 5 + 1 -> 6
 					// (7 - 1 / 5) * 5 + 1 -> 6
 					// (8 - 1 / 5) * 5 + 1 -> 6
 					// (9 - 1 / 5) * 5 + 1 -> 6
 					// (10 - 1 / 5) * 5 + 1 -> 6 
 					int startPage = ((pageNum - 1) / pageBlock) * pageBlock + 1;

 					// 끝페이지 번호 endPage
 					int endPage = startPage + pageBlock - 1;
 					if (pageCount < endPage) {
 						endPage = pageCount;
 					}
 					// [이전]
 					if (startPage > pageBlock) {
 			%>
 			<a
 				href="board.jsp?pageNum=<%=startPage - pageBlock + 4%>&search=<%=search%>">[이전]</a>
 			<%
 					}
 						// 페이지블록 페이지 5개 출력
 						for (int i = startPage; i <= endPage; i++) {
 			%>
 			<a href="board.jsp?pageNum=<%=i%>&search=<%=search%>"><%=pageNum == i ? "[<b style=\"color: blue;\">" + i + "</b>]" : i%></a>
 			<%
 						} // for
 					// [다음]
 					if (endPage < pageCount) {
 			%>
 			<a
 				href="board.jsp?pageNum=<%=startPage + pageBlock%>&search=<%=search%>">[다음]</a>
 			<%
 					}
 				} // if
 			%>
 		</div>
		
 		<form action="board.jsp" method="get">
 			<div id="board_search">
 				<input type="text" placeholder="제목을 입력하세요" name="search" value="<%=search%>" class="input_box">
 				<input type="submit" value="제목검색" id="search_btn"> -->
 				<% String curPage = "board"; %>
 				<button type="button" value="글쓰기" id="write_btn" onclick="location.href='write.jsp?prevPage=<%=curPage %>';" >글쓰기</button>
 			</div>

 		</form>
 --%>
	</div>
	<%-- footer area --%>
	<jsp:include page="../include/footer.jsp"/>
	
</body>
</html>