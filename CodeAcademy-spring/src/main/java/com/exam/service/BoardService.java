package com.exam.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.exam.domain.AttachVO;
import com.exam.domain.BoardVO;
import com.exam.mapper.AttachMapper;
import com.exam.mapper.BoardMapper;

@Service
@Transactional
public class BoardService {

	@Autowired
	private BoardMapper boardMapper;
	
	@Autowired
	private AttachMapper attachMapper;
	
	//=====================================================================================================
	public List<BoardVO> getBoards(int startRow, int pageSize, String category, String search) {
		List<BoardVO> list = boardMapper.getBoards(startRow, pageSize, category, search);
		return list;
	}
	
	//=====================================================================================================
	public int getBoardCount(String category, String search) {
		return boardMapper.getBoardCount(category, search);
	}
	
	//=====================================================================================================
	public void updateReadcount(int num) {
		boardMapper.updateReadcount(num);
	}
	
	//=====================================================================================================
	public BoardVO getBoard(int num) {
		return boardMapper.getBoard(num);
	}
	
	//=====================================================================================================
	public int nextBoardNum() {
		return boardMapper.nextBoardNum();
	}
	
	//=====================================================================================================
	public void insertBoardAndAttach(BoardVO boardVO, List<AttachVO> attachList) {
		boardMapper.insertBoard(boardVO);
		
		if (attachList.size() > 0) {
			for (AttachVO attachVO : attachList) {
				attachMapper.insertAttach(attachVO);
			}
		}
	}
	
	//=====================================================================================================
}
