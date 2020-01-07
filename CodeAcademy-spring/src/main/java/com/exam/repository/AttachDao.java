package com.exam.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.exam.domain.AttachVO;
import com.exam.mapper.AttachMapper;

public class AttachDao {

	private static AttachDao instance = new AttachDao();
	
	public static AttachDao getInstance() {
		return instance;
	}

	private AttachDao () {}
	
	// 첨부파일 정보 입력하기 메소드
	public void insertAttach(AttachVO attachVO) {
		try (SqlSession sqlSession = DBManager.getSqlSessionFactory().openSession()) {
			AttachMapper mapper = sqlSession.getMapper(AttachMapper.class);
			mapper.insertAttach(attachVO);
			sqlSession.commit();
		}
	} // insertAttach
	
	// 글번호에 해당 하는 첨부파일 정보 가져오기 첨부파일이 1개 일때를 가정
	public List<AttachVO> getAttach(int bno) {
		List<AttachVO> list = new ArrayList<AttachVO>();
		
		try (SqlSession sqlSession = DBManager.getSqlSessionFactory().openSession()) {
			AttachMapper mapper = sqlSession.getMapper(AttachMapper.class);
			list = mapper.getAttach(bno);
		}
		return list;
	} // getAttach
	
	
//	public List<AttachVO> getAttach(int[] numArr) {
//		List<AttachVO> list = new ArrayList<AttachVO>();
//		
//		try (SqlSession sqlSession = DBManager.getSqlSessionFactory().openSession()) {
//			for (int bno : numArr) {
//				list = sqlSession.getMapper(AttachMapper.class).getAttach(bno);
//			}
//		}
//		return list;
//	} // getAttach
	
	// 게시판 글번호에 해당하는 첨부파일 정보 삭제하는 메소드
	public void deleteAttach(int bno) {
		try (SqlSession sqlSession = DBManager.getSqlSessionFactory().openSession()) {
			AttachMapper mapper = sqlSession.getMapper(AttachMapper.class);
			mapper.deleteAttachByBno(bno);
			sqlSession.commit();
		}
	} // deleteAttach(bno)
	
	// 게시판 글번호에 해당하는 첨부파일 정보 삭제하는 메소드
	public void deleteAttach(int[] num) {
		try (SqlSession sqlSession = DBManager.getSqlSessionFactory().openSession()) {
			for (int bno : num) {
				sqlSession.getMapper(AttachMapper.class).deleteAttachByBno(bno);;
				sqlSession.commit();
			}
		}
	} // deleteAttach(bno)
	
	
	public void deleteAttach(String uuid) {
		try (SqlSession sqlSession = DBManager.getSqlSessionFactory().openSession()) {
			AttachMapper mapper = sqlSession.getMapper(AttachMapper.class);
			mapper.deleteAttachByUuid(uuid);
			sqlSession.commit();
		}
		Connection con = null;
		PreparedStatement pstmt = null;
	} // deleteAttach(uuid)
	
} // class AttachDAO