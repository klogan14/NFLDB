<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<body background = "${pageContext.request.contextPath}/images/d.jpg">
<link rel = "stylesheet" type = "text/css" href = "${pageContext.request.contextPath}/style.css" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>NFC N. DB App</title>
</head>
<%@ page import="java.io.*" %> 
<%@ page import="Lab6_485.*" %>

<h1 align="center">
Detroit Lions
</h1>
<div>
City: Detroit<br>
State: Michigan<br>
Owner: Martha Firestone Ford<br>
GM: Bob Quinn <br>
Coach: Jim Caldwell<br>
Stadium: Ford Field<br><br>
</div>

<form  action="index.jsp" method="get" >
Select Category to View.<br>

	
	  <input type="radio" name="SO" value="Wins" /> Overall Wins<br>
	  <input type="radio" name="SO" value="Losses"/>Overall Losses<br>
	  <input type="radio" name="SO" value="Season Wins" /> Season Wins <br>
	  <input type="radio" name="SO" value="Season Losses" /> Season Losses<br>
	  <input type="radio" name="SO" value="Net Cap" /> Net Cap<br>
	  <input type="radio" name="SO" value="Team Worth" /> Team Worth<br>
	  <input type="radio" name="SO" value="Select All Categories" /> Select all Categories<br>
	  
	  
	  	
	

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