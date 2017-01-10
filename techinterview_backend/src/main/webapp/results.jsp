<%@ page
        import="com.halcyonmobile.model.User,com.halcyonmobile.model.Result,com.halcyonmobile.rest.UserService,com.halcyonmobile.rest.ResultService,java.util.List"
        language="java" contentType="text/html; charset=ISO-8859-1"
        pageEncoding="ISO-8859-1" %>
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
        <li><a
                href="http://localhost:8080/techinterview-backend/logout.do">Log
            out</a></li>
        <li><a
                href="http://localhost:8080/techinterview-backend/contact.jsp">Contact</a></li>
        <li><a
                href="http://localhost:8080/techinterview-backend/about.jsp">About</a></li>
        <li><a
                href="http://localhost:8080/techinterview-backend/index.jsp">Home</a></li>
    </ul>
</div>
<div id="main">
    <%
        Integer nrPerPage = 10;
        Integer id = Integer.parseInt(request.getParameterValues("position")[0]);
        Integer p;
        try{
        	p = Integer.parseInt(request.getParameterValues("p")[0]);
        } catch(Exception e) {
        	p = 1;
        }
        UserService userService = new UserService();
        List<User> users = userService.findByPosition(id);
    %>

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
            if (limit > p * nrPerPage) limit = p * nrPerPage;
            for (int i = (p - 1) * nrPerPage; i < limit; i++) {
                ResultService rs = new ResultService();
                List<Result> res = rs.findByUserId(users.get(i).getId());
                int duration = 0;
                String date = "";
                for (int j = 0; j < res.size(); j++) {
                    duration += res.get(i).getThinkingTime();
                    date = res.get(i).getDate();
                }

                int minutes = 0, seconds = 0;
                minutes = duration / 60;
                seconds = duration % 60;
        %>
        <tr
                onclick="document.location='#popup<%=users.get(i).getId()%>';return false;">
            <td><%=users.get(i).getId()%>
            </td>
            <td><%=users.get(i).getName()%>
            </td>
            <td><%=date%>
            </td>
            <td><%=minutes + " m " + seconds + " s"%>
            </td>
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
                            String correct = "correct";
                            if (!res.get(j).getIsCorrect())
                                correct = "wrong";
                    %>
                    <tr class="<%=correct%>">
                        <td><%=j + 1%>
                        </td>
                        <td><%=res.get(j).getQuestion()%>
                        </td>
                        <td><%=res.get(j).getAnswer()%>
                        </td>
                    </tr>
                    <%
                        }
                    %>
                    </tbody>
                </table>
                <div id="chartContainer<%=users.get(i).getId()%>" style="height: 300px; width: 100%;"></div>
            </div>
        </div>
    </div>
    <%
        }
    %>
    <script type="text/javascript">
        window.onload = function () {
            <%
                for (int i = (p-1) * nrPerPage; i < limit; i++) {
                    ResultService rs = new ResultService();
                    List<Result> res = rs.findByUserId(users.get(i).getId());
            %>
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
            <%
                }
            %>
        }
    </script>
    <ul class="pagination">
        <%
            Integer nrOfPage = users.size() / nrPerPage;
            if (users.size() % nrPerPage != 0) nrOfPage++;
            for (int i = 1; i <= nrOfPage; i++) {
        %>
        <li><a href="?position=<%=id %>&p=<%=i %>"><%=i %>
        </a></li>
        <%
            }
        %>
    </ul>
</div>
</body>
</html>