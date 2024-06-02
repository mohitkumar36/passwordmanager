<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
    <title>Add Password</title>
    <%@ include file="styles.jsp" %>
</head>
<body>
	<%@ include file="nav.jsp" %>
	<div class="container">

    <h1>Enter Details</h1>
     <br>
    <form action="addpassword" method="POST">
		<c:if test="${password.websitename.length()>0}">
	    	<input type="hidden" value="${password.passwordId}" name="passwordId">
		</c:if>
    	Website Name: <input type="text" name="websitename" value="${password.websitename}">
    	<br/>
    	Website URL: <input type="text" name="url" value="${password.url}" required>
    	<br/>
    	Username : <input type="text" name="username" value="${password.username}">
        <br/>
        Email : <input type="email" name="email" value="${password.email}" required>
        <br/>
        Password : <input type="password" name="pass" required>
        <br/>
        Master Password : <input type="password" name="masterpass" required>
        <input name="_csrf" type="hidden" value="${_csrf.token}">
        <button type="submit">Submit</button>
    </form>
    </div>
     
</body>
</html>