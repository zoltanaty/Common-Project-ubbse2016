<%@ page 
	import="com.halcyonmobile.rest.OwnersService, com.halcyonmobile.model.Owners, com.halcyonmobile.rest.UserService, com.halcyonmobile.model.User, com.halcyonmobile.rest.PositionService, com.halcyonmobile.model.Position, com.halcyonmobile.rest.QuestionTypeService, com.halcyonmobile.model.QuestionType, com.halcyonmobile.rest.QuestionService, com.halcyonmobile.model.Question, java.util.List"
	language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<link rel="stylesheet" href="manage.css">
		<script type="text/javascript" src="manage.js"></script>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Admin</title>
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
	
		<div id="manage">
			<div id="tabs">
				<ul class="tab">
					<li><a href="javascipt:void(0)" class="tablinks" onclick="disp(event, 'addAM')">Add Admin/Manager</a></li>
					<li><a href="javascipt:void(0)" class="tablinks" onclick="disp(event, 'delAM')">Delete Admin/Manager</a></li>
					<li><a href="javascipt:void(0)" class="tablinks" onclick="disp(event, 'delInt')">Delete Interviewee</a></li>
					<li><a href="javascipt:void(0)" class="tablinks" onclick="disp(event, 'addQue')">Add Question</a></li>
					<li><a href="javascipt:void(0)" class="tablinks" onclick="disp(event, 'delQue')">Delete Question</a></li>
				</ul>
			</div>
		
			<div id="mforms">
				<div id="addAM" class="tabcont" style="display: block;">
					<h1 class="whiteText">Add admin/manager</h1>
					<form method="POST" action="addAM.do">
						<label class="whiteText" for="Username">Username</label>
						<input type="text" name="user">
						<br>
						<label class="whiteText" for="Password">Password</label>
						<input type="text" name="pass">
						<br>
						<input class="whiteText" checked type="radio" value="Admin" name="radio">Admin
						<br>
						<input class="whiteText" type="radio" value="Manager" name="radio">Manager
						<br>
						<input class="button" type="submit" value="Add">
					</form>
				</div>
				
				<div id="delAM" class="tabcont">
					<h1 class="whiteText">Delete admin/manager</h1>
					<form method="POST" action="deleteAM.do" name="delAM">
						<label class="whiteText" for="Username">Username</label>
						<select name="AMList">
						<%
							OwnersService os = new OwnersService();
							List<Owners> ownersList = os.findAll();
								
							for(int i = 0; i < ownersList.size(); i++) {
						%>
							<option value="<%=ownersList.get(i).getUsername() %>"><%=ownersList.get(i).getUsername()%></option>
							
						<%
							}
						%>
						</select>
						<input class="button" type="submit" value="Delete">
					</form>
				</div>
				
				<div id="delInt" class="tabcont">
					<h1 class="whiteText">Delete interviewee</h1>
					<form method="POST" action="deleteInt.do" name="delInt">
						<label class="whiteText" for="Username">Username</label>
						<select name="IntList">
						<%
							UserService us = new UserService();
							List<User> usersList = us.findAll();
								
							for(int i = 0; i < usersList.size(); i++) {
						%>
							<option value="<%=usersList.get(i).getName() %>"><%=usersList.get(i).getName() %></option>
						<%
							}
						%>
						</select>	
						<input class="button" type="submit" value="Delete">
					</form>
				</div>
			
				<div id="addQue" class="tabcont">
					<h1 class="whiteText">Add question</h1>
					<form method="POST" action="addQue.do" name="addQue">
						<label class="whiteText" for="Positions">Positions</label>
						<select name="posList">
						<%
							PositionService ps = new PositionService();
							List<Position> positionList = ps.findAll();
								
							for(int i = 0; i < positionList.size(); i++) {
						%>
							<option value="<%=positionList.get(i).getName()%>"><%=positionList.get(i).getName() %></option>
						<%
							}
						%>
						</select>
						<br>
						<label class="whiteText" for="QuestionType">Question Type</label>
						<select onchange="switchQuestionType()" id="queType" name="queType">
						<%
							QuestionTypeService qts = new QuestionTypeService();
							List<QuestionType> queTypeList = qts.findAll();
								
							for(int i = 0; i < queTypeList.size(); i++) {
						%>
							<option value="<%=queTypeList.get(i).getName() %>"><%=queTypeList.get(i).getName()%></option>
						<%
							}
						%>
						</select>
						<br>
						<label class="whiteText" for="Question">Question</label>
						<input type="text" name="Question"><br><br>
						<label class="whiteText" for="Answer1">Answer1</label>
						<input id="answer1" type="text" name="Answer"><input id="hidden1" type="hidden" value="false" name="Correct"><input onclick="checkboxValidator()" id="checkbox1" type="checkbox" value="true" name="Correct"><br>
						<label class="whiteText" for="Answer1">Answer2</label>
						<input id="answer2" type="text" name="Answer"><input id="hidden2" type="hidden" value="false" name="Correct"><input onclick="checkboxValidator()" id="checkbox2" type="checkbox" value="true" name="Correct"><br>
						<label class="whiteText" for="Answer1">Answer3</label>
						<input id="answer3" type="text" name="Answer"><input id="hidden3" type="hidden" value="false" name="Correct"><input onclick="checkboxValidator()" id="checkbox3" type="checkbox" value="true" name="Correct"><br>
						<label class="whiteText" for="Answer1">Answer4</label>
						<input id="answer4" type="text" name="Answer"><input id="hidden4" type="hidden" value="false" name="Correct"><input onclick="checkboxValidator()" id="checkbox4" type="checkbox" value="true" name="Correct"><br><br>
						<input class="button" type="submit" value="Add">
					</form>
				</div>
				
				<div id="delQue" class="tabcont">
					<h1 class="whiteText">Delete existing question</h1>
					<form method="POST" action="delQue.do" name="delQue">
						<label class="whiteText" for="Questions">Questions</label><br>
						<select name="que">
						<%
							QuestionService qs = new QuestionService();
							List<Question> questionList = qs.findAll();
								
							for(int i = 0; i < questionList.size(); i++) {
						%>
							<option value="<%=questionList.get(i).getQuestion() %>"><%=questionList.get(i).getQuestion() %></option>
						<%
							}
						%>
						</select>
						<br><br>
						<input class="button" type="submit" value="Delete">
					</form>
				</div>
				
				<script>
					function disp(e, dispName) {
						var i, tabcont, tablinks;
						
						tabcont = document.getElementsByClassName("tabcont");
						
						for(i = 0; i < tabcont.length; i++) {
							tabcont[i].style.display = "none";
						}
						
						tablinks = document.getElementsByClassName("tablinks");
						
						for(i = 0; i < tablinks.length; i++) {
							tablinks[i].className = tablinks[i].className.replace(" active", "");
						}
						
						document.getElementById(dispName).style.display = "block";
						e.currentTarget.className += " active";
					}
				</script>
			</div>
		</div>
	</body>
</html>