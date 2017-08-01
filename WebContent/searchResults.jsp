<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="buyAndSell.*"%>
<%@ page import="java.util.Vector"%>
<%@ page import="java.util.HashMap"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <%

        Category[] allCategories = Category.values();
        String searchTerm = (String) request.getAttribute("searchTerm");
        String category = (String) request.getAttribute("category");
        Vector<Item> results = StoreDatabase.search(searchTerm, category);
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
        <title>Search Results for "<%=searchTerm %>"</title>
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
                #searchbar{
                    margin-left:100px;
                    height: 30px;
                }
                #search{
                    width: 700px;
                    height: 30px;
                }
                .searchinput{
                    height: 30px;
                    width: 80px;
                }
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
       
        <div id="login_header">placeholder</div>   

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
        
        <br /><br/>
        <div id="outter">
            <div id="searchbar">
                <form name="search_form" action="search" method="GET">
                        <select class="searchinput" name="category" value="all">
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
                        <input class="searchinput" id="search" type="text" name="term" value="<%=searchTerm %>">
                    <input class="searchinput" type="submit" value="SEARCH">
                </form>
            </div>
            
            <div id="main">
                <div id="result">
                    <ul>
                        <% 
                            for (int i=0; i<results.size(); i++){
                                Item item = results.elementAt(i);
                        %>
                            <li class="item_list">
                                <div class="item_container">
                                    <div class="item_img">
                                            <img src="<%= item.getImage()%>" width="200" height="200" alt="Product Image" border="3"/>
                                    </div>
                                    <div class="item_info">
                                            <div class="item_name">
                                                <a href="individualitem.jsp?itemID=<%= item.getItemID()%>">
                                                        <h2><%if (item.getQuantity() ==0){%>
			    			<del><%= item.getName() %></del>
			    		<%}else { %>
			    		<%= item.getName() %>
			    		<%} %></h2>
                                                </a>
                                        </div>
                                        <div class="seller_info">
                                            <% User seller = StoreDatabase.getUserProfileByID(item.getSellerID());%>
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
        </div>
    </body>
</html>