<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원정보확인</title>
</head>
<body>
회원정보를 출력해주세요.

<c:if test="${list!=null}">
  <h2>[ 회원정보 ]</h2>
   <table>
    <tr>
       <th>UserNo</th>
       <th>UserId</th>
       <th>UserName</th>
       <th>UserPass</th>
     </tr>
   <c:forEach items="${list}" var="vo">
     <tr>
       <td>${vo.userNo}</td>
       <td>${vo.userId}</td>
       <td>${vo.userName}</td>
       <td>${vo.userPass}</td>
     </tr>
   </c:forEach>
   </table>
   <p/>
   <form action="userSearch.do" method="post">
   <select name="keyField">
      <option value="0">--선택--</option>
      <option value="userName">이름</option>
      <option value="userId">아이디</option>
   </select>
   <input type="text" name="keyWord"/>
   <input type="submit" value="검색"/>
   </form>
</c:if>

</body>
</html>