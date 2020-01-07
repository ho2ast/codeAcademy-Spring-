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
	
	<form action="/member/login" method="post" class="login_form">
			<div id="login_header">
				<h2>로그인</h2>
			</div>
			<div id="id_input">
				<input type="text" name="id" placeholder="아이디" autofocus="autofocus">
			</div>
			<div id="passwd_input">
				<input type="password" name="passwd" placeholder="비밀번호">
			</div>
			<div id="rememberMe">
				<label>
					<input type="checkbox" name="rememberMe">
					로그인 상태 유지
				</label>
			</div>
			<div id="login_btn">
				<button type="submit" id="login_sub">로그인</button>
				<button type="reset" id="reset_sub">초기화</button>
			</div>
	</form>
	
	<%-- footer area --%>
	<jsp:include page="../include/footer.jsp"/>
	
</body>
</html>