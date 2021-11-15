<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var = "contextPath" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>파일 업로드</h1>
	<form action="${contextPath }/upload" method="post" enctype="multipart/form-data"><!-- 파일업로드는 무조건 post방식, type도 무조건 multipart로 써야한다 -->
		<input type="text" name="id"><br>
		<input type="text" name="name"><br>
		<input type="file" name="file"><br>
		<input type="submit" value="업로드"><br>
	</form>
	<hr>
	<a href="${contextPath }/views">파일보기</a>
	

</body>
</html>