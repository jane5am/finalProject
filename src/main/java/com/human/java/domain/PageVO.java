package com.human.java.domain;

import lombok.Data;

@Data
public class PageVO {

	  private int totalCount; // 글목록의 총 갯수
	  private int totalPageCount; // 총 페이지 갯수
	  
      private int pageNum = 1; // 첫화면일때 1페이지뜨게
      private int startPage; // 시작페이지
      private int endPage; // 끝 페이지
      
      private int countPerPage = 10; //한페이지 당 보여줄 게시글 수
      
      
      private int totalGroup; // 그룹의 총 갯수 =totalPageCount
	  private int totalGroupCount; // 내가 가지고있는 총 그룹 수   , 총페이지 / 5  
  	
      private int groupNum = 1; // 첫화면일때 그룹1뜨게
      private int startGroup ; // 시작 그룹
      private int endGroup; // 끝 그룹

      private int groupPerPage = 5; // 한번에 보여줄 페이지 갯수

      
      //검색
      
      private String type;
      private String keyword;
      
	
}
