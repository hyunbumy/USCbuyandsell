<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html class="no-js" lang="">
<head>
<%@ page import="buyAndSell.*"%>
  
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <meta charset="utf-8">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <title>User Register</title>
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
		#reg_form {text-align: center;}
	</style>
	
	<style>
			#outercontainer
			{
				margin-right: auto;
				margin-left: auto;
				background-color: white;
			}
	</style>
	
	<script>
        function getHeader() {
	      	$("#login_header").load("logged_out_loginpage.html");
    	}
    </script>
</head>
	<body onload="getHeader()">
	<div id="login_header">Placeholder</div>
		
		<div>
			<br><br><br/>
			<h3>Create Account</h3>
			<form id="reg_form" name="registerform" action="register_user" method="POST" class="pure-form">
				<fieldset>
		  			Your Name: <br>
		  			<input type="text" name="username" placeholder="name" required>
		  			<br>
		  			Profile Image: <br>
		  			<input type="file" name="userprofileimage" accept="image" required >
		  			<br>
		  			USC email:<br>
		  			<input type="email" name="useremail" placeholder="only @usc.edu emails" accept="@.usc.edu" required>
		  			<br>
		  			Phone Number:<br>
		  			<input type="tel" name="useremail" placeholder="phone" required>
		  			<br>
					Password:<br>
					<input type= "password" name="userpassword" id="userpassword" placeholder="Password" required >
					<br>
					Confirm Password:<br>
					<input type= "password" name="confirm_password" id="confirm_password" placeholder="Confirm Password" required>
					
					
					<script>
					
						var email
					
						var password = document.getElementById("userpassword")
					 	 , confirm_password = document.getElementById("confirm_password");
						function validatePassword() {
							if(password.value != confirm_password.value) {
							    confirm_password.setCustomValidity("Passwords Don't Match");
							  } else {
							    confirm_password.setCustomValidity('');
							  }
						}
						
						password.onchange = validatePassword;
						confirm_password.onkeyup = validatePassword;
						
					</script>
					
					<br><br>
					<input type="submit" name="submit" value= "Sign up!" onclick ="validatePassword()" class="pure-button pure-button-primary">
					<br>
				</fieldset>
			</form> 
		
		
		</div>

	</body>
</html>