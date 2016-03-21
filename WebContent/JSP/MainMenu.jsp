<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>FabFlix: Home</title>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<link rel="stylesheet" href="CSS/style.css">	
	</head>
	<body>
	<jsp:include page="Header.jsp" />
	<div class="multifield bordered gradient" align="center">
		<table>
  		<tr>
    		<td style="text-align:center;"><h3>Browse Movie by Genre</h3></td>
			<td style="text-align:center;"><h3>Browse Movie by Title</h3></td>
		</tr>
		<tr>
  			<td colspan="2"><div>&nbsp;</div></td>
		</tr>
		<tr>
			<td>
			<table>
      		<tr>
      			<td style="text-align:center;">
					<c:forEach items="${genre}" var="item">
					<a href="Search.cd?Name=${item.genreName}&id=${item.genreId}&moviesListby=genres" >${item.genreName}</a> | 
					</c:forEach>
				</td>
			</tr>
			</table>
				<td style="text-align:center;"> 
					<c:forEach items="${browseTitles}" var="item">
					<a href="Search.cd?title=${item}&moviesListby=Title" >${item}</a> | 
					</c:forEach>	
				</td>
 	 		</tr>
 		</table>
	</div>
	</body>
</html>