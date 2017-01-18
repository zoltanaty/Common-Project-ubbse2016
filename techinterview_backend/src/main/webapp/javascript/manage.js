function checkboxValidator() {
	if(document.getElementById("checkbox1").checked) document.getElementById("hidden1").disabled = true;
	else document.getElementById("hidden1").disabled = false;
	if(document.getElementById("checkbox2").checked) document.getElementById("hidden2").disabled = true;
	else document.getElementById("hidden2").disabled = false;
	if(document.getElementById("checkbox3").checked) document.getElementById("hidden3").disabled = true;
	else document.getElementById("hidden3").disabled = false;
	if(document.getElementById("checkbox4").checked) document.getElementById("hidden4").disabled = true;
	else document.getElementById("hidden4").disabled = false;
}

function switchQuestionType() {
	var questionType = document.getElementById("queType").value;
	
	if(questionType == "checkbox") {
		document.getElementById("answer1").disabled = false;
		document.getElementById("answer2").disabled = false;
		document.getElementById("answer3").disabled = false;
		document.getElementById("answer4").disabled = false;
		document.getElementById("checkbox1").disabled = false;
		document.getElementById("checkbox2").disabled = false;
		document.getElementById("checkbox3").disabled = false;
		document.getElementById("checkbox4").disabled = false;
		document.getElementById("checkbox1").setAttribute("type", "checkbox");
		document.getElementById("checkbox2").setAttribute("type", "checkbox");
		document.getElementById("checkbox3").setAttribute("type", "checkbox");
		document.getElementById("checkbox4").setAttribute("type", "checkbox");
	}
	else if(questionType == "radiobutton"){
		document.getElementById("answer1").disabled = false;
		document.getElementById("answer2").disabled = false;
		document.getElementById("answer3").disabled = false;
		document.getElementById("answer4").disabled = false;
		document.getElementById("checkbox1").disabled = false;
		document.getElementById("checkbox2").disabled = false;
		document.getElementById("checkbox3").disabled = false;
		document.getElementById("checkbox4").disabled = false;
		document.getElementById("checkbox1").setAttribute("type", "radio");
		document.getElementById("checkbox2").setAttribute("type", "radio");
		document.getElementById("checkbox3").setAttribute("type", "radio");
		document.getElementById("checkbox4").setAttribute("type", "radio");
	}
	else {
		document.getElementById("checkbox1").disabled = true;
		document.getElementById("checkbox2").disabled = true;
		document.getElementById("checkbox3").disabled = true;
		document.getElementById("checkbox4").disabled = true;
		document.getElementById("answer1").disabled = true;
		document.getElementById("answer2").disabled = true;
		document.getElementById("answer3").disabled = true;
		document.getElementById("answer4").disabled = true;
	}
}