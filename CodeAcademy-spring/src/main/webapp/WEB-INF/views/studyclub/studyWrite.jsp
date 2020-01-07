<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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

<%-- 페이지 파라미터 --%>
<% String prevPage = request.getParameter("prevPage"); %>

<body>
	<%-- header area --%>
	<jsp:include page="../include/header.jsp" />

	<%-- topMenu area --%>
	<jsp:include page="../include/community_topMenu.jsp" />
	
	<%-- 세션값 가져오기 --%>
	<% String id = (String) session.getAttribute("id"); %>
	
		<div class="col-md-11">
			<div id="write_header">
				<h2>글쓰기</h2>
			</div>
			<form action="writeProcess.jsp" method="post" class="form-horizontal" id="board_write_form" name="frm" enctype="multipart/form-data" onsubmit="return check();">
			<%
			if (id == null) { // 로그인 안했을때
				%>
					<div class="col-md-5">
						<div id="write_label">이름</div>
						<div id="input"><input type="text" class="form-control" name="username"></div>
					</div>
					<div class="col-md-5">
						<div id="write_label">비밀번호</div>
						<div id="input"><input type="password" class="form-control" name="passwd"></div>
					</div>
				<%
			} else { // id != null
				%>
					<div class="col-md-5">
						<div id="write_label">이름</div>
						<div id="input"><input type="text" class="form-control" name="username" value="<%=id %>" readonly="readonly"></div>
					</div>
				<%
			}
			%>
					<div class="col-md-5">
						<div id="write_label">제목</div>
						<div id="input"><input type="text" class="form-control" name="subject" rows="20" ></div>
					</div>
					<div class="col-md-5" id="file_container">
						<div id="write_label">파일</div>
						<button type="button" onclick="addFileElement();" id="addFile">파일추가</button><br>
						<input type="file" name="filename1" id="fileadd"/>
					</div>

			<%
				if (prevPage.equals("board")) {
					%>
					<div class="col-md-5">
						<div id="write_label">카테고리</div><select id="board_category" name="category" class="form-control">
							<option value="자유" selected>자유</option>
							<option value="질문">질문</option>
							<option value="자료">자료</option>
						</select>
					</div>
					<%
				} else if (prevPage.equals("qna")) {
					%>
					<div class="col-md-5">
						<div id="write_label">카테고리</div><select id="board_category" name="category" class="form-control">
							<option value="자유">자유</option>
							<option value="질문" selected>질문</option>
							<option value="자료">자료</option>
						</select>
					</div>
					<%
				} else {
					%>
					<div class="col-md-5">
						<div id="write_label">카테고리</div><select id="board_category" name="category" class="form-control">
							<option value="자유">자유</option>
							<option value="질문">질문</option>
							<option value="자료" selected>자료</option>
						</select>
					</div>
					<%
				}
			%>
					<div class="col-md-5">
						<div id="write_label">내용</div>
						<div id="input"><textarea id="board_content" name="content" cols="50" rows="20"></textarea></div>
					</div>
					<div>
						<%
						if (prevPage.equals("board")) {
							%>
						<button type="button" class="back_btn" onclick="location.href='board.jsp'">목록가기</button>
							<%
						} else if (prevPage.equals("qna")) {
							%>
							<button type="button" class="back_btn" onclick="location.href='qna.jsp'">목록가기</button>
							<%
						} else {
							%>
							<button type="button" class="back_btn" onclick="location.href='download.jsp'">목록가기</button>
							<%
						}
						%>
						<button type="reset" class="reset_btn">다시작성</button>
						<button type="submit" class="write_sub" obsubmit="return false;">글쓰기</button>
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