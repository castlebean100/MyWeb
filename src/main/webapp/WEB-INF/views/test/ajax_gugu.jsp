<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-3.4.1.min.js"></script>
<script>
$(function(){
	$("#button1").click(function() {
		// 아이디가 button1인 태그를 클릭하면
		dan=$("#dan").val(); // 아이디가 dan인 태그에 입력한 값을 가져와서
		param={"dan":dan}; // 보내준다 {변수명 : 값},컨트롤러로 보내주는 변수명 : dan 
		$.ajax({			// 비동기식으로
			type:"post",
			url:"ajax_gugu_result.do",	// 여기로 보낸다
			data:param,			 	// 데이터를
			success: function(result){ //처리가 완료된 값을
				$("#result").html(result); // 아이디가 result인 태그에 전달
				// 바디에 있는 div
			}
		});
	});
});

 
</script>
</head>

<body>
	<h2>구구단 계산</h2>
		단을 입력하세요
		<input type="number" id="dan" value="3">
		<input type="button" id="button1" value="확인" >
		
	<div id="result">   </div>
</body>
</html>