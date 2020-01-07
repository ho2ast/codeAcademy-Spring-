package com.exam.controller.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.exam.controller.Action;
import com.exam.controller.ActionForward;

public class AdminGenderStatisticsFormAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("AdminGenderStatisticsFormAction");
		ActionForward forward = new ActionForward();
		forward.setPath("admin/memberStatistics");
		forward.setRedirect(false);
		return forward;
	}
}
