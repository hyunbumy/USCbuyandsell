<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ page import="buyAndSell.*" %>
    <%
    Category[] allCategories = Category.values();
    
    int currItemId = Integer.parseInt(request.getParameter("itemID"));
	Item currItem = StoreDatabase.getItemByID(currItemId);
    %>
        <%!
            boolean validateSession()
            {
                if (StoreDatabase.getCurrUser() != null)
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
<title><%=currItem.getName() %></title>
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
    <div id="login_header">Placeholder</div><br/><br/>
	<table style="width:100%">
		<tr>
		    <th rowspan="10">
		    		<div class= "oneitemimage" id="oneitemimage">
    					<img src=<%=currItem.getImage() %> style="width:150px;height:150px;">
    				</div>
		    </th>
		    <td>
		    		<div class= "generaliteminfo" id="generaliteminfo">
			    		<h2>
			    		<%if (currItem.getQuantity() ==0){%>
			    			<del><%= currItem.getName() %></del>
			    		<%}else { %>
			    		<%= currItem.getName() %>
			    		<%} %>
			    		</h2>
			    		<br>
			    		<h3>Price: <%= currItem.getPrice() %> <br>
			    		Quantity: <%=currItem.getQuantity() %>
					</h3>
					<br>
					<%
						String desc;
						if (currItem.getDescription() == null || currItem.getDescription().equals("")) {
							desc = "";
						}
						else
							desc = currItem.getDescription();
					%>
					<h4>Description: <%=desc %></h4>
					
					<a href="userprofile.jsp?userID=<%=currItem.getSellerID() %>">Seller Profile</a>
					<br>
					<%
						if(StoreDatabase.getCurrUser() != null && StoreDatabase.getCurrUser().getUserID() == currItem.getSellerID()){
					%>
						<form action = "editindividualitem.jsp"	>
							<button type= "submit">Edit Item</button>
						</form>
					<% }else {
							if (currItem.getQuantity() != 0) {
					%>
						<form action = "createWishlist" method="GET">
							<button type="submit" name="itemID" value="<%=currItemId%>">Contact Seller</button>
						</form>
					<% }} %>
							    <%
						if(request.getAttribute("added") != null) {
					%>
					<%=request.getAttribute("added") %>
					<%} %>
				</div>
		    </td>
		  </tr>
		</table>
</body>
</html>