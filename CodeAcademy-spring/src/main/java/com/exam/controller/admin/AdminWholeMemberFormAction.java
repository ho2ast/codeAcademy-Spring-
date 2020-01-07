package com.exam.controller.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.exam.controller.Action;
import com.exam.controller.ActionForward;
import com.exam.domain.MemberVO;
import com.exam.repository.MemberDao;

public class AdminWholeMemberFormAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("AdminWholeMemberFormAction");
		// MemberDAO 준비
		MemberDao memberDao = MemberDao.getInstance();

		List<MemberVO> memberList = memberDao.getMemberList();

		request.setAttribute("memberList", memberList);

		ActionForward forward = new ActionForward();
		forward.setPath("admin/wholeMember");
		forward.setRedirect(false);
		return forward;
	}

}
