<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%
	HttpSession Session = request.getSession();
	String nickname = (String)Session.getAttribute("nickname");
	String userId = (String)Session.getAttribute("userId");
%>



<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>지식 공유 게시판</title>
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
  
  <!-- 테이블 CSS -->
<link href="resources/css/css.css" rel="stylesheet">
  
<script type="text/javascript"  src="http://code.jquery.com/jquery-1.9.0.js"></script>  
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>

<jsp:useBean id="now" class="java.util.Date" />
<fmt:formatDate value="${now}" pattern="yyyy-MM-dd" var="today" />
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

		<div class="serchForm" >
			<select class="search boardSearch">
				<option value='title'>제목</option>
				<option value='content'>내용</option>
				<option value='writer'>작성자</option>
			</select>

			<input type="text" name="searchKeyword" class="searchBox" />
			<input type="submit" value="검색" class="knowBLsearchBtn" />
		</div>
		
		<section>

	         <div class="board_title">
	            <strong>지식 공유 게시판</strong>
	            <p>지식을 공유하고 새로운 아이디어를 얻어보세요!</p>
	        </div>

			<div class="tbl-header">
				<table class="tbl_1">
					<tr>
						<th class="subjectTH">
						<select name="selectBox" class="selectBox">
								<option value='subject' hidden="hidden">말머리</option>
								<option value='all'>전체보기</option>
								<option value='JavaScript'>JavaScript</option>
								<option value='python'>python</option>
								<option value='Java'>Java</option>
								<option value='C/C++'>C/C++</option>
								<option value='PHP'>PHP</option>
								<option value='C#'>C#</option>
								<option value='Swift'>Swift</option>
								<option value='Kotlin'>Kotlin</option> 
								<option value='Go'>Go</option> 
								<option value='Ruby'>Ruby</option> 
								<option value='그 외'>그 외</option> 
						</select></th>
						<th >제목</th>
						<th class="writerTable">작성자</th>
						<th class="postingdateTable">등록일</th>
						<th class="hitsTable">조회수</th>
					</tr>

					<c:if test="${empty boardList }">
						<tr>
							<td colspan="5" class="notingBoardList">😅 아직 작성된 글이 없습니다!</td>
						</tr>
					</c:if>
					
					<c:forEach items="${boardList}" var="board">
						<tr class="currentList subjectTH">
							<td>${board.seq }</td>
							<td class="titleTable">
								<a href="getBoard.do?seq=${board.seq}">
									<div class="subjectS">[${board.subject}]</div> ${board.title }
								</a>
							</td>
							<td class="writerTable">${board.writer}</td>
							
							<td class="postingdateTable" >
							  <c:choose>
							    <c:when test="${board.postingdate < today}">
							      <fmt:formatDate value="${board.postingdate}" pattern="yyyy-MM-dd" />
							    </c:when>
							    <c:otherwise>
							      <fmt:formatDate value="${board.postingdate}" pattern="HH:mm" />
							    </c:otherwise>
							  </c:choose>
							</td>
							
							<td class="hitsTable">${board.hits }</td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</section>
		
		<c:if test="${not empty boardList }"> <!-- 만약 게시물이 하나도 없다면 페이지버튼을 작성하지 않는다 -->
				<div class="board_page">
					<c:choose>
						<c:when test="${pageInfoVO.groupNum > 1}">
						 	<a href="getPostlist?pageNum=${pageInfoVO.startGroup-1}&amp;groupNum=${pageInfoVO.groupNum-1}" class="bt first"> ◀ </a>
						</c:when>
						<c:otherwise>
						 	<a class="bt first" style="pointer-events: none;" > ◀ </a>
						</c:otherwise>
					</c:choose>
					<c:forEach var="i" begin="${pageInfoVO.startGroup}" end="${pageInfoVO.endGroup}" >
						<a href="getPostlist?pageNum=${i}&amp;groupNum=${pageInfoVO.groupNum}" class="num">${i}</a>
						
					</c:forEach>  
						<c:choose>
						<c:when test="${pageInfoVO.groupNum < pageInfoVO.totalGroup}">
						 	<a href="getPostlist?pageNum=${pageInfoVO.endGroup+1}&amp;groupNum=${pageInfoVO.groupNum+1}" class="bt last"> ▶ </a>
						</c:when>
						<c:otherwise>
						 	<a class="bt last" style="pointer-events: none;"> ▶ </a>
						</c:otherwise>
					</c:choose>
					
				</div>
		</c:if>
	
		<%
			// 로그인안한 사용자가 들어오면 튕겨내는것 - 이페이지를 볼때는 세션이 필요하다!
			if (session.getAttribute("nickname") != null) {
		%>
			<form action="knowBoard_write.do" method="post" class="write bt_wrap">
				<input type="submit" value="글 쓰기" name="write" class="btn on">
			</form>
		<% 
		    }
		%>
			
				<a href="logOut">로그아웃</a>
				
							
	</div>
<!-- Vendor JS Files -->
<script src="resources/assets/vendor/aos/aos.js"></script>
<script	src="resources/assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
<script src="resources/assets/vendor/glightbox/js/glightbox.min.js"></script>
<script	src="resources/assets/vendor/isotope-layout/isotope.pkgd.min.js"></script>
<script src="resources/assets/vendor/php-email-form/validate.js"></script>
<script src="resources/assets/vendor/swiper/swiper-bundle.min.js"></script>
<script src="resources/assets/vendor/waypoints/noframework.waypoints.js"></script>

<!-- Template Main JS File -->
<script src="resources/assets/js/main.js"></script>

</body>


<script type="text/javascript">
	$(document).ready( function() {
		
		// 말머리 검색
		$(".selectBox").on("change", function() {

			var selectedSubject = $(this).val();
			console.log(selectedSubject);
	
			$.ajax({
				url : "searchSubject", // 여기서 "ajax"는 컨트롤러에 있던 @RequestMapping("ajax")의 ajax이다
				data : { "selectedSubject" : selectedSubject },
// 				data : $('.board_wrap').serialize(),
				type : "post",
				dataType : 'json',
				//----------------------------------
				success : function(ajaxData) { // ajaxData : 그냥 아무 변수명을 쓰면된다, 뭐 data라고 써도되고
					alert("성공"); // 이때 ajaxData는 컨트롤러의 return데이터이다
					
					$('.currentList').remove();
					$.each(	ajaxData['jsonList'], function(	index, item) { // 데이터 =item
						console.log(index);
						let tagHtml = "<tr class='currentList subjectTH'>"
									+ "<td>"+ ajaxData['jsonList'][index]['seq']+ "</td>"
									+ "<td class='titleTable'>"+ "<a href='getBoard.do?seq="+ ajaxData['jsonList'][index]['seq']+ "'>"+ "<div class='subjectS'>["+ ajaxData['jsonList'][index]['subject']+ "] </div>"
									+ ajaxData['jsonList'][index]['title'] + "</a>"	+ "</td>" 
									+ "<td class='writerTable'>"+ ajaxData['jsonList'][index]['writer']+ "</td> "	
									+ "<td class='postingdateTable'>"+ ajaxData['jsonList'][index]['postingdate']+ "</td> "
									+ "<td class='hitsTable'>"+ ajaxData['jsonList'][index]['hits']+ "</td> "
									+ "</tr>";
	// 					let tagHtml = "<tr class='currentList'></tr>";
					$('.tbl_1 > tbody').append(tagHtml);

					});
				}, error : function() { 
					alert('실패');
				},
			});

		}); // selectBox 끝!
		
		
// 		게시판 글 목록 검색하기
		$('.knowBLsearchBtn').on('click', function() {
			let type = $(".search").val();
		   console.log(type);
		   let keyword = $(".searchBox").val();
		   console.log(keyword);
		   
			$.ajax({
				//url : 우리 form태그에 있던 action과 같은 기능, 내가 접속하려고 하는 페이지의 맵핑명, controller에 있는 맵핑명
				url : "knowBLsearch", // 여기서 "ajax"는 컨트롤러에 있던 @RequestMapping("ajax")의 ajax이다
				data : { "type" : type, "keyword" : keyword },
				type : "post",
				//----------------------------------
				success : function(ajaxData) { // ajaxData : 그냥 아무 변수명을 쓰면된다, 뭐 data라고 써도되고
					alert("성공"); // 이때 ajaxData는 컨트롤러의 return데이터이다
					$('.currentList').remove();
					
					console.log(ajaxData[0].postingdate);
					
					$.each(	ajaxData, function(	index, item) {  // 데이터 =item
						console.log(index);
						let tagHtml = "<tr class='currentList subjectTH'>"
									+ "<td>"+ ajaxData[index].seq + "</td>"
									+ "<td class='titleTable'>"+ "<a href='getBoard.do?seq="+ ajaxData[index].seq + "'>"+ "<div class='subjectS'>["+ ajaxData[index].subject + "] </div>"
									+ ajaxData[index].title + "</a>"	+ "</td>" 
									+ "<td class='writerTable'>"+ ajaxData[index].writer + "</td> "	
									+ "<td class='postingdateTable'>"+ ajaxData[index].postingdate + "</td> "
									+ "<td class='hitsTable'>"+ ajaxData[index].hits + "</td> "
									+ "</tr>";
	// 					let tagHtml = "<tr class='currentList'></tr>";
					$('.tbl_1 > tbody').append(tagHtml);
	
					});
					
				}, error : function() {
					alert('실패');
				},
			});
		});
		
		
	});
</script>
</html>
