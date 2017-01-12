
	

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<link rel="stylesheet" href="index.css" type="text/css"/>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>WebAdmin</title>
	</head>
	
	<body>
		<div id="menu">
			<ul class="topnav" id="myTopnav">	
				<li><a href="http://halcyonmobile.com/contact">Contact</a></li>
				<li><a href="about.jsp">About</a></li>
				<li><a href="index.jsp">Home</a></li>
			</ul>
		</div>
		<div id="login">
			<h2 class="whiteText">Halcyon Mobile</h2>
			<image src="images/rocket.png" alt="rocket.png" id="rocket">
			<h3 class="whiteText">Technical Interview Questionnaire results page</h3>
			<br>
			<br>
			<form method="POST" action="login.do">
				<label class="whiteText" for="Username">User name</label><br> 
				<input type="text" name="user"><br> 
				<label class="whiteText" for="Password">Password</label><br> 
				<input type="password" name="pass"> <br>
				<br> 
				<input id="button" type="submit" value="Log in">
			</form>
		</div>
	</body>
</html>