<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-3.4.1.min.js"></script>
<script>
$(function() {
	$("#btnLogin").click(function(){
		id=$("#id").val();
		pw=$("#pw").val();
		param={"id":id, "pw":pw};
		
		$.ajax({
			type:"post",
			url:"login_result.do",
			data:param,
			success:function(result){
				$("#result").html(result);
			}
		})
	})
})

/* $(function(){			// 페이지가 로딩되면 자동으로 실행되는 함수
	//id가 btnLogin인 버튼을 누르면!
	$("btnLogin").click(function(){
	id=$("#id").val();
	pw=$("#pw").val();
	param={"id":id, "pw":pw}; // {키:밸류, 키:밸류}, result로 보낼 값
	//비동기식 호출
	$.ajax({
		type: "post",
		url: "login_result.do",
		data: param,
		success: function(result) { // 서버에서 실행된 값이 result로 넘어옴
			$("#result").html(result);	// 아이디가 result인 태그인 아래 div로 넘김
			}
		});
	});
}); */

</script>
</head>

<body>

id : <input type="text" id="id">
pw : <input type="password" id="pw">
<input type="button" value="로그인" id="btnLogin">
<div id="result"> </div>

</body>
</html>