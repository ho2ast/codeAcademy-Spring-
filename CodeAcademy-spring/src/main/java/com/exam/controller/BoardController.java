package com.exam.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.exam.domain.AttachVO;
import com.exam.domain.BoardVO;
import com.exam.service.AttachService;
import com.exam.service.BoardService;

import lombok.extern.log4j.Log4j;
import net.coobird.thumbnailator.Thumbnailator;

@Controller
@RequestMapping("/community/*")
@Log4j
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	@Autowired
	private AttachService attachService;
	
	//=====================================================================================================
	@GetMapping("/board")
	public String board(@RequestParam(defaultValue = "1") int pageNum,
			@RequestParam(defaultValue = "") String search, Model model) {
		
		// 한페이지에 보여줄 글 개수
		int pageSize = 5;
		
		// 시작행번호
		int startRow = (pageNum - 1) * pageSize;
		
		String category = "자유";
		
		// 글목록 가져오기 메소드
		List<BoardVO> boardList = boardService.getBoards(startRow, pageSize, category, search);
		
		// board테이블 전체글 개수 가져오기메소드 호출
		int count = boardService.getBoardCount(category, search);
		
		// 총페이지 개수
		int pageCount = count / pageSize + (count % pageSize == 0 ? 0 : 1);
		
		// 페이지 블록수
		int pageBlock = 5;
		
		// 시작페이지 번호
		int startPage = ((pageNum - 1) / pageBlock) * pageBlock + 1;
		
		// 끝페이지 번호
		int endPage = startPage + pageBlock - 1;
		if (endPage > pageCount) {
			endPage = pageCount;
		}
		
		Map<String, Integer> pageInfoMap = new HashMap<String, Integer>();
		pageInfoMap.put("count", count);
		pageInfoMap.put("pageCount", pageCount);
		pageInfoMap.put("pageBlock", pageBlock);
		pageInfoMap.put("startPage", startPage);
		pageInfoMap.put("endPage", endPage);
		
		String curPage = "board";
		
		model.addAttribute("curPage", curPage);
		model.addAttribute("boardList", boardList);
		model.addAttribute("pageInfoMap", pageInfoMap);
		model.addAttribute("search", search);
		model.addAttribute("pageNum", pageNum);
		
		
		return "community/board";
	} // board
	
	//=====================================================================================================
	@GetMapping("/qna")
	public String qna(@RequestParam(defaultValue = "1") int pageNum,
			@RequestParam(defaultValue = "") String search, Model model) {
		
		// 한페이지에 보여줄 글 개수
		int pageSize = 5;
		
		// 시작행번호
		int startRow = (pageNum - 1) * pageSize;
		
		String category = "질문";
		
		// 글목록 가져오기 메소드
		List<BoardVO> boardList = boardService.getBoards(startRow, pageSize, category, search);
		
		// board테이블 전체글 개수 가져오기메소드 호출
		int count = boardService.getBoardCount(category, search);
		
		// 총페이지 개수
		int pageCount = count / pageSize + (count % pageSize == 0 ? 0 : 1);
		
		// 페이지 블록수
		int pageBlock = 5;
		
		// 시작페이지 번호
		int startPage = ((pageNum - 1) / pageBlock) * pageBlock + 1;
		
		// 끝페이지 번호
		int endPage = startPage + pageBlock - 1;
		if (endPage > pageCount) {
			endPage = pageCount;
		}
		
		Map<String, Integer> pageInfoMap = new HashMap<String, Integer>();
		pageInfoMap.put("count", count);
		pageInfoMap.put("pageCount", pageCount);
		pageInfoMap.put("pageBlock", pageBlock);
		pageInfoMap.put("startPage", startPage);
		pageInfoMap.put("endPage", endPage);
		
		String curPage = "qna";
		
		model.addAttribute("curPage", curPage);
		model.addAttribute("boardList", boardList);
		model.addAttribute("pageInfoMap", pageInfoMap);
		model.addAttribute("search", search);
		model.addAttribute("pageNum", pageNum);
		
		
		return "community/qna";
	} // board
	
	//=====================================================================================================
	@GetMapping("/download")
	public String download(@RequestParam(defaultValue = "1") int pageNum,
			@RequestParam(defaultValue = "") String search, Model model) {
		
		// 한페이지에 보여줄 글 개수
		int pageSize = 5;
		
		// 시작행번호
		int startRow = (pageNum - 1) * pageSize;
		
		String category = "자료";
		
		// 글목록 가져오기 메소드
		List<BoardVO> boardList = boardService.getBoards(startRow, pageSize, category, search);
		
		// board테이블 전체글 개수 가져오기메소드 호출
		int count = boardService.getBoardCount(category, search);
		
		// 총페이지 개수
		int pageCount = count / pageSize + (count % pageSize == 0 ? 0 : 1);
		
		// 페이지 블록수
		int pageBlock = 5;
		
		// 시작페이지 번호
		int startPage = ((pageNum - 1) / pageBlock) * pageBlock + 1;
		
		// 끝페이지 번호
		int endPage = startPage + pageBlock - 1;
		if (endPage > pageCount) {
			endPage = pageCount;
		}
		
		Map<String, Integer> pageInfoMap = new HashMap<String, Integer>();
		pageInfoMap.put("count", count);
		pageInfoMap.put("pageCount", pageCount);
		pageInfoMap.put("pageBlock", pageBlock);
		pageInfoMap.put("startPage", startPage);
		pageInfoMap.put("endPage", endPage);
		
		String curPage = "download";
		
		model.addAttribute("curPage", curPage);
		model.addAttribute("boardList", boardList);
		model.addAttribute("pageInfoMap", pageInfoMap);
		model.addAttribute("search", search);
		model.addAttribute("pageNum", pageNum);
		
		
		return "community/download";
	} // board
	
	//=====================================================================================================
	@GetMapping("/content")
	public String content(@RequestParam("prevPage") String prevPage,
			@RequestParam("pageNum") int pageNum, @RequestParam("num") int num, Model model) {
		
		boardService.updateReadcount(num);
		
		BoardVO boardVO = boardService.getBoard(num);
		List<AttachVO> attachList = attachService.getAttach(num);
		
		model.addAttribute("boardVO", boardVO);
		model.addAttribute("attachList", attachList);
		model.addAttribute("prevPage", prevPage);
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("num", num);
		
		return "community/content";
	} // content
	
	//=====================================================================================================
	@GetMapping("/write")
	public String write(@RequestParam("prevPage") String prevPage, HttpSession session, Model model) {
		String id = (String) session.getAttribute("id");
		
		// 비로그인 사용자일 경우 튕겨 내기
		if (id == null) {
			if(prevPage == "board") {
				return "community/board";
			} else if (prevPage == "qna") {
				return "community/qna";
			} else {
				return "community/download";
			}
		}
		
		model.addAttribute("id", id);
		model.addAttribute("prevPage", prevPage);
		
		return "community/write";
	} // write
	
	//=====================================================================================================
	@PostMapping("/write")
	public String write(MultipartFile[] files, HttpServletRequest request,
			BoardVO boardVO, @RequestParam("category") String category) throws Exception {
		if (files != null) {
			log.info("file.length : " + files.length);
		}
		
		boardVO.setIp(request.getRemoteAddr());
		
		int num = boardService.nextBoardNum();
		
		boardVO.setNum(num);
		boardVO.setReadcount(0);
		
		boardVO.setReRef(num);
		boardVO.setReLev(0);
		boardVO.setReSeq(0);
		
		ServletContext application = request.getServletContext();
		String realPath = application.getRealPath("/resources/upload");
		log.info("realPath : " + realPath);
		
		File uploadPath = new File(realPath, getFolder());
		log.info("uploadPath : " + uploadPath);
		if(!uploadPath.exists()) {
			uploadPath.mkdirs(); // 업로드할 폴더 생성
		}
		
		List<AttachVO> attachList = new ArrayList<AttachVO>();
		System.out.println(attachList);
		
		for (MultipartFile multipartFile : files) {
			log.info("파일명: " + multipartFile.getOriginalFilename());
			log.info("파일크기: " + multipartFile.getSize());
			
			if (multipartFile.isEmpty()) {
				continue;
			}
			
			String uploadFileName = multipartFile.getOriginalFilename();
			UUID uuid = UUID.randomUUID();
			uploadFileName = uuid.toString() + "_" + uploadFileName;
			log.info("최종 업로드 파일명: " + uploadFileName);
			
			File saveFile = new File(uploadPath, uploadFileName);
			
			multipartFile.transferTo(saveFile);
			
			AttachVO attachVO = new AttachVO();
			attachVO.setBno(boardVO.getNum());
			attachVO.setUuid(uuid.toString());
			attachVO.setUploadpath(getFolder());
			attachVO.setFilename(multipartFile.getOriginalFilename());
			
			if (isImageType(saveFile)) {
//				File thmbnailFile = new File(uploadPath, "s_" + uploadFileName);
//				
//				try (FileOutputStream fos = new FileOutputStream(thmbnailFile)) {
//					Thumbnailator.createThumbnail(multipartFile.getInputStream(), fos, 100, 100);
//				}
				attachVO.setFiletype("I");
			} else {
				attachVO.setFiletype("O");
			}
			
			attachList.add(attachVO);
		} // for
		
		boardService.insertBoardAndAttach(boardVO, attachList);
		
		if(category.equals("자유")) {
			return "redirect:/community/board";
		} else if (category.equals("질문")) {
			return "redirect:/community/qna";
		} else {
			return "redirect:/community/download";
		}
	} // write
	
	//=====================================================================================================
	private boolean isImageType(File file) throws IOException {
		boolean isImageType = false;

		String contentType = Files.probeContentType(file.toPath());

		if (contentType != null) {
			isImageType = contentType.startsWith("image");
		} else {
			isImageType = false;
		}
		return isImageType;
	}
	
	//=====================================================================================================
	private String getFolder() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		Date date = new Date();
		String str = sdf.format(date);
		return str;
	}
	
	//=====================================================================================================
	public ResponseEntity<String> checkIdSession(HttpSession session, HttpServletRequest request,
			HttpServletResponse response, BoardVO boardVO) {
		String id = (String) session.getAttribute("id");
		
		if (id == null && boardVO.getPasswd() == null
			|| id != null && boardVO.getPasswd() != null
			|| id != null && !id.equals(boardVO.getUsername())) {
			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-Type", "text/html; charset=UTF-8");
			
			StringBuilder sb = new StringBuilder();
			sb.append("<script>");
			sb.append("alert('수정권한이 없습니다.');");
			sb.append("history.back();");
			sb.append("</script>");
			
			ResponseEntity<String> responseEntity = new ResponseEntity<String>(sb.toString(), headers, HttpStatus.OK);
			return responseEntity;
		}
		return null;
	}
	
	@GetMapping("/modify")
	public String modify(HttpSession session, @RequestParam("num") int num,
			@RequestParam("pageNum") int pageNum, @RequestParam("prevPage") String prevPage, Model model,
			HttpServletResponse response, HttpServletRequest request ) {
		
		BoardVO boardVO = boardService.getBoard(num);
		List<AttachVO> attachList = attachService.getAttach(num);
		String id = (String) session.getAttribute("id");
		
		checkIdSession(session, request, response, boardVO);
		
		model.addAttribute("id", id);
		model.addAttribute("boardVO", boardVO);
		model.addAttribute("attachList", attachList);
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("prevPage", prevPage);
		model.addAttribute("num", num);
		
		return "community/update";
	} // modify
	
	@PostMapping("/modify")
	public ResponseEntity<String> modify(MultipartFile[] newFile, BoardVO boardVO, Model model,
			@RequestParam("pageNum") int pageNum, @RequestParam("prevPage") String prevPage,
			@RequestParam("num") int num, String[] delFiles,
			HttpSession session, HttpServletResponse reponse, HttpServletRequest request) throws Exception {
		
		// 비회원이  회원 글 수정 1
		// 회원이 비회원 글 수정 2
		// 회원인 경우 다른 회원 글 수정 3
		// 수정권한 없음을 알리고 글목록 페이지로 강제이동
		String id = (String) session.getAttribute("id");
		if (id == null && boardVO.getPasswd() == null
				|| id != null && boardVO.getPasswd() != null
				|| id != null && !id.equals(boardVO.getUsername())) {
				HttpHeaders headers = new HttpHeaders();
				headers.add("Content-Type", "text/html; charset=UTF-8");
				
				StringBuilder sb = new StringBuilder();
				sb.append("<script>");
				sb.append("alert('수정권한이 없습니다.');");
				sb.append("history.back();");
				sb.append("</script>");
				
				ResponseEntity<String> responseEntity = new ResponseEntity<String>(sb.toString(), headers, HttpStatus.OK);
				return responseEntity;
			}
		
		ServletContext application = request.getServletContext();
		String realPath = application.getRealPath("/resources/upload");
		log.info("realPath : " + realPath);
		
		File uploadPath = new File(realPath, getFolder());
		log.info("uploadPath : " + uploadPath);
		if(!uploadPath.exists()) {
			uploadPath.mkdirs(); // 업로드할 폴더 생성
		}
		
		List<AttachVO> attachList = new ArrayList<AttachVO>();
		System.out.println(attachList);
		
		for (MultipartFile multipartFile : newFile) {
			log.info(multipartFile);
			log.info("파일명: " + multipartFile.getOriginalFilename());
			log.info("파일크기: " + multipartFile.getSize());
			
			if (multipartFile.isEmpty()) {
				continue;
			}
			
			String uploadFileName = multipartFile.getOriginalFilename();
			UUID uuid = UUID.randomUUID();
			uploadFileName = uuid.toString() + "_" + uploadFileName;
			log.info("최종 업로드 파일명: " + uploadFileName);
			
			File saveFile = new File(uploadPath, uploadFileName);
			
			multipartFile.transferTo(saveFile);
			
			AttachVO attachVO = new AttachVO();
			attachVO.setBno(boardVO.getNum());
			attachVO.setUuid(uuid.toString());
			attachVO.setUploadpath(getFolder());
			attachVO.setFilename(multipartFile.getOriginalFilename());
			
			if (isImageType(saveFile)) {
//				File thmbnailFile = new File(uploadPath, "s_" + uploadFileName);
//				
//				try (FileOutputStream fos = new FileOutputStream(thmbnailFile)) {
//					Thumbnailator.createThumbnail(multipartFile.getInputStream(), fos, 100, 100);
//				}
				attachVO.setFiletype("I");
			} else {
				attachVO.setFiletype("O");
			}
			attachList.add(attachVO);
			attachService.insertAttach(attachVO);
		} // for
		
		// 파일삭제작업
		if (delFiles != null) {
			log.info("삭제할 파일: " + delFiles);
			for (String str : delFiles) {
				String[] strArr = str.split("_");
				String uuid = strArr[0];
				String delFilename = strArr[1];
				log.info("@@@@@@@str : " + str);
				log.info("@@@@@@@realPath : " + realPath);
				log.info("@@@@@@@uploadPath : " + uploadPath);
				// 파일 삭제하기
				File delFile = new File(uploadPath, str);
				if (delFile.exists()) {
					delFile.delete();
				}
				attachService.deleteAttachByUuid(uuid);
			} // for
		}// if
		
		boardService.updateBoard(boardVO);
		
		HttpHeaders headers = new HttpHeaders();
		 // HttpStatus.FOUND 리다이렉트
		if(prevPage.equals("board")) {
			headers.add("Location", "/community/board?pageNum=" + pageNum);
			return new ResponseEntity<String>(headers, HttpStatus.FOUND);
		} else if (prevPage.equals("qna")) {
			headers.add("Location", "/community/qna?pageNum=" + pageNum);
			return new ResponseEntity<String>(headers, HttpStatus.FOUND);
		} else {
			headers.add("Location", "/community/download?pageNum=" + pageNum);
			return new ResponseEntity<String>(headers, HttpStatus.FOUND);
		}
	}//modify
	
	//=====================================================================================================
	@GetMapping("/delete")
	public ResponseEntity<String> delete(HttpSession session, HttpServletRequest request, @RequestParam("num") int num,
			@RequestParam("pageNum") int pageNum, @RequestParam("prevPage") String prevPage) {
		
		BoardVO boardVO = boardService.getBoard(num);
		
		
		String id = (String) session.getAttribute("id");
		
		if (id == null || !id.equals(boardVO.getUsername())) {
				HttpHeaders headers = new HttpHeaders();
				headers.add("Content-Type", "text/html; charset=UTF-8");
				
				StringBuilder sb = new StringBuilder();
				sb.append("<script>");
				sb.append("alert('삭제권한이 없습니다.');");
				sb.append("history.back();");
				sb.append("</script>");
				
				ResponseEntity<String> responseEntity = new ResponseEntity<String>(sb.toString(), headers, HttpStatus.OK);
				return responseEntity;
			}
		List<String> strList = new ArrayList<String>();
		strList = attachService.getUuidAndFilenameByBno(num);
		
		List<String> uploadPathList = new ArrayList<String>();
		uploadPathList = attachService.getUploadpathByBno(num);
		
		attachService.deleteAttachByBno(num);
		boardService.deleteBoard(num);
		
		ServletContext application = request.getServletContext();
		String realPath = application.getRealPath("/resources/upload/");
		
		for (String uploadPath : uploadPathList) {
			realPath += uploadPath;
			log.info("@@@@@@@realPath : " + realPath);
		}
		
		for (String str : strList) {
			if (str != null) {
				File delFile = new File(realPath, str);
				if(delFile.exists()) {
					delFile.delete();
				}
			}
		}
		
		HttpHeaders headers = new HttpHeaders();
		if(prevPage.equals("board")) {
			headers.add("Location", "/community/board?pageNum=" + pageNum);
			return new ResponseEntity<String>(headers, HttpStatus.FOUND);
		} else if (prevPage.equals("qna")) {
			headers.add("Location", "/community/qna?pageNum=" + pageNum);
			return new ResponseEntity<String>(headers, HttpStatus.FOUND);
		} else {
			headers.add("Location", "/community/download?pageNum=" + pageNum);
			return new ResponseEntity<String>(headers, HttpStatus.FOUND);
		}
	} // delete
	
	//=====================================================================================================
	@GetMapping("/rewrite")
	public String rewrite(BoardVO boardVO, String pageNum, String prevPage, String category, HttpSession session) {
		
		
		String id = (String) session.getAttribute("id");
		// 비로그인 사용자일 경우 튕겨 내기
		if (id == null) {
			if(prevPage == "board") {
				return "community/board";
			} else if (prevPage == "qna") {
				return "community/qna";
			} else {
				return "community/download";
			}
		}
		
		return "community/reWrite";
	} // rewrite
	
	@PostMapping("/rewrite")
	public String rewrite(BoardVO boardVO, HttpServletRequest request, String pageNum, String prevPage, RedirectAttributes rttr) {
		
		boardVO.setIp(request.getRemoteAddr());
		
		// 게시글 번호 생성하는 메소드 호출
		int num = boardService.nextBoardNum();
		// 생성된 번호를 자바빈 글번호 필드에 설정
		boardVO.setNum(num);
		boardVO.setReadcount(0); // 조회수 0
		
		boardService.reInsertBoard(boardVO);
		
		//return "redirect:/board/list?pageNum=" + pageNum;
		String category = boardVO.getCategory();
		rttr.addAttribute("pageNum", pageNum);
		log.info("@@@@@@@@@@@@@@@@@@@" + category);
		if (category.equals("자유")) {
			return "redirect:/community/board";
		} else if (category.equals("질문")) {
			return "redirect:/community/qna";
		} else {
			return "redirect:/community/download";
		}
	}
}
