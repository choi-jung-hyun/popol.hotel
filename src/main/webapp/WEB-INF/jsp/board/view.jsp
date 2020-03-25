<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>boardView</title>
<%@include file="/WEB-INF/jsp/include/inc_common_header.jsp"%>
<script>
	function boardMain(){
		location.href = "/board/boardMain.do";
	}
	
	function update(){
		$("#boardFrm").submit();
	}
	
	function boardDelete(){
		$.ajax({
			url  : "/board/boardDelete.do",
			type : "POST",
			data : {
				board_no : $("#board_no").val(),
				title 	 : $("#title").val(),
				content	 : $("#content").val()
			}
		}).done(function(res){
			if(res.resultCode > 0){
				alert(res.msg);
				location.href = "/board/boardMain.do";
			}else{
				alert(res.smg);
			}
		})
		
		
	}
</script>

</head>
<body>
<div id="wrapper">
	<form id="boardFrm" action="/board/goBoardUpdate.do" method="post">
		<input type="hidden" name="board_no" id="board_no" value="${result.board_no }">
		작성자 : ${result.user_email } &nbsp; | &nbsp; | 작성일 : ${result.reg_dt } &nbsp; | 조회수 : ${result.view_count } 
		<br>
		---------------------------
		<br>
		제목 : <input type="text" name="title" id="title" value="${result.title }" readOnly> 
		<br>
		---------------------------
		<br>
		 	 <input type="text" name="content" id="content" value="${result.content }" readOnly>
		<br>
		-----------------------
		<br>
		
		<c:choose>
			<c:when test="${loginId.userEmail eq result.user_email}">
				<a href="javascript:update();">수정</a>
				<a href="javascript:boardDelete();">삭제</a>
			</c:when>
		</c:choose>
		<a href="javascript:boardMain();">목록</a>
	</form>
</div>
</body>
</html>