<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<div align="center">
		<hr width="50%" color="skyblue">
		<h3>BOARD1 테이블 메인페이지</h3>
		<hr width="50%" color="skyblue">
		<br><br>
		<a href="<%=request.getContextPath()%>/board_list.do">[전체목록]</a> 
		<%-- web.xml에서 *.do로 들어오면 FrontController로 보내주라 했으므로 FrontController로 감 --%>
	</div>

</body>
</html>