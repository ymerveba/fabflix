<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>FabFlix: Search Results</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="CSS/style.css">
<script type="text/javascript">
	
</script>
</head>
<body><jsp:include page="Header.jsp" />
	<div class="movieList bordered gradient">
		<h1>Star Details</h1>
		<table>
			<tr>
				<td>StarID:</td>
				<td>${star.id}</td>
			</tr>
			<tr>
				<td>First Name:</td>
				<td>${star.firstName}</td>
			</tr>
			<tr>
				<td>LastName:</td>
				<td>${star.lastName}</td>
			</tr>
			<tr>
				<td>Date Of Birth:</td>
				<td>${star.dob}</td>
			</tr>
			<tr>
				<td>LastName:</td>
				<td><a href="${star.photo_url}">${star.photo_url}</a></td>
			</tr>
				<tr><td>Movies:</td><td><table>
						<c:forEach items="${star.movies}" var="item">
							<tr>
								<td><a href="MovieDetail.cd?id=${item.movieId}">${item.title}</a></td>
							</tr>
						</c:forEach>
					</table></td>
			</tr>
	
		</table>

	</div>

</body>
</html>