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

@WebServlet("/GetTeamStats")
public class TeamStats extends HttpServlet
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
			int teamId= 0;
			String team = request.getParameter("TS");
			String h1 = "";
			String bc = ""; //background color hex
			String tc = ""; //table color hex
			
			if(team.equals("Packers Stats"))
			{
				h1 = "Packers Stats";
				teamId = 1;
				bc = "#175e33"; // forest green
				tc = "#FFB81C"; // cheese gold 
				
				
			}
			else if(team.equals("Vikings Stats"))
			{
				h1 = "Vikings Stats";
			
				teamId = 2;
				bc = "#512D6D"; // purple
				tc = "#FFB81C"; // gold 
			}
			else if(team.equals("Bears Stats"))
			{
				h1 = "Bears Stats";
				teamId = 3;
				bc = "#051C2C"; //dark navy
				tc = "#DC4405"; // orange

			}
			else
			{
				h1 = "Lions Stats";
				teamId = 4;
				bc = "#0069B1"; // honolulu blue
				tc = "#66666;"; // silver
			}
		
			String query = "select Seasons,Overall_Wins, Overall_Losses, Wins2016, Losses2016, Playoff_Record,SB_Appearances,SB_Wins,Championships\n" + 
					"from NFLSchema.Team, NFLSchema.Team_Stats\n" + 
					"where Team.Team_ID ="+teamId+" and Team_Stats.Team_ID = " + teamId+ ";\n" + 
					"";
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
	 		   + "Seasons" 
	 		   + "</th>" 
	 		   + "<th bgcolor="+tc+">"
	 		   + "Total Wins"
	 		   + "</th>"
	 		   + "<th bgcolor="+tc+">"
			   + "Total Lossess" 
			   + "</th>" 
			   + "<th bgcolor="+tc+">"
			   + "2016 Wins"
			   + "</th>"
			   + "<th bgcolor="+tc+">"
			   + "2016 Losses"
			   + "</th>"
			   + "<th bgcolor="+tc+">"
			   + "Playoff Record" 
			   + "</th>" 
			   + "<th bgcolor="+tc+">"
			   + "Super Bowl Appearances"
			   + "</th>"
			   + "<th bgcolor="+tc+">"
			   + "Super Bowl Wins" 
			   + "</th>" 
			   + "<th bgcolor="+tc+">"
			   + "Championships"
			   +	 "</th>"
			   +"</tr>"
	 		   );
			
			String Seasons = "";
			String TW = "";
			String TL = "";
			String Wins2016 = "";
			String Losses2016 = "";
			String PlayoffRec = "";
			String SBApps = "";
			String SBWins = "";
			String Champs = "";

			try
			{
			dbconn=instance.newConnection();
			sql=dbconn.prepareStatement(query);
			results=sql.executeQuery();
			System.out.println("Results DOGET: "+ results);
			
				while(results.next())
				{
					Seasons = results.getString("Seasons");
					TW = results.getString("Overall_Wins");
					TL = results.getString("Overall_Losses");
					Wins2016 = results.getString("Wins2016");
					Losses2016 = results.getString("Losses2016");
					 PlayoffRec = results.getString("Playoff_Record");;

					SBApps = results.getString("SB_Appearances");
					SBWins = results.getString("SB_Wins");
					Champs = results.getString("Championships");

					 
			        out.write("\n");	 
			        out.print
		    	     	(
			 		     "<tr>"
			 		   + "<td align=\"center\" bgcolor="+tc+">"
			 		   + Seasons
			 		   + "</td>" 
			 		   + "<td align=\"center\" bgcolor="+tc+">"
			 		   + TW
			 		   + "</td>"
			 		   + "<td align=\"center\" bgcolor="+tc+">"
					   + TL
					   + "</td>" 
					   + "<td align=\"center\" bgcolor="+tc+">"
					   + Wins2016
					   + "</td>"
					   + "<td align=\"center\" bgcolor="+tc+">"
					   + Losses2016
					   + "</td>"
					   + "<td align=\"center\" bgcolor="+tc+">"
					   + PlayoffRec
					   + "</td>" 
					   + "<td align=\"center\" bgcolor="+tc+">"
					   + SBApps
					   + "</td>" 
					   + "<td align=\"center\" bgcolor="+tc+">"
					   + SBWins
					   + "</td>"
					   + "<td align=\"center\" bgcolor="+tc+">"
					   + Champs
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

