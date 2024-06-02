<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<title>Register</title>
	<%@ include file="styles.jsp" %>
</head>
<body style="padding:4%">
	<div class="container">
    <h1>Register Here !!</h1>
    <br>
     
    <form action="process-registration" method="POST">
        Email : <input type="email" name="email">
        <br/>
        Password : <input type="password" name="password">
        <br/>
    	Name : <input type="text" name="username">
        <br/>
        <input name="_csrf" type="hidden" value="${_csrf.token}">
        <input type="submit" value="Register">
        <a href="/login">Login instead?</a>
    </form>
    </div>
     
     
</body>
</html>