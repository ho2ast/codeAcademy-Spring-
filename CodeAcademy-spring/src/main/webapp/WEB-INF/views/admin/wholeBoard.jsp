<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
	<%-- head Area --%>
	<jsp:include page="../include/common_head.jsp"/>
</head>

<body>
	<%-- header area --%>
	<jsp:include page="../include/header.jsp"/>
	<%-- adminBoard_topMenu area --%>
	<jsp:include page="../include/adminBoard_topMenu.jsp"/>
	
	<div class="row">
		<h2 class="community_board">전체 게시글조회</h2>
		<div class="col-md-12">
			<div class="table-responsive">
			<form action="adminContentDelete.do" method="post" >
				<table class="board_table" style="width: 100%;">
					<tr id="boarder_head">
						<th scope="col" class="tno">글번호</th>
						<th scope="col" class="tcate">카테고리</th>
						<th scope="col" class="ttitle">제목</th>
						<th scope="col" class="twrite">글쓴이</th>
						<th scope="col" class="tdate">작성일</th>
						<th scope="col" class="tread">조회수</th>
						<th scope="col" class="tread">선택</th>
					</tr>
					<c:choose>
						<c:when test="${fn:length(boardList) gt 0}">
							<c:forEach var="boardVO" items="${boardList}">
								<tr class="tr1">
									<td onclick="location.href='adminContentForm.do?num=${boardVO.num}';">${boardVO.num}</td>
									<td onclick="location.href='adminContentForm.do?num=${boardVO.num}';">${boardVO.category}</td>
									<td class="td1" onclick="location.href='adminContentForm.do?num=${boardVO.num}';">
										<div>${boardVO.subject}</div>
										<div class="td2" style="display: none; float: left; border: solid 1px;">내용 : ${boardVO.content}</div>
									</td>
									<td onclick="location.href='adminContentForm.do?num=${boardVO.num}';">${boardVO.username}</td>
									<td onclick="location.href='adminContentForm.do?num=${boardVO.num}';"><fmt:formatDate value="${boardVO.regDate}" pattern="yyyy.MM.dd"/></td>
									<td onclick="location.href='adminContentForm.do?num=${boardVO.num}';">${boardVO.readcount }</td>
									<td><input type="checkbox" name="delBoard" value="${boardVO.num}" /></td>
								</tr>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<tr>
								<td colspan="6">게시글이 없습니다.</td>
							</tr>	
						</c:otherwise>
					</c:choose>
				</table>
				<input id="wholeMemDel" type="submit" value="삭제"/>
				</form>
			</div>
		</div>
	</div>
	<%-- footer area --%>
	<jsp:include page="../include/footer.jsp"/>
</body>
<script src="scripts/jquery-3.4.1.min.js"></script>
<script>
	$(document).ready(function () {
	    $('.board_table').hover(function () {
	        $(this).find("div.td2").show();
	    }, function () {
	    	$(this).find("div.td2").hide();
	    });
	});
</script>
