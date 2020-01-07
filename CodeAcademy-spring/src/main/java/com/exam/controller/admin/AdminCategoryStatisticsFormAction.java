package com.exam.controller.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.exam.controller.Action;
import com.exam.controller.ActionForward;

public class AdminCategoryStatisticsFormAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("AdminCategoryStatisticsFormAction");
		
		ActionForward forward = new ActionForward();
		forward.setPath("admin/boardStatistics");
		forward.setRedirect(false);
		return forward;
	}

}
