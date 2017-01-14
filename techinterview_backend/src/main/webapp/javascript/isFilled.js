function isFilled() {
	var pos = document.getElementById("posText").value;
	var nr = document.getElementById("nrQues").value;
	
	if(pos == "" || nr == "") {
		alert("Missing information");
		return false;
	}
}