package com.exam.controller.community;

import java.io.File;
import java.nio.file.Files;
import java.util.Enumeration;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.exam.controller.Action;
import com.exam.controller.ActionForward;
import com.exam.domain.AttachVO;
import com.exam.domain.BoardVO;
import com.exam.repository.AttachDao;
import com.exam.repository.BoardDao;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class CommunityUpdateAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("CommunityUpdateAction");
		// COS 라이브러리를 이용한 파일 업로드
		// MultipartRequest 생성자 호출시에 파일업로드가 완료됨

		// 필요한 매개값 5개
		//1. request

		//2. saveDirectory (업로드 할 경로)
		ServletContext application = request.getServletContext();
		String realPath = application.getRealPath("/upload");

		//3. maxPostSize (최대 업로드 파일 크기, byte로 작성해야됨)
		int maxSize = 1024*1024*10;

		//4.encoding

		//5. policy

		// 파일업로드 수행 완료
		MultipartRequest multi = new MultipartRequest(request, realPath, maxSize, "utf-8", new DefaultFileRenamePolicy());

		// 세션값 가져오기(로그인 여부확인)
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");

		// 수정할 글번호, 패스워드, pageNum가져오기
		String pageNum = multi.getParameter("pageNum");
		int num = Integer.parseInt(multi.getParameter("num"));
		String passwd = multi.getParameter("passwd");

		BoardDao boardDao = BoardDao.getInstance();

		// 로그인 안한 사용자는 패스워드 여부 일치 확인
//		if (id == null) {
//			boolean isPasswdEqual = boardDao.isPasswdEqual(num, passwd);
//			if (!isPasswdEqual) {
//				<script>
//					alert('패스워드가 다릅니다.');
//					history.back();
//				</script>
//				return;
//			}
//		}
		// ==================== 게시판 글 수정 시작 ====================
		// 자바진 객체 생성
		BoardVO boardVO = new BoardVO();

		// 파라미터 찾아서 자바빈에 저장
		boardVO.setNum(num);
		boardVO.setPasswd(multi.getParameter("passwd"));
		boardVO.setSubject(multi.getParameter("subject"));
		boardVO.setContent(multi.getParameter("content"));

		// 수정 메소드 호출
		boardDao.updateBoard(boardVO);
		//==================== 게시판 글 수정 종료 ====================

		// ==================== 첨부파일 DB등록 시작 ====================
		// AttachDao준비
		AttachDao attachDao = AttachDao.getInstance();

		// Enumeration
		Enumeration<String> enu = multi.getFileNames();
		while (enu.hasMoreElements()) { // 다음 요소 있는지 확인
			String str = (String) enu.nextElement();

			// 실제 업로드 된 파일 이름 구하기
			String realFileName = multi.getFilesystemName(str);
			
			if (realFileName != null) {
				AttachVO attachVO = new AttachVO();
				
				UUID uuid = UUID.randomUUID();
				attachVO.setUuid(uuid.toString());
				attachVO.setFilename(realFileName);
				attachVO.setBno(num);
				
				// 이미지 파일여부 확인
				File file = new File(realPath, realFileName);
				String contentType = Files.probeContentType(file.toPath());
				boolean isImage = contentType.startsWith("image");
				if (isImage){
					attachVO.setFiletype("I");
				} else {
					attachVO.setFiletype("O");
				}
				// 첨부파일 한개 등록하는 메소드
				attachDao.insertAttach(attachVO);
			} // if
		} // while
		//==================== 첨부파일 DB등록 시작 ====================

		// ==================== 삭제파일 작업시작 ====================
		// 삭제할 파일정보 파라미터 가져오기
		String[] delFiles = multi.getParameterValues("delFiles");
		if (delFiles != null) {
			for (String str : delFiles) {
				String[] strArr = str.split("_");
				String uuid = strArr[0];
				String delFilename = strArr[1];
				
				// 파일 삭제하기
				File delFile = new File(realPath, delFilename);
				if (delFile.exists()) {
					delFile.delete();
				}
				
				attachDao.deleteAttach(uuid);
			} // for
		}// if


		//삭제 처리 후 글목록 게시판.jsp로 이동
		String prevPage = multi.getParameter("prevPage");
		
		ActionForward forward = new ActionForward();
		
		if (prevPage.equals("board")) {
			forward.setPath("boardForm.do");
			forward.setRedirect(true);
			return forward;
		} else if (prevPage.equals("qna")) {
			forward.setPath("qnaForm.do");
			forward.setRedirect(true);
			return forward;
		} else {
			forward.setPath("downloadForm.do");
			forward.setRedirect(true);
			return forward;
		}
	}

}
