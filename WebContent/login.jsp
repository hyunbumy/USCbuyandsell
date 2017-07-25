<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html class="no-js" lang="">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <meta charset="utf-8">
        <meta http-equiv="x-ua-compatible" content="ie=edge">
        <title>Log-In</title>
        <meta name="description" content="">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <link rel="apple-touch-icon" href="apple-touch-icon.png">
        <!-- Place favicon.ico in the root directory -->

        <!-- Link stylesheets-->
        <link rel="stylesheet" href="css/normalize.css">
        <link rel="stylesheet" href="css/main.css">
        <link href="css/nav.css" rel="stylesheet" type="text/css">
		<link href="https://fonts.googleapis.com/css?family=Cinzel" rel="stylesheet">
        
        <!-- Load js files-->
        <script src="js/vendor/modernizr-2.8.3.min.js"></script>
        <script src="https://code.jquery.com/jquery-1.12.0.min.js"></script>
        <script>window.jQuery || document.write('<script src="js/vendor/jquery-1.12.0.min.js"><\/script>')</script>
        <script src="js/plugins.js"></script>
        <script src="js/main.js"></script>
        
        <script>
        	function getHeader() {
        		$("#header").load("logged_out_loginpage.html");
        	}
        </script>
        
        <style>
        	#main {
        		text-align:center;
        	}
        </style>
	</head>
	<body onload="getHeader()">
		<div id="header">Placeholder</div>
		<br/><br/><br/>
		
		<div id="main">
			<form name="login" action="login" method="GET">
				Username:<br/>
				<input type="text" name="username" placeholder="Enter your username" required><br/>
				Password:<br/>
				<input type="password" name="password" placeholder="Eneter your password" required><br/>
				<input type="submit" value="Log In">
				<button type="button" onclick="location.href='register.jsp'">Register</button>
				
				
			</form><br/>
			<%
			  if (request.getAttribute("error") != null) {
			%>
			<%=request.getAttribute("error") %>
			<%} %>
		</div>
		
	</body>
</html>