<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<%-- head area --%>
	<jsp:include page="../include/common_head.jsp"/>
</head>
<body>
	<%-- header area --%>
	<jsp:include page="../include/header.jsp"/>
	
	<form action="/member/changePasswd" method="post" class="login_form" >
		<div id="login_header">
			<h2>비밀번호 확인</h2>
		</div>
		<div style="margin-bottom: 10px; text-align: center;">
			계속하려면 본인 인증을 하세요.
		</div>
		<div class="changePasswd_input">
			<input type="password" name="passwd" placeholder="현재 비밀번호">
		</div>
		<div id="login_btn">
			<button type="submit" id="login_sub">다음</button>
		</div>
	</form>
	<%-- footer area --%>
	<jsp:include page="../include/footer.jsp"/>
</body>
</html>