package com.human.java.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.human.java.dao.StudyDAO;
import com.human.java.domain.BoardVO;
import com.human.java.domain.CommentVO;
import com.human.java.domain.PageVO;
import com.human.java.domain.StudyBoardVO;
import com.human.java.domain.StudyCommentVO;

@Service("serviceService")
public class StudyServiceImpl implements StudyService {
	
	//##22
	@Autowired
	private StudyDAO studyDAO; 
 
	// 스터디게시판 - 글저장
	@Override
	public void saveStudyPost(StudyBoardVO vo) {
	System.out.println("## SERVICE saveStudyPost 접근 ##");
		
		studyDAO.saveStudyPost(vo);
	}
	
	// 글 삭제
	@Override
	public void deleteStudyPost(StudyBoardVO vo) {
		studyDAO.deleteStudyPost(vo);		
	}
	
	// 스터디게시판 - 글 상세목록 가져오기
	@Override
	public Object getStudyPost(StudyBoardVO vo) {
		System.out.println("## SERVICE getStudyPost 접근 ##");
		return studyDAO.getStudyPost(vo);
	}
	
	// 글 수정 - 저장하기
	@Override
	public void modifyStudyPost(StudyBoardVO vo) {
		System.out.println("## SERVICE modifyStudyPost 접근 ##");
		
		studyDAO.modifyStudyPost(vo);
	}
	
	@Override
	public PageVO getStudyListCount() {
		System.out.println("## SERVICE getStudyListCount 접근 ##");
		
		return studyDAO.getStudyListCount();
	}
	
	// 글 목록가져오기 (+페이징)
	@Override
	public List<StudyBoardVO> getStudyPostlist(PageVO vo) {
		System.out.println("## SERVICE getStudyPostlist 접근 ##");

		return studyDAO.getStudyPostlist(vo);
	}
	
	// 조회수 올리기
	@Override
	public int updateStudyhits(StudyBoardVO vo) {
		System.out.println("## SERVICE updateStudyhits 접근 ##");
		return studyDAO.updateStudyhits(vo);
	}

	//글 상세페이지 불러오기
	@Override
	public StudyBoardVO getStudyBoard(StudyBoardVO vo) {
		System.out.println("## SERVICE getStudyBoard 접근 ##");
		return studyDAO.getStudyBoard(vo);
	}
	
	// 댓글 리스트 불러오기
	@Override
	public List<StudyCommentVO> getSyudyCommentList(StudyCommentVO Svo) {
		System.out.println("## SERVICE getSyudyCommentList 접근 ##");
		return studyDAO.getStudyCommentList(Svo);
	}
	
	// 모집 여부 말머리 검색
	@Override
	public List<StudyBoardVO> searchStudySubject(String selectedRecruitment, StudyBoardVO vo) {
		System.out.println("## SERVICE searchStudySubject 접근 ##");
		return studyDAO.searchStudySubject(selectedRecruitment, vo);
	}

	// 제목, 내용, 작성자 검색
	@Override
	public List<StudyBoardVO> studyBLsearch(String searchtype, String keyword) {
		System.out.println("## SERVICE studyBLsearch 접근 ##");
		Map<String, String> searchParam = new HashMap<String, String>();
		searchParam.put("searchtype", searchtype);
		searchParam.put("keyword", keyword);
		List<StudyBoardVO> rList = studyDAO.studyBLsearch(searchParam);
		return rList;
	}


	

	



}
