package com.exam.controller.admin;

import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;

import com.exam.controller.Action;
import com.exam.controller.ActionForward;
import com.exam.repository.MemberDao;

public class AdminGenderStatisticsAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("AdminGenderStatisticsAction");
		MemberDao memberDao = MemberDao.getInstance();

		List<Map<String, Object>> list = memberDao.getGenderRatio(); 

		JSONArray jsonArray = new JSONArray(); // []
		// 열제목 JSONArray 준비하기
		JSONArray titleArray = new JSONArray();
		titleArray.add("성별");
		titleArray.add("인원수");
		jsonArray.add(titleArray);

		for (Map<String, Object> map : list) {
			JSONArray rowArray = new JSONArray();
			if (map.get("gender") == null) {
				rowArray.add("선택안함");
			} else {
				rowArray.add(map.get("gender"));
			}
			rowArray.add(map.get("cnt"));
			
			jsonArray.add(rowArray);
		}

		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println(jsonArray);
		
		return null;
	}

}
