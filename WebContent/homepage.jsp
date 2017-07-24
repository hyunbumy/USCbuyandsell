<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="buyAndSell.*"%>
<%@ page import="java.util.Vector"%>
<%@ page import="java.util.HashMap"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html class="no-js" lang="">
    
    <head>

    <!--------------FOR TESTING---------------------->
    <%
    
    
    Store s = new Store();
    request.getSession().setAttribute("store", s);
    s.createUser("Brandon", "Holden", "bholden@usc.edu", "978-257-5700", "bholden", "password");
	
	
	
	Item car = new Item("My awesome first car", 10000.99, Category.ELECTRONIC, 1, s.getCurrUser());
	Item tv = new Item("really awesome tv", 111.93, Category.ELECTRONIC, 1, s.getCurrUser());
	Store.sellItem(car);
	Store.sellItem(tv);
	
    Category[] allCategories = Category.values();
    
    %>
  	<!----------------FOR TESTING------------->
    <%!
    	boolean validateSession(HttpSession session)
    	{
    		if (session.getAttribute("currUser") != null)
    			return true;
    		else
    			return false;
    	}
    %>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <meta charset="utf-8">
        <meta http-equiv="x-ua-compatible" content="ie=edge">
        <title>USC Buy and Sell</title>
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
        
        <style>			
        	#main
        	{
        		position:fixed;
        		top:40%;
        		left:50%;
        		transform:translate(-50%,-50%);
        	}
        	
        	#title
        	{
        		color:#bd2031;
        		font-size:100px;
        		margin:0px;
        	}
        	
        	input[type=text]
        	{
        		width:73%;
        		height:100%;
        	}
        	input[type=submit]
        	{
        		width:10%;
        		height:100%;
        	}
        	select
        	{
        		width:15%;
        		border-radisu:4px;
        		height:100%;
        		border: 1px solid;
        		box-sizing:border-box;
        	}
        </style>
        
        <script>
        	function getHeader(valid) {
        		if (valid)
        			$("#login_header").load("logged_in.html");
        		else
	        		$("#login_header").load("logged_out.html");
        	}
        </script>
    </head>
    
    <%if (validateSession(session)) {%>
    <body onload="getHeader(true)">
    <%}
    else {
    %>
    <body onload="getHeader(false)">
    <%} %>
        <!--[if lt IE 8]>
            <p class="browserupgrade">You are using an <strong>outdated</strong> browser. Please <a href="http://browsehappy.com/">upgrade your browser</a> to improve your experience.</p>
        <![endif]-->

        <!-- Add your site or application content here -->
       
        <div id="login_header"></div>
        
        <div id="main">
        <h1 id="title">USC BUY AND SELL</h1>
        <form name="search_form" action="search" method="GET">
        	<select name="category" value="all">
        	<option value="all">All</option>
        	<%
        	for (int i = 0; i < allCategories.length; i++) {
        		//formating the category names
        		String catName = allCategories[i].toString();
        		String firstLetter = catName.substring(0, 1).toUpperCase();
        		String lastLetters = catName.substring(1).toLowerCase();
        		catName = firstLetter+lastLetters;
        		%>
        		<option value=<%=catName %>><%=catName %></option>
        		<%	
        	}
        	%>
        	 
        	</select>
        	<input type="text" name="term" placeholder="Search for an item!">
        	<input type="submit" value="SEARCH">
        </form><br/>
        <%
        	if (request.getAttribute("error") != null && 
        		!request.getAttribute("error").equals("")) {
        %>
        <span style="color:red;"><%=request.getAttribute("error") %></span>
        <%} %>
        </div>

        <script>
        	$(function() {
        		// jQuerymethods here
        		
        	})
        </script>
    </body>
</html>
