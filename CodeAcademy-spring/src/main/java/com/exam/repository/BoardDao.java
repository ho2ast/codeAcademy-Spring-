package com.exam.repository;

import java.sql.*;
import java.util.*;

import org.apache.ibatis.session.SqlSession;

import com.exam.domain.BoardVO;
import com.exam.mapper.BoardMapper;

public class BoardDao {
	private static final BoardDao instance = new BoardDao();
	
	public static BoardDao getInstance() {
		return instance;
	}
	
	private BoardDao () {}
	
	//=====================================================================================================
	// insert할 레코드의 번호 생성 메소드
	public int nextBoardNum() {
		try(SqlSession sqlSession = DBManager.getSqlSessionFactory().openSession()) {
			BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
			
			int bnum = mapper.nextBoardNum();
			
			return bnum;
		}
	} // nextBoardNum
	
	//=====================================================================================================
	// 게시글 한개 등록하는 메소드
	public void insertBoard(BoardVO boardVO) {
		try(SqlSession sqlSession = DBManager.getSqlSessionFactory().openSession()) {
			BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
			mapper.insertBoard(boardVO);
			sqlSession.commit();
		}
	} // insertBoard
	
	//=====================================================================================================
	// 게시판 리스트 가져오기
	public List<BoardVO> getBoards(int startRow, int pageSize, String category, String search) {
		try(SqlSession sqlSession = DBManager.getSqlSessionFactory().openSession()) {
			BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
			return mapper.getBoards(startRow, pageSize, category, search);
		}
	} // getBoards
	
	//=====================================================================================================
	public List<BoardVO> getBoardList() {
		try (SqlSession sqlSession = DBManager.getSqlSessionFactory().openSession()) {
			BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
			return mapper.getBoardList();
		}
	} // getBoards
	
	//=====================================================================================================
	public int getBoardCount(String category, String search) {
		try (SqlSession sqlSession = DBManager.getSqlSessionFactory().openSession()) {
			BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
			return mapper.getBoardCount(category, search);
		}
	} //getBoardCount
	
	//=====================================================================================================
	public void updateReadcount(int num) {
		try (SqlSession sqlSession = DBManager.getSqlSessionFactory().openSession()) {
			BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
			mapper.updateReadcount(num);
			sqlSession.commit();
		}
	} // updateReadcount
	
	//=====================================================================================================
	// 글 한개를 가져오는 메소드
	public BoardVO getBoard(int num) {
		try (SqlSession sqlSession = DBManager.getSqlSessionFactory().openSession()) {
			BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
			return mapper.getBoard(num);
			// return sqlSession.getMapper(BoardMapper.class).getBoard(num);
		}
	} // getBoard
	
	//=====================================================================================================
	public boolean isPasswdEqual(int num, String passwd) {
		boolean result = false;
		
		try (SqlSession sqlSession = DBManager.getSqlSessionFactory().openSession()) {
			BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
			int count = mapper.countByNumAndPasswd(num, passwd);
			if (count == 1) {
				result = true;
			} else {
				result = false;
			}
		}
		
		return result;
	} // isPasswdEqual
	
	//=====================================================================================================
	public void deleteBoard(int num) {
		try (SqlSession sqlSession = DBManager.getSqlSessionFactory().openSession()) {
			sqlSession.getMapper(BoardMapper.class).deleteBoard(num);
			sqlSession.commit();
		}
	}
	
	//=====================================================================================================
	public void deleteBoard(int[] numArr) {
		try (SqlSession sqlSession = DBManager.getSqlSessionFactory().openSession()) {
			for (int num : numArr) {
				sqlSession.getMapper(BoardMapper.class).deleteBoard(num);
			}
			sqlSession.commit();
		}
	} // deleteBoard
	
	//=====================================================================================================
	public void updateBoard(BoardVO boardVO) {
		try (SqlSession sqlSession = DBManager.getSqlSessionFactory().openSession()) {
			BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
			mapper.updateBoard(boardVO);
			sqlSession.commit();
		}
	} // updateBoard
	
	//=====================================================================================================
	public void reInsertBoard(BoardVO boardVO) {
		try (SqlSession sqlSession = DBManager.getSqlSessionFactory().openSession()) {
			BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
			
			mapper.updateReplyGroupBySequence(boardVO.getReRef(), boardVO.getReSeq());
			
			boardVO.setReLev(boardVO.getReLev()+1);
			boardVO.setReSeq(boardVO.getReSeq()+1);
			
			mapper.insertBoard(boardVO);
			sqlSession.commit();
		}
	} // reInsertBoard
	
	//=====================================================================================================
	public List<Map<String, Object>> getCountPostByCategory() {
		List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
		
		try (SqlSession sqlSession = DBManager.getSqlSessionFactory().openSession()) {
			BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
			mapList = mapper.getCountPostByCategory();
			return mapList;
		}
	} //getCountPostByCategory
} // BoardDao
