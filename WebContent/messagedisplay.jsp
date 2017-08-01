<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="buyAndSell.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	
	<script type="text/javascript">
	
		$(document).ready(function(){
		    $("button").click(function(){
		        $("#rated").load("editprofile.html #userrated");

		    });
		});
	
	
	</script>

	
	<style>
		@import url(//netdna.bootstrapcdn.com/font-awesome/3.2.1/css/font-awesome.css);
		fieldset, label { margin: 0; padding: 0; }
		body{ margin: 20px; }
		h1 { font-size: 1.5em; margin: 10px; }
		
		/****** Style Star Rating Widget *****/
		
		.rating { 
		  border: none;
		  float: left;
		}
		
		.rating > input { display: none; } 
		.rating > label:before { 
		  margin: 5px;
		  font-size: 1.25em;
		  font-family: FontAwesome;
		  display: inline-block;
		  content: "\f005";
		}
		
		.rating > .half:before { 
		  content: "\f089";
		  position: absolute;
		}
		
		.rating > label { 
		  color: #ddd; 
		 float: right; 
		}
		
		/***** CSS Magic to Highlight Stars on Hover *****/
		
		.rating > input:checked ~ label, /* show gold star when clicked */
		.rating:not(:checked) > label:hover, /* hover current star */
		.rating:not(:checked) > label:hover ~ label { color: #FFD700;  } /* hover previous stars in list */
		
		.rating > input:checked + label:hover, /* hover current star when changing rating */
		.rating > input:checked ~ label:hover,
		.rating > label:hover ~ input:checked ~ label, /* lighten current selection */
		.rating > input:checked ~ label:hover ~ label { color: #FFED85;  } 
	</style>
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
		<%} else if (message instanceof RatingMessage){ %>
			
			<form>
				<fieldset class="rating">
				    <input type="radio" id="star5" name="rating" value="5" /><label class = "full" for="star5" title="Awesome - 5 stars"></label>
				
				    <input type="radio" id="star4" name="rating" value="4" /><label class = "full" for="star4" title="Pretty good - 4 stars"></label>
				    
				    <input type="radio" id="star3" name="rating" value="3" /><label class = "full" for="star3" title="Meh - 3 stars"></label>
				   
				    <input type="radio" id="star2" name="rating" value="2" /><label class = "full" for="star2" title="Kinda bad - 2 stars"></label>

				    <input type="radio" id="star1" name="rating" value="1" /><label class = "full" for="star1" title="Sucks big time - 1 star"></label>
				   
				</fieldset>
				<button type= "submit" onclick="whichChecked()" > Submit Rating!</button>
			</form>
			
			<script>
				function whichChecked(){
					if(document.getElementById("star5").checked) {
						<%user.setRating(5);%>
					}else if(document.getElementById("star4").checked) {
						<%user.setRating(4);%>
					}else if(document.getElementById("star3").checked){
						<%user.setRating(3);%>
					} else if(document.getElementById("star2").checked){
						<%user.setRating(2);%>
					} else if(document.getElementById("star1").checked){
						<%user.setRating(1);%>
					}	
				}	
			
			</script>
			<br>
			<div id="rated">
			
			</div>
		
		<%} %>
		

</body>
</html>