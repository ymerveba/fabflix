<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
     
 <%@ page import="domain.Movies,domain.Star,java.util.*" %>

 <% 
 	List<Movies> movieList = (List<Movies>)session.getAttribute("showing");
 	if (movieList ==  null) {
 		movieList = new ArrayList<Movies>();
 	}
 	int results = ((List<Movies>)session.getAttribute("movielist")).size();
 
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>FabFlix: Search Results</title>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<link rel="stylesheet" href="CSS/style.css">
		<link href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.1/themes/base/jquery-ui.css" rel="stylesheet" type="text/css"/>
		<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.2/jquery.min.js"></script>
		<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.1/jquery-ui.min.js"></script> 
		<script type="text/javascript">
		
			function ajaxFunction(id) {
				var ajaxRequest;  // The variable that makes Ajax possible!
				
				try {
					// Opera 8.0+, Firefox, Safari
					ajaxRequest = new XMLHttpRequest();
				} catch (e) {
					// Internet Explorer Browsers
					try{
						ajaxRequest = new ActiveXObject("Msxml2.XMLHTTP");
					} catch (e) {
						try{
							ajaxRequest = new ActiveXObject("Microsoft.XMLHTTP");
						} catch (e) {
							// Something went wrong
							alert("Your browser broke!");
							return false;
						}
					}
				}
				
				// Create a function that will receive data sent from the server
				ajaxRequest.onreadystatechange = function(){
					if (ajaxRequest.readyState == 4 && ajaxRequest.status==200){
						var r = ajaxRequest.responseText;
						var s = r.split(";");
						document.getElementById("title").innerHTML = s[0];
						document.getElementById("stars").innerHTML = s[1];
						document.getElementById("year").innerHTML = s[2];
						document.getElementById("director").innerHTML = s[3];
						document.getElementById("trailer").innerHTML = "<a href=\"" + s[4] + "\">Click to see Trailer</a>";
						console.log(s);
						console.log("Here");
					}
				};
				ajaxRequest.open("GET", "MoviePopup.cd?id=" + id, true);
				ajaxRequest.send(null);
			}
		
			/**
				show popup when mouseover movie title element
			**/
			function showPopup(id) {
				
				/**
					get width of text
					SOURCE: http://stackoverflow.com/questions/1582534/calculating-text-width-with-jquery
				**/
				$.fn.textWidth = function(target){
					  var html_org = $(target).html();
					  var html_calc = '<span>' + html_org + '</span>';
					  $(target).html(html_calc);
					  var width = $(target).find('span:first').width();
					  $(target).html(html_org);
					  return width;
				};
				
				if ($("#popup").css('display') == 'none') {
					$(document).mousemove(function (event) {
							$("#popup").show();
							ajaxFunction(id);
					    	$("#popup").css(
					    		{"display": "block",
					    		 "top": $(event.target).offset().top - 35,
					    		 "left": $(event.target).offset().left + $.fn.textWidth(event.target) + 10});
					    	$(document).unbind("mousemove");
					});
				}
		    }
			
			/**
				hide the popup only if user mouseout of movie title element and
				NOT hovering over popup element
			**/
			function hidePopup() {
				if ($('#popup:hover').length == 0) {
					$("#popup").css({"display": "none"});
				}
			}
		</script>
	</head>
	
	<body><jsp:include page="Header.jsp"/>
	<div class="movieList bordered gradient">		
		<p><%= results %> Results Found!</p>
		
		<div id="popup" style="display:none; position:absolute; width:25%;
		background-color:#EEEEEE; border-style: groove;" onmouseout="hidePopup()">
		<table>
			<tr><td>Title: </td><td id="title"></td></tr>
			<tr><td>Stars: </td><td id="stars"></td></tr>
			<tr><td>Year: </td><td id="year"></td></tr>
			<tr><td>Director: </td><td id="director"></td></tr>
			<tr><td>Trailer: </td><td id="trailer"></td></tr>
		</table>
		</div>
		
		<table align="center">
			<tr>
				<td>Sort: </td>
				<td><a href="${sortByTitleASC}">Title ^</a></td>
				<td><a href="${sortByTitleDEC}">Title v</a></td>
				<td><a href="${sortByYearASC}">Year ^</a></td>
				<td><a href="${sortByYearDEC}">Year v</a></td>
				<td>|</td>
				<td>Show Per Page: </td>
				<td><a href="${show20URL}">20</a></td>
				<td><a href="${show50URL}">50</a></td>
				<td><a href="${show100URL}">100</a></td>
				<td>|</td>
				<td>Page:</td>
				<td><a href="${prevURL}">Prev</a></td>
				<td><a href="${nextURL}">Next</a></td>
			</tr>
		</table>
		
		<table width=100%>
		<%
			
			for(int i=0; i < movieList.size(); ++i)
			{
				Movies m = movieList.get(i);
		
		
		%>
		<tr>
			<td rowspan="2" align="center" width=140><img src= <%= m.getBanner_url() %> onError="this.src = 'IMAGES/dvd.png'" height="140" width="96"></td>
			<td></td>
		</tr>
		<tr>
			<td><a href="MovieDetail.cd?id=<%= m.getMovieId() %>&moviesListby=${moviesListby}&genreName=${genreName}">
				<h1 id="m<%= m.getMovieId() %>"onmouseover="showPopup(<%= m.getMovieId() %>)" onmouseout="hidePopup()" ><%= m.getTitle() %></h1>
			</a></td>
			<td></td>
		</tr>
		<tr>
			<td>Year:</td>
			<td><%= m.getYear() %></td>
		</tr>
		<tr>
			<td>Director:</td>
			<td><%= m.getDirector()%></td>
		</tr>
		<tr>
			<td>Trailer:${genreName}</td>
			<td><a href="<%= m.getTrailer_url()%>">Click here</a></td>
		</tr>
	<%-- 	<tr>
			<td>Stars:</td>
			<% if (m.getStars() != null) { %>
				<% for (Star s : m.getStars()) { %>
					<td><%= s.getName() %></td>
				<% } %>
			<% } %>
		</tr> --%>
		<tr>
			<td></td>
			<td align="right"><a href="AddToCart.cd?id=<%= m.getMovieId()%>"><img src="IMAGES/addToCart.png" height="42" width="140"></img></a></td>
		</tr>

		<% } %>
		</table>
		
		<table align="center">
			<tr>
				<td>Sort:</td>
				<td><a href="${sortByTitleASC}">Title ^</a></td>
				<td><a href="${sortByTitleDEC}">Title v</a></td>
				<td><a href="${sortByYearASC}">Year ^</a></td>
				<td><a href="${sortByYearDEC}">Year v</a></td>
				<td>|</td>
				<td>Show Per Page:</td>
				<td><a href="${show20URL}">20</a></td>
				<td><a href="${show50URL}">50</a></td>
				<td><a href="${show100URL}">100</a></td>
				<td>|</td>
				<td>Page:</td>
				<td><a href="${prevURL}">Prev</a></td>
				<td><a href="${nextURL}">Next</a></td>
			</tr>
		</table>
	</div>
	</body>
</html>