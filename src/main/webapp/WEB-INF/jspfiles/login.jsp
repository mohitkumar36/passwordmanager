<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
    <%@ include file="styles.jsp" %>
</head>
<body style="padding:5%">
	<div class="container">
	<h1>Login</h1>
	<br>
	<form action="perform_login" method="POST">
    	Email : <input type="text" name="email">
        <br/>
        Password : <input type="password" name="password">
        <br/>
        
        <input name="_csrf" type="hidden" value="${_csrf.token}">
        <input type="submit" value="Login">
        <c:if test="${param.error != null}">
		    <div id="error" style="color:red;">
		        Invalid Credentials
		    </div>
		</c:if>
        <a href="/resetpassword">Forgot Password?</a>
        <a href="/register">Register?</a>
    </form>
    </div>
</body>
</html>
