<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@page import="buyAndSell.*" %>
	<head>
		    <%
		    StoreDatabase store;
		    if (session.getAttribute("store") == null) {
		    	store = new StoreDatabase();
		    	session.setAttribute("store", store);
		    }
		    else
		    	store = (StoreDatabase) session.getAttribute("store");
			
		    Category[] allCategories = Category.values();
		    String temp = request.getParameter("userID");
		    int currId;
		    if (temp == null || temp.equals(""))
		    	currId = store.getCurrUser().getUserID();
		    else
	    		currId = Integer.parseInt(temp);
	    	User selectedUser = store.getUserProfileByID(currId);
    	%>
	        <%!
            boolean validateSession(StoreDatabase store)
            {
                if (store.getCurrUser() != null)
                    return true;
                else
                    return false;
            }
        %>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <meta charset="utf-8">
        <meta http-equiv="x-ua-compatible" content="ie=edge">
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
		<title>User Profile</title>
		<style>
		</style>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
		<script>
			$(document).ready(function(){
			    $("button").click(function(){
			        $("#profileinfo").load("edituserprofile.html #loadnewprofileinfo");
			        $("#profileimage").load("edituserprofile.html #loadnewprofileimage");
			        $("#profileimage").load("edituserprofile.html #loadsaveprofile");
			    });
			});
            function getHeader(valid) {
                if (valid)
                    $("#login_header").load("logged_in.html");
                else
                    $("#login_header").load("logged_out.html");
            }
		</script>
	</head>
    <%if (validateSession(store)) {%>
    <body onload="getHeader(true)">
    <%}
    else {
    %>
    <body onload="getHeader(false)">
    <%} %>
    <div id="login_header">Placeholder</div><br/><br/>
	
		
		
		<br><br><br><br><br><br>

		<table style="width:100%">

		  <tr>
		    <th rowspan="10">
		    		<div class= "profileimage" id="profileimage">
    					<img src="<%=selectedUser.getImage() %>"style="width:128px;height:128px;">
    				</div>
		    </th>
		    <td>
		    		<div class= "profileinfo" id="profileinfo">
			    		Name: <%=selectedUser.getfName() %> <% selectedUser.getlName();%>
			    		<br>
			    		<%
					int roundedRating = (int) Math.round(selectedUser.getRating());
					
					%>
					<% for(int i = 0; i < roundedRating; i++){ %>
							<span>★</span>
					<%	}
					%>
					<% for(int i = 0; i < (5-roundedRating); i++){ %>
							<span>☆</span>
					<% } %>
					<br>
					Phone: <%= " " + selectedUser.getPhoneNumber() + " " %>
					<br>
			    	Email: <%= " " + selectedUser.getEmail() %>
			    	<br>
				</div>
		    </td>
		    					<%
						if(store.getCurrUser() != null && selectedUser.getUserID() == store.getCurrUser().getUserID()){
					%>
					<td>
			    		<form action = "wishlist.jsp" >
						<button type="submit">Your Wishlist</button><br>		
					</form>
					<br>
							    		<div class="editprofilebutton" id="editprofilebutton">
					<button>Edit Profile</button><br>
				</div>
		    </td>
					<% } %>
		  </tr>
		</table>
	</body>
</html>