<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<link rel = "stylesheet" type = "text/css" href = "${pageContext.request.contextPath}/style.css" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>NFC N. DB App</title>
</head>
<body background = "${pageContext.request.contextPath}/images/NFN2.jpg">
<%@ page import="java.io.*" %> 
<%@ page import="Lab6_485.*" %>

<div class="topnav">
  <a href="index.jsp">Home</a>
  <a href="Bears.jsp">Bears</a>
  <a href="Lions.jsp">Lions</a>
  <a href="packers.jsp">Packers</a>
  <a href="Vikings.jsp">Vikings</a>
</div>

<h1 align="center">NFC North DB Web Application</h1>


<form  action="index.jsp" method="get" >
Select a team
	<select name = "Team">
		  <option value="All">All Teams</option>
	</select>
	<br/>Search Options<br/>
	  <input type="radio" name="SO" value="HighestPaidPs" /> Highest Paid Players
	  <input type="radio" name="SO" value="HighestPaidC"/> Highest Paid Coach<br>
	  <input type="radio" name="SO" value="Highest Team Networ"/> Highest Net Worth<br>
	  <input type="radio" name="SO" value="StateTotals"/> # of Players From States<br>
	  
<input type="submit" Value="Submit" ></input>
</form>

<!-- <img src="${pageContext.request.contextPath}/images/Pie_Chart_BearsPGSalary.jpg" 
alt="Smiley face" height="560" width="700">
 -->

<%
String team=request.getParameter("Team");
String radio=request.getParameter("SO");
System.out.println("Printed");

if (team!= null && !team.trim().equals("") && radio!= null)
{
	
	if(team.equals("All") && radio.equals("StateTotals"))
	{
		 %>
		 <div style="display: flex; justify-content: center;">
		 <img src="${pageContext.request.contextPath}/images/StateTotals.png" 
				 alt="PlayerStateTotals"  height="460" width="600" align="middle" >
		</div>
		<%
		System.out.println("Bears");
	}
	//DBentry DBentry=new DBentry();
	//boolean flag = DBentry.userLookUp(team, pass);
	
	
}
%>
</body>
</html>