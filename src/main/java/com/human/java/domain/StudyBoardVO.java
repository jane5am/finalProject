package com.human.java.domain;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class StudyBoardVO {

	  private int seq; //  글 번호
	  private int R_seq; // 수정 횟수
	  private String recruitment; // 모집상태(모집중 R, 마감 F)
	  private String title; // 제목
	  private String writer; // 작성자
	  private String content; // 내용
	  @JsonFormat(pattern = "yyyy-MM-dd")
	  private Date postingdate; // 작성일
	  
	  private String topic; // 주제
	  private String way; // 대면, 비대면 선택
	  private String language; // 언어
	  private String peopleNum; // 인원
	  private String picture; // 사진
	  private int hits; // 조회수
	  private String state; // 글 상태 ( Y : 정상, N: 삭제, M: 수정)
}
