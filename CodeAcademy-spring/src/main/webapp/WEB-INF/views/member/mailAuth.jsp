<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메일인증확인</title>
</head>
<body>
	<form onsubmit="return authCheck();">
		인증번호 <input type="text" name="auth" id="auth"/>
		<button type="submit" id="mailAuthSub">인증하기</button>
	</form>
</body>

<script src="/resources/scripts/jquery-3.4.1.min.js"></script>
<script>
function authCheck() {
	var authNum = ${authNum}
	var inputAuth = $('#auth').val();
	console.log(authNum);
	
	if (authNum != inputAuth) {
		alert('일치하지 않습니다. 다시확인하세요');
		$('#auth').focus();
		return false;
	} else {
		close();
		$(opener.document).find('#email').attr("readonly", "readonly");
		$(opener.document).find('#emailAuth').remove();
		$(opener.document).find('#authMailSuc').html("메일인증완료").css('color', 'green');
		<% session.invalidate(); %>
		return true;
	}
}
</script>
</html>