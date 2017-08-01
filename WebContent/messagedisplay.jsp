<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="buyAndSell.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

		<% 
        		User user = StoreDatabase.getCurrUser();
        %>
		<% 
			Message message = (Message) session.getAttribute("theMessage"); 
			//set message as read once opened	
			message.setRead(true);
			//display title, message, and date
		
		
		%>
		
		<b><h1><%= message.getTitle()%></h1></b>
		<br>
		
	
		<h5><%= message.getDate() %> at <%= message.getTime() %> </h5> 
		
		<br><br><br>
		
		<h5><%=message.getMsg() %></h5>
		<%
			if (message instanceof WishlistMessage) {
		%>
		<form action="markassold" method="GET">
		  <button type="submit" name="itemID" value="<%=message.getItem().getItemID() %>">
		  Sell Item to this user!
		  </button>
		</form>
		<%if (request.getAttribute("marked") != null) { %>
		<%=request.getAttribute("marked") %>
		<%} %>
		<%} %>
		

</body>
</html>