package com.exam.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.exam.domain.MemberVO;
import com.exam.mapper.MemberMapper;

public class MemberDao {
	private static final MemberDao instance = new MemberDao();
	
	public static MemberDao getInstance () {
		return instance;
	}
	
	private MemberDao () {}
	//=====================================================================================================
	public int insertMember(MemberVO vo) { // 매개변수의 입력시 오류를 방지하지 위해서 MemberVO에서 받아온다.
		// Connection 가져오기
		SqlSession sqlSession = DBManager.getSqlSessionFactory().openSession();
		// 인터페이스를 구현한 Mapper 프록시 객체를 만들어서 리턴
		MemberMapper mapper = sqlSession.getMapper(MemberMapper.class);
		int count = mapper.insertMember(vo);
		if(count > 0) {
			sqlSession.commit();
		} else {
			sqlSession.rollback();
		}
		sqlSession.close();
		return count;
	} // insertMemBer Method
	
	//=====================================================================================================
	public boolean isIdDuplicated(String id) {
		boolean isIdDuplicated = false;
		
		SqlSession sqlSession = DBManager.getSqlSessionFactory().openSession();
		
		MemberMapper mapper = sqlSession.getMapper(MemberMapper.class);
		
		int count = mapper.countMemberById(id);
		
		if (count > 0) {
			isIdDuplicated = true;
		}
		
		sqlSession.close();
		
		return isIdDuplicated;
	} // isIdDuplicated
	
	//=====================================================================================================
	public boolean isEmailDuplicated(String email) {
		boolean isEmailDuplicated = false;
		
		SqlSession sqlSession = DBManager.getSqlSessionFactory().openSession();
		
		MemberMapper mapper = sqlSession.getMapper(MemberMapper.class);
		
		int count = mapper.countMemberByEmail(email);
		
		if (count > 0) {
			isEmailDuplicated = true;
		}
		
		sqlSession.close();
		
		return isEmailDuplicated;
	} // isEmailDuplicated
	
	//=====================================================================================================
	public int userCheck(String id, String passwd) {
		int check = -1;
		
		SqlSession sqlSession = DBManager.getSqlSessionFactory().openSession();
		
		MemberMapper mapper = sqlSession.getMapper(MemberMapper.class);
		
		MemberVO memberVO = mapper.getMemberById(id);
		if(memberVO != null) {
			if (passwd.equals(memberVO.getPasswd())) {
				check = 1;
			} else {
				check = 0;
			}
		} else {
			check = -1;
		}
		
		sqlSession.close();
		
		return check;
	} // userCheck
	
	//=====================================================================================================
	public MemberVO getMember(String id) {
		MemberVO memberVO = null;
		
		SqlSession sqlSession = DBManager.getSqlSessionFactory().openSession();
		
		MemberMapper mapper = sqlSession.getMapper(MemberMapper.class);
		
		mapper.getMemberById(id);
		
		sqlSession.close();
		
		return memberVO;
	} //getMember
	
	//=====================================================================================================
    public List<MemberVO> getMemberList() {
    	SqlSession sqlSession = DBManager.getSqlSessionFactory().openSession();
    	
    	MemberMapper mapper = sqlSession.getMapper(MemberMapper.class);
    	
        List<MemberVO> list = mapper.getMembers();
        
        sqlSession.close();
        
        return list;
    } // getMemberList
	
	//=====================================================================================================
	public int updateMember(MemberVO memberVO) {
		SqlSession sqlSession = DBManager.getSqlSessionFactory().openSession();
		
		MemberMapper mapper = sqlSession.getMapper(MemberMapper.class);
		
		int result = mapper.updateMember(memberVO);
		
		sqlSession.close();
		
		return result;
	} // updateMember
	
	//=====================================================================================================
	public void changePasswd(MemberVO memberVO) {
		SqlSession sqlSession = DBManager.getSqlSessionFactory().openSession();
		
		MemberMapper mapper = sqlSession.getMapper(MemberMapper.class);
		
		mapper.updateMember(memberVO);
		
		sqlSession.close();
	} // changePasswd
	
	//=====================================================================================================
	public void deleteMember(String[] idArr) {
		SqlSession sqlSession = DBManager.getSqlSessionFactory().openSession();
		
		MemberMapper mapper = sqlSession.getMapper(MemberMapper.class);
		
		for (String id : idArr) {
			mapper.deleteMember(id);
		}
		
		sqlSession.close();
	} // deleteMember
	
	//=====================================================================================================
	public List<Map<String, Object>> getGenderRatio() {
		SqlSession sqlSession = DBManager.getSqlSessionFactory().openSession();
		
		MemberMapper mapper = sqlSession.getMapper(MemberMapper.class);
		List<Map<String, Object>> mapList = mapper.getGenderRatio();
		sqlSession.close();
		return mapList;
	} // getGenderRatio
	
} // MemberDao
