<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<title>사용자 등록</title>

<!-- 
          정적자원들은 resources 폴더에 저장
	 현재 프로젝트명에서 user 경로를 더한 상태이기에 상위경로를 올라갔다가 resources를 찾음.
 -->

<link rel="stylesheet" href="../resources/style/stylecss.css" type="text/css">
<script type="text/javascript" src="../resources/js/jquery-1.7.1.js"></script>
<script  src="../resources/js/js_userinput.js"></script>
</head>



<body bgcolor="#FFFFFF">

<table class='table1'>
  <tr bgcolor="#3399CC"> 
    <td height="30" class="normalbold"><font color="#FFFFFF">사용자 정보입력</font></td>
  </tr>
  <tr>
    <td>안녕하세요. 우리 홈페이지를 자유롭게 이용하려면 회원가입을 하셔야 합니다.<br>
      아래의 사항들을 빠짐없이 기록해 주세요.</td>
  </tr>
</table>

<form method="post" action="userInsert.do" name="userinput" id="userinput">
  <table class='table2'>
    <tr> 
      <td class="normalbold tb_ttl"> 
        <div align="center">사용자 ID</div>
      </td>
      <td colspan="3" class="normal"> 
        <input type="text" name="userId" id="userId" size="20"> 
        <span id="idCheckResult" style="width:150px;color:red"></span>
      </td>
    </tr>
    <tr> 
      <td class="normalbold tb_ttl">
        <div align="center">비밀번호</div>
      </td>
      <td width="154" class="normal"> 
        <input type="password" name="userPass" id="userPass">
      </td>
      <td class="normalbold tb_ttl"> 
        <div align="center">비밀번호 확인</div>
      </td>
      <td width="160"> 
        <input type="password" name="userPass2" id="userPass2">
      </td>
    </tr>
    <tr> 
      <td class="normalbold tb_ttl"> 
        <div align="center">사용자 이름</div>
      </td>
      <td colspan="3" height="23" class="normal"> 
        <input type="text" name="nickname" id="nickname">
      </td>
    </tr>
    <tr>
      <td colspan="4" class="normal"> 
        <div align="center"> 
          <input type="button" name="confirm" id="confirm" value="등   록">
          <input type="reset" name="reset" value="취   소">
        </div>
      </td>
    </tr>
  </table>
</form>

<script type="text/javascript">
	document.getElementById("confirm").addEventListener("click",function(e) {
		// 유효성검사 - 구글에 '자바스크립트 유효성 검사' ,'자바스크립트 유효성 검사 라이브러리' / 데이터베이스에 부담을 덜 준다
		// 사용자가 입력한 값이 우리시스템에 적용할 수 있도록 잘 입력되어있는지 검사하는 행위
		// 가장 효율적인언어, 가장 효율적인 시점(포인트)가 존재할 뿐 특정언어로 해야한다는 규칙은 없다
		
		//IF조건을 통해서 해당 이벤트를 강제로 종료 : return;
		//alert(document.getElementById('userId').value);
		if(document.getElementById('userId').value =='' ) { //id가 공란이면 문제발생하고 return
			alert('문제발생 : 아이디를 입력해주세요');
			document.getElementById('userId').focus();
			return;
		}
		
	      // 비밀번호 공백일 경우
	      if ( document.getElementById('userPass').value == '') {
	         alert('문제발생 :비밀번호를 입력해주세요');
	         document.getElementById('userPass').focus();
	         return;
	      }
	      

		
	      // 비밀번호1,2번끼리 확인
	      if ( document.getElementById('userPass').value != document.getElementById('userPass2').value) {
	         alert('문제발생 :비밀번호가 서로 다릅니다');
	         document.getElementById('userPass').value = '';
	         document.getElementById('userPass2').value = '';
	         document.getElementById('userPass').focus();
	         return;
	      }    
		
		// 이름 공백일 경우
		if(document.getElementById('nickname').value =='' ) { //id가 공란이면 문제발생하고 return
			alert('문제발생 : 이름을 입력해주세요');
			document.getElementById('nickname').focus();
			return;
		}
		
		// 모든 조건이 맞을때만 서버에 폼데이터를 제출할거다
		//userinput은 form태그의name
		document.userinput.submit(); //action = "userInsert.do"
	});
</script>

</body>
</html>
