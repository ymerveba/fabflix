<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>FabFlix: Failure</title>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<link rel="stylesheet" href="CSS/style.css">
		<script type="text/javascript">
		</script>
	</head>
	<jsp:include page="Header.jsp" />
	<body>
		<div class="shoppingCart bordered gradient">
			<h1>Payment Failure!</h1>
			<p>No Record Found</p>
			<form name="goHome" method="post" action="GoHome.cd">
			<input type="submit" name="update" value="Home"/>
			</form>
		</div>
	</body>
</html>