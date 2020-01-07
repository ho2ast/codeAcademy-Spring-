package com.exam.controller.admin;

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

public class AdminContentFormAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("AdminContentFormAction");
		// 글번호 num 파라미터 가져오기
		int num = Integer.parseInt(request.getParameter("num"));
		
		HttpSession session = request.getSession();
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
		
		request.setAttribute("id", id);
		request.setAttribute("num", num);
		request.setAttribute("boardVO", boardVO);
		request.setAttribute("atttachList", attachList);
		
		ActionForward forward = new ActionForward();
		forward.setPath("admin/adminContent");
		forward.setRedirect(false);
		return forward;
	}

}
