<%@ page import="java.io.*, java.util.*" language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
if ((Boolean) session.getAttribute("ok") != null && (Boolean) session.getAttribute("ok")){
	if(session.getAttribute("privilege").equals("admin")) response.sendRedirect("manage.jsp");
	else response.sendRedirect("results.jsp");
}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<link rel="stylesheet" href="styles/style.css" type="text/css"/>
		<script src="javascript/vaidator.js"></script>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="shortcut icon" href="favicon.ico" />
		<title>WebAdmin - Login</title>
	</head>
	
	<body>
		<div id="login">
			<h1>Login to WebAdmin</h1>
			<form autocomplete="off" class="login-form" method="POST" action="login.do">
				<div class="input">
					<div class="personIcon"></div>
					<input type="text" name="userName" style="display: none"/>
					<input onblur="checkUserName(true)" onkeyup="checkUserName(true)" id="userName" type="text" name="userName" placeholder="Username"/>
					<p id="usrMSG" class="wrongUsername">Bad username</p>
				</div>
				<div class="input">
					<div class="passwordIcon"></div>
					<input type="password" name="password" style="display: none"/>
					<input onblur="checkPassword(true)" onkeyup="checkPassword(true)" id="passw" type="password" name="password" placeholder="Password"/>
					<p id="pasMSG" class="wrongPassword">Bad password</p>
				</div>
				<div class="loginButton"><button id="done" disabled type="submit">DONE</button></div>
			</form>
		</div>
	</body>
</html>