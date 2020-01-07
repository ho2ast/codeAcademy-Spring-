<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<%-- head area --%>
	<jsp:include page="../include/common_head.jsp"/>
	
	<script>
	// 사용자 입력값 검증 작업
	function check() {
		if (frm.id.value.length < 3) { // 텍스트 상자는 입력을 안해도 null 아니고 빈 문자열이다 
			alert('아이디는 세글자 이상 사용가능합니다.');
			frm.id.select();
			return false;
		}
		if($('#idMessage').text() != '사용가능 아이디') {
			alert('이미 사용중인 아이디입니다.');
			document.frm.id.focus();
			return false;
		}
		if (frm.passwd.value.length == 0) {
			alert('패스워드는 필수 입력 항목입니다.');
			frm.passwd.focus();
			return false;
		}
		if (frm.name.value.length == 0) {
			alert('이름은 필수 입력 항목입니다.');
			frm.name.focus(); // 커서 갖다 놓기
			return false;
		}
		if (document.frm.passwd.value != document.frm.passwd2.value) {
			alert('패스워드 입력값이 서로 다릅니다.');
			document.frm.passwd.select(); // 해당 내용 전체 블록 선택
			return false;
		}
		if ($('#authMailSuc').text() != '메일인증완료') {
			alert('이메일 인증은 필수 사항입니다.');
			document.frm.email.focus();
			return false;
		}
		return true;
	}
	
	// 새로운 브라우저를 띄우고 아이디 중복확인 해주는 기능
// 	function winOpen() {
		// var inputId = document.getElementById('id').value; -> 속성 방식
// 		var inputId = document.frm.id.value; // 폼이름 방식
		// id입력값이 공백이면 '아이디 입력하세요' 포커스 주기
// 		if (inputId == '') { // inputId.length == 0
// 			alert('아이디를 입력하세요.');
// 			document.frm.id.focus();
// 			return;
// 		}
		// 새로운 자식창(브라우저) 열기
		// open() 호출 한쪽은 부모창이 되고
		// open()에 의해 새로 열린 창은 자식창이 된다
		// 부모-자식 관계가 있음. 자식창의 데이터를 부모창으로 가져올 수 있음
// 		var childWindow = window.open('joinIdDupCheck.jsp?userid=' + inputId, '', 'width = 400, height=300'); // window -> 현재브라우저
			 //ㄴ>자식창						//ㄴ> 아이디 중복확인 요청 ? 하고 = + 값
 		// childWindow.document.write('입력아이디' + inputId +'<br>'); // 부모창에서 작성한 코드가 자식창에서 출력됨
		// 부모창에서 자식창을 조작하는 것은 좋지 않다. 보안상의 문제
		// 자식창에서 부모창에 접근하는 것은 괜찮다
// 	}
	

	</script>
	
</head>
<body>
	<%-- header area --%>
	<jsp:include page="../include/header.jsp"/>

	<form name="frm" action="/member/join" method="post" class="join_form" onsubmit="return check();">
			<div id="join_header">
				<h2>회원가입</h2>
				<div id="join_essential">*은 필수 입력사항입니다.</div>
			</div>
		<div id="join_container">
			<div id="join_id">
				아이디 *<br> <input type="text" id="id" name="id" autofocus="autofocus"><br><span id="idMessage"></span>
			</div>
			<div id="join_passwd">
				비밀번호 *<br> <input type="password" id="passwd1" name="passwd">
			</div>
			<div id="join_passwd">
				비밀번호 확인<br> <input type="password" id="passwd2" name="passwd2"><br><span id="pwMessage"></span>
			</div>
			<div id="join_name">
				이름 *<br> <input type="text" name="name" id="name"><br>
			</div>
			<div id="join_gender">
				성별<br> <input type="radio" name="gender" value="남"/>남자<input type="radio" name="gender" value="여"/>여자<br>
			</div>
			<div id="join_email">
				이메일 *<br> <input type="email" id="email" name="email"><br>
				<span name="authMailSuc" id="authMailSuc"></span>
				<button type="button" id="emailAuth" name="emailAuth">인증메일 전송</button><br>
			</div>
			<div id="join_tel">
				전화번호<br> <input type="text" name="tel"><br>
			</div>
			<div id="join_btn">
				<input type="submit" value="회원가입" id="join_sub" obsubmit="return false;">
				<input type="reset" value="초기화" id="reset_sub">
			</div>
		</div>
	</form>
	
	<%-- footer area --%>
	<jsp:include page="../include/footer.jsp"/>
	
	<script src="/resources/scripts/jquery-3.4.1.min.js"></script>
	<script>
	$('#id').keyup(function () {
		var id = $(this).val();

		$.ajax({
			// json 형식일 때는 url뒤에 .json을 붙여줘야한다. 아니면 xml로 받아옴
			url: '/member/joinIdDupCheckJson.json',
			data: {id: id},
			success: function (data) {
				//console.log(typeof data);
				console.log(data);
				
			idDupMessage(data);
			}
		})
	});
	
	function idDupMessage(isIdDup){
		if (isIdDup) {
			$('span#idMessage').html('중복된 아이디').css('color', 'red');
		} else {
			$('span#idMessage').html('사용가능 아이디').css('color', 'green');
		}
		
	}
	</script>

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
	</script>

	
	<script>
	$('#emailAuth').click(function () {
		var email = $('#email').val();
		var name = $('#name').val();
		
		if (email != "") {
			$.ajax({
				url: '/member/emailDupCheckJson.json',
				data: {email: email},
				success: function (data) {
					console.log(typeof data);
					console.log(data);
					emailDup(data)
				}
			})
			function emailDup(isEmailDup){
				console.log("true면 중복" + isEmailDup + "\nfalse면 사용가능");
				console.log(typeof isEmailDup)
				
				if (isEmailDup) {
					alert('이미 사용중인 이메일입니다.');
				} else {
					$.ajax({
						url: '/member/mailAuthJson',
						data: {email: email, name: name},
						success: function (data) {
							// console.log(typeof data);
							// console.log(data);
						}
					});
					window.open("/member/mailAuth", "popup_window", "width=500, height=300, scrollbars=no");
// 					return false;
				}
			}
		} else {
			alert('이메일을 입력하세요.')
		}
	});
	</script>
	
	
	<%-- footer area --%>
	<%-- 	<jsp:include page="../include/footer.jsp"/> --%>
</body>
</html>