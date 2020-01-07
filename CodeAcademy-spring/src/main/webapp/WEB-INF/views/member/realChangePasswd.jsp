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
	
	<form action="/member/realChangePasswd" method="post" class="login_form" name="frm" onsubmit="return passCheck()">
		<div id="login_header">
			<h2>비밀번호변경</h2>
		</div>
		<div style="margin-bottom: 10px; text-align: center;">
			새로운 비밀번호를 입력해주세요.
		</div>
		<div class="changePasswd_input">
			<input type="password" name="passwd" id="passwd" placeholder="새로운 비밀번호">
		</div>
		<div class="changePasswd_input">
			<input type="password" name="passwd2" id="passwd2" placeholder="새로운 비밀번호 확인"><br><span id="pwMessage"></span>
		</div>
		<div id="login_btn">
			<input type="submit" id="login_sub" value="변경" onsubmit="return false;"/>
		</div>
	</form>
	
	<script src="/resources/scripts/jquery-3.4.1.min.js"></script>
	<script>
	$('#passwd2').keyup(function () {
		passwordCheck();
	});
	
	function passwordCheck() {
		if (frm.passwd.value == frm.passwd2.value) {
			$('span#pwMessage').html('패스워드 일치').css('color', 'green');		
		} else {
			$('span#pwMessage').html('패스워드 불일치').css('color', 'red');
		}
	}
	function passCheck() {
		var passwd1 = $('#passwd').val();
		var passwd2 = $('#passwd2').val();
		
		if (passwd1 != passwd2) {
			alert('패스워드가 다릅니다.');
			frm.passwd2.focus();
			return false;
		}
		return true;
	}
	</script>
</body>
</html>