<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>

<%-- <form action="sign">
	姓名：<input/><br/>
	密码：<input/><br/>
	爱好：
	学历：
	专业：
	<input type="submit" value="登录"/><br/>
</form> --%>

<!-- modelAttribute用于定义从model哪个对象对表单进行填充 -->
<form:form modelAttribute="user"  method="GET" action="reg">
	姓名：<form:input path="username"/>
	<form:errors path="username"></form:errors>
	<br/>
	密码：<form:input path="password"/>
	<form:errors path="password"></form:errors>
	<br/>
	邮箱：<form:input path="email"/>
	<form:errors path="email"></form:errors>
	<br/>
	爱好：<form:checkboxes items="${likeItems }" path="likes"/><br/>
	学历：<form:radiobuttons items="${eduItems }" path="edu"/><br/>
	专业：<form:select items="${subjectItems }" path="subject"
						itemLabel="name" itemValue="id"></form:select><br/>
	<input type="submit" value="登录"/><br/>
</form:form>

</body>
</html>