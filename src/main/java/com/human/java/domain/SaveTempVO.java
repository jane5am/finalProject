package com.human.java.domain;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class SaveTempVO {
	
	private int board_num; // 저장한 게시판 번호 ( 지식게시판 1, 스터디게시판 2 )
	
	private int seq; // 임시 글 번호
	private String title; // 제목
	private String writer; // 작성자
	private String content; // 내용
  @JsonFormat(pattern = "yyyy-MM-dd")
	private Date postingdate; // 작성일
	private String subject; // 말머리
	private String picture; // 사진

	private String recruitment; // 모집상태
	private String topic; // 주제
	private String way; // 대면,비대면 선택
	private String language; // 언어
	private String peopleNum; // 인원
	
}
