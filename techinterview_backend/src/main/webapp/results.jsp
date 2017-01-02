<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html ng-app="myApp">
	<head>
		<!--  <link rel="stylesheet" href="manage.css">-->
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">	
		<title>Results</title>
	</head>

	<body>
	<%
		try{
			Integer id = Integer.parseInt(request.getParameterValues("position")[0]);
		} catch(Exception E) {
			System.out.println("Input not found.");
		}
	 %>
		<canvas id="lineChart" height="400" width="400"></canvas>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.4.0/Chart.min.js"></script>
		<script src="javascript/getChart.js"></script>
	</body>
</html>