package com.human.java.service;

import java.util.List;

import com.human.java.domain.BoardVO;
import com.human.java.domain.BookmarkVO;
import com.human.java.domain.SaveTempVO;

public interface BoardService {

	List<BoardVO> knowBLsearch(String type, String keyword);

	// 북마크 리스트 얻어오기
	List<BookmarkVO> getBookmark(BookmarkVO vo);

	// 임시저장하기
	int saveTempPost(SaveTempVO vo);

	// 임시저장 글 목록 가져오기
	List<SaveTempVO> getTempList(SaveTempVO vo);

	// 임시저장 글목록 갯수 세기
	int countTempPost(SaveTempVO vo);
      

   
}
