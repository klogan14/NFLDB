<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Register a account with us</title>
</head>
<body>

<%@ page import="java.io.*" %> 
<%@ page import="Lab6_485.*" %>

<form  action="registration.jsp" method="get">
Account information:<br/><br/>
First Name: <input id="firstName" name="firstName" type="text"/><br/>
Last Name:  <input id="lastName" name="lastName" type="text"/><br/>
User Name: <input id="user" name="user" type="text"/><br/>
Password:  <input id="password" name="password" type="text"/><br/>
<input type="submit" Value="Submit" ></input>
</form>
<%
String user =request.getParameter("user");
String pass =request.getParameter("password");
String firstName =request.getParameter("firstName");
String lastName =request.getParameter("lastName");
DBentry DBentry=new DBentry();
boolean flag = DBentry.entry(user, pass, firstName, lastName);
System.out.println("userName in reg: "  + user);
if (user!= null&&!user.trim().equals(""))
{
	//DBentry DBentry=new DBentry();
	//boolean flag = DBentry.entry(user, pass, firstName, lastName);
	System.out.println("inside if stmnt");
	if(flag) 
	{
		%><script type="text/javascript">window.location.replace("welcome.jsp");</script><%
		System.out.println("inside if stmnt2");

	}
}

%>

</body>
</html>