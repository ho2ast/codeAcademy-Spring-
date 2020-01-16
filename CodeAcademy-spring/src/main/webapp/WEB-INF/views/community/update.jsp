<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>

<head>
<%-- head area --%>
<jsp:include page="../include/common_head.jsp"/>
</head>

<body>
<%-- header area --%>
<jsp:include page="../include/header.jsp"/>

		<div class="col-md-11">
			<div id="write_header">
				<h2>글 수정</h2>
			</div>
			<form action="/community/modify" method="post" class="form-horizontal" id="board_write_form" name="frm" enctype="multipart/form-data" onsubmit="return check();">
			<input type="hidden" name="pageNum" value="${pageNum}">
			<input type="hidden" name="num" value="${num}">
			<input type="hidden" name="prevPage" value="${prevPage}">

			<div class="col-md-5">
				<div id="write_label">이름</div>
				<div id="input"><input type="text" class="form-control" name="username" value="${id}" readonly="readonly"></div>
			</div>

			<div class="col-md-5">
				<div id="write_label">제목</div>
				<div id="input"><input type="text" class="form-control" name="subject" rows="20" value="${boardVO.subject}"></div>
			</div>

			<div class="col-md-5" id="file_container">
			<div id="write_label">파일</div>
			<c:if test="${!empty attachList && fn:length(attachList) gt 0}">
				<ul id="fileList" style="margin-left: 100px; text-align: left; margin-bottom: 5px; margin-top: 5px;">
				<c:forEach var="attach" items="${attachList }">
					<li>
						<div class="attach-item">
							${attach.filename}
							<span class="del" style="color: red; font-weight: bold">X</span>
						</div>
							<input type="hidden" name="oldFiles" value="${attach.uuid}_${attach.filename}">
					</li>
				</c:forEach>
				</ul>
			</c:if>
				<button type="button" id="addBtn" style="margin-right: 173px; margin-bottom: 5px;">새로 업로드</button><br>
				<div id="newFilesContainer"></div>
			</div>


			<div class="col-md-5">
				<div id="write_label">내용</div>
				<div id="input"><textarea id="board_content" name="content" cols="50" rows="20">${boardVO.content}</textarea></div>
			</div>
			<div>
				<c:choose>
					<c:when test="${prevPage eq 'board' }">
						<button type="button" class="back_btn" onclick="location.href='/community/board'">목록가기</button>
					</c:when>
					<c:when test="${prevPage eq 'qna' }">
						<button type="button" class="back_btn" onclick="location.href='/community/qna'">목록가기</button>
					</c:when>
					<c:otherwise>
						<button type="button" class="back_btn" onclick="location.href='/community/download'">목록가기</button>
					</c:otherwise>
				</c:choose>
				<button type="reset" class="reset_btn">다시작성</button>
				<button type="submit" class="write_sub" obsubmit="return false;">글수정</button>
			</div>
			</form>
		</div>

<%-- footer area --%>
<jsp:include page="../include/footer.jsp"/>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script>
function check() {
	// 로그인 안한 사용자일 경우 패스워드 입력여부 확인
	var objPasswd = document.frm.passwd;
	if (objPasswd != null) {
		if (objPasswd.value.length == 0) {
			alert('게시글 패스워드는 필수 입력사항입니다.');
			objPasswd.focus();
			return false;
		}
	}
	// 글수정 의도 확인
	var result = confirm('${num}번 글을 정말로 수정하시겠습니까?');
	if (result == false) {
		return false;
	}
} // function check

//id가 btn인 버튼에 클릭이벤트 연결
let num = 1;
$('#addBtn').on('click', function () {
	if(num > 5) {
		alert('최대 5개까지 추가 가능합니다.');
		return;
	}
	let str = '<input type="file" name="newFile"><br>';
	$('#newFilesContainer').append(str); // 뒤에 추가
	num++;
});

// class명이 del인 span태그에 클릭 이벤트 연결하기
// querySelectoAll로 리턴되는 객체는 NodeList타입임
// var delList = document.querySelectorAll('span.del');
// for (let i=0; i<delList.length; i++) {
// 	var spanElem = delList.item(i);
// 	// span요소에 이벤트 연결하기
// 	spanElem.onclick = function (event) {
// 		// 이벤트객체의 target은 이벤트가 발생된 객체를 의미함.
// 		// closest()는 가장 가까운 상위요소 한개 가져오기
// 		var liElem = event.target.closest('li');
// 		// childeNodes는 현재 요소의 자식요소들을 NodeList 타입으로 가져옴.
// 		var ndList = liElem.childNodes;
		
// 		var divElem = ndList.item(1);
// 		var inputElem = ndList.item(3);
		
// 		//inputElem.setAttribute('name', 'delFiles'); // name 속성값 바꾸기
// 		divElem.remove(); // 삭제
// 	};
// } // for

//class명이 del인 span태그에 클릭이벤트 연결하기 - jQuery방식
$('span.del').on('click', function () {
	var $li = $(this).closest('li');

	$li.children('input[type="hidden"]').attr('name', 'delFiles');
	$li.children('div.attach-item').remove();
});
</script>
</body>
</html>