package com.exam.controller.community;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.exam.controller.Action;
import com.exam.controller.ActionForward;
import com.exam.domain.BoardVO;
import com.exam.repository.BoardDao;

public class DownloadAction implements Action {

	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("DownloadAction");
		
		// 파라미터값 pageNum 가져오기
		String search = request.getParameter("search"); // 검색어
		if (search == null) { // null 자체가 쿼리스트링으로 받아진다.
			search = "";
		}
		
		String strPageNum = request.getParameter("pageNum");
		if (strPageNum == null) {
			strPageNum = "1";
		}
		
		// 페이지 번호
		int pageNum = Integer.parseInt(strPageNum);
		
		// DAO 객체준비
		BoardDao boardDao = BoardDao.getInstance();
		
		// 한 페이지(화면)에 보여줄 글 개수
		int pageSize = 5;
		
		// 시작행번호 구하기
		// int startRow = (pageNum - 1) * pageSize + 1; // -1 을 안하면
		int startRow = (pageNum - 1) * pageSize; // MySQL용
		
		String category = "자료";
		
		// 글목록 가져오기 메소드 호출
		List<BoardVO> boardList = boardDao.getBoards(startRow, pageSize, category ,search);
		
		// board 테이블 전체 글개수 가져오기 메소드 호출
		int count = boardDao.getBoardCount(category, search);
		
		int pageCount = count / pageSize + (count % pageSize == 0 ? 0 : 1);
		
		int pageBlock = 5;
		
		int startPage = ((pageNum - 1) / pageBlock) * pageBlock + 1;
		
		int endPage = startPage + pageBlock - 1;
		if (pageCount < endPage) {
			endPage = pageCount;
		}
		
		// 페이지블록 관련정보를 Map 또는 VO객체로 준비
		Map<String, Integer> pageInfoMap = new HashMap<String, Integer>();
		pageInfoMap.put("count", count);
		pageInfoMap.put("pageCount", pageCount);
		pageInfoMap.put("pageBlock", pageBlock);
		pageInfoMap.put("startPage", startPage);
		pageInfoMap.put("endPage", endPage);
		
		String curPage = "download";
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		// 뷰(jsp)에 사용할 데이터를 request영역 객체에 저장
		request.setAttribute("id", id);
		request.setAttribute("boardList", boardList);
		request.setAttribute("pageInfoMap", pageInfoMap);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("search", search);
		request.setAttribute("curPage", curPage);
		
		ActionForward forward = new ActionForward();
		forward.setPath("community/download");
		forward.setRedirect(false);
		return forward;
	}

}
