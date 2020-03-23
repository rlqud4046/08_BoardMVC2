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
	
	<c:set var="dto" value="${dto }" />
		<form action="board_delete_ok.do" method="post">
		<input type="hidden" name="board_no" value="${dto.getBoard_no()}">

			비밀번호 확인 : <input type="text" name="board_pwd">
			&emsp; <input type="submit" value="확인">

		</form>
	</div>
</body>
</html>