<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@include file="/WEB-INF/jsp/include/inc_common_header.jsp"%>
<script type="text/javascript" src="/resources/smartEditer2/js/HuskyEZCreator.js" charset="utf-8"></script>
<script type="text/javascript" src="//code.jquery.com/jquery-1.11.0.min.js"></script>

</head>
<script>
	
	function write_reg(){
		oEditors.getById["smartEditor"].exec("UPDATE_CONTENTS_FIELD", []);
		
		var content = document.getElementById("smartEditor").value;
		
		if($("#title").val() == null || $("#title").val() == ''){
			alert("제목에 공백을 입력할 수 없습니다.");
			return false;
		}
		
		if(content == null || content == '' || content =='<br>' ){
			alert("내용을 작성해주세요.");
			return false;
		}
			$.ajax({
				
				type : "POST",
				url : "/board/writeReg.do",
				data : {
					title : $("#title").val(),
					content : content
				}
				
			}).done(function (res){
				if(res.resultCode >0){
					alert(res.msg);
					location.href = "/board/boardMain.do";
				}else{
					alert(res.msg);
				
				}
			})	
		
	}
	
	function update(board_no){
		oEditors.getById["smartEditor"].exec("UPDATE_CONTENTS_FIELD", []);
		
		var content = document.getElementById("smartEditor").value;
		
		if($("#title").val() == null || $("#title").val() == ''){
			alert("제목에 공백을 입력할 수 없습니다.");
			return false;
		}
		
		if(content == null || content == '' || content =='<br>' ){
			alert("내용을 작성해주세요.");
			return false;
		}
		
		$.ajax({
			
			type : "POST",
			url  : "/board/boardUpdate.do",
			data : {
				title : $("#title").val(),
				content : content,
				board_no : board_no
			}
			
		}).done(function (res){
			if(res.resultCode > 0){
				alert(res.msg);
				location.href = "/board/boardView.do?board_no=${vo.board_no}";
			}else{
				alert(res.msg);
			
			}
		})	
		
	}
	
	function write_cancel(){
		location.href = "/board/boardMain.do";
	}
	
</script>
<body>
	<div id="wrapper">
		<c:choose>
			<c:when test="${empty vo}">
				<h2>게시물 작성</h2>
			</c:when>
			<c:otherwise>
				<h2>게시물 수정</h2>
			</c:otherwise>
		</c:choose>
		<p>제목 : <input type="text" id="title" value="${vo.title }"></p>
		<div class="jsx-2303464893 editor"> 
			<div class="fr-box fr-basic fr-top" role="application"> 
				<div class="fr-wrapper show-placeholder" dir="auto" style="overflow: scroll;"> 
					<textarea name="notice_content" id="smartEditor" style="width: 100%; height: 412px;">
					${vo.content }
					</textarea> 
					<script type="text/javascript" src = "/resources/smartEditer2/js/notice-write.js"></script>
				</div> 
			</div> 
		</div>
		<p>
		<c:choose>
			<c:when test="${empty vo }">
				<a href="javascript:write_reg();">등록</a> 
			</c:when>
			<c:otherwise>
				<a href="javascript:update('${vo.board_no }');">수정</a> 
			</c:otherwise>
		</c:choose>	
		
		<a href="javascript:write_cancel();">취소</a>
		</p>
		
		
		
	</div>
</body>
</html>

