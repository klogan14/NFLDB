<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome to CS 485</title>
</head>
<body>
<%@ page import="java.io.*" %> 
<%@ page import="Lab6_485.*" %>

<form  action="index.jsp" method="get">
Login your account:<br/><br/>

User Name: <input id="user" name="user" type="text"/><br/>
Password:  <input id="password" name="password" type="text"/><br/>
<input type="submit" Value="Submit" ></input>
</form>
<%
String user=request.getParameter("user");
String pass=request.getParameter("password");
//System.out.println("UserName in Index: " + user);
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