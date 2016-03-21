<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>FabFlix</title>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<link rel="stylesheet" href="CSS/style.css">
	</head>
 	<body>
    	<div class="multifield bordered gradient">
	      	<form name="loginForm" method="post" action="Signin.cd">
	      		<h1>FabFlix Log In</h1>
	      		<p>Email</p>
				<input type="text" name="user" placeholder="Email" autofocus required>
				<p>Password</p>
				<input type="password" name="password" placeholder="Password" required>
				<input type="submit" name="login" value="Log In">
				<a href="Register.jsp">Register Now</a>
				<a>|</a>
				<a href="index.jsp">Forgot Password?</a>
			</form>
		</div>
	</body>
</html>