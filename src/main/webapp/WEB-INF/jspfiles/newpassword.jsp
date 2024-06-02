<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<title>Reset Password</title>
	<%@ include file="styles.jsp" %>
</head>
<body>
	<div class="container">
    <h1>Enter new Password</h1>
    <br>
     
    <form action="newpassword" method="POST">
        New Password : <input type="password" name="password">
        <br/>
        <input type="hidden" name="code" value="${code}">
        <input name="_csrf" type="hidden" value="${_csrf.token}">
        <input type="submit" value="Reset">
    </form>
    </div>
     
     
</body>
</html>