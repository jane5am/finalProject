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
        <div class="board_view_wrap">
           <form method="post" id="frm">
               <div class="board_view">
                   <div class="title">
                      [<input type="text" name="recruitment"  value="${study.recruitment}" readonly="readonly" />
						]<input type="text" readonly="readonly" name="title" value="${study.title}">
                   </div>
                   
                   <img alt="더보기" class="button_more" src="resources/assets/img/three-dots.png">
                   <button type="button" data-oper="moveStudyModify" class="btn btn-default modButton" style="display: none;">수 정</button>
                   <button type="button" data-oper="deleteStudyPost" class="btn btn-default delButton" style="display: none;">삭 제</button>
                   <div class="info">
                       <dl>
                           <dt>번호</dt>
                           <dd><input type="text" name="seq" id="seq" value="${study.seq}" readonly="readonly"/></dd>
                       </dl>
                       <dl>
                           <dt>글쓴이</dt>
                           <dd>${study.writer}</dd>
                       </dl>
                       <dl>
                           <dt>작성일</dt>
                           <dd>${study.postingdate}</dd>
                       </dl>
                       <dl>
                           <dt>조회</dt>
                           <dd><input type="text" value="${study.hits}" name="hits" readonly="readonly"> </dd>
                       </dl>
                   </div>
                   
                   <img alt="bookmark" src="resources/assets/img/bookmark.png" class="bookmarkImg">
                   
                   <div class="cont">
						<div class="conditionDiv">
			          		<div class="studyInfo">
				           		<img alt="스터디모집조건" src="resources/assets/img/check.png" class="checkImg">
				           		<h1 class="conditionInfo" >이런 팀원을 모집하고 있어요!</h1>
			          		</div>
			          		<div class="selectCondition">
							   <strong>⭐ 스터디 주제</strong>
							   <input type="text" class="inputBoxView" value="${study.topic}" readonly="readonly"><br>
							   <strong>⭐ 스터디 방식</strong>
								<input type="text" class="inputBoxView" value="<c:choose><c:when test='${study.way == "contact"}'>대면</c:when><c:when test='${study.way == "untact"}'>비대면</c:when><c:otherwise>대면, 비대면  모두!</c:otherwise></c:choose>" readonly="readonly"><br>
							   <strong>⭐ 모집 언어</strong>
							   <input type="text" class="inputBoxView" value="${study.language}" readonly="readonly"><br>
							   <strong>⭐ 모집 인원</strong>
							   <input type="text" class="inputBoxView" value="${study.peopleNum}" readonly="readonly"><br>
							</div>
						</div>
                       <textarea name="content" readonly="readonly" class="textarea textareaDetail">${study.content}</textarea>
                   </div>
               </div>
               <div class="bt_wrap">
                   <button type="button" data-oper="moveStudyModify" class="btn btn-default" >수정</button>
                   <button type="button" data-oper="deleteStudyPost" class="btn btn-default">삭제</button>
                   <button type="button" data-oper= "getStudyPostlist" class="btn btn-default on" id="listBtn">글목록</button>
               </div>
            </form>
        </div>
    </div>
         
	<strong class="commentCnt">🗨️ 댓글 0개</strong>

	<div class="commentTable">
	
		<div class="writeCommentBox">
			<input type="text" value="닉네임" id="nickname" readonly="readonly">
			<input type="text" placeholder="댓글을 남겨보세요" id="commentContent"></input>
				<input type="submit" class="writeComment btn" data-oper= "writeComment" value="작성">
			<div>
<!-- 				<img alt="" src="resources/assets/img/bookmark.png" style="width: 15px"> -->
				😀 
<!-- 				<img alt="" src="resources/assets/img/bookmark.png" style="width: 15px"> -->
				📷
			</div>
		</div>
		
	
		<c:forEach items="${commentList}" var="comment">
			<div class="viewCommentContentBox">
				<img alt="프로필사진" src="resources/assets/img/bookmark.png" class="profilePicture " style="width: 36px">
				<input type="hidden" value="${comment.comment_id }" class="commentID">
				<input type="text" value="${comment.nickname }" id="nicknameView" readonly="readonly">
				<div class="viewCommentContentBox">
					<input type="text" class="viewContent" value="${comment.comment_content }" readonly="readonly" >
					<span class="viewDate"><fmt:formatDate value="${comment.comment_date }" pattern="yyyy-MM-dd"/></span>
					<span class="comment_info_button">답글 쓰기</span>
					<input type="button" class="commentMod" value="수정">
					<input type="button" class="commentDel" value="삭제">
				</div>
			</div>
			
        </c:forEach>
	        
	</div>
	
	
	
<!-- 		<tr class="writeCommentBox"> -->
<!-- 			<td><input type="text" placeholder="닉네임" id="nickname"></td> -->
<!-- 			<td><input type="text" placeholder="댓글을 남겨보세요" id="commentContent"></td> -->
<!-- 			<td></td> -->
<!-- 			<td></td> -->
<!-- 			<td><input type="submit" class="writeComment btn" data-oper= "writeComment" value="작성"></td> -->
<!-- 		</tr> -->
		
<!-- 		<tr class="writeCommentBox"> -->
			
<!-- 		</tr> -->
<%-- 			<c:forEach items="${commentList}" var="comment"> --%>
<!-- 				<tr> -->
<%-- 					<td><input type="hidden" value="${comment.comment_id }"> ${comment.nickname }</td> --%>
<%-- 					<td> ${comment.comment_content }</td> --%>
<%-- 					<td><fmt:formatDate value="${comment.comment_date }" pattern="yyyy-MM-dd"/></td> --%>
<!-- 				    <td><input type="button" class="commentMod" value="수정"></td> -->
<!-- 				    <td><input type="button" class="commentDel" value="삭제"></td> -->
<!-- 				</tr> -->
<%-- 	        </c:forEach> --%>
<!-- 	</table> -->
        

<!--   Vendor JS Files -->
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
<script type="text/javascript"  src="http://code.jquery.com/jquery-1.9.0.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>


<script type="text/javascript">

$(document).ready(function() {
   
	  
	// 댓글 갯수 실시간 업데이트
	  // 초기 개수 설정
	  updateCommentCount();
	  // MutationObserver를 사용하여 요소 변경 감지
	  var observer = new MutationObserver(function(mutations) {
	    updateCommentCount();
	  });
	  // 감지할 대상 요소 설정
	  var target = document.querySelector('.commentTable');
	  // MutationObserver 시작
	  observer.observe(target, { childList: true, subtree: true });
	  // 개수 업데이트 함수
	  function updateCommentCount() {
	    var count = $(".viewCommentContentBox").length;
	    $(".commentCnt").text("🗨️ 댓글 " + count/2 + "개");
	  }
	

	// 더보기(점세개) 버튼 눌렀을 때
	  $(".button_more").click(function() { 
		    $(".modButton, .delButton").each(function() {
		      $(this).toggle();
		      if ($(this).css("display") === "none") {
		        $(this).attr("style", "display: none;");
		      } else {
		        $(this).removeAttr("style");
		      }
		    });
	  });

	
	
	// 북마크 기능
	let seq = $('#seq').val();
	var imgSrc = $(this).attr("src");
	
	 $.ajax( {
	 	 url : "checkBookmark", // 여기서 "ajax"는 컨트롤러에 있던 @RequestMapping("ajax")의 ajax이다
	     data : { "seq" : seq },
	     type : "post",
	     //----------------------------------
	     success : function (ajaxData) { // ajaxData : 그냥 아무 변수명을 쓰면된다, 뭐 data라고 써도되고
			 alert(ajaxData);         
	          
	         if ( ajaxData == 1  ){
	           	console.log('북마크되어있지 않은 게시물');
	         }else {
	        	console.log('북마크되어있는 게시물');
		          $('.bookmarkImg').attr("src", "resources/assets/img/bookmarkOn.png");
	         }
	     },
	     error : function() {
	         alert('실패');
	     },
	  });
     

let formObj = $("form");
   $('.btn').on("click", function(e) {       //모든 button태그에 대한 클릭 이벤트 처리
      e.preventDefault();                //버튼의 기본 submit() 동작 막기
      let operation = $(this).data("oper");   //data-oper 값 읽어오기
      console.log(operation);
  
      if (operation == 'deleteStudyPost') {
         if (confirm("게시글을 삭제하시겠습니까?") == true) {
            alert("게시글이 삭제되었습니다.");
            formObj.attr("action", "deleteStudyPost");
         } else {
            return false;
         }
      } else if (operation == 'moveStudyModify' ){
         if (confirm("게시글을 수정하시겠습니까?") == true) {
            formObj.attr("action", "moveStudyModify");
         } else {
            return false;
         }
      } else if (operation == 'writeComment' ){
         if (confirm("댓글을 등록하시겠습니까?") == true) {
            formObj.attr("action", "writeComment");
            return;
         } else {
            return false;
         }
      }       else if (operation == 'modifyComment' ){
       if (confirm("댓글을 수정하시겠습니까?") == true) {
          formObj.attr("action", "writeComment");
          return;
       } else {
          return false;
       }
    }       
         else {
         formObj.attr("action", "getStudyPostlist");
      }
     
      formObj.submit();
   })
  
  
  
  //ajax 사용하기 위한 부분
  $('.writeComment').click(function(){
     // ajax 주요 키(옵션)
     // 1. url : 요청할 주소, controller 에 있는 맵핑명
     // 2. data : 요청과 함께 서버에 보낼 문자열, JSON 객체
     // 3. type : 전송할 방법, 보통 post or get 방식 중 하나인데 post를 더 많이 사용한다
     // 4. success : 요청이 성공되면 실행되는 함수(callback), function(){} 으로 생성
     // 5. dataType : 서버에서 받아온 데이터의 형식(자료형)
     // 6. error : 요청이 실패하면 실행되는 함수(callback), function(){ }으로 생성
     
     //JS를 이용해서 rentTitle과 rentDirector를 채워준다. 단, null체크 중요하다! java에서 실제로 null인지 판별해줘야한다
//     let inputData = { rentTitle : "제목" ,  rentDirector : "감독"}; // 미리 배열을 변수화 해둔 것

     let nickname = $('#nickname').val();
     let commentContent = document.getElementById('commentContent').value;
     let seq = $('#seq').val();
     console.log(nickname);
     console.log(commentContent);
     console.log(seq);
     
     let inputData = { "nickname" : nickname , "comment_content" : commentContent, "seq" : seq }; // 미리 배열을 변수화 해둔 것

     $.ajax( {
        url : "writeComment", // 여기서 "ajax"는 컨트롤러에 있던 @RequestMapping("ajax")의 ajax이다
        data : inputData,
        type : "post",
        dataType : 'json', // json쓸거면 데이터타입에 json써줘야한다 그리고 변환기 pom.xml에 넣어주기
        //----------------------------------
        success : function (ajaxData) { // ajaxData : 그냥 아무 변수명을 쓰면된다, 뭐 data라고 써도되고
           console.log(ajaxData);
              let str = JSON.stringify(ajaxData); // <> parse()
              console.log(str); 
              
           // ajaxData를 return 받아왔으니 그 데이터로 태그를 만드는 작업이 필요하다 (*)단 기존태그는 지우고
           // 데이터를 확인하고 싶을 때.
//             String -> Json 으로 바꾸는 함수 : JSON.parse // Json -> String 으로 바꾸는 함수 : JSON.stringify

           
//            Object.keys(ajaxData).forEach(function(k){
//                console.log('키값 : '+ ajaxData['jsonList'][index] + ', 데이터값 : ' + ajaxData['jsonList'][index]['comment_id']);
//            });

              $.each(ajaxData['jsonList'], function(index, item) { // 데이터 =item
                 console.log("얍" + ajaxData['jsonList'][index]['comment_content']); 
                 
//                     let tagHtml = "<tr>"+
//                    "<td>"+ "<input type='hidden' id='comment_id' value='"+ ajaxData['jsonList'][index]['comment_id'] + "'>" +ajaxData['jsonList'][index]['nickname']+"</td> "+
//                      "<td>"+ ajaxData['jsonList'][index]['comment_content'] +"</td> "+
//                      "<td>"+ ajaxData['jsonList'][index]['comment_date'] +"</td> "+
//                      "<td><input type='button' class='commentMod' value='수정'></td> "+
//                      "<td><input type='button' class='commentDel' value='삭제'></td> "+
//                    "</tr>";
                       
//                     $('.commentTable > tbody').append(tagHtml);

                    let tagHtml = 
                    	
                   "<div class='viewCommentContentBox'>" +
                   "<img alt='프로필사진' src='resources/assets/img/bookmark.png' class='profilePicture' style='width: 36px'>"+
                   "<input type='hidden' value='" + ajaxData['jsonList'][index]['comment_id'] + "'>"+
                   "<input type='text' value='" + ajaxData['jsonList'][index]['nickname']+"' id='nicknameView'>"+
                   "<div class='viewCommentContentBox'>"+
                   "<input type='text' class='viewContent' value=' " + ajaxData['jsonList'][index]['comment_content'] + "' readonly='readonly' >"+
                   "<span class='viewDate'>" + ajaxData['jsonList'][index]['comment_date'] + "</span> " +
                   "<span class='comment_info_button'>답글 쓰기</span>" +
                   "<input type='button' class='commentMod' value='수정' style='margin-left: 3px'> " +
                   "<input type='button' class='commentDel' value='삭제' style='margin-left: 3px'>" +
                   "</div>" + "</div>" ;
                    $('.commentTable').append(tagHtml);
              });
              
              $('#commentContent').val('');
           
        },
        error : function() {
           alert('실패');
        },
     });
        
  })
  
		      
		//댓글 수정
		          
        // 수정 버튼 클릭 시
		  $(document).on('click', '.commentMod', function() {
			  
		    // 해당 댓글의 내용과 id 가져오기
		    var commentContent = $(this).prev().prev().prev().val().trim();
		    var comment_id = $(this).parent().prev().prev().val();
		    
		    // 댓글 내용의 readonly해제
		    $(this).prev().prev().prev().attr('readonly', false);
		    
		    // 버튼 변경
		    $(this).val('등록').removeClass('commentMod').addClass('commentSubmit');
		    $(this).next().val('취소').removeClass('commentDel').addClass('commentCancel');
		 
		    // 취소 버튼 클릭 시
			  $(document).on('click', '.commentCancel', function() {
			    // 원래 댓글 내용 가져오기
			    $(this).prev().prev().prev().prev().val(commentContent);
			    $(this).prev().prev().prev().prev().attr('readonly', true);
			    
			    // 버튼 변경
			    $(this).prev().val('수정').removeClass('commentSubmit').addClass('commentMod');
			    $(this).val('삭제').removeClass('commentDel').addClass('commentDel');
			  }); // 취소 버튼 클릭 시 끝
			    
		  }); // 수정 버튼 클릭 시 끝
		
		
		  // 등록 버튼 클릭 시
		  $(document).on('click', '.commentSubmit', function() {
			 	alert('등록 해써요');
			    // 수정된 댓글 내용과 id 가져오기
			    var comment = $(this).prev().prev().prev();
			    var comment_content = $(this).prev().prev().prev().val();
			    var comment_id = $(this).parent().prev().prev().val();
			    console.log(comment_content);
			    console.log(comment_id);
			    
		        
		        // 버튼 되돌리기
		   		$(this).val('수정').removeClass('commentSubmit').addClass('commentMod');
				$(this).next().val('삭제').removeClass('commentCancel').addClass('commentDel');
			    
			    // 서버로 수정된 댓글 내용과 id 전송
			    $.ajax({
			      url: 'modifyComment',
			      type: 'post',
			      data: { "comment_id" : comment_id, "comment_content" : comment_content },
			      success: function(ajaxData) {
			    	  alert("성공");
			    	  console.log(ajaxData);
			          // 수정된 댓글 내용으로 변경
			          
			        comment.attr("readonly", true);
			        
			      },
			      error: function(xhr, status, error) {
			    	  alert('실패');
			        console.error(error);
			      }
			    });
	  });
		  
      
  
  //댓글 삭제
  $("body").on("click",".commentDel", function(){
       
	  var comment_id = $(this).parent().prev().prev().val();
	  var comment = $(this).parent().parent();
      console.log("내가 선택한 것의 코멘트 아이디 : " + comment_id);
      
        $.ajax( {
           url : "deleteComment",
           data : { "comment_id" : comment_id },
           type : "post",
           dataType : 'json',
           //----------------------------------
           success : function (ajaxData) {
        	   
                	 comment.remove();
                 
           },
           error : function() {
              alert('실패');
           },
        });
  }); // 댓글 삭제 끝
   
  
  
  // 북마크 기능
	$('.bookmarkImg').click(function(){
		
		let seq = $('#seq').val();
		var imgSrc = $(this).attr("src");
		
		if (imgSrc == "resources/assets/img/bookmark.png") {
			
		$(this).attr("src", "resources/assets/img/bookmarkOn.png");
			
			// 북마크 DB저장
			 $.ajax( {
            	url : "saveBookmark", // 여기서 "ajax"는 컨트롤러에 있던 @RequestMapping("ajax")의 ajax이다
	            data : { "seq" : seq, "boardNumber" : 1 },
	            type : "post",
	            //----------------------------------
	            success : function (ajaxData) { // ajaxData : 그냥 아무 변수명을 쓰면된다, 뭐 data라고 써도되고
			          console.log("북마크저장 성공!");    
	            },
            	error : function() {
	               		alert('실패');
	            }
			 });
		} else {
			$(this).attr("src", "resources/assets/img/bookmark.png");
			
			// 북마크 DB저장 해제
			 $.ajax( {
            	url : "saveCancelBookmark", // 여기서 "ajax"는 컨트롤러에 있던 @RequestMapping("ajax")의 ajax이다
	            data : { "seq" : seq, "boardNumber" : 1 },
	            type : "post",
	            //----------------------------------
	            success : function (ajaxData) { // ajaxData : 그냥 아무 변수명을 쓰면된다, 뭐 data라고 써도되고
			          console.log("북마크저장해제 성공!");    
	            },
            	error : function() {
	               		alert('실패');
	            }
			 });
		}
		
     });
        
  
  
  
  
  
})// script전체 끝
   
   
</script>