<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>FabFlix: Register Now</title>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<link rel="stylesheet" href="style.css">
		<script type="text/javascript">
			var checkSamePassword = function () {
				var s = document.forms.registerForm.password.value == document.forms.registerForm.password2.value;
				if (!s) alert("Passwords are different!");
				return s;
			}
		</script>
	</head>
 	<body>
		<div class="registerForm">
	      	<form name="registerForm" method="post" action="Register.cd">
	      		<h1>Register Now!</h1>
	      		<p>Email</p>
				<input type="email" name="email" placeholder="Email" required autofocus>
				<p>Password (8-25 char)</p>
				<input type="password" name="password" placeholder="Password (8-25 char)" required pattern=".{8,25}">
				<p>Confirm Password</p>
				<input type="password" name="password2" placeholder="Confirm Password" required pattern=".{8,25}">
				<input type="submit" name="register" onclick="return checkSamePassword();" value="Create Account">
				<a href="index.jsp">Back</a>
			</form>
		</div>
	</body>
</html>