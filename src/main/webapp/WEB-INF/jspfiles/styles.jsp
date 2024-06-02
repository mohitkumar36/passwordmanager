<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script src="https://code.jquery.com/ui/1.13.2/jquery-ui.min.js"></script>
<link rel="stylesheet"
	href="https://code.jquery.com/ui/1.13.2/themes/base/jquery-ui.css">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Merriweather+Sans:ital,wght@0,300..800;1,300..800&display=swap"
	rel="stylesheet">
<style>
* {
	margin: 0;
	padding: 0;
	font-family: "Merriweather Sans", sans-serif;
	font-optical-sizing: auto;
	font-weight: <weight>;
	font-style: normal;
}
/* Add a dark background color to the top navigation */
.topnav {
	background-color: #2c3e50; /* Dark blue-gray background color */
	overflow: hidden;
}

/* Style the links inside the navigation bar */
.topnav a {
	float: left; /* Align links to the left */
	display: block;
	color: #ecf0f1; /* Light gray text color */
	text-align: center;
	padding: 14px 16px;
	text-decoration: none;
	font-size: 17px;
}

/* Change the color of links on hover */
.topnav a:hover {
	background-color: #3498db; /* Light blue background color on hover */
	color: white; /* White text color on hover */
}

/* Add an active class to highlight the current page */
.topnav a.active {
	background-color: #e74c3c; /* Red background color for active link */
	color: white;
}

/* Move the logout button to the right */
.topnav a.logout {
	float: right; /* Align the logout button to the right */
}

.forbackground {
	border: 10px solid #e8e8e8;
	background-color: green;
	background:
		url("https://media.wired.com/photos/641e1a1b43ffd37beea02cdf/master/w_1600,c_limit/Best%20Password%20Managers%20Gear%20GettyImages-1408198405.png")
		no-repeat scroll right center #e8e8e8;
}
/* Card style for the container */
.container {
	max-width: 400px; /* Define maximum width */
	padding: 20px; /* Padding inside the card */
	margin: 50px auto; /* Center the card and add margin */
	background-color: #ffffff; /* White background color */
	border-radius: 10px; /* Rounded corners */
	box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2); /* Shadow effect */
	text-align: center; /* Center the text */
}

.containerhome {
	max-width: 80%; /* Define maximum width */
	padding: 20px; /* Padding inside the card */
	margin: 10% auto; /* Center the card and add margin */
	background-color: #ffffff; /* White background color */
	border-radius: 10px; /* Rounded corners */
	box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2); /* Shadow effect */
	text-align: center; /* Center the text */
}

/* Style for links in the card */
.container a {
	text-decoration: none; /* Remove underline from links */
	color: #007bff; /* Bootstrap primary color */
	font-weight: bold; /* Bold text */
	display: block; /* Each link takes up a block */
	margin: 10px 0; /* Space between links */
}

/* Hover effect for links */
.container a:hover {
	color: #0056b3; /* Darker blue on hover */
}

/* CSS for form styling */
form {
	display: block; /* Ensure form is treated as a block element */
	max-width: 400px; /* Set max width for the form */
	margin: 0 auto; /* Center the form */
	padding: 20px; /* Add padding around the form */
	background-color: #f8f9fa; /* Light gray background color */
	border-radius: 10px; /* Rounded corners */
	box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2); /* Box shadow for depth */
}

form input {
	display: block; /* Ensure inputs are block elements */
	width: 100%; /* Set width to full container width */
	margin-bottom: 15px; /* Add margin between input fields */
	padding: 10px; /* Add padding inside input fields */
	border: 1px solid #ccc; /* Light gray border */
	border-radius: 5px; /* Rounded corners */
	box-sizing: border-box; /* Account for padding and border in width */
}

form input[type="submit"] {
	background-color: #007bff; /* Bootstrap primary color */
	color: white; /* White text */
	border: none; /* Remove border */
	padding: 10px 20px; /* Add padding around submit button */
	cursor: pointer; /* Pointer cursor on hover */
}

form input[type="submit"]:hover {
	background-color: #0056b3; /* Darker blue on hover */
}

/* CSS for styling the table */
table {
	width: 100%; /* Set the width of the table to 100% of the container */
	border-collapse: collapse; /* Remove the space between cells */
	 /* Add margin at the bottom of the table */
}

th, td {
	border: 1px solid #ddd; /* Add a light border around each cell */
	padding: 10px; /* Add padding inside each cell */
	text-align: center; /* Align text to the left */
}

th {
	background-color: #f4f4f4; /* Light gray background color for header */
	font-weight: bold; /* Bold text in the header */
}

tr:nth-child(even) {
	background-color: #f9f9f9; /* Light background color for even rows */
}

tr:hover {
	background-color: #f1f1f1; /* Light gray background color on hover */
}

button {
	padding: 5px 10px; /* Add padding around button */
	margin-right: 5px; /* Add margin between buttons */
	border: none; /* Remove border */
	border-radius: 3px; /* Rounded corners */
	background-color: #007bff; /* Bootstrap primary color */
	color: white; /* White text */
	cursor: pointer; /* Pointer cursor on hover */
}

button:hover {
	background-color: #0056b3; /* Darker blue on hover */
}

a {
	color: #007bff; /* Bootstrap primary color for links */
	text-decoration: none; /* Remove underline from links */
}

a:hover {
	color: #0056b3; /* Darker blue on hover */
}

/* Custom CSS for the jQuery UI dialog box */
.ui-dialog {
	background-color: #f0f0f0; /* Light gray background */
	border: 2px solid #333333; /* Solid dark gray border */
	padding: 20px; /* Add padding to dialog contents */
	border-radius: 8px; /* Rounded corners */
}

.ui-dialog-titlebar {
	background-color: #333333; /* Dark gray background for title bar */
	color: #ffffff; /* White text color */
}

/* Style for dialog buttons */
.ui-dialog-buttonpane .ui-dialog-buttonset {
	padding: 10px; /* Padding around buttons */
}

/* Style for OK and Cancel buttons */
.ui-button {
	background-color: #007bff; /* Green background */
	color: white; /* White text */
	border: none; /* Remove border */
	padding: 10px 20px; /* Add padding */
	margin: 5px; /* Margin between buttons */
	cursor: pointer; /* Pointer cursor on hover */
}

.ui-button:hover {
	background-color: #0056b3; /* Darker green on hover */
}
</style>