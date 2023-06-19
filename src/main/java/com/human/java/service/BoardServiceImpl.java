package com.human.java.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.human.java.dao.BoardDAO;
import com.human.java.domain.BoardVO;
import com.human.java.domain.BookmarkVO;
import com.human.java.domain.SaveTempVO;

@Service("boardService")
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	private BoardDAO boardDAO;
	
	// 제목, 내용, 작성자 검색
	@Override
	public List<BoardVO> knowBLsearch(String searchtype, String keyword) {
		Map<String, String> searchParam = new HashMap<String, String>();
		searchParam.put("searchtype", searchtype);
		searchParam.put("keyword", keyword);
		List<BoardVO> rList = boardDAO.knowBLsearch(searchParam);
		return rList;
	}

	// 북마크 리스트 얻어오기
	@Override
	public List<BookmarkVO> getBookmark(BookmarkVO vo) {
		System.out.println("## SERVICE getBookmark 호출");
		return boardDAO.getBookmark(vo);
	}

	// 임시저장 하기
	@Override 
	public int saveTempPost(SaveTempVO vo) {
		System.out.println("## SERVICE saveTempPost 호출");
		
		return boardDAO.saveTempPost(vo);
		
	}

	// 임시저장 글목록 불러오기
	@Override
	public List<SaveTempVO> getTempList(SaveTempVO vo) {
		System.out.println("## SERVICE getTempList 호출");
		return boardDAO.getTempList(vo);
	}

	// 임시저장 글목록 갯수 세기
	@Override
	public int countTempPost(SaveTempVO vo) {
		System.out.println("## SERVICE countTempPost 호출");
		return boardDAO.countTempPost(vo);
	}

}
