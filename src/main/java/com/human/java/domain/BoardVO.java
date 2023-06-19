package com.human.java.domain;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class BoardVO {

	private int seq; // 글 번호
	private int R_seq; // 수정 글번호 
	private String title; // 글 제목
	private String writer; // 작성자
	private String userId; // 유저 아이디
	private String content; // 내용
	@JsonFormat(pattern = "yyyy-MM-dd")
    private Date postingdate; // 작성일자
    private String subject; // 말머리
    private String picture; // 사진
    private int hits; // 조회수
    private String state; // 상태
}
