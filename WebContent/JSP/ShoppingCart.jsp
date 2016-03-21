<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
     
     <%@ page import="domain.ShoppingCart" %>
     
     <% 
	 	ShoppingCart shoppingCart = (ShoppingCart)session.getAttribute("shoppingCart");
	 	if (shoppingCart ==  null){
	 		shoppingCart = new ShoppingCart();
	 	}
	 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>FabFlix: Shopping Cart</title>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<link rel="stylesheet" href="CSS/style.css">
		<script type="text/javascript">
		</script>
	</head>
	<jsp:include page="Header.jsp" />
	<body>
		<div class="shoppingCart bordered gradient">
			<form name="updateForm" method="post" action="UpdateCart.cd?update=true">
			<table>
				<c:forEach items="${shoppingCart}" var="item">
				<tr>
					<td width=90%><h1>${item.key.title}</h1><td>
					<td>Quantity<input type="number" name="z${item.key.movieId}" value="${item.value}" min=0><br><td>
					<td><a href="UpdateCart.cd?id=${item.key.movieId}&remove=true">Remove</a><td>
				</tr>
				</c:forEach>
			</table>
			<h1 align="right">Subtotal = <%= shoppingCart.quantity %></h1>
			<input type="submit" name="update" value="Update"/>
			</form>
			<% if (shoppingCart.quantity > 0) { %>
			<form name="updateForm" method="post" action="CheckOut.cd">
			<input type="submit" name="update" value="Checkout"/>
			</form>
			<% } %>
		</div>
	</body>
</html>