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
<div class="tab">
  <button class="tablinks" onclick="openCity(event, 'London')">Home</button>
  <button class="tablinks" onclick="openCity(event, 'Paris')">Bears</button>
  <button class="tablinks" onclick="openCity(event, 'Tokyo')">Lions</button>
  <button class="tablinks" onclick="openCity(event, 'Tokyo')">Packers</button>
  <button class="tablinks" onclick="openCity(event, 'Tokyo')">Vikings</button>
</div>
<h1 align="center">
NFC North DB Web Application
</h1>


<form  action="index.jsp" method="get" >
Select a team
	<select>
		  <option value="Bears">Bears</option>
		  <option value="Lions">Lions</option>
		  <option value="Packers">Packers</option>
		  <option value="Vikings">Vikings</option>
		  <option value="AllTeams">All Teams</option>
	</select>
	<br/>Search Options<br/>
	
	  <input type="radio" name="SO" value="GetRoster" /> Get Rosters 
	  <input type="radio" name="SO" value="GetRoster"/> Position Salary Chart<br>
	  	
	

<input type="submit" Value="Submit" ></input>
</form>
<%
String user=request.getParameter("user");
String pass=request.getParameter("password");
if (user!= null&&!user.trim().equals(""))
{
	DBentry DBentry=new DBentry();
	boolean flag = DBentry.userLookUp(user, pass);
	
	if(flag) 
	{
		%><script type="text/javascript">window.location.replace("welcome.jsp");</script><%
	}
	else { 
		%><script type="text/javascript">window.location.replace("registration.jsp");</script><%
		
	}
}

%>

</body>
</html>