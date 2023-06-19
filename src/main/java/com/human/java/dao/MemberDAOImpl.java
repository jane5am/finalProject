package com.human.java.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.human.java.domain.MemberVO;

@Repository("memberDAO")
public class MemberDAOImpl implements MemberDAO {

	//##10 이거하고 매퍼만들러가기
	@Autowired
	private SqlSessionTemplate mybatis;
	
	@Override
	public MemberVO idCheck(MemberVO vo) {
		System.out.println("## member idCheck DAO 진입 ##");
		return mybatis.selectOne("MemberDAO.idCheck", vo);
	}

	//##9
	@Override
	public int memberInsert(MemberVO vo) {
		System.out.println("==>> member Insert DAO 진입");
		return mybatis.insert("MemberDAO.userInsert",vo);
	}

	@Override
	public MemberVO memberLogin(MemberVO vo) {
		return null;
	}

	@Override
	public List<MemberVO> memberInfo() {
		
		return null;
	}

}
