package com.exam.controller.admin;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;

import com.exam.controller.Action;
import com.exam.controller.ActionForward;
import com.exam.repository.BoardDao;

public class AdminCategoryStatisticsAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		BoardDao boardDao = BoardDao.getInstance();

		List<Map<String, Object>> list = boardDao.getCountPostByCategory();

		JSONArray jsonArray = new JSONArray(); // []
		// 열제목 JSONArray 준비하기
		JSONArray titleArray = new JSONArray();
		titleArray.add("작성년월");
		titleArray.add("자유게시판");
		titleArray.add("자료게시판");
		titleArray.add("질문게시판");
		jsonArray.add(titleArray);

		for (Map<String, Object> map : list) {
			JSONArray rowArray = new JSONArray();
			
			rowArray.add(map.get("month"));
			rowArray.add(map.get("board"));
			rowArray.add(map.get("download"));
			rowArray.add(map.get("qna"));
			
			jsonArray.add(rowArray);
		}
		
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println(jsonArray);
		
		return null;
	}
}
