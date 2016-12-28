<%@ page import="java.io.*, java.util.*" language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	
<%
Date createDate = new Date(session.getCreationTime());
session.setAttribute("Date created", createDate);
session.setMaxInactiveInterval(2 * 60 * 60);
System.out.println("Session created on: " + session.getAttribute("Date created"));
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<link rel="stylesheet" href="http://localhost:8080/techinterview-backend/index.css" type="text/css"/>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>WebAdmin</title>
	</head>
	
	<body>
		<div id="menu">
			<ul class="topnav" id="myTopnav">	
				<li><a href="http://localhost:8080/techinterview-backend/contact.jsp">Contact</a></li>
				<li><a href="http://localhost:8080/techinterview-backend/about.jsp">About</a></li>
				<li><a href="http://localhost:8080/techinterview-backend/index.jsp">Home</a></li>
			</ul>
		</div>
		<div id="login">
			<h1>Halcyon Mobile</h1>
			<h2>Technical Interview Questionnaire results page</h2>
			<br>
			<br>
			<form method="POST" action="login.do">
				<label for="Username">User name</label><br> 
				<input type="text" name="user"><br> 
				<label for="Password">Password</label><br> 
				<input type="password" name="pass"> <br>
				<br> 
				<input type="submit" value="Log in">
			</form>
		</div>
	</body>
</html>