<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Header</title>
<link href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.1/themes/base/jquery-ui.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.2/jquery.min.js"></script>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.1/jquery-ui.min.js"></script>  
<script type="text/javascript">

    function SearchText() {
        $("#txtSearch").autocomplete({ 
        	 width: 300,
             max: 10,
             delay: 100,
             minLength: 1,
             autoFocus: true,
             cacheLength: 1,
             scroll: true,
             highlight: false,
            source: function(request, response) {
                $.ajax({
                	url: "SearchPredictor.cd",
                    dataType: "json",
                    data: request,
                    success: function(data, textStatus, jqXHR) {        	
                        console.log(data);
                        var items = data;
                        response(data);
                    },
                    error: function(result) {
                        alert("Error");
                    }
                });
            }
        });
        
    }
 </script>
</head>
<body>

		<div class="header">
		<a href="GoHome.cd?"><img src="IMAGES/logo.png"/></a>
	    <form action="Search.cd?moviesListby=Search" method="post" name="searchBarForm" id="general_search">        
	        <input type="text"  id="txtSearch" name="moviesList" placeholder="Search database" required maxlength="64" onclick="SearchText();"/>
	        <input type="submit" name="search" value="Search"/>
	    </form>
	    <a href="AdvancedSearch.cd"><input type="hidden" name="moviesListby" value="Advanced">Advanced Search</a> | <a href="UpdateCart.cd">My Cart</a> | <a href="LogOut.cd">Logout</a>
	</div>
</body>
</html>