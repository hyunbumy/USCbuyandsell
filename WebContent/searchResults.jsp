<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="buyAndSell.*"%>
<%@ page import="java.util.Vector"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<%
		Store store = (Store) request.getSession().getAttribute("store");
		String searchTerm = (String) request.getSession().getAttribute("searchTerm");
		Vector<Item> results = store.search(searchTerm);
		%>
	
	
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Insert title here</title>
	</head>
	<body>
		<!-- DISPLAY SEARCH RESULTS -->
		<h1>Results for: <%= searchTerm %></h1>
		<h2>Num results: <%= results.size() %></h2>
		<%
			for (int i = 0; i < results.size(); i++) {
				Item item = results.get(i);
				%>
				<h3><%= item.getName() %></h3>
				<%
			}
		%>
		
	</body>
</html>