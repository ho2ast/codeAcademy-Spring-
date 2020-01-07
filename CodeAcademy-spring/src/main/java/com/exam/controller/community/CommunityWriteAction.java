package com.exam.controller.community;

import java.io.File;
import java.nio.file.Files;
import java.sql.Timestamp;
import java.util.Enumeration;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.exam.controller.Action;
import com.exam.controller.ActionForward;
import com.exam.domain.AttachVO;
import com.exam.domain.BoardVO;
import com.exam.repository.AttachDao;
import com.exam.repository.BoardDao;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class CommunityWriteAction implements Action {

	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("CommunityWriteAction");
		// COS 라이브러리를 이용한 파일 업로드
		// MultipartRequest 생성자 호출시에 파일업로드가 완료됨
		
		// 세션값 가져오기
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		String prevPage = request.getParameter("prevPage");
		// 비로그인 사용자가 글쓰기 시도할 경우 튕겨내기
		
		ActionForward forward = new ActionForward();

		// 필요한 매개값 5개
		// 1. request
		// 2. saveDirectory (업로드 할 경로)
		ServletContext application = request.getServletContext();
		String realPath = application.getRealPath("/upload"); // 프로젝트 당 하나 주어짐, "/" -> 프로젝트 경로, String으로 리턴
		System.out.println("realPath : " + realPath);

		// 3. maxPostSize (최대 업로드 파일 크기, byte로 작성해야됨(1MB=1024*1024))
		int maxSize = 1024*1024*10;   // 10MB

		// 4. encoding (파일명 한글처리)

		// 5. policy (파일이름중복 처리)

		// 파일업로드 수행
		MultipartRequest multi = new MultipartRequest(request, realPath, maxSize, "utf-8", new DefaultFileRenamePolicy());
		// 파일을 찾을때 기본 request.getParameter 사용 불가 <- 파일을 전송할때 multipart 방식으로 모두 쪼갠다음 전송하므로
//		      -> 액션태그와 연동이 안된다
		// 업로드가 먼저수행되고 request 가 MultipartRequest 형식으로

		//================================게시판 글 등록 처리 시작================================

		// 자바빈 객체 생성
		BoardVO boardVO = new BoardVO();

		// 파라미터 찾아서 자바빈에 저장
		boardVO.setUsername(multi.getParameter("username"));
		boardVO.setPasswd(multi.getParameter("passwd"));
		boardVO.setSubject(multi.getParameter("subject"));
		boardVO.setContent(multi.getParameter("content"));
		boardVO.setCategory(multi.getParameter("category"));
		// 글 등록 날짜, IP주소 값 저장
		boardVO.setRegDate(new Timestamp(System.currentTimeMillis()));
		boardVO.setIp(request.getRemoteAddr());

		// BoardDao 객체 준비
		BoardDao boardDao = BoardDao.getInstance();

		//게시글 번호생성하는 메소드 호출
		int num = boardDao.nextBoardNum();
		// 생성된 번호를 자바빈 글번호 필드에 설정
		boardVO.setNum(num);
		boardVO.setReadcount(0); // 조회수 0

		//주글일 경우
		boardVO.setReRef(num); // 주글 쓰기에서 [글그룹 번호]는 글번호와 동일함
		boardVO.setReLev(0); // [들여쓰기 레벨] 0
		boardVO.setReSeq(0); // [글그룹 안에서의 순서] 0

		// 게시글 한개 등록하는 메소드 호출 insertBoard(boardVO)
		boardDao.insertBoard(boardVO);
		//================================게시판 글 등록 처리 종료================================


		//================================첨부파일 등록 처리 시작================================

		// 업로드한 원본 파일이름 (a.ppt)
		// String originalName = multi.getOriginalFileName("filename");
		// System.out.println("originalName = " + originalName);

		// 실제로 업로드된 파일이름 (같은 이름으로 올리면 중복되서 a1.ppt)
		// String realFileName = multi.getFilesystemName("filename"); // fwrite의 filename을 불러옴
		// System.out.println("realFileName = " + realFileName);

		// Enumeration 열거형. file의 파라미터 이름들을 가짐
		// 자바의 Iterator와 사용방법이 동일함
		Enumeration<String> enu = multi.getFileNames();
		while (enu.hasMoreElements()) { // 그 다음요소 있으면
			String str = (String) enu.nextElement();
			System.out.println(str);
			
			// 실제로 업로드 된 파일이름 구하기
			// 해당 파라미터 이름을 업로드에 사용 안했으면 null이 리턴
			String realFileName = multi.getFilesystemName(str);
			
			if (realFileName != null) {
				
				// 자바빈 AttachVO 객체 생성
				AttachVO attachVO = new AttachVO();
				
				UUID uuid = UUID.randomUUID(); // 랜덤 ID값을 가진 객체를 만들어줌, 
				attachVO.setUuid(uuid.toString()); // ㄴ>만들어진 ID문자열을  toString으로
				attachVO.setFilename(realFileName); // 실제 생성된 파일이름
				attachVO.setBno(num); // 게시글 번호
			
				// 이미지 파일여부 확인
				File file = new File(realPath, realFileName);
				String contentType = Files.probeContentType(file.toPath());
				System.out.print(contentType);
				if (contentType != null) {
					boolean isImage = contentType.startsWith("image");
					if (isImage) {
						attachVO.setFiletype("I"); // Image File
					} else {
						attachVO.setFiletype("O"); // Other(Not Image)
					}
				} else {
					attachVO.setFiletype("O"); // Other(Not Image)
				}
				
				// AttachDao 준비
				AttachDao attachDao = AttachDao.getInstance();
				// 첨부파일 한개 등록하는 메소드
				attachDao.insertAttach(attachVO);
			} // if 파일 업로드 여부 확인
		} // while

		//================================첨부파일 등록 처리 종료================================

		// 이동 board.jsp
		
		String prePage = multi.getParameter("category");
		
		if (prePage.equals("자유")) {
			forward.setPath("boardForm.do");
			forward.setRedirect(true);
			return forward;
		} else if (prePage.equals("질문")) {
			forward.setPath("qnaForm.do");
			forward.setRedirect(true);
			return forward;
		} else {
			forward.setPath("downloadForm.do");
			forward.setRedirect(true);
			return forward;
		}
	}

}
