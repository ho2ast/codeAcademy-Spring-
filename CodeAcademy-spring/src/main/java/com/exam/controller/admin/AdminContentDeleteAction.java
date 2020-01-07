package com.exam.controller.admin;

import java.io.File;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.exam.controller.Action;
import com.exam.controller.ActionForward;
import com.exam.domain.AttachVO;
import com.exam.repository.AttachDao;
import com.exam.repository.BoardDao;

public class AdminContentDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("AdminContentDeleteAction");

		HttpSession session = request.getSession();
		String adminId = (String) session.getAttribute("id");

		if (adminId.equals("admin")) {

			// post 파라미터값 한글처리
			request.setCharacterEncoding("utf-8");

			// 파라미터값 가져오기
			String[] strNumArr = request.getParameterValues("delBoard");
			int[] numArr = new int[strNumArr.length];

			for (int i = 0; i < strNumArr.length; i++) {
				numArr[i] = Integer.parseInt(strNumArr[i]);
			}

			// DAO 객체준비
			BoardDao boardDao = BoardDao.getInstance();

			// 첨부파일 테이블 AttachDao객체 준비
			AttachDao attachDao = AttachDao.getInstance();

			// 삭제할 첨부파일경로 가져오기
			ServletContext application = request.getServletContext();
			String realPath = application.getRealPath("/upload");

			for (int num : numArr) {
				// 게시판 글번호에 해당하는 첨부파일 정보 가져오기
				List<AttachVO> attachList = attachDao.getAttach(num);
				
				// 첨부파일 정보가 있으면 해당 실제 파일 삭제하기
				for (AttachVO attachVO : attachList) { // List 0개이면 실행 안함

					// 파일 삭제를 위한 File 객체 준비
					File file = new File(realPath, attachVO.getFilename()); // 폴더나 파일 작업 가능한 객체
					// parent = 파일이 위치하고 있는 경로, child = 파일명

					// 삭제 수행
					if (file.exists()) { // 해당경로에 파일 유무 확인
						file.delete(); // 해당경로에 있는 파일 삭제 수행
					}
				} // for

				// attach 테이블 레코드 삭제
			} // for
			attachDao.deleteAttach(numArr);
			boardDao.deleteBoard(numArr);
			
			response.sendRedirect("adminWholeBoardForm.do");
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
