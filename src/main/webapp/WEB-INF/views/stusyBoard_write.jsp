<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%
	HttpSession Session = request.getSession();
	String nickname = (String)Session.getAttribute("nickname");
	String userId = (String)Session.getAttribute("userId");
%>

<%
	// 로그인안한 사용자가 들어오면 튕겨내는것 - 이페이지를 볼때는 세션이 필요하다!
	if (session.getAttribute("nickname") == null) {
		response.sendRedirect("/user/userLogin.do");
    }
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>스터디 & 모임 게시판</title>
<meta content="" name="description">
 <meta content="" name="keywords">
 
<link href="resources/assets/img/favicon.png" rel="icon">
<link href="resources/assets/img/apple-touch-icon.png" rel="apple-touch-icon">

 <!-- Google Fonts -->
<link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Jost:300,300i,400,400i,500,500i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i" rel="stylesheet">

<!-- Vendor CSS Files -->
<link href="resources/assets/vendor/aos/aos.css" rel="stylesheet">
<link href="resources/assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="resources/assets/vendor/bootstrap-icons/bootstrap-icons.css" rel="stylesheet">
<link href="resources/assets/vendor/boxicons/css/boxicons.min.css" rel="stylesheet">
<link href="resources/assets/vendor/glightbox/css/glightbox.min.css" rel="stylesheet">
<link href="resources/assets/vendor/remixicon/remixicon.css" rel="stylesheet">
<link href="resources/assets/vendor/swiper/swiper-bundle.min.css" rel="stylesheet">

<!-- Template Main CSS File -->
<link href="resources/assets/css/style2.css" rel="stylesheet">
<link href="resources/assets/css/style3.css" rel="stylesheet">
<link href="resources/assets/css/study.css" rel="stylesheet">
  
  <!-- 테이블 CSS -->
<link href="resources/css/css.css" rel="stylesheet">
  
<script type="text/javascript"  src="http://code.jquery.com/jquery-1.9.0.js"></script>  
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>

</head>

<body>
	<header id="header" class="fixed-top ">
		<div class="container d-flex align-items-center">

			<h1 class="logo me-auto">
				<a href="home.do">soupieeeee</a>
			</h1>
			<!-- Uncomment below if you prefer to use an image logo -->
			<!-- <a href="index.html" class="logo me-auto"><img src="assets/img/logo.png" alt="" class="img-fluid"></a>-->

			<nav id="navbar" class="navbar">
				<ul>
			        <li><a class="nav-link scrollto active" href="getPostlist">지식게시판</a></li>
			        <li><a class="nav-link scrollto" href="getStudyPostlist">스터디게시판</a></li>
			        <li><a class="nav-link scrollto" href="stusyBoard_write.do">스터디게시판 글쓰기</a></li>
			        <li><a class="nav-link scrollto" href="getBookmark">북마크</a></li>
					<li><a class="nav-link scrollto" href="#team">Team</a></li>
					<li class="dropdown"><a href="#"><span>Drop Down</span> <i
							class="bi bi-chevron-down"></i></a>
						<ul>
							<li><a href="#">Drop Down 1</a></li>
							<li class="dropdown"><a href="#"><span>Deep Drop
										Down</span> <i class="bi bi-chevron-right"></i></a>
								<ul>
									<li><a href="#">Deep Drop Down 1</a></li>
									<li><a href="#">Deep Drop Down 2</a></li>
									<li><a href="#">Deep Drop Down 3</a></li>
									<li><a href="#">Deep Drop Down 4</a></li>
									<li><a href="#">Deep Drop Down 5</a></li>
								</ul></li>
							<li><a href="#">Drop Down 2</a></li>
							<li><a href="#">Drop Down 3</a></li>
							<li><a href="#">Drop Down 4</a></li>
						</ul></li>
					<li><a class="nav-link scrollto" href="#contact">Contact</a></li>
					<li><a class="getstarted scrollto" href="#about">Get
							Started</a></li>
				</ul>
				<i class="bi bi-list mobile-nav-toggle"></i>
			</nav>
			<!-- .navbar -->
		</div>
	</header>
	<!-- End Header -->
	
	  <div class="board_wrap">
         <div class="board_title">
            <strong>스터디 & 모임 게시판</strong>
            <p>개발과 관련된 공통 주제나 관심사로 사람을 모아보세요!</p>
        </div>
        
        <div class="board_write_wrap">
	        <form action="saveStudyPost" method="post">
	            <div class="board_write" style="border-bottom: 1px solid #000">
	                <div class="title">
	                    <dl>
	                        <dt> 제 목 </dt>
	                        <dd><input type="hidden" name="R_SEQ"><input type="hidden" name="board_num" value="2"><input type="text" name="title"></dd>
	                    </dl>
	                </div>
<!-- 	                    <dl> -->
<!-- 	                        <dt>모집상태</dt> -->
<!-- 	                        <dd> -->
<!-- 		                        <select name="recruitment" id="recruitment" size="1"> -->
<!-- 									<option value="select" hidden="hidden">모집상태를 선택하세요</option> -->
<!-- 								    <option value="recruiting">모집중</option> -->
<!-- 								    <option value="finish">마감</option> -->
<!-- 								 </select> -->
<!-- 							 </dd> -->
<!-- 	                    </dl> -->
<!-- 	                    <dl> -->
<!-- 	                        <dt>비밀번호</dt> -->
<!-- 	                        <dd><input type="password" placeholder="비밀번호 입력"></dd> -->
<!-- 	                    </dl> -->
	                </div>
	                
	                <div class="writeDiv">
                    	<div class="conditionDiv">
                    		<div class="studyInfo">
	                    		<img alt="스터디모집조건" src="resources/assets/img/check.png" class="checkImg">
	                    		<h1 class="conditionInfo" >스터디 모집에 요구되는 언어 및 정보들을 선택해주세요!</h1>
                    		</div>
                    		<div class="selectCondition">
						       <strong>⭐ 스터디 주제</strong>
						       <input type="text" class="inputBox" name="topic"><br>
						       <strong>⭐ 스터디 방식</strong>
						       <label><input type="checkbox" name="way" value="contact"> 대면</label>
						       <label><input type="checkbox" name="way" value="untact"> 비대면</label><br>
						       <strong>⭐ 모집 언어</strong>
 			   				   <input type="text" class="inputBox" name="language"><br>
						       <strong>⭐ 모집 인원</strong>
						       <input type="text" class="inputBox" name="peopleNum"><br>
<!-- 						       <p><input type="submit" value="Submit"> <input type="reset" value="Reset"></p> -->
						    </div>
                    	</div>
	                    <textarea placeholder="내용 입력" name="content" class="writeSpace"></textarea>
	                </div>
	                
	            </div>
	            <div class="bt_wrap">
					<button type="submit" class="on btn">등록</button>
	                <button type="button" class="btn">임시저장</button>
	            </div>
            </form>
        </div>
    </div>


  <!-- Vendor JS Files -->
  <script src="resources/assets/vendor/aos/aos.js"></script>
  <script src="resources/assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
  <script src="resources/assets/vendor/glightbox/js/glightbox.min.js"></script>
  <script src="resources/assets/vendor/isotope-layout/isotope.pkgd.min.js"></script>
  <script src="resources/assets/vendor/php-email-form/validate.js"></script>
  <script src="resources/assets/vendor/swiper/swiper-bundle.min.js"></script>
  <script src="resources/assets/vendor/waypoints/noframework.waypoints.js"></script>

  <!-- Template Main JS File -->
  <script src="resources/assets/js/main.js"></script>
  
  
</body>


</html>