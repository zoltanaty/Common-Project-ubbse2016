<%@ page 
	import="com.halcyonmobile.rest.OwnersService, com.halcyonmobile.model.Owners, com.halcyonmobile.rest.UserService, com.halcyonmobile.model.User, com.halcyonmobile.rest.PositionService, com.halcyonmobile.model.Position, com.halcyonmobile.rest.QuestionTypeService, com.halcyonmobile.model.QuestionType, com.halcyonmobile.rest.QuestionService, com.halcyonmobile.model.Question, java.util.List"
	language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
if (!((Boolean) session.getAttribute("ok") != null && (Boolean) session.getAttribute("ok"))) response.sendRedirect("index.jsp");
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<link rel="stylesheet" href="styles/style.css" type="text/css"/>
		<script type="text/javascript" src="javascript/manage.js"></script>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<script src="javascript/isFilled.js" type="text/javascript"></script>
		<link rel="shortcut icon" href="favicon.ico" />
		<title>Admin Dashboard</title>
	</head>

	<body>
	
		<div id="manage">
			<div id="tabs">
				<ul class="tab">
					<li><a href="#" class="tablinks" onclick="disp(event, 'addAM')">Add Admin/Manager</a></li>
					<li><a href="#" class="tablinks" onclick="disp(event, 'delAM')">Delete Admin/Manager</a></li>
					<li><a href="#" class="tablinks" onclick="disp(event, 'delInt')">Delete Interviewee</a></li>
					<li><a href="#" class="tablinks" onclick="disp(event, 'addQue')">Add Question</a></li>
					<li><a href="#" class="tablinks" onclick="disp(event, 'delQue')">Delete Question</a></li>
					<li><a href="#" class="tablinks" onclick="disp(event, 'addPos')">Add Position</a></li>
					<li><a href="#" class="tablinks" onclick="disp(event, 'delPos')">Delete Position</a></li>
					<li><a href="logout.do" class="tablinks">Logout</a></li>
				</ul>
			</div>
		
			<div id="mforms">
				<div id="addAM" class="tabcont" style="display: block;">
					<h1>Add admin/manager</h1>
					<form method="POST" action="addAM.do">
					<table align="center" border="0">
					<tr><td>
						<input type="text" name="user" placeholder="Username">
						<br>
						<input type="text" name="pass" placeholder="Password">
						<br><br>
						<input id="radio1" checked type="radio" value="Admin" name="radio"><label for="radio1"><span></span>Admin</label>
						<br>
						<input id="radio2" type="radio" value="Manager" name="radio"><label for="radio2"><span></span>Manager</label>
						<br><br>
						<button type="submit" >ADD</button>
						</td></tr></table>
					</form>
				</div>
				
				<div id="delAM" class="tabcont">
					<h1>Delete admin/manager</h1>
					<form method="POST" action="deleteAM.do" name="delAM">
					<table align="center" border="0">
					<tr><td>
						<select name="AMList">
						<option value="" disabled selected>Username</option>
						<%
							OwnersService os = new OwnersService();
							List<Owners> ownersList = os.findAll();
								
							for(int i = 0; i < ownersList.size(); i++) {
						%>
							<option value="<%=ownersList.get(i).getUsername() %>"><%=ownersList.get(i).getUsername()%></option>
							
						<%
							}
						%>
						</select><br>
						<button type="submit">DELETE</button>
						</td></tr></table>
					</form>
				</div>
				
				<div id="delInt" class="tabcont">
					<h1>Delete interviewee</h1>
					<form method="POST" action="deleteInt.do" name="delInt">
					<table align="center" border="0">
					<tr><td>
						<select name="IntList">
						<option value="" disabled selected>Username</option>
						<%
							UserService us = new UserService();
							List<User> usersList = us.findAll();
								
							for(int i = 0; i < usersList.size(); i++) {
						%>
							<option value="<%=usersList.get(i).getName() %>"><%=usersList.get(i).getName() %></option>
						<%
							}
						%>
						</select><br>
						<button type="submit">DELETE</button>
						</td></tr></table>
					</form>
				</div>
			
				<div id="addQue" class="tabcont">
					<h1>Add question</h1>
					<form method="POST" action="addQue.do" name="addQue">
					<table align="center" border="0">
					<tr><td>
						<select name="posList">
						<option value="" disabled selected>Positions</option>
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
						<select onchange="switchQuestionType()" id="queType" name="queType">
						<option value="" disabled selected>Question Type</option>
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
						<input placeholder="Question" type="text" name="Question"><br><br>
						<input placeholder="Answer1" id="answer1" type="text" name="Answer"><input id="hidden1" type="hidden" value="false" name="Correct"><input onclick="checkboxValidator()" id="checkbox1" type="checkbox" value="true" name="Correct"><label for="checkbox1"><span></span></label><br>
						<input placeholder="Answer2" id="answer2" type="text" name="Answer"><input id="hidden2" type="hidden" value="false" name="Correct"><input onclick="checkboxValidator()" id="checkbox2" type="checkbox" value="true" name="Correct"><label for="checkbox2"><span></span></label><br>
						<input placeholder="Answer3" id="answer3" type="text" name="Answer"><input id="hidden3" type="hidden" value="false" name="Correct"><input onclick="checkboxValidator()" id="checkbox3" type="checkbox" value="true" name="Correct"><label for="checkbox3"><span></span></label><br>
						<input placeholder="Answer4" id="answer4" type="text" name="Answer"><input id="hidden4" type="hidden" value="false" name="Correct"><input onclick="checkboxValidator()" id="checkbox4" type="checkbox" value="true" name="Correct"><label for="checkbox4"><span></span></label><br><br>
						<button type="submit">ADD</button>
						</td></tr></table>
					</form>
				</div>
				
				<div id="delQue" class="tabcont">
					<h1>Delete existing question</h1>
					<form method="POST" action="delQue.do" name="delQue">
					<table align="center" border="0">
					<tr><td>
						<select name="que">
						<option value="" disabled selected>Questions</option>
						<%
							QuestionService qs = new QuestionService();
							List<Question> questionList = qs.findAll();
								
							for(int i = 0; i < questionList.size(); i++) {
						%>
							<option value="<%=questionList.get(i).getQuestion() %>"><%=questionList.get(i).getQuestion() %></option>
						<%
							}
						%>
						</select><br>
						<button type="submit">DELETE</button>
						</td></tr></table>
					</form>
				</div>
				
				<div id="addPos" class="tabcont">
					<h1>Add Position</h1>
					<form method="POST" action="addPos.do" name="addPos" onSubmit="return isFilled();">
					<table align="center" border="0">
					<tr><td>
						<input placeholder="Position" id="posText" class="rowinput1" type="text" name="posname">
						<br>
						<input placeholder="Nr. of Questions" id="nrQues" class="rowinput2" type="text" name="nrQue">
						<br>
						<button type="submit">ADD</button>
						</td></tr></table>
					</form>
				</div>
				
				<div id="delPos" class="tabcont">
					<h1>Delete position</h1>
					<form method="POST" action="delPos.do" name="delPos">
					<table align="center" border="0">
					<tr><td>
						<select name="posname">
						<option value="" disabled selected>Positions</option>
						<%
							PositionService p = new PositionService();
							List<Position> posList = p.findAll();
								
							for(int i = 0; i < posList.size(); i++) {
						%>
							<option value="<%=posList.get(i).getName() %>"><%=posList.get(i).getName() %></option>
						<%
							}
						%>
						</select><br>
						<button type="submit">DELETE</button>
						</td></tr></table>
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