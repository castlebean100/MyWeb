<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>회원 목록</h2>
<input type="button" value="회원등록" onclick="location.href='/member/write.do'">
<table border="1" width="700px">
	<tr>
		<td>아이디</td>
		<td>이름</td>
		<td>비밀번호</td>
		<td>이메일</td>
		<td>가입일자</td>
	</tr>

<c:forEach var="row" items="${items}">
<!-- row는 개별값 items은 전체리스트 -->

	<tr>
		<td>${row.userid}</td>
		<td><a href="${path}/member/view.do?userid=${row.userid}">${row.userid}</a></td>
		<td>${row.passwd}</td>
		<td>${row.email}</td>
		<td><fmt:formatDate value="${row.regDate}"
		pattern="yyyy-MM-dd HH:mm:ss" />
	</tr>

<!-- 
core tag 쓰려면 prefix="c" 이거 라이브러리 있어야함
 -->
</c:forEach>

</table>
</body>
</html>