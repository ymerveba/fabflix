<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>FabFlix: Search Results</title>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<link rel="stylesheet" href="CSS/style.css">
		<script type="text/javascript">
		</script>
	</head>
<body><jsp:include page="Header.jsp"/>
	<div class="movieList bordered gradient">
	<table><tr><td rowspan="6" align="center"><img src="${details.banner_url}"onError="this.src = 'IMAGES/dvd.png'" /></td>
	<td>Tile:</td><td>${details.title}</td></tr>
	<tr><td>Director:</td><td>${details.director}</td></tr>
<tr><td>Stars:</td><td><table><c:forEach items="${star}" var="item"><tr><td><a href="MovieDetail.cd?act=show&id=${item.s_id}&pic_url=${item.star_photo_url}&moviesListby=Search&act=show">${item.star_First_name}&nbsp;${item.star_last_name}</a></td></tr></c:forEach></table></td></tr>
<tr><td>ReleaseYear:</td><td>${details.year}</td></tr>
<tr><td>Genre:</td><td>${details.genre_of_movie}</td></tr>
<tr><td>Trailer:</td><td><a href="${details.trailer_url}">${details.trailer_url}</a></td></tr>
<tr>
			<td width="25%"></td>
			<td colspan="2" width=75% align="right"><a href="AddToCart.cd?id=${details.movieId}"><img src="IMAGES/addToCart.png" height="42" width="140"></img></a></td>
		</tr>
	</table>
</div>

</body>
</html>