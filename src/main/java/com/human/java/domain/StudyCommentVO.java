package com.human.java.domain;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class StudyCommentVO {
	
	private int comment_id; // 댓글 번호, 순번
	private int seq; // 댓글을 다는 그 글번호
	private String nickname; // 닉네임
	private String comment_content; // 댓글 내용
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date comment_date; // 작성일자
	private String comment_state; // 댓글 상태
	
	
}
