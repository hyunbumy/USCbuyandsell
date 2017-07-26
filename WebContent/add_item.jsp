<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="buyAndSell.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html class="no-js" lang="">
<head>
<%
	Category[] allCategories = Category.values();
%>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <meta charset="utf-8">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <title>Sell Item</title>
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
		h1 {
	    		text-align: center;
		}
		h2 {
		    text-align: center;
		}
		h3 {text-align: center;} 	
		#post_form {text-align: center;}
	</style>
	
	<style>
			#outercontainer
			{
				margin-right: auto;
				margin-left: auto;
				background-color: white;
			}
			fieldset {
				border-size:1px;
			}
	</style>

</head>
	<body>
		<div id="outercontainer">
			<div id="nav">

				<ul>
					<li><a href="index.jsp">Home</a></li>

					<li><a href="#">Departments</a>
						<ul>
						    <li><a href="search?category=Book&term=">Book</a></li>
					      	<li><a href="search?category=Clothing&term=">Clothing</a></li>
					      	<li><a href="search?category=Electronic&term=">Electronics</a></li>
					      	<li><a href="search?category=Movie&term=">Movie</a></li>
					      	<li><a href="search?category=Service&term=">Service</a></li>
					   	</ul>
					</li>

					<li><a href="#">Account</a>

						<ul>
							<li><a href="wishlist">Profile</a></li>
					        <li><a href="wishlist">Wishlist</a></li>
					        <li><a href="inbox.html">Inbox</a></li>
					        <li><a href="add_item.jsp">Add Item</a></li>
					    </ul>
					</li>

					<li style="float:right;"><a href="logout">Log Out</a></li>
					<div style="clear:both;"></div>

				</ul>
			</div>

		</div> 
		
		<div>
			<br><br><br/>
			<h3>Post an Item for Sale</h3>
			<form id="post_form" name="itemform" action="post_item" method="GET" class="pure-form">
				<fieldset>
		  			Item Name: <br>
		  			<input type="text" name="itemname" placeholder="Item Name" required>
		  			<br>
		  			Price: <br>
		  			<input type="number" name="price" placeholder="0.00" min="0" step="0.01" data-number-to-fixed="2" required>
		  			<br>
		  			Category: <br>
		        	<select name="category" value="<%=allCategories[0]%>">
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
		  			<br>
		  			Quantity:<br>
		  			<input type="number" name="quantity" value="0" min="0" step="1" data-number-to-fixed="0" required>
		  			<br>
		  			Image:<br>
		  			<input type="text" name="image" placeholder="Image URL" >
		  			<br>
					Description:<br>
					<textarea name="description" rows="10" cols="30"></textarea>
					
					<br><br>
					<input type="submit" name="submit" value= "Post" class="pure-button pure-button-primary">
					<br>
				</fieldset>
			</form> 
		
		
		</div>

	</body>
</html>