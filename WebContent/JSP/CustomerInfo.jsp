<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>FabFlix</title>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<link rel="stylesheet" href="CSS/style.css">
		<script type="text/javascript">
		</script>
	</head>
	
 	<body><jsp:include page="Header.jsp"/>
		<div class="multifield bordered gradient">
	      	<form name="customerInfoForm" method="post" action="ValidateUserInfo.cd">
	      		<h1>Enter Payment Information</h1>
				<table><tr><td>First Name *</td></tr><tr><td>
				<input type="text" name="firstName" placeholder="First Name" required autofocus></td></tr>
				<tr><td>Last Name *</td></tr><tr><td>
				<input type="text" name="lastName" placeholder="Last Name" required></td></tr>
				<tr><td>Credit Card *</td></tr><tr><td>
				<input type="text" name="creditCard" placeholder="Credit Card" required></td></tr>
				<tr><td>Expiration (MM/DD/YYYY) *</td></tr><tr><td>
				<input type="date" name="expiration" placeholder="Expiration" required></td></tr>
				<tr><td>* required</td></tr><tr><td>
				<input type="submit" name="customerInfo" onclick="return true;" value="Submit Info"></td></tr>
			</table></form>
		</div>
	</body>
</html>