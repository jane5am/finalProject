package com.human.java.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.human.java.domain.MemberVO;
import com.human.java.service.MemberService;

@Controller
@RequestMapping("/user") // 이 컨트롤ㄹ러는 /user경로가 호출될 때 실행하는 컨트롤러
public class MemberController {

	//@@5
	@Autowired
	private MemberService memberService;
//	
//	@RequestMapping(value="userLogin.do") // user/userLogin.do 맵핑과 다른 값 : Error
	@RequestMapping(value="user/userLogin.do") // user/userLogin.do 맵핑과 다른 값
	public String userLogin() {
		
		System.out.println("## userLogin.do 진입");
		// WEB-INF/views/return값.jsp
//		return "userLogin"; // WEB-INF/views/userLogin.jsp
		return "user/userLogin"; // WEB-INF/views/user/userLogin.jsp
		
	}
	
//  잘못된 맵핑 상태 : 폴더를 누락하여 맵핑이 제대로 되지않고, 보여줄페이지(리턴)도 제대로 되지 않는 상태
//  @RequestMapping(value="userLogin.do") // user/userLogin.do 맵핑과 다른 값 : Error
//  public String userLogin() {
//     
//     System.out.println("## userLogin.do 진입");
//
//     // WEB-INF/views/리턴값.jsp
//     return "userLogin";          // WEB-INF/views/userLogin.jsp
//  }
  
	
	
	
	
	
	
	
	

//  // 폴더가 있을 경우 잘한 맵핑
////  @RequestMapping(value="user/userLogin.do")  // Controller 자체에 Mapping이 생길경우 맵핑주소가 변경된다
//  @RequestMapping(value="userLogin.do")
//  public String userLogin() {
//     
//     System.out.println("## userLogin.do 진입");
//
//     // WEB-INF/views/리턴값.jsp
//     return "user/userLogin";       // WEB-INF/views/user/userLogin.jsp
//  }
//
//  @RequestMapping(value="/userJoin.do")
//  public String userJoin() {
//     
//     System.out.println("## userJoin.do 진입");
//
//     return "user/userJoin";      
//  }
  
  
  
  // 위 코드는 단순이동이기 때문에 맵핑을 하나로 합칠거다 - step으로 변경하겠다는 말
	// 로직없이 단순 화면 변경을 담당하는 맵핑 메소드
	@RequestMapping("/{url}.do")
	public String viewPage(@PathVariable String url) {
		
		System.out.println("## 이동 " + url );
		return "/user/" + url;
	}
  
	@RequestMapping("/userInsert.do")
	public ModelAndView userInsert(MemberVO vo) {
		
		System.out.println("## userInsert.do 진입 ##");
		System.out.println(vo);
		// 데이터베이스에 등록 작업
		int result = memberService.userInsert(vo);
		System.out.println(("결과 : " + result)); // 1이면 잘 들어간것
		String message = "가입이 되지 않았습니다";
		
		if(result > 0) message = vo.getNickname() + "님, 가입을 축하드립니다";
		
		// message와 result를 화면에 전달 >> Model & ModelAndView
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/user/userJoin_ok");
		mv.addObject("message",message);
		mv.addObject("result",result);
		
		return mv;
	}
	
	
	// 세션 (session) : 웹브라우저가 종료될 때까지 (서버와 연결종료) 유지되는 객체  
	// session 객체에 한번 등록해놓은 값은 연결종료될 때까지 유지되는 값으로 사용, 시간 제어 가능(없앨시간도 제어가능)
	// 예) 인증서 로그인, 접속한계시간 정한 사이트 
	// session을 활용할 때는 "언제" session객체에 등록할 것이냐
	
	@RequestMapping("/login.do")
	public String login(MemberVO vo, HttpSession session) {
		System.out.println("## login.do 진입 ##");
		
		//로그인:
		//1. 로그인화면에 아이디와 비밀번호를 입력한다
		//2. 로그인 버튼을 누른다 ( 화면(view, *.jsp에서 일어난일 ) >> ( Controller로 전달, 파라미터)
		System.out.println("## id > " + vo.getUserId());
		System.out.println("## password > " + vo.getUserPass());
		
		//3. 아이디와 비밀번호가 맞는지 확인한다 >> 회원가입시 사용하나 아이디와 비밀번호와 비교(DB에 있는 값)
		// >> Service > DAO > MAPPER > DB를 통해서 확인 
		MemberVO result = memberService.idCheck_Login(vo);
		
		System.out.println("과연 result 값은?" + result);
		//4. 맞으면 성공(객체 생성) / 틀리면 실패(null)
//		System.out.println(result.getUserId());  // 아이디와 비밀번호 실패 시 오류납니다. NullPointerException
		if ( result == null || result.getUserId() == null) {
			// 결과 없을 때
			return "/user/userLogin";
		}else {
			System.out.println("[ " + result.getUserId() + " ] 로그인 성공");
			
			// 로그인이 성공되었을 때 session을 등록할 것

			session.setAttribute("nickname", result.getNickname());
			session.setAttribute("userId", result.getUserId());
			session.setAttribute("sessionTime", new Date().toLocaleString());
			
			// 있을 경우 연결하는 페이지
			return "redirect:/getPostlist";	// 리턴에는 /user/ 꼭 써줘야함
		}
		
	}
	
	@RequestMapping("/memberInfo.do")
	public String memberInfo(Model model, HttpSession session) {
		
		if ( session.getAttribute("userId") == null ) {
			return "redirect:/user/userLogin.do";
		}
		// 회원정보 탐색
		// 1. 메인화면에서 회원정보 클릭 시 Controller에세 전달할 값 존재여부 - 전달할값 없음, 즉 파라미터 미 존재
		// 2. Service → DAO → Mapper → DB를 통해서 회원정보를 리턴 (반환, 조회)
		// -- 회원정보 : 개인정보냐 전체정보냐 > 여기서는 전체정보로 간대!
		List<MemberVO> mList = memberService.memberInfo();
		
		// 3. 화면에 전달
		model.addAttribute("list",mList);
		
		return "/user/memberInfo";
		
		
	}
	
	@RequestMapping("logOut.do")
	public ModelAndView logOut(HttpSession session) {
		ModelAndView mv = new ModelAndView();
		// 로그아웃 : 로그인이 아닌 상태
		session.removeAttribute("sessionTime");// 한개 삭제
//		session.invalidate();// 초기화, 모든 세션을 제거하기 때문에 주의
		mv.setViewName("/user/userLogin");
		return mv;
	}
	
	
}
