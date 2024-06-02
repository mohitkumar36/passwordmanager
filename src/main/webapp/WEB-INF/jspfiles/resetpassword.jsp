<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <title>Reset Password</title>
    <%@ include file="styles.jsp" %>
</head>
<body style="padding:10%">
	<div class="container">
	<h1>Reset Password</h1>
	<br>
	<form action="resetpassword" method="POST">
    	Email : <input type="text" name="email">
        <br/>
        <input name="_csrf" type="hidden" value="${_csrf.token}">
        <input type="submit" value="Reset">
        <a href="/login">Login?</a>
    </form>
    </div>
	
</body>
</html>
