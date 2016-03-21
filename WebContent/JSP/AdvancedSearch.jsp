<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>FabFlix: Home</title>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<link rel="stylesheet" href="CSS/style.css">	
		<script type="text/javascript">
			var atLeastOneInput = function () {
				var l = document.forms["advancedSearchForm"].getElementsByTagName("input");
				/* do not include submit button value */
				for (var i = 0; i < l.length-1; ++i) {
					if (l[i].value != "") {
						return true;
					}
				}
				alert("Must include at least 1 search term.");
				return false;
			}
		</script>
	</head>
	
 	<body>
 	<jsp:include page="Header.jsp"/>
 	
      	
      		
	<form name="advancedSearchForm" method="post" action="Search.cd?moviesListby=Advanced">
		<div class="multifield bordered gradient" align="center">
		<table>
  		<tr>
    		<td style="text-align:center;"><h3>Advanced Search</h3></td>
			
		</tr>
		<tr>
  			<td colspan="2"><div>&nbsp;</div></td>
		</tr>
		<tr>
			<td>
			<table>
      		<tr><td>Title</td><td>
      			<input type="text" name="title" placeholder="Title" autofocus></td></tr>
			<tr><td>Year</td><td>
			<input type="text" name="year" placeholder="Year"></td></tr>
			<tr><td>Director</td><td>
			<input type="text" name="director" placeholder="Director">
			</td></tr>
			<tr><td>Star's First Name</td><td>
			<input type="text" name="firstName" placeholder="Star's First Name">
			<p></p>
			<tr><td>Star's Last Name</td><td>
			<input type="text" name="lastName" placeholder="Star's Last Name"></td></tr><tr><td>
			<input type="submit" name="advancedSearch" onclick="return atLeastOneInput();" value="Advanced Search"></td></tr>
</table></td></tr>
		
 		</table>
	</div>	</form>
	</body>
</html>