package com.human.java.dao;

import java.util.List;
import java.util.Map;

import com.human.java.domain.BoardVO;
import com.human.java.domain.CommentVO;
import com.human.java.domain.PageVO;
import com.human.java.domain.StudyBoardVO;
import com.human.java.domain.StudyCommentVO;

public interface StudyDAO {

	public void saveStudyPost(StudyBoardVO vo);

	public Object getStudyPost(StudyBoardVO vo);

	public PageVO getStudyListCount();

	public List<StudyBoardVO> getStudyPostlist(PageVO vo);

	//조회수 올리기
	public int updateStudyhits(StudyBoardVO vo);

	// 글 상세페이지 보기
	public StudyBoardVO getStudyBoard(StudyBoardVO vo);

	// 글 상세페이지에 댓글목록 불러오기
	public List<StudyCommentVO> getStudyCommentList(StudyCommentVO svo);

	//모집 여부 말머리 검색
	public List<StudyBoardVO> searchStudySubject(String selectedRecruitment, StudyBoardVO vo);

	// 제목, 내용, 작성자 검색
	public List<StudyBoardVO> studyBLsearch(Map<String, String> searchParam);

	// 글 삭제 
	public void deleteStudyPost(StudyBoardVO vo);

	// 글 수정 - 저장하기
	public void modifyStudyPost(StudyBoardVO vo);



	


}
