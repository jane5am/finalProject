package com.human.java.domain;

import java.sql.Date;

import lombok.Data;

@Data
public class modifyBoardVO {
	
	private int Seq; // 기존 글 번호
	private int R_Seq; // 수정 글 번호
	private String R_Title; // 글 제목
	private String R_Content; // 내용
    private Date R_Reporstingdate; // 수정일자
    private String R_Subject; // 말머리
    private String R_Picture; // 사진
}
