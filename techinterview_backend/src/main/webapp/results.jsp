	<%@ page
		import="com.halcyonmobile.model.User,com.halcyonmobile.model.Result,com.halcyonmobile.model.Position,com.halcyonmobile.rest.PositionService,com.halcyonmobile.rest.UserService,com.halcyonmobile.rest.ResultService,java.util.List"
		language="java" contentType="text/html; charset=UTF-8"
		pageEncoding="UTF-8"%>
	<%
		if (!((Boolean) session.getAttribute("ok") != null && (Boolean) session.getAttribute("ok")))
			response.sendRedirect("index.jsp");
	%>
	<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
	<html>
	<head>
	<link rel="stylesheet" href="styles/style.css" type="text/css" />
	<script type="text/javascript" src="manage.js"></script>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<script type="text/javascript" src="javascript/canvasjs.min.js"></script>
	<title>Admin</title>
	</head>
	
	<body>
	
		<div id="manager">
			<div id="tabs">
				<ul class="tab">
					<%
						PositionService ps = new PositionService();
						List<Position> positions = ps.findAll();
						for (int i = 0; i < positions.size(); i++) {
					%>
					<li><a
						href="results.jsp?position=<%=positions.get(i).getId()%>"
						class="tablinks"><%=positions.get(i).getName()%></a></li>
					<%
						}
					%>
					<li><a href="logout.do" class="tablinks">Logout</a></li>
				</ul>
			</div>
			<div class="tabcont" style="display: block">
				<%
					Integer nrPerPage = 25;
					Integer id;
					try {
						id = Integer.parseInt(request.getParameterValues("position")[0]);
					} catch (Exception e) {
						id = -1;
					}
					Integer p;
					try {
						p = Integer.parseInt(request.getParameterValues("p")[0]);
					} catch (Exception e) {
						p = 1;
					}
	
					String find;
					try {
						find = request.getParameterValues("find")[0].toString();
					} catch (Exception e) {
						find = "";
					}
	
					if (id == -1) {
				%>
				<h1>Please select a position</h1>
				<form class="buttons" method="GET" action="results.do">
	
					<table align="center" border="0">
						<tr>
							<td><select name="position">
									<%
										for (int i = 0; i < positions.size(); i++) {
									%>
									<option value="<%=positions.get(i).getId()%>"><%=positions.get(i).getName()%></option>
									<%
										}
									%>
							</select> <br>
								<button type="submit">SHOW RESULTS</button></td>
						</tr>
					</table>
				</form>
				<%
					} else {
						UserService userService = new UserService();
						List<User> users = userService.findByPositionAndByKeyword(id, find);
				%>
				<h1><%=ps.findById(id).getName()%></h1>
				<form action="" method="GET">
					<table align="center" border="0">
						<tr>
							<td><input type="text" placeholder="Find by name" value="<%= find %>" name="find">
								<br> <input type="hidden" name="position" value="<%=id%>">
								<input type="hidden" name="p" value="<%=p%>">
								<button type="submit">FIND</button></td>
						</tr>
					</table>
				</form>
				<table class="list">
					<tr>
						<th>ID</th>
						<th>Name</th>
						<th>Date</th>
						<th>Duration</th>
					</tr>
	
					<%
						//for (int i = 0; i < users.size(); i++) {
							Integer limit = users.size();
							if (limit > p * nrPerPage)
								limit = p * nrPerPage;
							for (int i = (p - 1) * nrPerPage; i < limit; i++) {
								ResultService rs = new ResultService();
								List<Result> res = rs.findByUserId(users.get(i).getId());
								int duration = 0;
								String date = "";
								for (int j = 0; j < res.size(); j++) {
									duration += res.get(j).getThinkingTime();
									date = res.get(j).getDate();
								}
	
								int minutes = 0, seconds = 0;
								minutes = duration / 60;
								seconds = duration % 60;
					%>
					<tr
						onclick="document.location='#popup<%=users.get(i).getId()%>';return false;">
						<td><%=users.get(i).getId()%></td>
						<td><%=users.get(i).getName()%></td>
						<td><%=date%></td>
						<td><%=minutes + " m " + seconds + " s"%></td>
					</tr>
					<%
						}
					%>
	
	
				</table>
				<%
					for (int i = (p - 1) * nrPerPage; i < limit; i++) {
							ResultService rs = new ResultService();
							List<Result> res = rs.findByUserId(users.get(i).getId());
				%>
				<div id="popup<%=users.get(i).getId()%>" class="overlay">
					<div class="popup">
						<h2><%=users.get(i).getName()%>
						</h2>
						<a class="close" href="#">&times;</a>
	
						<div class="content">
							<table>
								<thead>
									<tr>
										<th>#</th>
										<th>Question</th>
										<th>Answer</th>
									</tr>
								</thead>
								<tbody>
									<%
										for (int j = 0; j < res.size(); j++) {
													String correct = "nincsvalasz";
													if (res.get(j).getIsCorrect() != null)
														if (res.get(j).getIsCorrect())
															correct = "correct";
														else
															correct = "wrong";
									%>
									<tr class="<%=correct%>">
										<td><%=j + 1%></td>
										<td><%=res.get(j).getQuestion()%></td>
										<td><%=res.get(j).getAnswer()%></td>
									</tr>
									<%
										}
									%>
								</tbody>
							</table>
							<div id="chartContainer<%=users.get(i).getId()%>"
								style="height: 300px; width: 100%;"></div>
						</div>
					</div>
				</div>
				<%
					}
				%>
				<script type="text/javascript">
	        window.onload = function () {
	            <%for (int i = (p - 1) * nrPerPage; i < limit; i++) {
						ResultService rs = new ResultService();
						List<Result> res = rs.findByUserId(users.get(i).getId());%>
	            var chart<%=users.get(i).getId()%> = new CanvasJS.Chart("chartContainer<%=users.get(i).getId()%>",
	                    {
	                        animationEnabled: true,
	                        title: {
	                            text: "Elapsed time to answer."
	                        },
	                        data: [
	                            {
	                                type: "column", //change type to bar, line, area, pie, etc
	                                dataPoints: [
	                                    <%for (int j = 0; j < res.size(); j++) {
							if (j != res.size() - 1) {%>
	                                    {label: "#<%=j + 1%>", y: <%=res.get(j).getThinkingTime()%>},
	                                    <%} else {%>
	                                    {label: "#<%=j + 1%>", y: <%=res.get(j).getThinkingTime()%>}
	                                    <%}
						}%>
	                                ]
	                            }]
	                    });
	            chart<%=users.get(i).getId()%>.render();
	            <%}%>
	        }
	    </script>
				<ul class="pagination">
					<%
						Integer nrOfPage = users.size() / nrPerPage;
							if (users.size() % nrPerPage != 0)
								nrOfPage++;
							for (int i = 1; i <= nrOfPage; i++) {
					%>
					<li <% if (i == p) { %> class="active"<% } %>>
						<a href="?position=<%=id%>&p=<%=i%>&find=<%=find%>"><%=i%></a>
					</li>
					<%
						}
					%>
				</ul>
				<%
					}
				%>
			</div>
		</div>
	</body>
	</html>