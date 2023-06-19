package com.human.java.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.human.java.dao.BoardDAO;
import com.human.java.domain.BoardVO;
import com.human.java.domain.BookmarkVO;
import com.human.java.domain.CommentVO;
import com.human.java.domain.PageVO;
import com.human.java.domain.SaveTempVO;
import com.human.java.service.BoardService;

@Controller 
public class BoardController {

		@Autowired
		private BoardDAO boardDAO;
		
		@Autowired
		private BoardService boardService;
		
//		@Autowired
//		private knowboard_PageVO knowboard_PageVO;
		
			@RequestMapping("/{step}.do") // 매핑으로 적은 ("/{step}.do") 조건에만 맞으면 무조건 호출되는 메소드, .do라고만 적혀있으면 무조건 호출된대
			public String viewPage(@PathVariable String step) {
				// 단순하게 페이지를 이동하는 경우에 호출되는 메소드
				// 요청한 페이지의 경로이름을 변수화 : {변수명} - 현재 우리는 {step}으로 설정해놨음
				// @PathVariable : 사용자가 입력한 요청명을 변수화
				// 변수로 활용하여 단순하게 페이지 이동할 때 사용하는 메소드를 구성
				System.out.println("step >> " + step);
				return step; //WEB-INF/views/step에들어간파일명.jsp
			}
			
			
			// 지식게시판 - 글쓰기  - 글저장하기
			@RequestMapping("savePost")
			public String postSave(BoardVO vo, Model model, HttpSession session) {
				
				
				System.out.println("## savePost 컨트롤러 진입 ##");
				
				//rentTest.jsp에서 input태그에 name title, director를 VO에있는 변수명과 동일하게 넣어줬잖아
				// 그래서 FORM태그로 전달해서 그게 이 컨트롤러에 오면 파라미터에 RentVO vo 써줬으니까 자동으로 vo에 담긴대
				// 잘 담겼는지 확인해볼까?
				System.out.println(vo);
			    boardDAO.savePost(vo);
				
				System.out.println("컨트롤러 복귀");

				model.addAttribute("board", boardDAO.getPost(vo));
				System.out.println(model.toString());
				return "knowBoard_textDetail";
				
				// DB로 부터 가져온 데이터 rList를 이제 화면에 출력해야한다
				// session, model 등 통신을 위한 내장객체를 활용하여 출력하는데 그 중에 우리는 model을 쓸거다, 파라미터로 선언해주면된다 그래서 (Model model) 이렇게 써주는것
			}
			
			
			// 지식게시판 - 글 삭제
			@RequestMapping("deletePost" )
			public String deletePost(BoardVO vo, Model model) {
				
				System.out.println("## deletePost 컨트롤러 진입 ##");
				
				System.out.println("vo : " + vo);
				boardDAO.deletePost(vo);
				
				
				model.addAttribute("board", boardDAO.deletePost(vo));

				System.out.println("컨트롤러 복귀");

				return "redirect:/getPostlist";
			}

			
			// 지식게시판 - 글 수정페이지로 이동
			@RequestMapping("moveModify")
			public String moveModify(BoardVO vo, Model model) {
				
				System.out.println("## moveModify 컨트롤러 진입 ##");
				System.out.println("seq : " + vo.getSeq());
				model.addAttribute("board", boardDAO.getBoard(vo));

				System.out.println(" moveModify 컨트롤러 복귀");
				
				return "knowBoard_modify";
			}
			
			// 지식게시판 글수정 - 저장하기 
			@RequestMapping("modifyPost")
			public String modifyPost(BoardVO vo, Model model) {
				
				System.out.println("## modifyPost 컨트롤러 진입 ##");
				
				System.out.println("vo : " + vo);
			    boardDAO.modifyPost(vo);
				
				System.out.println("컨트롤러 복귀");
				
				boardDAO.updatePost(vo);
				
				model.addAttribute("board", boardDAO.getPost(vo));
				System.out.println(model.toString());
				return "knowBoard_textDetail";
				
			}
			
			
			
			// 지식게시판 - 게시판 리스트 검색
			@RequestMapping("knowBLsearch")
			@ResponseBody
			public List<BoardVO> knowBLsearch(@RequestParam("type") String type,
									@RequestParam("keyword") String keyword) {
				System.out.println("## Ajax knowBLsearch 컨트롤러 접근 ##");
				System.out.println("type : " + type + ", keyword : " + keyword);
				
				List<BoardVO> rList = boardService.knowBLsearch( type, keyword );
				System.out.println("컨트롤러 rList : " + rList);
				return rList;
			}
			
		
					
			// 지식게시판- 글 목록보기 페이징!!
			@RequestMapping("getPostlist")
			public String getBoardList(Model model, PageVO vo, HttpSession session) {
				System.out.println("## getPostlist 컨트롤러 진입 ##");
				
				System.out.println("vo.getGroupNum : " + vo.getGroupNum());
				
				//pageNum을 전달
//				int pageNum = 1; //1일때 1 10 , 2일때 >> 11 20
//				vo.setPageNum(1);
				
//				int countPerPage = 10; //한페이지 당 보여줄 게시글 수
				int startPage = (vo.getPageNum() -1 ) * vo.getCountPerPage() +1;
				int endPage = vo.getPageNum() * vo.getCountPerPage();
				
				vo.setStartPage(startPage);
				vo.setEndPage(endPage);
				
				
				
				// 총페이지 개념 , 서비스에서 return하면 게시글에 대한 정보를 return하는 거라 list가 boardList로 뽑혀서
				// 게시글에 대한 정보와 총 페이지수에 대한 정보는 섞이기 어려운 정보이다
				// 그러므로 별도의 서비스를 진행하는게 좋다 
				PageVO pageInfoVO = boardDAO.getBoardListCount();
				
				int totalPageCount = pageInfoVO.getTotalCount()/ pageInfoVO.getCountPerPage();
				
				if(pageInfoVO.getTotalCount() % pageInfoVO.getCountPerPage() !=0 ) {
					totalPageCount++;
				}
				
				pageInfoVO.setTotalPageCount(totalPageCount);
				
				
				//이제 그룹 저장할거야
				// 그룹 정하기
				int startGroup = (vo.getGroupNum() - 1) * vo.getGroupPerPage() + 1;
				int endGroup = vo.getGroupNum() * vo.getGroupPerPage() ;
//				if (end가 15이민데 totalpagecount가 해씅ㄹ대 15가 더 크면 end= totalpagecount로 바꿔준디)
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
				
				List<BoardVO> bList = boardDAO.getPostlist(vo);

				model.addAttribute("boardList", bList);
				model.addAttribute("pageInfoVO", pageInfoVO);
//				List<BoardVO> bList = boardDAO.getPostlist( map );

				//			for ( BoardVO v : bList) {
//			         System.out.println(v);
//			     }
//				
				// 데이터를 화면(jsp)에 전달 : model 객체를 이용 - 파라미터에 작성
				// addAttribute("화면에서 사용할변수명", 저장할값)
//				model.addAttribute("boardList", bList);
//				// viewResolver를 통해서 맵핑된 메소드가 종료할 때 사용자에게 보여줄 화면페이지 파일
//				// /WEB-INF/views/ + 파일명 + .jsp 의 파일을 응답용 페이지로 호출
//				System.out.println("여기까지 왔어요");
				
				System.out.println("pageInfoVO.getPageNum : " + pageInfoVO.getPageNum());
				System.out.println("pageInfoVO.getGroupNum : " + pageInfoVO.getGroupNum());
				return "knowBoard_List";
				
			}
			
			
			
//			@RequestMapping("getPostlist")
//			public String getBoardList(Model model, String searchCondition, String searchKeyword) {
//				System.out.println("## getPostlist 컨트롤러 진입 ##");
//				
//				System.out.println("조건 : " + searchCondition);
//				System.out.println("키워드 : " + searchKeyword);
//				// mybatis는 하나의 자료형만 입력가능하기 때문에 여러개의 값을 가진 형태(배열)로 변경
//				HashMap map = new HashMap();
////				map.put("searchCondition", searchCondition);
////				map.put("searchKeyword", searchKeyword);
//				//해쉬맵 쓰는 이유 : 키값을 정할 수 있음 마치 vo처럼
//				
//				// 컨트롤러에서 할 역할 : 글목록을 조회하기 위해서는 DB를 접근해야 한다
//				// 조회 기능 : DB에서 필요한 값(파라미터, 인자) / DB에서 출력되는 값(return)
//				List<BoardVO> bList = boardDAO.getPostlist( map );
//				
//				//			for ( BoardVO v : bList) {
////			         System.out.println(v);
////			     }
////				
//				// 데이터를 화면(jsp)에 전달 : model 객체를 이용 - 파라미터에 작성
//				// addAttribute("화면에서 사용할변수명", 저장할값)
//				model.addAttribute("boardList", bList);
//				// viewResolver를 통해서 맵핑된 메소드가 종료할 때 사용자에게 보여줄 화면페이지 파일
//				// /WEB-INF/views/ + 파일명 + .jsp 의 파일을 응답용 페이지로 호출
//				System.out.println("여기까지 왔어요");
//				return "knowBoard_List";
//				
//			}
			
			// 지식게시판 - 글상세 페이지 
			@RequestMapping("getBoard.do")
			public String getBoard(BoardVO vo, CommentVO Cvo, Model model) {
				System.out.println(" ## getBoard 컨트롤러 호출 ## ");
				System.out.println("seq : " + vo.getSeq());
				System.out.println(vo);
				
				//조회수 올리기
				boardDAO.updatehits(vo);
				
				//댓글목록 가져오기
				model.addAttribute("board", boardDAO.getBoard(vo));
				model.addAttribute("commentList", boardDAO.getCommentList(Cvo));
			
				return "knowBoard_textDetail";
			}
			
			
			// 지식게시판 - 말머리 검색
			@RequestMapping("searchSubject")
			@ResponseBody       // @ResponseBody: return이 페이지로 변환하는 것이 아니라 데이터를 리턴하겠다고 하는것
			public HashMap<String, List<BoardVO>> searchSubject(@RequestParam("selectedSubject") String selectedSubject, 
																Model model, BoardVO vo) {// 니가 입력하는 거 RentVO 로 받을꺼니까 그 VO변수값이랑 맞춰써라, vo로받을값을 rentTest에서는 let inputData = { rentTitle : "제목" ,  rentDirector : "감독"}; 이렇게 맞춰서 써서 보내준거임
				System.out.println("## Ajax searchSubject 컨트롤러 접근 ##");
				System.out.println("selectedSubject : "+ selectedSubject);
				
				// ----------------------------------------------------------
				
				
				List<BoardVO> rList  = boardDAO.searchSubject(selectedSubject, vo);
				
				HashMap map = new HashMap();
				map.put("jsonList" , rList);
//				map.put("pageInfoVO" , pageInfoVO);
				
				return map;
			}
			
			
					
					
			
			// 지식게시판 - 댓글 출력
			@RequestMapping("getCommentList")
			@ResponseBody       // @ResponseBody: return이 페이지로 변환하는 것이 아니라 데이터를 리턴하겠다고 하는것
			public HashMap<String, List<CommentVO>> getCommentList(CommentVO vo) {// 니가 입력하는 거 RentVO 로 받을꺼니까 그 VO변수값이랑 맞춰써라, vo로받을값을 rentTest에서는 let inputData = { rentTitle : "제목" ,  rentDirector : "감독"}; 이렇게 맞춰서 써서 보내준거임
				System.out.println("## Ajax getCommentList 컨트롤러 접근 ##");
				
				System.out.println("seq : " + vo.getSeq());

				// 댓글 LIST 가져오기
				List<CommentVO> rList = boardDAO.getCommentList(vo);
				
				//현재 rList는 list형태이다
//						[RentVO(rentId=2, rentTitle=아바타, rentDirector=이감독, rentState=Y, rentDate=2023-04-20 10:23:46)]
//						[vo객체, vo객체, vo객체 ... ]
				// JSON 형태
//						{
//							0 :	{ rentId : 2 , rentTitle: 아타바, rentDirector : 이감독 ... } 이 안에 rentId : 1이렇게 또 들어가면 rentId가 두번들어가서 중복되니 안된다 그래서 다ㄹ른 중괄호를 만들어주고 그걸 또 감싸줘야한다
//							1 : { rentId : 1 , rentTitle: 존윅4, rentDirector : 김감독 ... }
//						}
				
				
//						return "ajax 데이터"; // 원래는 이동할 페이지 였는데 ajax는 더이상 return할 페이지가 없으니 전달할 데이터를 쓴다, ajax쓰면 Model도 안써도된대
								// 그래서 붙는게 @responseBody
//						 return rList; // 이 코드가 정상적으로 진행되도록 구현해야한다. rList는 List형태 / List형태가 자바스크립트에서 문제가 없어야 한다
				// return 이 List일 경우 다음과 같은 형태(json)로 전달된다 : [ {} , {} ]
				// 단 웹표준에 맞지 않아서 지양된다
				 
				 // 가장 올바른 방법 은 json 은 java에서 hashMap의 형태로 구현이된다
				 // hashMap의 형태로 리턴해서 json으로 변환하는 것이다
				
				
				HashMap<String, List<CommentVO>> map = new HashMap<String, List<CommentVO>>(); // 만약 보낼데이터가 List<RentVO>가 아니고 여러 형태다 하면 Object라고 넣어도 된대
				map.put("jsonList" , rList);
				
				return map; // return 타입이 HashMap<String, List<RentVO>> 
							// 근데 전달해야할 값이 아까 위에 return rList 써도된대
			}
					
			
			// 지식게시판 - 댓글달기
			@RequestMapping("writeComment")
			@ResponseBody       // @ResponseBody: return이 페이지로 변환하는 것이 아니라 데이터를 리턴하겠다고 하는것
			public HashMap<String, List<CommentVO>> writeComment(CommentVO vo) {// 니가 입력하는 거 RentVO 로 받을꺼니까 그 VO변수값이랑 맞춰써라, vo로받을값을 rentTest에서는 let inputData = { rentTitle : "제목" ,  rentDirector : "감독"}; 이렇게 맞춰서 써서 보내준거임
				System.out.println("## Ajax writeComment 컨트롤러 접근 ##");
				
				System.out.println("seq : " + vo.getSeq());
				System.out.println("nickname : " + vo.getNickname());
				System.out.println("content  : " + vo.getComment_content());
				

				// 댓글 DB에 저장
				boardDAO.saveComment(vo);
				
				// 댓글 LIST 가져오기
				List<CommentVO> rList = boardDAO.getWriteComment(vo);
				
				System.out.println(rList.get(0));
				
				//현재 rList는 list형태이다
//				[RentVO(rentId=2, rentTitle=아바타, rentDirector=이감독, rentState=Y, rentDate=2023-04-20 10:23:46)]
//				[vo객체, vo객체, vo객체 ... ]
				// JSON 형태
//				{
//					0 :	{ rentId : 2 , rentTitle: 아타바, rentDirector : 이감독 ... } 이 안에 rentId : 1이렇게 또 들어가면 rentId가 두번들어가서 중복되니 안된다 그래서 다ㄹ른 중괄호를 만들어주고 그걸 또 감싸줘야한다
//					1 : { rentId : 1 , rentTitle: 존윅4, rentDirector : 김감독 ... }
//				}
				
				
//				return "ajax 데이터"; // 원래는 이동할 페이지 였는데 ajax는 더이상 return할 페이지가 없으니 전달할 데이터를 쓴다, ajax쓰면 Model도 안써도된대
								// 그래서 붙는게 @responseBody
//				 return rList; // 이 코드가 정상적으로 진행되도록 구현해야한다. rList는 List형태 / List형태가 자바스크립트에서 문제가 없어야 한다
				// return 이 List일 경우 다음과 같은 형태(json)로 전달된다 : [ {} , {} ]
				// 단 웹표준에 맞지 않아서 지양된다
				 
				 // 가장 올바른 방법 은 json 은 java에서 hashMap의 형태로 구현이된다
				 // hashMap의 형태로 리턴해서 json으로 변환하는 것이다
				
				
				HashMap<String, List<CommentVO>> map = new HashMap<String, List<CommentVO>>(); // 만약 보낼데이터가 List<RentVO>가 아니고 여러 형태다 하면 Object라고 넣어도 된대
				map.put("jsonList" , rList);
				
				return map; // return 타입이 HashMap<String, List<RentVO>> 
							// 근데 전달해야할 값이 아까 위에 return rList 써도된대
			}
			
			// 지식게시판 - 댓글삭제
			@RequestMapping("deleteComment")
			@ResponseBody       // @ResponseBody: return이 페이지로 변환하는 것이 아니라 데이터를 리턴하겠다고 하는것
			public CommentVO deleteComment(CommentVO vo) {// 니가 입력하는 거 RentVO 로 받을꺼니까 그 VO변수값이랑 맞춰써라, vo로받을값을 rentTest에서는 let inputData = { rentTitle : "제목" ,  rentDirector : "감독"}; 이렇게 맞춰서 써서 보내준거임
				System.out.println("## Ajax deleteComment 컨트롤러 접근 ##");
				System.out.println(vo);
				
				// DB 상태 N으로 바꿔주기 
				boardDAO.deleteComment(vo);
				
				System.out.println("N으로 바꿨어요");
				
				return vo;
			} 
			
			// 지식게시판 - 댓글 수정
			@RequestMapping("modifyComment")
			@ResponseBody       // @ResponseBody: return이 페이지로 변환하는 것이 아니라 데이터를 리턴하겠다고 하는것
			public CommentVO modifyComment(CommentVO vo) {// 니가 입력하는 거 RentVO 로 받을꺼니까 그 VO변수값이랑 맞춰써라, vo로받을값을 rentTest에서는 let inputData = { rentTitle : "제목" ,  rentDirector : "감독"}; 이렇게 맞춰서 써서 보내준거임
				System.out.println("## Ajax modifyComment 컨트롤러 접근 ##");
				System.out.println(vo);
				
				// DB 댓글 수정
				boardDAO.modifyComment(vo);
				System.out.println("댓글 수정 완");
				
				return vo;
			}
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			// 북마크 저장
			@RequestMapping("saveBookmark")
			@ResponseBody       // @ResponseBody: return이 페이지로 변환하는 것이 아니라 데이터를 리턴하겠다고 하는것
			public BookmarkVO saveBookmark(BookmarkVO vo) {// 니가 입력하는 거 RentVO 로 받을꺼니까 그 VO변수값이랑 맞춰써라, vo로받을값을 rentTest에서는 let inputData = { rentTitle : "제목" ,  rentDirector : "감독"}; 이렇게 맞춰서 써서 보내준거임
				System.out.println("## Ajax saveBookmark 컨트롤러 접근 ##");
				System.out.println(vo);
			
				// 북마크 저장
				boardDAO.saveBookmark(vo);
				
				return vo;
			}
			
			// 북마크 해제
			@RequestMapping("saveCancelBookmark")
			@ResponseBody       // @ResponseBody: return이 페이지로 변환하는 것이 아니라 데이터를 리턴하겠다고 하는것
			public BookmarkVO saveCancelBookmark(BookmarkVO vo) {// 니가 입력하는 거 RentVO 로 받을꺼니까 그 VO변수값이랑 맞춰써라, vo로받을값을 rentTest에서는 let inputData = { rentTitle : "제목" ,  rentDirector : "감독"}; 이렇게 맞춰서 써서 보내준거임
				System.out.println("## Ajax saveCancelBookmark 컨트롤러 접근 ##");
				System.out.println(vo);
				
				// 북마크 저장
				boardDAO.saveCancelBookmark(vo);
				
				return vo;
			}
			
			// 북마크 확인
			@RequestMapping("checkBookmark")
			@ResponseBody       
			public int checkBookmark(BookmarkVO vo) {
				System.out.println("## Ajax checkBookmark 컨트롤러 접근 ##");
				System.out.println(vo);
				
				// 북마크 확인
				List<BookmarkVO> rList = boardDAO.checkBookmark(vo);
				System.out.println("rList : " +rList);
				
				if( rList.isEmpty()) {
					return 1;
				}
				return 2;
			}
			
		
			// 북마크페이지로 가기 - 북마크 불러오기
			@RequestMapping("getBookmark")       
			public String getBookmark(BookmarkVO vo, Model model) { // 나중에 세션 받아서 그걸로 넣고 조회하는 걸로 바꾸자
				System.out.println("## getBookmark 컨트롤러 접근 ##");
				System.out.println(vo);
				
				// 북마크 확인
				List<BookmarkVO> rList = boardService.getBookmark(vo);
				
				System.out.println("rList : " +rList);
				model.addAttribute("bookmarkList", rList );
				
				return "bookmarkList";
			}
			
			// 임시저장하기
			@RequestMapping("saveTempPost")    
			@ResponseBody    
			public int saveTempPost(SaveTempVO vo, Model model) { // 나중에 세션 받아서 그걸로 넣고 조회하는 걸로 바꾸자
				System.out.println("## saveTempPost 컨트롤러 접근 ##");
				System.out.println(vo);
				
				return boardService.saveTempPost(vo);
			}
			
			// 임시저장목록 가져오기
			@RequestMapping("getTempList")    
			@ResponseBody    
			public List<SaveTempVO> getTempList(SaveTempVO vo, Model model) { // 나중에 세션 받아서 그걸로 넣고 조회하는 걸로 바꾸자
				System.out.println("## getTempList 컨트롤러 접근 ##");
				System.out.println(vo);
				
				return boardService.getTempList(vo);
			}
			
			// 임시저장 목록 갯수 세기
			@RequestMapping("countTempPost")    
			@ResponseBody    
			public int countTempPost(SaveTempVO vo, Model model) { // 나중에 세션 받아서 그걸로 넣고 조회하는 걸로 바꾸자
				System.out.println("## countTempPost 컨트롤러 접근 ##");
				System.out.println(vo);
				
				return boardService.countTempPost(vo);
			}
			
			
			// 로그아웃
			@RequestMapping("logOut")
			public ModelAndView logOut(HttpSession session) {
				ModelAndView mv = new ModelAndView();
				// 로그아웃 : 로그인이 아닌 상태
//				session.removeAttribute("sessionTime");// 한개 삭제
				session.invalidate();// 초기화, 모든 세션을 제거하기 때문에 주의
				mv.setViewName("home");
				return mv;
			}
}
