package com.human.java.domain;

import lombok.Data;

@Data
public class BookmarkVO {

	private int bookmark_seq; // 북마크 번호
	private int boardNumber; // 게시판 번호 ( 1 : 지식게시판, 2 : 스터디 게시판)
	private int seq; // 저장할 글 번호
	
}
