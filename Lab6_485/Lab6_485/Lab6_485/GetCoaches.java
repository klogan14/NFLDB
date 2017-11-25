package Lab6_485;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/GetCoaches")
public class GetCoaches extends HttpServlet
{
	
	private static final long serialVersionUID = 1L;
	static DBentry instance = new DBentry();
	Connection dbconn;
	ResultSet results = null;
	PreparedStatement sql;
	String dpwd = null;
	StringBuilder sb = new StringBuilder();
	
	String dbPath="jdbc:mysql://localhost:3306";
	

	public static DBentry getInstance() 
	{
		if (instance==null) 
		{
			instance = new DBentry();
		}
		return instance;
	}

		public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException
		{
			int teamId= 1;
			String team = request.getParameter("Coaches");
			String h1 = "";
			String bc = ""; //background color hex
			String tc = ""; //table color hex

			if(team.equals("Packers Coaches"))
			{
				h1 = "Packers Coaches";
				teamId = 1;
				bc = "#175e33"; // forest green
				tc = "#FFB81C"; // cheese gold 
			}
			else if(team.equals("Vikings Coaches"))
			{
				h1 = "Vikings Coaches";
			
				teamId = 2;
				bc = "#512D6D"; // purple
				tc = "#FFB81C"; // gold 
			}
			else if(team.equals("Bears Coaches"))
			{
				h1 = "Bears Coaches";
				teamId = 3;
				bc = "#051C2C"; //dark navy
				tc = "#DC4405"; // orange

			}
			else
			{
				h1 = "Lions Coaches";
				teamId = 4;
				bc = "#0069B1"; // honolulu blue
				tc = "#66666;"; // silver
			}
		
			String query = "Select FirstName, LastName,age, Type, Years,Salary\n" + 
					"From NFLSchema.Person,NFlSchema.Coach,NFLSchema.Team\n" + 
					"where Person.ID = Coach.P_Id \n" + 
					"and Coach.Team_Id = "+teamId+" \n" + 
					"and Team.Team_Id = "+teamId;
			//System.out.println("Query in doGEt: "+query);
			PrintWriter out = response.getWriter();
			//url(\"${pageContext.request.contextPath}/images/c.jpg\"
			String docType = "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 "+
					"Transitional//EN\">\n";
	        out.println(docType +"<HTML>\n"+
					"<HEAD><TITLE>"+
	        			"Roster"+ 
	        			"</TITLE>"+
	        			"<style>"+
	        			"body {background-color :  "+bc+" }"+
						".topnav {\n" + 
						"  overflow: hidden;\n" + 
						"  background-color: #333;\n" + 
						"}\n" + 
						"\n" + 
						".topnav a {\n" + 
						"  float: left;\n" + 
						"  display: block;\n" + 
						"  color: #f2f2f2;\n" + 
						"  text-align: center;\n" + 
						"  padding: 14px 16px;\n" + 
						"  text-decoration: none;\n" + 
						"  font-size: 17px;\n" + 
						"}\n" + 
						"\n" + 
						".topnav a:hover {\n" + 
						"  background-color: #ddd;\n" + 
						"  color: black;\n" + 
						"}\n" + 
						"\n" + 
						".topnav a.active {\n" + 
						"    background-color: #4CAF50;\n" + 
						"    color: white;\n" + 
						"}"+
		        			"</style>"+
					
					"</HEAD>\n"+
			        "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=ISO-8859-1\">"+
			        "<title>Bears</title>"+
			        "</head>"+ 
			        "<div class=\"topnav\">"+
			          "<a href=\"index.jsp\">Home</a>"+
			          "<a href=\"Bears.jsp\">Bears</a>"+
			          "<a href=\"Lions.jsp\">Lions</a>"+
			          "<a href=\"packers.jsp\">Packers</a>"+
			          "<a href=\"Vikings.jsp\">Vikings</a>"+
			        "</div>"+
			        "<h1 style=\"color:white;\" align=\"center\">"+
			        	h1+
			        "</h1>"
					);
			
			
			out.print("<table align = \"center\" bgcolor=\"#060606\" style=\"width:45%\">"
	 		   	   + "<tr>"
	 		       + "</tr>");	 
			out.write("\n");	 
		    out.print
	    	     (
	 		    "<tr>"
	 		   + "<th bgcolor="+tc+">"
	 		   + "Name" 
	 		   + "</th>" 
	 		   + "<th bgcolor="+tc+">"
	 		   + "Type"
	 		   + "</th>"
	 		   + "<th bgcolor="+tc+">"
			   + "Age" 
			   + "</th>" 
			   + "<th bgcolor="+tc+">"
			   + "Years"
			   + "</th>"
			   + "<th bgcolor="+tc+">"
			   + "Salary"
			   + "</th>"
			   +"</tr>"
	 		   );
			
			String FirstName = "";
			String LastName = "";
			String Type = "";
			String Age = "";
			String Years = "";
			String Salary = "";

			try
			{
			dbconn=instance.newConnection();
			sql=dbconn.prepareStatement(query);
			results=sql.executeQuery();
			System.out.println("Results DOGET: "+ results);
			
				while(results.next())
				{
					FirstName = results.getString("FirstName");
					LastName = results.getString("LastName");
					Type = results.getString("Type");
					Age = results.getString("Age");
					Years = results.getString("Years");
					Salary = results.getString("Salary");
					 
			        out.write("\n");	 
			        out.print
		    	     	(
			 		     "<tr>"
			 		   + "<td align=\"center\" bgcolor="+tc+">"
			 		   + FirstName + " " + LastName
			 		   + "</td>" 
			 		   + "<td align=\"center\" bgcolor="+tc+">"
			 		   + Type
			 		   + "</td>"
			 		   + "<td align=\"center\" bgcolor="+tc+">"
					   + Age
					   + "</td>" 
					   + "<td align=\"center\" bgcolor="+tc+">"
					   + Years
					   + "</td>"
					   + "<td align=\"center\" bgcolor="+tc+">"
					   + "$"+Salary
					   + "</td>"
					   +"</tr>"
					   );
			        
					
					
					
					
				}
				//System.out.println("Encrypted creditCard # doGet:" + ccNum);
				
				try 
				{
					//ccNum = PWCrypto.decrypt(ccNum);
					//ccType = PWCrypto.decrypt(ccType);
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
				
				//System.out.println("Decrypted ccNum: " + ccNum);
				
				 out.print("</tr>"+
						 "</table>");
						 out.println("</BODY></HTML>");
				
				
				dbconn.close();

			} 
			catch (SQLException e) 
			{
				System.out.println("Exception error for while results.next()");
				e.printStackTrace();
			}
			
			
		}
	}

