package com.human.java.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.human.java.domain.BoardVO;
import com.human.java.domain.BookmarkVO;
import com.human.java.domain.CommentVO;
import com.human.java.domain.PageVO;
import com.human.java.domain.StudyBoardVO;
import com.human.java.domain.StudyCommentVO;

// DB와 직접 연결하는 클래스 + mybatis를 통해서 데이터베이스 쿼리문을 관리
@Repository("studyDAO")
public class StudyDAOImpl implements StudyDAO {
	
	//mybatis를 쓰기전에는 해당 클래스에 문자열의 형태인 sql문장을 만들어야 했었다.  String sql = ""; 이렇게
	// 그런데 mybatis를 사용하면 xml파일을 통해 DB연결관리를 도움 받는다.
	// 그래서 데이터를 꺼내오거나 집어넣거나 할때 편하게 사용하도록 하는 게 mybatis이다
	// 그리고 그걸 쓰려면 config.xml 이랑 mapper.xml이 필요하다
	// config.xml은 설정할때 사용하고, mapper.xml은 쿼리를 만드는데 필요하다
	// mybatis를 스프링에 등록하기 위해서는 pom.xml을 통해서 외부의 라이브러리를 추가해야한다
	// bean이라는 객체로 설정 및 XML 파일을 추가해야한다 >> 이건 root-context.xml에서 설정한다
	
	// 1. pom.xml 기존에 있는거 이 프로젝트의 pom.xml에 복붙하기
	// 2. web.xml 기존에 있는 거 한글 인코딩 이 프로젝트의 web.xml에 복붙하기
	// 3. root-context.xml 기존에 있는 거 이 프로젝트의 root-context.xml에 복붙하기
	
	 
	// 컨트롤러와 DAO를 나누어진 이유 : 분업을 하기위해
	// DAO는 DB와 직접적으로 연결해주기 위한 기능을함
	// 그 DB와 연결해주는 걸 mybatis로 위임해서 할거임
	// 얘 쓰면 싱글톤만들고,  sql문장쓰고 그런거를 여기서 안해도된다
	// mybatis를 사용하기전에 root-context, mybatis-config, mapper 세개를 만들어줘야 한다
	
	@Autowired 
	private SqlSessionTemplate mybatis;
	
	// 스터디게시판 - 글 저장
	@Override
	public void saveStudyPost(StudyBoardVO vo) {
		
		System.out.println("## DAO saveStudyPost 접근 ##");
		mybatis.insert("studyDAO.saveStudyPost",vo); 
		
		System.out.println("DAO 에서 저장성공!");
	}
	
	// 글 삭제
	@Override
	public void deleteStudyPost(StudyBoardVO vo) {
		System.out.println("## DAO deleteStudyPost 접근 ##");
		
		mybatis.update("studyDAO.deleteStudyPost",vo); 
	}
 
	// 스터디게시판 - 글 상세보기 
	@Override   
	public Object getStudyPost(StudyBoardVO vo) {
		System.out.println(" ##DAO getStudyPost 접근 ## ");
		  
		return mybatis.selectOne("studyDAO.getStudyPost",vo);
	}

	// 페이징
	@Override
	public PageVO getStudyListCount() {
		System.out.println("##DAO getStudyListCount 접근 ## ");
		// TODO Auto-generated method stub
		return mybatis.selectOne("studyDAO.getStudyListCount");
	}

	@Override
	public List<StudyBoardVO> getStudyPostlist(PageVO vo) {
		System.out.println(" ##DAO getStudyPostlist 접근 ## ");
		
		return mybatis.selectList("studyDAO.getStudyPostlistPaging",vo);
	}
	
	
	//조회수 올리기
	@Override
	public int updateStudyhits(StudyBoardVO vo) {
		System.out.println(" ##DAO updateStudyhits 접근 ## ");
		
		return mybatis.update("studyDAO.updateStudyhits",vo);
	}
	
	//글 상세페이지 불러오기
	@Override
	public StudyBoardVO getStudyBoard(StudyBoardVO vo) {
		System.out.println("## getStudyBoard DAO 호출 ## ");
		System.out.println(vo);
		return mybatis.selectOne("studyDAO.getStudyBoard",vo);
	}
	
	// 댓글 리스트 불러오기
	@Override
	public List<StudyCommentVO> getStudyCommentList(StudyCommentVO Svo) {
		System.out.println("## DAO getStudyCommentList 접근 ##");
		
		return mybatis.selectList("studyDAO.getStudyCommentList",Svo);
	}
	
	// 모집 여부 말머리 검색
	@Override
	public List<StudyBoardVO> searchStudySubject(String selectedRecruitment, StudyBoardVO vo) {
		
		System.out.println("## DAO searchStudySubject 접근 ##");
		
		List<StudyBoardVO> rList;
		
		if (selectedRecruitment.equals("all")) {
			System.out.println("전체보기 mybatis로!");
			rList = mybatis.selectList("studyDAO.getStudyPostlistAll",vo);
		}else {
			System.out.println("모집여부에 맞는 mybatis로!");
			rList = mybatis.selectList("studyDAO.getStudyPostlist",selectedRecruitment);
			System.out.println(rList);
		}

		System.out.println("rList : " + rList);
		return rList;
	}

	// 제목, 내용, 작성자 검색
	@Override
	public List<StudyBoardVO> studyBLsearch(Map<String, String> searchParam) {
		System.out.println("## DAO studyBLsearch 접근 ##");
		List<StudyBoardVO> rList = mybatis.selectList("studyDAO.studyBLsearch", searchParam);
		System.out.println("rList@@@ : " + rList);
		return rList;
	}

	// 글 수정 - 저장하기
	@Override
	public void modifyStudyPost(StudyBoardVO vo) {
		System.out.println("## DAO modifyStudyPost 접근 ##");
		mybatis.update("studyDAO.modifyStudyPost", vo);
	}
	



	
}
