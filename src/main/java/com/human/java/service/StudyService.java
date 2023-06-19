package com.human.java.service;

import java.util.List;

import com.human.java.domain.BoardVO;
import com.human.java.domain.CommentVO;
import com.human.java.domain.PageVO;
import com.human.java.domain.StudyBoardVO;
import com.human.java.domain.StudyCommentVO;

public interface StudyService {

	// 스터디게시판 - 글쓰기
	public void saveStudyPost(StudyBoardVO vo);
 
	// 글 삭제
	public void deleteStudyPost(StudyBoardVO vo);

	// 스터디게시판 - 글 상세목록 가져오기
	public Object getStudyPost(StudyBoardVO vo);

	public PageVO getStudyListCount();

	// 스터디게시판 - 글 리스트 가져오기(페이징)
	public List<StudyBoardVO> getStudyPostlist(PageVO vo);

	// 글 조회수 올리기
	public int updateStudyhits(StudyBoardVO vo);

	// 글 상세페이지 보기
	public StudyBoardVO getStudyBoard(StudyBoardVO vo);

	// 상세페이지에 댓글목록 가져오기
	public List<StudyCommentVO> getSyudyCommentList(StudyCommentVO svo);

	// 모집 여부 말머리 검색
	public List<StudyBoardVO> searchStudySubject(String selectedRecruitment, StudyBoardVO vo);

	// 제목,내용, 작성자 검색
	public List<StudyBoardVO> studyBLsearch(String type, String keyword);

	// 글 수정 - 저장하기
	public void modifyStudyPost(StudyBoardVO vo);



   
}
