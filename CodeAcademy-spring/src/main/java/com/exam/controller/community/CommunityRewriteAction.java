package com.exam.controller.community;

import java.io.File;
import java.nio.file.Files;
import java.sql.Timestamp;
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

public class CommunityRewriteAction implements Action {

	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("CommunityRewriteAction");
		
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		String prevPage = request.getParameter("prevPage");
		ActionForward forward = new ActionForward();
		
		if (id == null) {
			if(prevPage == "board") {
				forward.setPath("boardForm.do");
				forward.setRedirect(true);
				return forward;
			} else if (prevPage == "qna") {
				forward.setPath("qnaForm.do");
				forward.setRedirect(true);
				return forward;
			} else {
				forward.setPath("downloadForm.do");
				forward.setRedirect(true);
				return forward;
			}
		}

		// 자바빈 객체 생성
		BoardVO boardVO = new BoardVO();

		// 파라미터 찾아서 자바빈에 저장
		boardVO.setUsername(request.getParameter("username"));
		boardVO.setPasswd(request.getParameter("passwd"));
		boardVO.setSubject(request.getParameter("subject"));
		boardVO.setContent(request.getParameter("content"));
		boardVO.setCategory(request.getParameter("category"));
		boardVO.setReRef(Integer.parseInt(request.getParameter("reRef")));
		boardVO.setReLev(Integer.parseInt(request.getParameter("reLev")));
		boardVO.setReSeq(Integer.parseInt(request.getParameter("reSeq")));
		// 글 등록 날짜, IP주소 값 저장
		boardVO.setRegDate(new Timestamp(System.currentTimeMillis()));
		boardVO.setIp(request.getRemoteAddr());

		// BoardDao 객체 준비
		BoardDao boardDao = BoardDao.getInstance();

		//게시글 번호생성하는 메소드 호출
		int num = boardDao.nextBoardNum();
		// 생성된 번호를 자바빈 글번호 필드에 설정
		boardVO.setNum(num);
		boardVO.setReadcount(0); // 조회수 0

		// 게시글 한개 등록하는 메소드 호출 insertBoard(boardVO)
		boardDao.reInsertBoard(boardVO);
		//================================게시판 글 등록 처리 종료================================


		// 이동 board.jsp
		String prePage = request.getParameter("category");
		
		if (prePage.equals("자유")) {
			forward.setPath("boardForm.do");
			forward.setRedirect(true);
			return forward;
		} else if (prePage.equals("질문")) {
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
