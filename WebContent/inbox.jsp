<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="buyAndSell.*"%>
<%@ page import="java.util.Vector"%>
<%@ page import="java.util.HashMap"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <%
			
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
        <title>My Inbox</title>
        <link href="https://fonts.googleapis.com/css?family=Lora" rel="stylesheet">
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
                #outter{
                    width: 1000px;
                }
                
                li{
                    list-style-type: none;
                    border:1px solid #F1F1F1;
                    padding: 5px;
                }

                #messages{
                    width: 800px;
                    margin-right: auto;
                    margin-left: auto;
                }

                h1{
                    margin-top: 80px;
                    margin-left: 80px;
                    font-family: 'Lora', serif;
                }

                #sort{
                    margin-left: 30px;
                    margin-right: 0px;
                }

                #delete{
                    width: 50px;
                    float: right;
                }
                #notread{
                		font-weight: bold;
                }
                
                #read{
                		font-weight: none;
                }
                
                .date{
                    margin-right: 5px;
                   float: right; 
                }
                
        </style>
        
        <script>
                function getHeader(valid) {
                    if (valid)
                        $("#login_header").load("logged_in.html");
                    else
                        $("#login_header").load("logged_out.html");
                }
                
                function showAll(){
                    var x = document.getElementsByClassName("Wishlist");
                    var i;
                    for (i = 0; i < x.length; i++) {
                        x[i].style.display = 'block';
                    }

                    x = document.getElementsByClassName("Rating");
                    for (i = 0; i < x.length; i++) {
                        x[i].style.display = 'block';
                    }
                    
                    hide();
                }

                function showWishlist(){

                    showAll();

                    var x = document.getElementsByClassName("Rating");
                    for (i = 0; i < x.length; i++) {
                        x[i].style.display = 'none';
                    }

                }

                function showRating(){
                    showAll();

                    var x = document.getElementsByClassName("Wishlist");
                    for (i = 0; i < x.length; i++) {
                        x[i].style.display = 'none';
                    }
                }

                function showHide(){
                    if (document.getElementById('drop_down').value == 'All'){
                        showAll();
                    }
                    else if (document.getElementById('drop_down').value == 'Wishlist'){
                        showWishlist();
                    }
                    else if (document.getElementById('drop_down').value == 'Rating'){
                        showRating();
                    }
                }
                
                function hide(){
                    var x = document.getElementsByClassName("tocheck");
                    var y = document.getElementsByClassName("list");
                    for (i = 0; i < x.length; i++) {
                        if(x[i].checked) {
                            y[i].style.display = 'none';
                        }
                    }
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
       
        <div id="login_header">placeholder</div>   

        <div id="outter">

            <h1> My Inbox </h1>
            <br /> <br />
            

            <div id="messages">
                <div id="sort">
                    Sort by 
                    <select name="messageType" id="drop_down" onchange="showHide();">
                        <option value="All" >All</option>
                        <option value="Wishlist" >Wishlist</option>
                        <option value="Rating" >Rating</option>
                    </select> 
                    <button onclick="hide();" id="delete">delete</button>
                    <!-- THIS IS ONLY HIDING CHECK MESSAGES BUT NOT ACTUALLY DELETING IT -->
                    <div style="clear:both;"></div>
                </div>
                
                <% 
                		User user = StoreDatabase.getCurrUser();
                		Vector<Message> messages = user.getMessages();
                %>
                
                <br />
                <ul>
					<% 
						for (int i=messages.size(); i>0; i--){
							Message message = messages.elementAt(i);
							boolean isread = message.isRead();
							String ifread = "notread";
							if (isread)
								ifread = "read";
							String type = null;
							if (message instanceof RatingMessage){
								type = "Rating";
							}
							else if (message instanceof WishlistMessage){
								type = "Wishlist";
							}
                            request.getSession().setAttribute("theMessage", message);
					%>
							<li class="list <%= type%>">
								<input class="tocheck" type="checkbox"> 
		                    		<a class="<%= ifread%>" 
		                    			href="message.jsp">
		                    			<%= message.getTitle()%>
		                    		</a>
		                    		<span class="date"><%= message.getDate()%></span>
                        			<div style="clear:both;"></div>
		                    	</li>
					
					<%		
						}
					%>
                    <!-- <li class="Wishlist"> <input type="checkbox"> 
                        <a href="">This is wishlist</a>
                    </li>

                    <li class="Rating"> <input type="checkbox"> 
                        <a href="">This is rating</a>
                    </li>

                    <li class="Wishlist"> <input type="checkbox"> 
                        <a href="">This is wishlist</a>
                    </li>

                    <li class="Rating"><input type="checkbox"> 
                        <a href="">This is rating</a>
                    </li> -->

                </ul>
            </div>

        </div>
        
        <br /><br/>
        
    </body>
</html>