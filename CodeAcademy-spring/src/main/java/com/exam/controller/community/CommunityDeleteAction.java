package com.exam.controller.community;

import java.io.File;
import java.io.PrintWriter;
import java.util.List;

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

public class CommunityDeleteAction implements Action {

	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("CommunityDeleteAction");
		
		HttpSession session = request.getSession();
		// 세션값 가져오기(로그인여부 확인)
		String id = (String) session.getAttribute("id");
		
		// 파라미터값 가져오기
		String pageNum = request.getParameter("pageNum");
		int num = Integer.parseInt(request.getParameter("num"));
		// DAO 객체준비
		BoardDao boardDao = BoardDao.getInstance();

		// 삭제할 글 가져오기
		BoardVO boardVO = boardDao.getBoard(num);

		// 페이지 파라미터
		String prevPage = request.getParameter("prevPage");

		// DAO 객체준비

		if (id == null || !id.equals(boardVO.getUsername())) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('삭제 권한이 없습니다.');");
			out.println("history.back();");
			out.println("</script>");
			out.close();
			return null;
		}

		// 첨부파일 테이블 AttachDao객체 준비
		AttachDao attachDao = AttachDao.getInstance();

		// 게시판 글번호에 해당하는 첨부파일 정보 가져오기
		List<AttachVO> attachList = attachDao.getAttach(num);
		
		ServletContext application = request.getServletContext();
		
		// 첨부파일 정보가 있으면 해당 실제 파일 삭제하기
		for (AttachVO attachVO : attachList) { // List 0개이면 실행 안함
			// 삭제할 첨부파일경로 가져오기
			String realPath = application.getRealPath("/upload");
			
			// 파일 삭제를 위한 File 객체 준비
			File file = new File(realPath, attachVO.getFilename()); // 폴더나 파일 작업 가능한 객체
			// parent = 파일이 위치하고 있는 경로, child = 파일명
			
			// 삭제 수행
			if (file.exists()) { // 해당경로에 파일 유무 확인
				file.delete(); // 해당경로에 있는 파일 삭제 수행
			}
		} // for

		// attach 테이블 레코드 삭제
		attachDao.deleteAttach(num);

		// 게시글 삭제하기 메소드 호출
		// [로그인한 사용자] 또는 [비로그인 사용자이고 비밀번호 일치하는 경우]
		// int[] arr = new int[] { num };
		// boardDao.deleteBoard(new int[] { num }); // 배열을 매개변수로 받을때 사용하는법
		boardDao.deleteBoard(num);

		// 삭제 처리 후 글목록 fnotice.jsp로 이동

		System.out.println(prevPage);

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
