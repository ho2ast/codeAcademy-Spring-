<%@page import="com.exam.repository.MemberDao"%>
<%@page import="com.exam.domain.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<%-- head area --%>
	<jsp:include page="../include/common_head.jsp"/>
</head>
<body>
	<%-- header area --%>
	<jsp:include page="../include/header.jsp"/>
	
		<form name="frm" action="/member/infoUpdate" method="post" class="join_form" onsubmit="return check();">
			<div id="join_header">
				<h2>정보수정</h2>
				<div id="join_essential">변경을 원하시면 비밀번호를 입력하세요.</div>
			</div>
		<div id="join_container">
			<div id="join_id">
				아이디 <br> <input type="text" id="id" name="id" autofocus="2" value="${id}" readonly><br><span id="idMessage"></span>
			</div>
			<div id="join_passwd">
				비밀번호<button type="button" id="psswdChange" onclick="location.href='/member/changePasswd'">변경</button> <br> <input type="password" id="passwd1" name="passwd">
			</div>
			<div id="join_name">
				이름 <br> <input type="text" name="name" id="name" value="${member.name}" readonly><br>
			</div>
			<div id="join_gender">
			<c:choose>
				<c:when test="${member.gender eq '여'}">
					성별<br> <input type="radio" name="gender" value="남"/>남자<input type="radio" name="gender" value="여" checked="checked">여자<br>
				</c:when>
				<c:when test="${member.gender eq '남'}">
					성별<br> <input type="radio" name="gender" value="남" checked="checked"/>남자<input type="radio" name="gender" value="여"/>여자<br>
				</c:when>
				<c:otherwise>
					성별<br> <input type="radio" name="gender" value="남"/>남자<input type="radio" name="gender" value="여"/>여자<br>
				</c:otherwise>
			</c:choose>
			</div>
			<div id="join_email">
				<c:if test="${!empty member.email}">
					이메일<br> <input type="email" name="email" value="${member.email}" readonly><br>
				</c:if>
			</div>
			<div id="join_tel">
				<c:choose>
					<c:when test="${!empty member.tel}">
						전화번호<br> <input type="text" name="tel" value="${member.tel }"><br>
					</c:when>
					<c:otherwise>
						전화번호<br> <input type="text" name="tel" ><br>
					</c:otherwise>
					
				</c:choose>
			</div>			
			<div id="join_btn">
				<input type="submit" value="정보수정" id="join_sub" obsubmit="return false;">
				<input type="reset" value="초기화" id="reset_sub">
			</div>
		</div>
	</form>
	
	<%-- footer area --%>
	<jsp:include page="../include/footer.jsp"/>

</body>
</html>