<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="buyAndSell.*"%>
<%@ page import="java.util.Vector"%>
<%@ page import="java.util.HashMap"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<%
		
		/* StoreDatabase store;
		if (session.getAttribute("store") == null) {
            store = new StoreDatabase();
            session.setAttribute("store", store);
        }
        else
            store = (StoreDatabase) session.getAttribute("store"); */
            
		/* 
		String userID = request.getParameter("userID"); */
		
		User user = StoreDatabase.getCurrUser();
		Vector<Item> wishlist = user.getWishlist(); 
		
		// HashMap<String,User> userMap = store.getUserMap();
		// User user = userMap.get(userID);
		// Vector<Item> wishlist = user.getWishlist(); 
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
		
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>My Wishlist</title>
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
        		
        		#main{
        		    margin-top: 50px;
        		    margin-left: 100px;
        		}
        		li{
        		    list-style-type: none;
        		}
        		.item_container{
        		    margin: 10px;
        		    padding: 10px;
        		}
        		.item_img{
        		    width: 210px;
        		    float: left;
        		}
        		.item_info{
        		    margin-left: 5px;
        		    float: left;
        		}
        		.info{
        		    font-size: 10pt;
        		}
        		
        		#result{
        			margin: 10px;
        			margin-bottom: 30px;
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
	
	<%if (validateSession()) {%>
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

		<%-- <!-- DISPLAY SEARCH RESULTS -->
		<h1>Results for: <%= searchTerm %></h1>
		<h2>Num results: <%= results.size() %></h2>
		<%
			for (int i = 0; i < results.size(); i++) {
				Item item = results.get(i);
				%>
				<h3><%= item.getName() %></h3>
				<%
			}
		%> --%>
		
		<br />
               	
       	<div id="main">
       		<div id="result">
       			<ul>
       				<% 
       					for (int i=0; i<wishlist.size(); i++){
       						Item item = wishlist.elementAt(i);
       				%>
       					<li class="item_list">
							<div class="item_container">
								<div class="item_img">
						        		<img src="<%= item.getImage()%>" width="200" height="200" alt="Product Image" />
						        </div>
						        <div class="item_info">
								    	<div class="item_name">
							            	<a href="individualitem.jsp?itemID=<%= item.getItemID()%>">
							                		<h2><%= item.getName()%></h2>
							        		</a>
							        </div>
								    <div class="seller_info">
								        <% 
								        		int sellerid = item.getSellerID();
								        		User seller = StoreDatabase.getUserProfileByID(sellerid);
								        %>
								        	<a href="userprofile.jsp?userID=<%= item.getSellerID()%>">BY <%= seller.getfName()%> <%= seller.getlName()%></a>
								    </div>
								    <div class="info">
								       	<sup>$</sup>
							        		<span><%= item.getPrice()%></span> <br />
										<span style="color: red;"><%= item.getQuantity()%> left</span>
									</div>
								</div>
								<div style="clear:both;"></div>
							</div>
						</li>
       				<%
       					}
       				%>
        			</ul>
        		</div>
		</div>
	</body>
</html>