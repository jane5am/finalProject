package com.human.java.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.human.java.domain.BoardVO;
import com.human.java.domain.BookmarkVO;
import com.human.java.domain.CommentVO;
import com.human.java.domain.PageVO;
import com.human.java.domain.SaveTempVO;

// DB와 직접 연결하는 클래스 + mybatis를 통해서 데이터베이스 쿼리문을 관리
@Repository("boardDAO")
public class BoardDAOImpl implements BoardDAO {
	
	//mybatis를 쓰기전에는 해당 클래스에 문자열의 형태인 sql문장을 만들어야 했었다.  String sql = ""; 이렇게
	// 그런데 mybatis를 사용하면 xml파일을 통해 DB연결관리를 도움 받는다.
	// 그래서 데이터를 꺼내오거나 집어넣거나 할때 편하게 사용하도록 하는 게 mybatis이다
	// 그리고 그걸 쓰려면 config.xml 이랑 mapper.xml이 필요하다
	// config.xml은 설정할때 사용하고, mapper.xml은 쿼리를 만드는데 필요하다
	// mybatis를 스프링에 등록하기 위해서는 pom.xml을 통해서 외부의 라이브러리를 추가해야한다
	// bean이라는 객체로 설정 및 XML 파일을 추가해야한다 >> 이건 root-context.xml에서 설정한다
	
	// 1. pom.xml 기존에 있는거 이 프로젝트의 pom.xml에 복붙하기
	// 2. web.xml 기존에 있는 거 한글 인코딩 이 프로젝트의 web.xml에 복붙하기
	// 3. root-context.xml 기존에 있는 거 이 프로젝트의 root-context.xml에 복붙하기
	
	 
	// 컨트롤러와 DAO를 나누어진 이유 : 분업을 하기위해
	// DAO는 DB와 직접적으로 연결해주기 위한 기능을함
	// 그 DB와 연결해주는 걸 mybatis로 위임해서 할거임
	// 얘 쓰면 싱글톤만들고,  sql문장쓰고 그런거를 여기서 안해도된다
	// mybatis를 사용하기전에 root-context, mybatis-config, mapper 세개를 만들어줘야 한다
	@Autowired
	private SqlSessionTemplate mybatis;
	
	@Override
	public void savePost( BoardVO vo) {
		// DB와 접근할거다 - DB랑 접근할거니까 오라클 준비되어있어야겠지
		System.out.println("## DAO savePost 접근 ##");
		
		// DB와 접근 - select라는 조회
		// mapper라는 데이터 안에는 여러 쿼리문이 들어있는데 그 쿼리문마다 올바른 사용방법이 있다
		// insert() / update() / delete() : 우리가 알고있는 것처럼 추가, 수정, 삭제
		// selectOne() : 조회하는데 값이 하나만 조회된다(단일값만 조회된다) , select 결과의 행이 하나여야 한다
		// selectList() : 조회하는데 값을 여러개 조회해야하는 경우, select 결과의 행이 몇개든 상관없다
		
		// insert(mapper 의 네임스페이스.태그id, 전달할값)
		mybatis.insert("boardDAO.savePost",vo); 
		// rList : 데이터베이스를 접근하여 나온 결과
		// DAO는 데이터베이스에 갔다와서 결과를 준것만으로 할일을 다함
		// 이제 화면까지 되돌려야하니까 rList를 return해준다
		System.out.println("DAO 에서 저장성공!"); // 쿼리문으로 인해 조회된 데이터를 한번 볼거야
		
	}
 
	@Override
	public Object getPost(BoardVO vo) { // 왜 Object인거지
		System.out.println(" ##DAO getPost 접근 ## ");
		System.out.println("vo.Seq() : " + vo.getSeq());
		return mybatis.selectOne("boardDAO.getPost",vo);
	}

	@Override
	public Object deletePost(BoardVO vo) {
		System.out.println(" ##DAO deletePost 접근 ## ");
		System.out.println("vo.getTitle() : " + vo.getTitle());
		return mybatis.update("boardDAO.deletePost",vo);
		
	}


	@Override
	public void modifyPost( BoardVO vo ) {
		// DB와 접근할거다 - DB랑 접근할거니까 오라클 준비되어있어야겠지
		System.out.println(" ##DAO modifyPost 접근 ## ");
		
		// DB와 접근 - select라는 조회
		// mapper라는 데이터 안에는 여러 쿼리문이 들어있는데 그 쿼리문마다 올바른 사용방법이 있다
		// insert() / update() / delete() : 우리가 알고있는 것처럼 추가, 수정, 삭제
		// selectOne() : 조회하는데 값이 하나만 조회된다(단일값만 조회된다) , select 결과의 행이 하나여야 한다
		// selectList() : 조회하는데 값을 여러개 조회해야하는 경우, select 결과의 행이 몇개든 상관없다
		
		// insert(mapper 의 네임스페이스.태그id, 전달할값)
		mybatis.insert("boardDAO.modifyPost",vo); 
		// rList : 데이터베이스를 접근하여 나온 결과
		// DAO는 데이터베이스에 갔다와서 결과를 준것만으로 할일을 다함
		// 이제 화면까지 되돌려야하니까 rList를 return해준다
		System.out.println("DAO 에서 저장성공!"); // 쿼리문으로 인해 조회된 데이터를 한번 볼거야
		
	}
	
	@Override
	public void updatePost(BoardVO vo) {
		System.out.println("## updatePost DAO 호출 ##");
		mybatis.update("boardDAO.updatePost",vo); 
	}

//	public List<BoardVO> getPostlist(HashMap map) {
//		System.out.println(" ## DAOgetPostlist");
//		
//		//selectList(mapper의 네임스페이스.태그id, 전달할 값) >> DB결과를 리턴
//		return mybatis.selectList("boardDAO.getPostlist",map);
//		//selectList는 한번실행ㅎ할때 데이터 전달 단 하나만 가능
//		// 그런데 우리는 serchCondition, seachKeyword 두개를 보내야하니까 list로 만들어서 한번에 보낸다
//	}
	
	@Override
	public List<BoardVO> getPostlist( PageVO vo ) {
		System.out.println(" ## DAOgetPostlist 호출 ##");
		
		//selectList(mapper의 네임스페이스.태그id, 전달할 값) >> DB결과를 리턴
		return mybatis.selectList("boardDAO.getPostlistPaging",vo);
		//selectList는 한번실행ㅎ할때 데이터 전달 단 하나만 가능
		// 그런데 우리는 serchCondition, seachKeyword 두개를 보내야하니까 list로 만들어서 한번에 보낸다
	}

	
	
	
	
	
	
	
	
	
	
	//글 상세페이지 불러오기
	@Override
	public BoardVO getBoard(BoardVO vo) {
		System.out.println("## getBoard DAO 호출 ## ");
		System.out.println(vo);
		return mybatis.selectOne("boardDAO.getBoard",vo);
	}

	@Override
	public int updatehits(BoardVO vo) {
		System.out.println("## updatehits DAO 호출 ##");
		System.out.println("hits : " + vo.getSeq());
		return mybatis.update("boardDAO.updatehits",vo);
	}

	
	// 지식게시판 - 댓글 저장
	@Override
	public void saveComment(CommentVO vo) {
		System.out.println("## saveComment DAO 호출 ##");	
		
		mybatis.insert("boardDAO.saveComment",vo); 
		
	}

	// 지식게시판 - 댓글목록 가져오기
	@Override
	public List<CommentVO> getCommentList(CommentVO vo) {
		
		System.out.println("## DAO getCommentList 접근 ##");
		
		List<CommentVO> rList = mybatis.selectList("boardDAO.getCommentList",vo); // 데이터가 조회되고있는중~
		// rList : 데이터베이스를 접근하여 나온 결과
		// DAO는 데이터베이스에 갔다와서 결과를 준것만으로 할일을 다함
		// 이제 화면까지 되돌려야하니까 rList를 return해준다
		System.out.println(rList); // 쿼리문으로 인해 조회된 데이터를 한번 볼거야
		System.out.println(rList.size()); // 쿼리문으로 인해 조회된 데이터를 한번 볼거야
		
		return rList;
	}
	
	// 지식게시판 - 내가 방금 쓴댓글 가져오기
	@Override
	public List<CommentVO> getWriteComment(CommentVO vo) {
		
		System.out.println("## DAO getWriteComment 접근 ##");
		List<CommentVO> rList = mybatis.selectList("boardDAO.getWriteComment",vo); // 데이터가 조회되고있는중~
		// rList : 데이터베이스를 접근하여 나온 결과
		// DAO는 데이터베이스에 갔다와서 결과를 준것만으로 할일을 다함
		// 이제 화면까지 되돌려야하니까 rList를 return해준다
		System.out.println(rList); // 쿼리문으로 인해 조회된 데이터를 한번 볼거야
		System.out.println(rList.size()); // 쿼리문으로 인해 조회된 데이터를 한번 볼거야
		
		return rList;
	}

	// 말머리 검색
	@Override 
	public List<BoardVO> searchSubject(String selectedSubject, BoardVO vo) {
		
		System.out.println("## DAO searchSubject 접근 ##");
		
		List<BoardVO> rList;
		
		if (selectedSubject.equals("all")) {
			System.out.println("전체보기 mybatis로!");
			rList = mybatis.selectList("boardDAO.getPostlistAll",vo);
		}else if(selectedSubject.equals("그 외")) {
			System.out.println("그 외 mybatis로! ");
			rList = mybatis.selectList("boardDAO.getPostlist",selectedSubject);
		}else {
			System.out.println("subject에 맞는 mybatis로!");
			rList = mybatis.selectList("boardDAO.getPostlist",selectedSubject);
			System.out.println(rList);
		}

		System.out.println("rList : " + rList);
		return rList;
	}

	@Override
	public void deleteComment(CommentVO vo) {
		System.out.println("## DAO deleteComment 접근 ##");
		mybatis.update("boardDAO.deleteComment",vo); 
		System.out.println("댓글 삭제완료");
	}

	// 지식게시판 - 댓글 수정
	@Override
	public void modifyComment(CommentVO vo) {
		System.out.println("## DAO modifyComment 접근 ##");
		mybatis.update("boardDAO.modifyComment",vo); 
		System.out.println("댓글 수정완료");
	}
	
	
	// 북마크 게시글 저장
	@Override
	public void saveBookmark(BookmarkVO vo) {
		System.out.println("## DAO saveBookmark 접근 ##");
		mybatis.insert("boardDAO.saveBookmark",vo); 
		System.out.println("북마크 저장완료!");
	}

	// 북마크 저장 취소
	@Override
	public void saveCancelBookmark(BookmarkVO vo) {
		System.out.println("## DAO saveCancelBookmark 접근 ##");
		mybatis.update("boardDAO.saveCancelBookmark",vo); 
		System.out.println("북마크 저장 취소 완료!");
	}

	@Override
	public PageVO getBoardListCount() {
		System.out.println("## DAO getBoardListCount 접근 ##");
		return mybatis.selectOne("boardDAO.getBoardListCount");
	}
	
	//북마크 되어있는지 확인
	@Override
	public List<BookmarkVO> checkBookmark(BookmarkVO vo) {
		System.out.println("## DAO checkBookmark 접근 ##");
		List<BookmarkVO> rList = mybatis.selectList("boardDAO.checkBookmark", vo);
	
		return rList;
		
	}

	// 지식게시판 - 글목록 검색
	@Override
	public List<BoardVO> knowBLsearch(Map<String, String> searchParam) {
		System.out.println("## DAO knowBLsearch 접근 ##");
		List<BoardVO> rList = mybatis.selectList("boardDAO.knowBLsearch", searchParam);
		System.out.println("rList@@@ : " + rList);
		return rList;
		
	}

	// 북마크 리스트 얻어오기
	@Override
	public List<BookmarkVO> getBookmark(BookmarkVO vo) {
		System.out.println("## DAO getBookmark 접근 ##");
		List<BookmarkVO> rList = mybatis.selectList("boardDAO.getBookmark", vo);
		return rList;
	}

	// 임시저장하기
	@Override
	public int saveTempPost(SaveTempVO vo) {
		System.out.println("## DAO saveTempPost 접근 ##");
		return mybatis.insert("boardDAO.saveTempPost", vo);
		
	}

	// 임시저장 글 목록 불러오기
	@Override
	public List<SaveTempVO> getTempList(SaveTempVO vo) {
		System.out.println("## DAO getTempList 접근 ##"); 
		return mybatis.selectList("boardDAO.getTempList", vo);
	}

	// 임시저장 글 목록 갯수 세기
	@Override
	public int countTempPost(SaveTempVO vo) {
		System.out.println("## DAO countTempPost 접근 ##"); 
		return mybatis.selectOne("boardDAO.countTempPost", vo);
	}


	
}
