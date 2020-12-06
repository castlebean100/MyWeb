<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-3.4.1.min.js"></script>
<script>
// $(선택자: 태그나 객체).이벤트함수(실행될 코드)
// 현재 문서를 객체로 선택하고, 로딩이되면 이 function을 실행하시오

$(function() {
	$.ajax({
		type:"post",
		url : "background.do",
		success : function(result) {
			console.log("test:"+result);
			$("#result").html("상품명:"+result.name +",가격:"+result.price);
			
		}
	});
});

</script>
</head>
<body>
	<jsp:include page="../include/menu.jsp" />
	<div id="result"> </div>
</body>
</html>