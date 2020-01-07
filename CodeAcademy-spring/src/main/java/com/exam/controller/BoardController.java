package com.exam.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
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
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

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
				return "/community/board";
			} else if (prevPage == "qna") {
				return "/community/qna";
			} else {
				return "/community/download";
			}
		}
		
		model.addAttribute("id", id);
		model.addAttribute("prevPage", prevPage);
		
		return "/community/write";
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
				File thmbnailFile = new File(uploadPath, "s_" + uploadFileName);
				
				try (FileOutputStream fos = new FileOutputStream(thmbnailFile)) {
					Thumbnailator.createThumbnail(multipartFile.getInputStream(), fos, 100, 100);
				}
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
	
}
