<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>boardMain</title>
<%@include file="/WEB-INF/jsp/include/inc_common_header.jsp"%>
<style>
	table {
	text-align: center;
	}

	  td {
	border: 1px solid black;
	}
</style>

<script>
	$(document).ready(function(){
		if('${vo.searchKey}' != null || '${vo.searchKey}' != ''){
			$("#searchKey").val('${vo.searchKey}')
		}
		
		if('${vo.searchWrd}' != null || '${vo.searchWrd}' != ''){
			$("#searchWrd").val('${vo.searchWrd}')
		}
		
		
	})
	
	
	function goWrite() {
		location.href = "/board/goWrite.do";
	}
	
	 function linkPage(idx) {
	        if (idx <= 0 || idx > '${pageSize }') {
	            return;
	        }
	        if ('${vo.pageIndex}' != idx) {
	            $("#pageIndex").val(idx);
	        }
	        $("#boardSub").attr("action", "/board/boardMain.do");
	        $("#boardSub").submit();
	        	
	    }

	 function boardView(board_no){
		 
			$("#board_no").val(board_no);
			$("#boardSub").attr("action", "/board/boardView.do");
			$("#boardSub").submit();
	 }
	 
	 function boardSearch(){
		 if($("#seachWrd").val() == '' || $("#searchWrd").val() == null ){
			 alert("검색어를 입력해주세요!");
			 return;
		 }
		 
		 $("#boardSub").attr("action","/board/boardMain.do");
		 $("#boardSub").submit();
	 }
	 
	 function searchReset(){
		 $("#searchKey").val("");
		 $("#searchWrd").val("");
		 
		 $("#boardSub").attr("action","/board/boardMain.do");
		 $("#boardSub").submit();
	 }
	 
</script>	
</head>
<form id="boardSub" method="POST">
	<input type="hidden" id="board_no" name="board_no">
	
	<div id="wrapper">
		<div id="container">
			<div>
				<select id="searchKey" name="searchKey">
					<option value="">전체</option>
					<option value="title">제목</option>
					<option value="user_email">작성자</option>
				</select>
				<input type="text" id="searchWrd" name="searchWrd">
				<a href="javascript:boardSearch();">검색</a>	
				<a href="javascript:searchReset();">초기화</a>
			</div>
			<table >
			<colgroup>
				<col width="10%">
				<col width="40%">
				<col width="15%">
				<col width="20%">
				<col width="15%">
			</colgroup>
				<thead>
					<tr>
						<th>NO</th>
						<th>제목</th>
						<th>작성자</th>
						<th>작성일</th>
						<th>조회</th>
					</tr>
		        </thead>
		        <tbody>
		        <c:choose>
		        	<c:when test="${!empty list }">
		        		<c:forEach items="${list }" var="list" varStatus="idx">
				            <tr>
				               <td>
				               		${(vo.pageIndex - 1) * vo.pageUnit + idx.count}
				               </td>
				               <td>
				               		<a href="javascript:boardView('${list.board_no }');">
				               			${list.title }
				               		</a>
				               </td>
				               <td>
				               		${list.user_email }
				               </td>
				               <td>
				               		${list.reg_dt }
				               </td>
				               <td>
				               		${list.view_count }
				               </td>
				            </tr>
		            	</c:forEach>
		            </c:when>
		        	<c:otherwise>
			        	<tr>
			        		<td colspan="4">작성된 글이 없습니다.</td>
			        	</tr>
		        	</c:otherwise>
		        </c:choose>
		        </tbody>
		    </table>
		    <a href="javascript:goWrite();" class="btn btn-primaey">글쓰기</a>
	    </div>
	    
	    <!-- 페이징처리 -->
	    <fmt:parseNumber var="firstPage" value="${(vo.pageIndex -1) / 10}" integerOnly="true"/>
	    <fmt:parseNumber var="firstPage" value="${firstPage * 10 + 1}" integerOnly="true"/>
	    <fmt:parseNumber var="lastPage" value="${firstPage + vo.pageSize - 1}" integerOnly="true"/>
	    <c:if test="${lastPage > pageSize}">
	    	<c:set var="lastPage" value="${pageSize}"/>
	    </c:if>
	    
	    <div class="pagingWrap">
	    <a href="n" class="page first" onclick="linkPage(${firstPage});return false;">처음</a>&nbsp;
	    <a href="n" class="page prev" onclick="linkPage(${vo.pageIndex - 1});return false;">이전</a>&nbsp;
	    <c:forEach var="i" begin="${firstPage }" end="${lastPage}" varStatus="idx">
	    	<a href="javascript:linkPage(${idx.index});"><span class="page <c:if test='${idx.index eq vo.pageIndex}'>active</c:if>">${idx.index }</span></a>&nbsp;
		</c:forEach>
	    <a href="n" class="page next" onclick="linkPage(${vo.pageIndex + 1});return false;">다음</a>&nbsp;
	    <a href="n" class="page last" onclick="linkPage(${lastPage});return false;">마지막</a>&nbsp;
	    </div>
	    <input type="hidden" name="pageIndex" id="pageIndex" value="${vo.pageIndex }">
	
	
	</div>
</form>
</body>
</html>