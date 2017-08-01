<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="java.util.Vector"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="buyAndSell.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
        
        <%!
            boolean validateSession()
            {
                if (StoreDatabase.getCurrUser() != null)
                    return true;
                else
                    return false;
            }
        %>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>A Message</title>
        <link href="nav.css" rel="stylesheet" type="text/css">
        <link href="main.css" rel="stylesheet" type="text/css">
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
		
		
		<style>
		</style>
		
		
		
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
		
        <script>
                function getHeader(valid) {
                    if (valid)
                        $("#login_header").load("logged_in.html");
                    else
                        $("#login_header").load("logged_out.html");
                }
        </script>		
	</head>
    <%if (validateSession()) {%>
    <body onload="getHeader(true)">
    <%}
    else {
    %>
    <body onload="getHeader(false)">
    <%} %>
		
		<h1>Message</h1>
		<% 
            	User user = StoreDatabase.getCurrUser();
        		Vector<Message> messages = user.getMessages();
        		Message message = session.getAttribute("theMessage");
        		
        %>

		<center>
		<div id="messagebody">
		<!--  LOADS the message in messagedisplay.jsp, which will display in the iframe -->
			<iframe src="messagedisplay.jsp" width="500" height="500" scrolling="yes">
	  			<p>Your browser does not support iframes.</p>
			</iframe>
	
			<div id="deletebutton">
		    		<form action = "inbox.jsp" >
					<button type="submit" onclick="<%= deletWishlisteMessage(message.getMessageID())%>">Delete</button><br>		
				</form>
			</div>
		</div>		
		</center>
		
		
	
	</body>
</html>