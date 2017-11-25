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
	  <input type="radio" name="SO" value="StadiumSize"/> Stadium Size Comparrison<br>
	  <input type="radio" name="SO" value="StateTotals"/> # of Players From States<br>
	  <input type="radio" name="SO" value="TeamState"/> # of Players From Team State<br>
	  
<input type="submit" Value="Submit" ></input>
</form>

<br>

<form action ="${pageContext.request.contextPath}/HighestPaidPlayers" method = "get">
	  <input type="submit" name="HPP" value="Highest Paid Players" /><br>
</form>

<br>

<form action ="${pageContext.request.contextPath}/GetDPlayerStats" method = "get">
Select players with more than or equal to <input type="text" name="statNumber" value="">
<select name = "StatType">
		  <option value="Tackles">Tackles</option>
		  <option value="Sacks">Sacks</option>
		  <option value="INTS">Interceptions</option>
		  <option value="ForcedFumbles">Forced Fumbles </option>
</select>
<br>
<input type="submit" Value="Submit" ></input>
</form>

<form action ="${pageContext.request.contextPath}/GetOPlayerStats" method = "get">
Select players with more than or equal to <input type="text" name="statNumber" value="">
<select name = "StatType">
		  <option value="PassingYards">Passing Yards</option>
		  <option value="RushingYards">Rushing Yards</option>
		  <option value="Receptions">Receptions</option>
		  <option value="ReceivingYards">Receiving Yards</option>
		  <option value="TDs">Touchdowns</option>		  
		  <option value="GameStarts">Starts</option>
		  <option value="FGMade">Field Goals Made</option>
		  
</select>
<br>
<input type="submit" Value="Submit" ></input>
</form>

<%
String team=request.getParameter("Team");
String radio=request.getParameter("SO");
System.out.println("Printed");
//SqlQueries sqlQ = new SqlQueries();
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
	}
	else if(team.equals("All") && radio.equals("TeamState"))
	{
		 %>
		 <div style="display: flex; justify-content: center;">
		 <img src="${pageContext.request.contextPath}/images/PlayersFromTeamState.png" 
				 alt="PlayerStateTotals"  height="460" width="600" align="middle" >
		</div>
		<%
	}
	else if(team.equals("All") && radio.equals("StadiumSize"))
	{
		 %>
		 <div style="display: flex; justify-content: center;">
		 <img src="${pageContext.request.contextPath}/images/StadiumSizeComparrison.png" 
				 alt="PlayerStateTotals"  height="460" width="600" align="middle" >
		</div>
		<%
	}
	//DBentry DBentry=new DBentry();
	//boolean flag = DBentry.userLookUp(team, pass);
	
	
}
%>
</body>
</html>