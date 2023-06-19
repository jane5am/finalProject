package com.human.java.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.human.java.domain.BoardVO;
import com.human.java.domain.BookmarkVO;
import com.human.java.domain.CommentVO;
import com.human.java.domain.PageVO;
import com.human.java.domain.SaveTempVO;

public interface BoardDAO {

	public void savePost( BoardVO vo);
	
	public Object getPost(BoardVO vo);
	
	public Object deletePost(BoardVO vo);
	
	public void modifyPost( BoardVO vo );
	
	public void updatePost(BoardVO vo);
	
	public List<BoardVO> getPostlist( PageVO vo );
	
	public BoardVO getBoard(BoardVO vo);
	
	public int updatehits(BoardVO vo);
	
	// 지식게시판 - 댓글 저장
	public void saveComment(CommentVO vo);
	
	// 지식게시판 - 댓글목록 가져오기
	public List<CommentVO> getCommentList(CommentVO vo);
	
	// 지식게시판 - 쓴댓글 가져오기
	public List<CommentVO> getWriteComment(CommentVO vo);
	
	// 지식게시판 - 말머리 검색
	public List<BoardVO> searchSubject(String selectedSubject, BoardVO vo);
	
	// 지식게시판 - 조건 검색
	public List<BoardVO> knowBLsearch(Map<String, String> searchParam);

	public void deleteComment(CommentVO vo);
	
	// 지식게시판 - 댓글 수정
	public void modifyComment(CommentVO vo);
	
	// 북마크 게시글 저장
	public void saveBookmark(BookmarkVO vo);
	
	// 북마크 저장 취소
	public void saveCancelBookmark(BookmarkVO vo);
	
	//북마크 되어있는지 확인
	public List<BookmarkVO> checkBookmark(BookmarkVO vo);
	
	public PageVO getBoardListCount();

	// 북마크 목록
	public List<BookmarkVO> getBookmark(BookmarkVO vo);

	// 임시저장하기 
	public int saveTempPost(SaveTempVO vo);

	// 임시저장 글목록 불러오기
	public List<SaveTempVO> getTempList(SaveTempVO vo);

	// 임시저장 글목록 갯수 세기
	public int countTempPost(SaveTempVO vo);

	


}
