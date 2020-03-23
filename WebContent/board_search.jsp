<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div align="center">
		<hr width="50%" color="tomato">
		<h3>BOARD1 테이블 게시물 검색 목록</h3>
		<hr width="50%" color="tomato">
		<br> <br>
		<table border="1" cellspacing="0" width="400">
			<c:set var="search" value="${Search }" />

			<tr>
				<th>글번호</th>
				<th>글제목</th>
				<th>조회수</th>
				<th>작성일자</th>
			</tr>

			<c:if test="${!empty search }">
				<c:forEach items="${search }" var="dto">
					<!-- if (search.size() != 0) { // 검색한 레코드가 있으면
					// 검색된 레코드 수만큼 반복해서 웹에 출력
					for (int i = 0; i  -->
					<tr>
						<td>${dto.getBoard_no() }</td>
						<td><a href="board_cont.do?no=${dto.getBoard_no()}">${dto.getBoard_title()}</a></td>
						<td>${dto.getBoard_hit() }</td>
						<td>${dto.getBoard_regdate().substring(0,10) }</td>
				</c:forEach>
				<c:if test="${empty search }">
					<tr>
						<td colspan="4" align="center">
							<h3>검색 결과가 없습니다.</h3>
						</td>
					</tr>
				</c:if>
			</c:if>

		</table>


		<br>
		<hr width="50%" color="tomato">
		<br> <input type="button" value="전체목록 "
			onclick="location.href ='board_list.do'"> <br>

	</div>
</body>
</html>