<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>

<%
	// 로그인안한 사용자가 Main들어오면 튕겨내는것 - 이페이지를 볼때는 세션이 필요하다!
	if (session.getAttribute("userName") == null) {
		response.sendRedirect("/user/userLogin.do");
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<style>
  a{text-decoration: none}
</style>
</head>
<body>

<h1>로그인 결과</h1>

<table width="500">
<tr>
<th>
<!-- sessionTime과 userName는 세션 속성에 저장된 값 :  sessionScope 접근 없이도 가능  -->
 [ ${sessionScope.sessionTime} ] ${userName}님 &nbsp; &nbsp;
</th>
</tr>
<tr>
<th>
 <a href="logOut.do">[로그아웃]</a> &nbsp;&nbsp;
 <a href="memberInfo.do">[회원정보]</a>

</th>
</tr>
</table>



</body>
</html>







