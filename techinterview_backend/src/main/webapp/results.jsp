<%@ page import="com.halcyonmobile.model.User,com.halcyonmobile.model.Result,com.halcyonmobile.rest.UserService,com.halcyonmobile.rest.ResultService,java.util.List"  language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<link rel="stylesheet" href="success.css">
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<script type="text/javascript" src="javascript/canvasjs.min.js"></script>
		<title>Results</title>
	</head>

	<body>
		<div id="menu">
			<ul class="topnav" id="myTopnav">	
				<li><a href="http://localhost:8080/techinterview-backend/logout.do">Log out</a></li>
				<li><a href="http://localhost:8080/techinterview-backend/contact.jsp">Contact</a></li>
				<li><a href="http://localhost:8080/techinterview-backend/about.jsp">About</a></li>
				<li><a href="http://localhost:8080/techinterview-backend/index.jsp">Home</a></li>
			</ul>
		</div>
		<div id="main">
	<%
			Integer id = Integer.parseInt(request.getParameterValues("position")[0]);
			UserService userService = new UserService();
			List<User> users = userService.findByPosition(id);
	 %>
	 
	 	<table class="list">
	 		<tr>
	 			<th>#</th>
	 			<th>Name</th>
	 			<th>Date</th>
	 			<th>Duration</th>
	 		</tr>
	 		
	 <%
	 		for(int i=0; i < users.size(); i++) {
	 			ResultService rs = new ResultService();
	 			List<Result> res = rs.findByUserId(users.get(i).getId());
	 			int duration = 0;
	 			String date = "";
	 			for(int j=0; j < res.size(); j++) {
	 				duration += res.get(i).getThinkingTime();
	 				date = res.get(i).getDate();
	 			}
	 			
	 			int minutes = 0, seconds = 0;
	 			minutes = duration / 60;
	 			seconds = duration % 60;
	 %>
		 		<tr onclick="document.location='#popup<%= users.get(i).getId() %>';return false;">
		 			<td><%= i+1 %></td>
		 			<td><%= users.get(i).getName() %></td>
		 			<td><%= date %></td>
		 			<td><%= minutes + " m " + seconds + " s" %></td>
		 		</tr>
	 <%
	 		}
	 %>
	 
	 
	 		
	 	</table>
	 	<%
	 		for(int i=0; i < users.size(); i++) {
	 			ResultService rs = new ResultService();
	 			List<Result> res = rs.findByUserId(users.get(i).getId());
	 	%>
	 		<div id="popup<%= users.get(i).getId() %>" class="overlay">
				<div class="popup">
					<h2><%= users.get(i).getName() %></h2>
					<a class="close" href="#">&times;</a>
					<div class="content">
					<table width="80%">
						<tr>
							<th>#</th>
							<th>Question</th>
							<th>Answer</th>
						</tr>
						<%
						for(int j=0; j<res.size(); j++) {
							String correct = "correct";
							if(!res.get(j).getPertinence()) correct = "wrong";
						%>
						<tr class="<%= correct %>">
							<td><%=j+1 %></td>
							<td><%=res.get(j).getQuestion() %></td>
							<td><%=res.get(j).getAnswer() %></td>
						</tr>
						<%
						}
						 %>
					</table>
						<script type="text/javascript">
							window.onload = function () {
								var chart = new CanvasJS.Chart("chartContainer",
								{
									animationEnabled: true,
									title:{
										text: "Elapsed time to answer."
									},
									data: [
									{
										type: "column", //change type to bar, line, area, pie, etc
										dataPoints: [
										<%
										for(int j=0; j<res.size(); j++) {
											if(j!=res.size()-1) {
										%>
											{ label: "#<%=j+1%>", y: <%= res.get(j).getThinkingTime() %> },
										<%
										} else {
										%>
											{ label: "#<%=j+1%>",  y: <%= res.get(j).getThinkingTime() %> }
										<%
										}
										}
										%>
										]
									}
									]
									});
							
								chart.render();
							}
						</script>
						<div id="chartContainer" style="height: 300px; width: 100%;"></div>
					</div>
				</div>
			</div>
		<%	
			}
		 %>
	 	</div>
	</body>
</html>