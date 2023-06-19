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
            <strong>지식 공유 글쓰기</strong>
            <p>지식을 공유하고 새로운 아이디어를 얻어보세요!</p>
        </div>
        
        <div class="board_write_wrap">
	        <form id="toSavePost" action="savePost" > <input type="hidden" name="writer" value=<%=nickname%>><input type="hidden" name="userId" value=<%=userId %> value=<%=nickname %>>
	            <div class="board_write">
	                <div class="title">
	                    <dl>
	                        <dt class="writeDT"> 제 목 </dt>
	                        <dd><input type="hidden" name="R_SEQ"><input type="hidden" name="board_num" value="1"><input type="text" name="title"></dd>
	                    </dl>
	                </div>
	                <div class="info">
	                    <dl>
	                        <dt class="writeDT">말머리</dt>
	                        <dd>
		                        <select name="subject" id="subject" size="1">
									<option value="select" hidden="hidden">말머리를 선택하세요</option>
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
								 </select>
							 </dd>
	                    </dl>
<!-- 	                    <dl> -->
<!-- 	                        <dt>비밀번호</dt> -->
<!-- 	                        <dd><input type="password" placeholder="비밀번호 입력"></dd> -->
<!-- 	                    </dl> -->
	                </div>
	                <div class="cont">
	                    <textarea placeholder="내용 입력" name="content" class="textarea"></textarea>
	                </div>
	            </div>
	            <div class="bt_wrap">
					<button type="submit" class="on btn saveBtn">등록</button>
<!-- 	                <button type="button" data-oper="btn_temp_count" class="btn btn_temp_count">0</button> -->
<!-- 	                <input type="button" class="modalScreen" value="모달창"> -->
	                <button type="button" data-oper="btn_temp_count" class="btn btn_temp_count">0</button>
	                <button type="button" data-oper="btn_temp_save" class="btn btn_temp_save">임시저장</button>
	            </div>
	            
	            
	            
				<div class="modal">
					<div class="modal_content" title="클릭하면 창이 닫힙니다.">
						지식 공유게시판 임시글 <strong class="TempPostNum">0</strong>
						<table class="tempListTable">
							<tr>	
								<td class="tempListTitle">글제목</td>		
								<td class="tempListDate">날짜</td>		
								<td>     </td>
							</tr>
						</table>
					</div>
				</div>
				
				<div class="modal2">
					<div class="modal_content2">
						작성한 글이 임시저장되었습니다.
					</div>
				</div>
	            
            </form>
        </div>
    </div>
</body>

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
  
  <!-- textarea 크기 자동조절 -->
  <script src="https://rawgit.com/jackmoore/autosize/master/dist/autosize.min.js"></script> 
  
</body>



<script type="text/javascript">

	$(document).ready(function() {
		
		//textarea 자동조절
		autosize($("textArea"));
		
		
		let TempPostNumber = 0;
// 		// FORM버튼 url 변경
// 	    let formObj = $("form");
// 	       $('.btn').on("click", function(e) {       //모든 button태그에 대한 클릭 이벤트 처리
// 	          e.preventDefault();                //버튼의 기본 submit() 동작 막기
// 	          let operation = $(this).data("oper");   //data-oper 값 읽어오기
// 	          console.log(operation);
	      
// 	          if (operation == 'btn_temp_count') {
// 	             if (confirm("임시저장글을 불러오시겠습니까?") == true) {
// 	                formObj.attr("action", "btn_temp_count");
// 	             } else {
// 	                return false;
// 	             }
// 	          } else if (operation == 'btn_temp_save' ){
// 	             if (confirm("임시저장 하시겠습니까?") == true) {
// 	                formObj.attr("action", "saveTempPost");
// 	             } else {
// 	                return false;
// 	             }
// 	          }     
// 	             else {
// 	             formObj.attr("action", "savePost");
// 	          }
	         
// 	          formObj.submit();
// 	       })


		
		// 임시저장 갯수 변경
			$.ajax( {
				url : "countTempPost", 
				data : { "board_num" : 1 },
				type : "post",
				//----------------------------------
				success : function (ajaxData) { 
				    $(".btn_temp_count").text(ajaxData);
				    TempPostNumber = ajaxData;
				},
				error : function() {
					alert('실패');
				},
			});

		// 임시저장하기
		 $('.btn_temp_save').click(function(){
		
	         $.ajax( {
	        	  url : "saveTempPost", 
	              data : $("#toSavePost").serialize(),
	              type : "post",
	            //----------------------------------
	            success : function (ajaxData) { 
	               console.log(TempPostNumber)
	               TempPostNumber = TempPostNumber + 1;
	               $(".btn_temp_count").text( ( TempPostNumber) );
	               $(".modal2").fadeIn();
	               
	               setTimeout(function() {
	                   $(".modal2").fadeOut();
	                 }, 2700);
	               
	            },
	            error : function() {
	               alert('실패');
	            },
	         });
		});

	
	
	// 임시저장 리스트 목록 모달창 띄우기
	  $(".btn_temp_count").click(function(){
	    
		    $.ajax({
	            url : "getTempList", 
	            data : { "board_num" : 1 },
	            type : "post",
	            //----------------------------------
	            success : function (ajaxData) {
	            	
	               alert("목록가져오기 성공");
	               console.log(ajaxData);
	               
	               
	               $('.TempPostNum').text(TempPostNumber);
				   $('.tempListTitle').closest('tr').siblings().remove();
		           	$.each(	ajaxData, function(	index, item) {  // 데이터 =item
						console.log(index);
						let tagHtml = "<tr>"
									+ "<td>"+ ajaxData[index].title + "</td>"
									+ "<td>"+ ajaxData[index].postingdate + "</td>"
									+ "<td><button type='button' style='border: none'>X</button></td>"
									+ "</tr>";
						$('.tempListTable > tbody').append(tagHtml);
	
					});
		           
	               $(".modal").fadeIn();
	               
	               
	             
	               
	            },
	            error : function() {
	               alert('실패');
	            },
	         });
		});
	  
	  
	  // 모달창 사라지기
	  $(document).click(function(event) {
		  var target = $(event.target);
		  if (!target.closest('.modal_content').length) {
		    $('.modal').fadeOut();
// 		    $('.tempListTable').remove();
		  }
		});
	  
	});
	
</script>
</html>