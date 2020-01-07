package com.exam.controller.community;

import java.io.PrintWriter;
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

public class CommunityUpdateFormAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("CommunityUpdateFormAction");
		// 파라미터 가져오기
		int num = Integer.parseInt(request.getParameter("num"));
		String pageNum = request.getParameter("pageNum");
		String prevPage = request.getParameter("prevPage");

		// DAO 객체
		BoardDao boardDao = BoardDao.getInstance();

		// 수정할 글
		BoardVO boardVO = boardDao.getBoard(num);
		
		AttachDao attachDao = AttachDao.getInstance();
		
		List<AttachVO> attachList = attachDao.getAttach(num);
		
		// 세션값 가져오기
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
//		if (id == null) {
//			if(prevPage == "board") {
//				forward.setPath("boardForm.do");
//				forward.setRedirect(true);
//			} else if (prevPage == "qna") {
//				forward.setPath("qnaForm.do");
//				forward.setRedirect(true);
//			} else {
//				forward.setPath("downloadForm.do");
//				forward.setRedirect(true);
//			}
//		}
		
		// 비회원이  회원 글 수정 1
		// 회원이 비회원 글 수정 2
		// 회원인 경우 다른 회원 글 수정 3
		// 수정권한 없음을 알리고 글목록 페이지로 강제이동
		if (id == null && boardVO.getPasswd() == null
			|| id != null && boardVO.getPasswd() != null
			|| id != null && !id.equals(boardVO.getUsername())) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('수정권한이 없습니다.');");
			out.println("history.back();");
			out.println("</script>");
			
			return null;
		}
		
		request.setAttribute("num", num);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("prevPage", prevPage);
		request.setAttribute("boardVO", boardVO);
		request.setAttribute("attachList", attachList);
		
		ActionForward forward = new ActionForward();
		forward.setPath("community/update");
		forward.setRedirect(false);
		return forward;
	}

}
