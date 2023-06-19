package com.human.java.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.human.java.dao.MemberDAO;
import com.human.java.domain.MemberVO;
//@@4 멤버서비스 상속받아서 임플만들고 구현

@Service("memberService")
public class MemberServiceImpl implements MemberService {

	//##8
	@Autowired
	private MemberDAO memberDAO;
	
	@Override
	public MemberVO idCheck_Login(MemberVO vo) {
		System.out.println("## idCheck_Login 호출 ##");
		return memberDAO.idCheck(vo);
	}

	
	//##9
	@Override
	public int userInsert(MemberVO vo) {
		
		return memberDAO.memberInsert(vo);
	}

	@Override
	public List<MemberVO> memberInfo() {

		return null;
	}
	

}
