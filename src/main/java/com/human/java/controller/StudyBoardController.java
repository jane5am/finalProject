package com.human.java.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.human.java.domain.BoardVO;
import com.human.java.domain.PageVO;
import com.human.java.domain.StudyBoardVO;
import com.human.java.domain.StudyCommentVO;
import com.human.java.service.StudyService;

@Controller 
public class StudyBoardController {
   
	@Autowired
	private StudyService studyService;
	
	
   // 스터디게시판- 글 목록보기 페이징!!
   @RequestMapping("getStudyList")
   public String getStudyList() {
      return "studyBoard_List";
   }
   
   // 스터디게시판- 글 목록보기 페이징!!
   @RequestMapping("writePost")
   public String writePost() {
      return "stusyBoard_write";
   }
      
	// 글쓰기  - 글저장하기
	@RequestMapping("saveStudyPost")
	public String postSave(StudyBoardVO vo, Model model) {
		
		System.out.println("## saveStudyPost 컨트롤러 진입 ##");
		System.out.println(vo);
		
		studyService.saveStudyPost(vo);
		
		System.out.println("저장하고 컨트롤러 복귀");
		
		model.addAttribute("study", studyService.getStudyPost(vo));
		
		return "studyBoard_textDetail";
	}
	
	// 글 삭제
	@RequestMapping("deleteStudyPost" )
	public String deleteStudyPost( StudyBoardVO vo, Model model) {
		
		System.out.println("## deleteStudyPost 컨트롤러 진입 ##");
		System.out.println("vo : " + vo);
		
		studyService.deleteStudyPost(vo);
		
		
//		model.addAttribute("board", studyService.deletePost(vo));
		System.out.println("컨트롤러 복귀");

		return "redirect:/getStudyPostlist";
	}
	
	// 글 수정페이지로 이동
	@RequestMapping("moveStudyModify")
	public String moveStudyModify(StudyBoardVO vo, Model model) {
		
		System.out.println("## moveStudyModify 컨트롤러 진입 ##");
		System.out.println(vo);
		model.addAttribute("board", studyService.getStudyBoard(vo));
		System.out.println(" moveStudyModify 컨트롤러 복귀");
		
		return "studyBoard_modify";
	}
	
	// 글수정 - 저장하기 
	@RequestMapping("modifyStudyPost")
	public String modifyStudyPost(StudyBoardVO vo, StudyCommentVO Svo, Model model) {
		
		System.out.println("## modifyStudyPost 컨트롤러 진입 ##");
		System.out.println("vo : " + vo);
		
		studyService.modifyStudyPost(vo);
		
		System.out.println("컨트롤러 복귀");
		
		model.addAttribute("study", studyService.getStudyBoard(vo));
		model.addAttribute("commentList", studyService.getSyudyCommentList(Svo));
		
		return "studyBoard_textDetail";
		
	}
				
	
	// 글상세 페이지 
	@RequestMapping("geStudytBoard.do")
	public String getBoard(StudyBoardVO vo, StudyCommentVO Svo, Model model) {
		System.out.println(" ## geStudytBoard 컨트롤러 호출 ## ");
		System.out.println(vo);
		HashMap map = new HashMap();
		
		//조회수 올리기
		studyService.updateStudyhits(vo);
		
		//댓글목록 가져오기
		model.addAttribute("study", studyService.getStudyBoard(vo));
		model.addAttribute("commentList", studyService.getSyudyCommentList(Svo));
		
		return "studyBoard_textDetail";
	}
		
	@RequestMapping("getStudyPostlist")
	public String getBoardList(Model model, PageVO vo) {
		System.out.println("## getStudyPostlist 컨트롤러 진입 ##");
		
		System.out.println("vo.getGroupNum : " + vo.getGroupNum());
		
		//pageNum을 전달
//		int pageNum = 1; //1일때 1 10 , 2일때 >> 11 20
//		vo.setPageNum(1);
		
//		int countPerPage = 10; //한페이지 당 보여줄 게시글 수
		int startPage = (vo.getPageNum() -1 ) * vo.getCountPerPage() +1;
		int endPage = vo.getPageNum() * vo.getCountPerPage();
		
		vo.setStartPage(startPage);
		vo.setEndPage(endPage);
		
		
		
		// 총페이지 개념 , 서비스에서 return하면 게시글에 대한 정보를 return하는 거라 list가 boardList로 뽑혀서
		// 게시글에 대한 정보와 총 페이지수에 대한 정보는 섞이기 어려운 정보이다
		// 그러므로 별도의 서비스를 진행하는게 좋다 
		PageVO pageInfoVO = studyService.getStudyListCount();
		
		int totalPageCount = pageInfoVO.getTotalCount()/ pageInfoVO.getCountPerPage();
		
		if(pageInfoVO.getTotalCount() % pageInfoVO.getCountPerPage() !=0 ) {
			totalPageCount++;
		}
		
		pageInfoVO.setTotalPageCount(totalPageCount);
		
		
		//이제 그룹 저장할거야
		// 그룹 정하기
		int startGroup = (vo.getGroupNum() - 1) * vo.getGroupPerPage() + 1;
		int endGroup = vo.getGroupNum() * vo.getGroupPerPage() ;
//		if (end가 15이민데 totalpagecount가 해씅ㄹ대 15가 더 크면 end= totalpagecount로 바꿔준디)
		if ( endGroup >= totalPageCount) {
			endGroup = totalPageCount;
		}
		pageInfoVO.setStartGroup(startGroup);
		pageInfoVO.setEndGroup(endGroup);
		pageInfoVO.setGroupNum(vo.getGroupNum());
		pageInfoVO.setPageNum(vo.getPageNum());
		
		// totalCountGroup : 그룹의 갯수
		int totalGroup = totalPageCount / vo.getGroupPerPage();
		if ( totalPageCount % vo.getGroupPerPage() > 0 ) {
			totalGroup++;
		}
		vo.setTotalGroup(totalGroup);
		pageInfoVO.setTotalGroup(totalGroup);
		
		System.out.println("pageInfoVO : " + pageInfoVO);
		
		List<StudyBoardVO> bList = studyService.getStudyPostlist(vo);

		model.addAttribute("boardList", bList);
		model.addAttribute("pageInfoVO", pageInfoVO);
		
		System.out.println("pageInfoVO.getPageNum : " + pageInfoVO.getPageNum());
		System.out.println("pageInfoVO.getGroupNum : " + pageInfoVO.getGroupNum());
		return "studyBoard_List";
		
	}
	
	
	// 모집 여부 말머리 검색
	@RequestMapping("searchStudySubject")
	@ResponseBody     
	public HashMap<String, List<StudyBoardVO>> searchStudySubject(@RequestParam("selectedRecruitment") String selectedRecruitment, 
														Model model, StudyBoardVO vo) {// 니가 입력하는 거 RentVO 로 받을꺼니까 그 VO변수값이랑 맞춰써라, vo로받을값을 rentTest에서는 let inputData = { rentTitle : "제목" ,  rentDirector : "감독"}; 이렇게 맞춰서 써서 보내준거임
		System.out.println("## Ajax searchStudySubject 컨트롤러 접근 ##");
		System.out.println("selectedRecruitment : "+ selectedRecruitment);
		
		List<StudyBoardVO> rList  = studyService.searchStudySubject(selectedRecruitment, vo);
		
		HashMap map = new HashMap();
		map.put("jsonList" , rList);
//		map.put("pageInfoVO" , pageInfoVO);
		
		return map;
	}
	
	// 제목, 내용, 작성자 검색
	@RequestMapping("studyBLsearch")
	@ResponseBody
	public List<StudyBoardVO> studyBLsearch(@RequestParam("type") String type,
							@RequestParam("keyword") String keyword) {
		System.out.println("## Ajax studyBLsearch 컨트롤러 접근 ##");
		System.out.println("type : " + type + ", keyword : " + keyword);
		
		List<StudyBoardVO> rList = studyService.studyBLsearch( type, keyword );
		
		return rList;
	}
	
}