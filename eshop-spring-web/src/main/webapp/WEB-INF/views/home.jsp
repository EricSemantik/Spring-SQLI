<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<% String user = (String)request.getAttribute("user"); 
	
	if(user != null) { %>
		<h1>Bonjour <%=user %></h1>
	
	<% } else {  %>
	<h1>Allo le monde !</h1>
	
	<% } %>
</body>
</html>