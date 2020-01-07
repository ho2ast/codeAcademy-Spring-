package com.exam.controller.admin;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.exam.controller.Action;
import com.exam.controller.ActionForward;
import com.exam.repository.MemberDao;

public class AdminMemberDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("AdminMemberDeleteAction");
		
		HttpSession session = request.getSession();
		String adminId = (String) session.getAttribute("id");

		if (adminId.equals("admin")) {
			
			String[] idArr = request.getParameterValues("delId");
			
			MemberDao memberDao = MemberDao.getInstance();
			
			memberDao.deleteMember(idArr);
			 
			response.sendRedirect("adminWholeMemberForm.do");
		} else {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('권한이 없습니다.');");
			out.println("location.href='main.do';");
			out.println("</script>");
		}
		return null;
	}

}
