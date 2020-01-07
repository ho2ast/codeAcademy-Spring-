package com.exam.controller.community;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.exam.controller.Action;
import com.exam.controller.ActionForward;

public class CommunityWriteFormAction implements Action {

	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("WriteFormAction");

		// 페이지 파라미터
		String prevPage = request.getParameter("prevPage");
		
		// 세션값 가져오기
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		// 비로그인 사용자가 글쓰기 시도할 경우 튕겨내기
		
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
		
		request.setAttribute("id", id);
		request.setAttribute("prevPage", prevPage);

		forward.setPath("community/write");
		forward.setRedirect(false);
		return forward;
	}

}
