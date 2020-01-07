package com.exam.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.exam.domain.MemberVO;
import com.exam.mapper.MemberMapper;

@Service
@Transactional //  100개 데이터 처리중 50번째에서 에러가 발생하면 처리된 49번까지의 데이터를 롤백, 100번째까지 에러 발생 안되고 정상처리되면 커밋
// connection의 auto commit를 false로 주고 try catch를 이용해서 commit 또는 rollback 하는 것이 트랜잭션의 기본 형태
public class MemberService {
	
	@Autowired
	private MemberMapper memberMapper;
	
	//=====================================================================================================
	public int insertMember(MemberVO memberVO) {
		return memberMapper.insertMember(memberVO);
	} //insertMember
	//=====================================================================================================
	
	public int userCheck(String id, String passwd) {
		int check = -1;
		
		MemberVO memberVO = memberMapper.getMemberById(id);
		
		if(memberVO != null) {
			if(passwd.equals(memberVO.getPasswd())) {
				check = 1;
			} else {
				check = 0;
			}
		} else {
			check = -1;
		}
		return check;
	} //userCheck
	//=====================================================================================================
	
	public MemberVO getMember(String id) {
		MemberVO memberVO = memberMapper.getMemberById(id);
		
		return memberVO;
	} // getMember
	//=====================================================================================================
	
	public String getPasswd(String id) {
		String passwd = memberMapper.getPasswdByID(id);
		return passwd;
	}
	
	//=====================================================================================================
	public int updateMember(MemberVO memberVO, String passwd) {
		int check = -1;
		
		if (passwd == null) {
			check = -1;
		} else 
			if (passwd.equals(memberVO.getPasswd())) {
				memberMapper.updateMember(memberVO);
				check = 1;
			} else {
				check = 0;
			}
		return check;
	}
	
	//=====================================================================================================
	public int changePasswd(String id, String passwd) {
		int result = memberMapper.changePasswd(id, passwd);
		return result; 
	} // changePasswd
	
	//=====================================================================================================
	public boolean isIdDuplicated(String id) {
		boolean isIdDuplicated = false;
		int count = memberMapper.countMemberById(id);
		
		if(count > 0) {
			isIdDuplicated = true;
		}
		
		return isIdDuplicated;
	} // isIdDuplicated
	
	//=====================================================================================================
	public boolean isEmailDuplicated(String email) {
		boolean isEmailDuplicated = false;
		int count = memberMapper.countMemberByEmail(email);
		
		if(count > 0) {
			isEmailDuplicated = true;
		}
		
		return isEmailDuplicated;
	} // isEmailDuplicated
}