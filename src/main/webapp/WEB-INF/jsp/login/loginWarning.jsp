<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
<meta charset="EUC-KR">
<title>loginWarning</title>
<script>
	$(document).ready(function() {
		alert("로그인 후 이용해주시기 바랍니다.").then(function(){
			location.href = "/login/loginView.do";
		})
		
	});
</script>
</head>
<body>

</body>
</html>