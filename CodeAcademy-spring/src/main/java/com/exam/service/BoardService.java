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

import lombok.extern.log4j.Log4j;

@Service
@Transactional
@Log4j
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
	public void updateBoard(BoardVO boardVO) {
		boardMapper.updateBoard(boardVO);
	}
	
	//=====================================================================================================
	public void deleteBoard(int num) {
		boardMapper.deleteBoard(num);
	}
	
	//=====================================================================================================
	public void reInsertBoard(BoardVO boardVO) {
		// 같은 글그룹에서의 답글순서(re_seq) 재배치 update수행
		// 조건 re_ref같은그룹 re_seq 큰값은 re_seq+1
		boardMapper.updateReplyGroupBySequence(boardVO.getReRef(), boardVO.getReSeq());
		
		// 답글 insert re_ref그대로 re_lev+1 re_seq+1
		// re_lev 는 [답글을 다는 대상글]의 들여쓰기값 + 1
		boardVO.setReLev(boardVO.getReLev() + 1);
		// re_seq 는 [답글을 다는 대상글]의 글그룹 내 순번값 + 1
		boardVO.setReSeq(boardVO.getReSeq() + 1);
		log.info("답글: " + boardVO);
		// 답글 insert 수행
		boardMapper.insertBoard(boardVO);
	}
}
