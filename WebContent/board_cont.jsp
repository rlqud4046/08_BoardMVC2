<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
table {
	text-align: center;
	margin-top: 100px;
	border: 1px solid black;
	border-collapse: collapse; /* == cellspacing="0" */
	width: 350px;
}

th, td {
	border: 1px solid black;
	border-collapse: collapse; /* == cellspacing="0" */
	text-align: center;
}
</style>


</head>
<body>

	<div align="center">
		<c:set var="cont" value="${Cont }" />

		<hr width="50%" color="gray">
		<h3>${cont.getBoard_title()} 게시물 상세내용</h3>
		<hr width="50%" color="gray">
		<table>
			<c:if test="${!empty cont }">
				<tr>
					<th>글번호</th>
					<td>${cont.getBoard_no() }</td>
				</tr>
				<tr>
					<th>작성자</th>
					<td>${cont.getBoard_writer() }</td>
				</tr>
				<tr>
					<th>제목</th>
					<td>${cont.getBoard_title() }</td>
				</tr>
				<tr>
					<th>내용</th>
					<td><textarea rows="7" cols="30" readonly style="resize: none">${cont.getBoard_cont() }</textarea></td>
				</tr>
				
				<tr>
					<th>조회수</th>
					<td>${cont.getBoard_hit() }</td>
				</tr>
				<tr>
					<th>작성일</th>
					<td>${cont.getBoard_regdate() }</td>
				</tr>
				<tr>
					<td colspan="2" align="right">					
					<a href="board_update.do?no=${cont.getBoard_no() }">[수정]</a>&emsp;
					<a href="board_delete.do?no=${cont.getBoard_no() }">[삭제]</a>
					
					</td>
				</tr>


			</c:if>
			<c:if test="${empty cont }">
				<td colspan="2"><h3>검색된 레코드가 없습니다</h3></td>

			</c:if>

		</table>
	</div>


</body>
</html>