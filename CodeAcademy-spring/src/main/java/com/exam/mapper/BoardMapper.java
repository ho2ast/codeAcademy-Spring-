package com.exam.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.exam.domain.BoardVO;

public interface BoardMapper {
	// insert할 레코드의 번호 생성 메소드
	public int nextBoardNum();
	
	// 게시글 한개 등록하는 메소드
	public void insertBoard(BoardVO boardVO);
	
	public List<BoardVO> getBoards(@Param("startRow") int startRow, @Param("pageSize") int pageSize, @Param("category")String category, @Param("search") String search);
	
	public List<BoardVO> getBoardList();
	
	public int getBoardCount(@Param("category") String category, @Param("search") String search);
	
	public void updateReadcount(int num);
	
	public BoardVO getBoard(int num);
	
	public int countByNumAndPasswd(@Param("num") int num, @Param("passwd") String passwd);
	
	public void deleteBoard(int num);
	
	public void updateBoard(BoardVO boardVO);
	
	public void updateReplyGroupBySequence(@Param("reRef") int reRef, @Param("reSeq") int reSeqboardVO);
	
	public void reInsertBoard(BoardVO boardVO);
	
	public List<Map<String, Object>> getCountPostByCategory();
}
