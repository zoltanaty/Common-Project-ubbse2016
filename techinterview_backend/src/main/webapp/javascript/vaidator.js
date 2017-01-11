function turnOffAutoComplet() {
	if (document.getElementsByTagName) {
		var inputElements = document.getElementsByTagName("input");
		for (i=0; inputElements[i]; i++) {
			inputElements[i].setAttribute("autocomplete","off");
		}
	}
}

function checkUserName(display) {
	var userName = document.getElementById("userName").value;
	if(userName.length > 0) {
		if(display) {
			goodUsername();
			isValid();
		}
		else return true;
	}
	else {
		if(display) wrongUsername();
		else {
			document.getElementById("done").disabled = true;
			return false;
		}
	}
}

function checkPassword(display) {
	var passw = document.getElementById("passw").value;
	if(passw.length < 6 || passw.length > 32) {
		if(display) wrongPassword();
		else {
			document.getElementById("done").disabled = true;
			return false;
		}
	}
	else {
		if(display) {
			goodPassword();
			isValid();
		}
		else return true;
	}
}

function isValid() {
	if(checkUserName(false) && checkPassword(false)) document.getElementById("done").disabled = false;
	else document.getElementById("done").disabled = true;
}

function wrongUsername() {
	document.getElementById("done").disabled = true;
	document.getElementById("userName").style.borderBottom="2px solid #fb5250";
	document.getElementById("usrMSG").style.display="block";
}

function wrongPassword() {
	document.getElementById("done").disabled = true;
	document.getElementById("passw").style.borderBottom="2px solid #fb5250";
	document.getElementById("pasMSG").style.display="block";
}

function goodUsername() {
	document.getElementById("userName").style.borderBottom="1px solid #c7c7c7";
	document.getElementById("usrMSG").style.display="none";
}

function goodPassword() {
	document.getElementById("passw").style.borderBottom="1px solid #c7c7c7";
	document.getElementById("pasMSG").style.display="none";
}