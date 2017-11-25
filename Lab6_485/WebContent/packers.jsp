<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<body background = "${pageContext.request.contextPath}/images/gb.jpg">
<link rel = "stylesheet" type = "text/css" href = "${pageContext.request.contextPath}/style.css" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Packers</title>
</head>
<%@ page import="java.io.*" %> 
<%@ page import="Lab6_485.*" %>
<div class="topnav">
  <a href="index.jsp">Home</a>
  <a href="Bears.jsp">Bears</a>
  <a href="Lions.jsp">Lions</a>
  <a href="packers.jsp">Packers</a>
  <a href="Vikings.jsp">Vikings</a>
</div>
<h1 align="center">
Green Bay Packers
</h1>
<div>
City: Green Bay<br>
State: Wisconsin<br>
Owner: Green Bay Packers Inc.<br>
GM: Ted Thompson <br>
Coach: Mike McCarthy<br>
Stadium: Lambeau Field<br><br>
</div>

<form  action="packers.jsp" method="get" >
Select Category to View.<br>
	  <input type="radio" name="SO" value="SalaryCapChart" /> Position Group Breakdown <br>
	  <input type="radio" name="SO" value="Roster" /> Get Roster<br>

<input type="submit" Value="Submit" ></input>
</form>


<br><div>Click the buttons to get the coaches, players, or team statistics</div>
<form action ="${pageContext.request.contextPath}/GetCoaches" method = "get">
	  <input type="submit" name = "Coaches"  value="Packers Coaches" /><br>
</form>

<form action ="${pageContext.request.contextPath}/GetRoster" method = "get">
	  <input type="submit" name="Roster" value="Packers Roster" /><br>
</form>

<form action ="${pageContext.request.contextPath}/GetTeamStats" method = "get">
	  <input type="submit" name="TS" value="Packers Stats" /><br>
</form>

<%
String option=request.getParameter("SO");
String pass=request.getParameter("password");
if (option!= null&&!option.trim().equals(""))
{

	if( option.equals("SalaryCapChart"))
	{
		 %>
		 <div style="display: flex; justify-content: center;">
		 <img src="${pageContext.request.contextPath}/images/PG_Salary_Pack.png" 
				 alt="Packers Salary Chart"  height="360" width="500" align="middle" >
		</div>
		<%
	}
}


%>

</body>
</html>