<%@ page import="com.halcyonmobile.model.Position,com.halcyonmobile.rest.PositionService,java.util.List"  language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<link rel="stylesheet" href="success.css">
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Where to now</title>
	</head>

	<body>
		<div id="menu">
			<ul class="topnav" id="myTopnav">	
				<li><a href="logout.do">Log out</a></li>
				<li><a href="http://halcyonmobile.com/contact">Contact</a></li>
				<li><a href="about.jsp">About</a></li>
				<li><a href="login.do">Home</a></li>
			</ul>
		</div>
	
	<%
		String user = "";
		String privilege = "";

		if ((Boolean) session.getAttribute("ok") != null) {			
			if ((Boolean) session.getAttribute("ok")) {
				user = (String) session.getAttribute("user");
				privilege = (String) session.getAttribute("privilege");
	%>	
		<div id="main">
			<p id="login">Login successful</p><br><br>
			<p id="credentials">Welcome back </p>
			<p id="user"><%= user %></p><br>
			<p id="privilege">You are:</p>
	<% 			
				if(privilege.equals("admin")) { 
	%> 
			<em id="admin"><%= privilege%></em>
	<%
				} else {
	%>
			<em id="manager"><%= privilege%></em>
	<% 
				}
	%>
			<br><br>
	
	<%
				if(privilege.equals("admin")) {
	%>
			<br>
			<div id="form-buttons">
				<form class="buttons" method="GET" action="results.do">
					<input type="submit" value="Results">
				</form>
	
				<form class="buttons" method="GET" action="manage.do">
					<input type="submit" value="Manage site">
				</form>
				
	<%
				} else {
				
				PositionService positionService = new PositionService();
				List<Position> positions = positionService.findAll();
	%>
	
				<form class="buttons" method="GET" action="results.do">
				<select name="position">
	<%
				for(int i=0; i<positions.size(); i++) {
	%>
				<option value="<%= positions.get(i).getId() %>"><%= positions.get(i).getName() %></option>
	<%
				}
	%>
				</select>
				<input type="submit" value="Show results">
				</form>
			</div>
	<%
				}
			} else {
	%>
			<em>Login failed</em>			
	<%
			}
		} else {
	%>
			<em>Error</em>	
	<%
		}
	%>
		</div>
	</body>
</html>