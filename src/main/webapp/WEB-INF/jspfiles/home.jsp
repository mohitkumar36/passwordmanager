<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <title>Home</title>
    <%@ include file="styles.jsp" %>
</head>

<body>
	<%@ include file="nav.jsp" %>
	<div class="forbackground">
		<div class="containerhome">
		<c:if test="${passwords.size() > 0 }">
			<table>
				<tr>
					<th>Website Name</th>
					<th>URL</th>
					<th>Username</th>
					<th>Email</th>
					<th>Copy to clipboard</th>
					<th>Modify</th>
				</tr>
				<c:forEach items="${passwords}" var="p">
					<tr>
						<td>${p.getWebsitename()}</td>
						<td>${p.getUrl()}</td>
						<td>${p.getUsername()}</td>
						<td>${p.getEmail()}</td>
						<td><button onclick="askForPassword(${p.getPasswordId()})">Copy Password</button></td>
						<td><a href="/update?passwordId=${p.getPasswordId()}"style="color:green">Update</a> / <a href="/delete?passwordId=${p.getPasswordId()}" style="color:red">Delete</a></td>
					</tr>
				</c:forEach>
			</table>
		</c:if>
		<c:if test="${passwords.size() == 0 }">
			<a href="/addpassword">Add new Password</a>
		</c:if>
		</div>
	</div>
	
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.min.js"></script>
	<link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
	
	<script>
	function askForPassword(passwordId) {      
	    // Clear the input field      
	    $('#passwordInput').val('');            
	
	    // Create a jQuery dialog      
	    $("<div><input type='password' id='passwordInput' placeholder='Master Password'></div>").dialog({          
	    	dialogClass:"popup",
	    	title: "Enter Password",
	        modal: true,
	        resizable: false,
	        appendTo: "body", // Ensure the dialog is appended to the body
	        buttons: {              
	            "OK": function () {                  
	                var password = $('#passwordInput').val();                  
	                if (!password) {                      
	                    alert("Password cannot be empty");                      
	                    return;                  
	                }                  
	                $.ajax({                      
	                    url: "/copytoclip",                      
	                    type: "POST",                      
	                    data: {                          
	                        passwordId: passwordId,                          
	                        password: password,                          
	                        _csrf: "${_csrf.token}"                      
	                    },                      
	                    success: function (response) {                          
	                        if (response === "Wrong MasterPassword") {                              
	                            alert(response);                          
	                        } else {                              
	                            navigator.clipboard.writeText(response)                                  
	                            .then(() => {                                      
	                                console.log('Password copied to clipboard successfully');                                      
	                                alert('Password copied to clipboard successfully');                                  
	                            })                                  
	                            .catch(err => {                                      
	                                console.error('Unable to copy text to clipboard:', err);                                      
	                                alert('Unable to copy text to clipboard');                                  
	                            });                          
	                        }                      
	                    },                      
	                    error: function (xhr, status, error) {                          
	                        console.error('Error copying text to clipboard:', error);                          
	                        alert('Error copying text to clipboard');                      
	                    }                   
	                });                  
	                $(this).dialog("close").remove(); // Close and remove the dialog              
	            },              
	            "Cancel": function () {                  
	                $(this).dialog("close").remove(); // Close and remove the dialog              
	            }          
	        }      
	    }).css({
	    	"font-family":"Merriweather Sans",
	    	"display": "flex",
	    	"align-items": "center"
	    	})
	    	.prev(".ui-dialog-titlebar")
	    	.css({
				"color":"#ecf0f1", 
				"background":"#2c3e50"
				});       
	}
	</script>

	
</body>
</html>
