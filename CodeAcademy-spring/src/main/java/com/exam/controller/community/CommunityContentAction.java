package com.exam.controller.community;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.exam.controller.Action;
import com.exam.controller.ActionForward;
import com.exam.domain.AttachVO;
import com.exam.domain.BoardVO;
import com.exam.repository.AttachDao;
import com.exam.repository.BoardDao;

public class CommunityContentAction implements Action {

	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("CommunityContentAction");
		
		// 페이지 번호 pageNum 파라미터값 가져오기
		String pageNum = request.getParameter("pageNum");
		String prevPage = request.getParameter("prevPage");
		// 글번호 num 파라미터 가져오기
		int num = Integer.parseInt(request.getParameter("num"));
		
		HttpSession session = request.getSession();
		// 세션값 아이디 가져오기
		String id = (String) session.getAttribute("id");

		// DAO 객체준비
		BoardDao boardDao = BoardDao.getInstance();

		// 조회수 1증가
		boardDao.updateReadcount(num);

		// 글번호에 해당되는 레코드 한개
		BoardVO boardVO = boardDao.getBoard(num);

		// AttachDao객체 준비
		AttachDao attachDao = AttachDao.getInstance();

		// 글번호랑 맞는 첨부파일 가져오기
		List<AttachVO> attachList = attachDao.getAttach(num);
		
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("prevPage", prevPage);
		request.setAttribute("num", num);
		request.setAttribute("id", id);
		request.setAttribute("boardVO", boardVO);
		request.setAttribute("attachList", attachList);

		// 페이지 파라미터
		
		ActionForward forward = new ActionForward();
		forward.setPath("community/content");
		forward.setRedirect(false);
		return forward;
	}

}
