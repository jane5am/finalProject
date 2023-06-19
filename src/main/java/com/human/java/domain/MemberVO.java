package com.human.java.domain;

import lombok.Data;

@Data
public class MemberVO {
	// @@2 롬북설정
	// getter / setter / toString/ 생성자가 다 여기에 있는 것
	private String userId; // 아이디
	private String userPass;
	private String nickname; // 닉네임
	private String userNo;
}
