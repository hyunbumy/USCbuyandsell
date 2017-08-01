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
		
		<h1><%= message.getTitle()%></h1>
		
		<h5><%= message.getDate() %> </h5>
		
		<br><br><br>
		
		<h5><%=message.getMsg() %></h5>
		
		
		

</body>
</html>