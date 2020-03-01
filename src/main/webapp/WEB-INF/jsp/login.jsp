<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
<c:if test="${!empty msg }">
	<font color="red">${msg}</font>
</c:if>
<form action="sign">
	username:<input name="username"/><br/>
	password:<input name="password"/><br/>
	<input type="submit"/><br/>
</form>
<form action="reg" method="post">
	username:<input name="username"/><br/>
	password:<input name="password"/><br/>
	<input type="submit"/><br/>
</form>
</body>
</html>