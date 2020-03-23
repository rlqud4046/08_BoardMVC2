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
		<c:set var="dto" value="${Dto }" />

		<hr width="50%" color="gray">
		<h3>${dto.getBoard_title()}게시글 수정</h3>
		<hr width="50%" color="gray">
		
		<form action="board_update_ok.do?no=${dto.getBoard_no() }" method="post">
		<table>
			<c:if test="${!empty dto }">
				<tr>
					<th>글번호</th>
					<td>${dto.getBoard_no() }</td>
				</tr>
				<tr>
					<th>작성자</th>
					<td>${dto.getBoard_writer() }</td>
				</tr>
				<tr>
					<th>제목</th>
					<td><input type="text" name="board_title" value="${dto.getBoard_title() }">
					</td>
				</tr>
				<tr>
					<th>내용</th>
					<td><textarea rows="7" cols="30" style="resize: none" name="board_cont">${dto.getBoard_cont() }</textarea></td>
				</tr>
				<tr>
					<th>조회수</th>
					<td>${dto.getBoard_hit() }</td>
				</tr>
				<tr>
					<th>작성일</th>
					<td>${dto.getBoard_regdate() }</td>
				</tr>
				<tr>
					<td colspan="2" align="right">					
					<input type="submit" value="확인">&emsp;
					<input type="reset" value="취소" onclick="">
					
					</td>
				</tr>


			</c:if>
			<c:if test="${empty dto }">
				<td colspan="2"><h3>검색된 레코드가 없습니다</h3></td>

			</c:if>

		</table>
		</form>
	</div>


</body>
</html>