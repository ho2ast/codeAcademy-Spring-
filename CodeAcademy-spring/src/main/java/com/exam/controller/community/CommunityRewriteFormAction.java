package com.exam.controller.community;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.exam.controller.Action;
import com.exam.controller.ActionForward;

public class CommunityRewriteFormAction implements Action {

	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("RewriteFormAction");
		

		// 페이지 파라미터
		String prevPage = request.getParameter("prevPage");
		
		
		//세션값 가져오기
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		
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
		
		// 파라미터값 가져오기
		String reRef = request.getParameter("reRef");
		String reLev = request.getParameter("reLev");
		String reSeq = request.getParameter("reSeq");
		String subject = request.getParameter("Subject");
		String category = request.getParameter("Category");
		String pageNum = request.getParameter("pageNum");
		
		request.setAttribute("prevPage", prevPage);
		request.setAttribute("id", id);
		request.setAttribute("reRef", reRef);
		request.setAttribute("reLev", reLev);
		request.setAttribute("reSeq", reSeq);
		request.setAttribute("subject", subject);
		request.setAttribute("category", category);
		request.setAttribute("pageNum", pageNum);
		
		forward.setPath("community/reWrite");
		forward.setRedirect(false);
		return forward;
	}

}
