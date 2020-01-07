<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<%-- head area --%>
<jsp:include page="../include/common_head.jsp" />

	<script>
	// 사용자 입력값 검증 작업
	function check() {
		if (frm.username.value.length == 0) { // 텍스트 상자는 입력을 안해도 null 아니고 빈 문자열이다 
			alert('작성자를 입력하세요');
			frm.username.select();
			return false;
		}
		if (document.frm.passwd.value == 0) {
			alert('패스워드를 입력하세요');
			document.frm.passwd.focus(); // 해당 내용 전체 블록 선택
			return false;
		}
		if (frm.subject.value.length == 0) {
			alert('제목을 입력하세요');
			frm.subject.focus();
			return false;
		}
		if (frm.content.value.length == 0) {
			alert('내용을 입력하세요');
			frm.content.focus(); // 커서 갖다 놓기
			return false;
		}
		return true;
	}
	</script>
</head>

<body>
	<%-- header area --%>
	<jsp:include page="../include/header.jsp" />
	
	<%-- topMenu area --%>
	<jsp:include page="../include/community_topMenu.jsp" />
	
		<div class="col-md-11">
			<div id="write_header">
				<h2>답글쓰기</h2>
			</div>
			<form action="communityRewrite.do" method="post" class="form-horizontal" id="board_write_form" name="frm" onsubmit="return check();">
			<input type="hidden" name="reRef" value="${reRef}" />
			<input type="hidden" name="reLev" value="${reLev}" />
			<input type="hidden" name="reSeq" value="${reSeq}" />
			<input type="hidden" name="Category" value="${category}" />
			<input type="hidden" name="pageNum" value="${pageNum}">
			<input type="hidden" name="prevPage" value="${prevPage}">
					<div class="col-md-5">
						<div id="write_label">이름</div>
						<div id="input"><input type="text" class="form-control" name="username" value="${id}" readonly="readonly"></div>
					</div>
					<div class="col-md-5">
						<div id="write_label">제목</div>
						<div id="input"><input type="text" class="form-control" name="subject" rows="20" value="[답글] ${subject}"></div>
					</div>
<!-- 					<div class="col-md-5" id="file_container"> -->
<!-- 						<div id="write_label">파일</div> -->
<!-- 						<button type="button" onclick="addFileElement();" id="addFile">파일추가</button><br> -->
<!-- 						<input type="file" name="filename1" id="fileadd"/> -->
<!-- 					</div> -->
					<div class="col-md-5">
						<div id="write_label">내용</div>
						<div id="input"><textarea id="board_content" name="content" cols="50" rows="20"></textarea></div>
					</div>
					<div>
					<c:choose>
						<c:when test="${prevPage eq 'board'}">
							<button type="button" class="back_btn" onclick="location.href='boardForm.do'">목록가기</button>
						</c:when>
						<c:when test="${prevPage eq 'qna'}">
							<button type="button" class="back_btn" onclick="location.href='qnaForm.do'">목록가기</button>
						</c:when>
						<c:otherwise>
							<button type="button" class="back_btn" onclick="location.href='downloadForm.do'">목록가기</button>
						</c:otherwise>
					</c:choose>
						
						<button type="reset" class="reset_btn">다시작성</button>
						<button type="submit" class="write_sub" obsubmit="return false;">답글쓰기</button>
					</div>
			</form>
		</div>

	<%-- footer area --%>
	<jsp:include page="../include/footer.jsp" />
	
<script>
var num = 2; // 초기값 2

function addFileElement() {
	if (num > 5) { // 파일 업로드 최대 5개까지만 허용할 때
		alert('최대 5개까지 등록가능합니다.');
		return;
	}
	
	// div요소에 file타입 input 요소를 추가하기
	var input = '<br><input type="file" name="filename' + num + '">';
	num++; // 다음번 추가를 위해 값을 1 증가
	
	// id 속성값이 file_container인 div 요소의 참조 구하기
	var fileContainer = document.getElementById('file_container');
	fileContainer.innerHTML += input;
}
</script>
</body>
</html>