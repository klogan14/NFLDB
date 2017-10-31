package Lab6_485;
/*
 * Program: CS485_lab4
 * Purpose: The program is designed to illustrate 1) the use of SQL connector 
 * to connect to and update on MySQL database; 2) the use of JSP to create a
 * Web page for data entry. To make the program work, both JDK, Apache, MySQL
 * need to be installed.
 * @copyright the program is intended for class use, it should not be distributed
 * outside the class without permission from the instructor, Dr. Mingrui Zhang  
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/DBentry")
public class DBentry extends HttpServlet
{

	private static final long serialVersionUID = 1L;
	static DBentry instance = new DBentry();
	Connection dbconn;
	ResultSet results = null;
	PreparedStatement sql;
	String dpwd = null;
	StringBuilder sb = new StringBuilder();
	
	//change URL to your database server as needed
	String dbPath="jdbc:mysql://localhost:3306";
	
	public static DBentry getInstance() 
	{
		if (instance==null) 
		{
			instance = new DBentry();
		}
		return instance;
	}
	
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		doGet(request,response);
		System.out.println("DoPost Run");
	}
	
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		String user = request.getParameter("user");
		String password = request.getParameter("password");
		
		
		//entry(user,password);
		
		String query = "Select * from Lab5.lab5 where ItemNum = \'" + user + "\'";
		//System.out.println("Query in doGEt: "+query);
		try
		{
		dbconn=instance.newConnection();
		sql=dbconn.prepareStatement(query);
		results=sql.executeQuery();
		//System.out.println("Results DOGET: "+ results);
	
			while(results.next())
			{
				user = results.getString("UserName");
				password = results.getString("Password");
				
			}
			//System.out.println("Encrypted password # doGet:" + password);
			
			try 
			{
				password = PWCrypto.decrypt(password);
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			}
			
			//System.out.println("Decrypted pass: " + password);
			
			response.setContentType("text/html");
			
			dbconn.close();

		} 
		catch (SQLException e) 
		{
			//System.out.println("Exception error for while results.next()");
			e.printStackTrace();
		}
	
		
		PrintWriter out = response.getWriter();
		String docType = "";
		
		docType = "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 "+
				"Transitional//EN\">\n";
        out.println(docType +"<HTML>\n"+
				"<HEAD><TITLE>"+ 
				"Confirmation of Order"+ 
				"</TITLE></HEAD>\n"+
				"<BODY BGCOLOR=\"#E6E6FA\">\n"
				);
      
        
		 out.print("</tr>"+"</table>");
		 out.println("</BODY></HTML>");
	}
	
	//Establish connection to MySQL server
	public Connection newConnection() 
	{
		try 
		{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			
			try 
			{			
				dbconn = DriverManager.getConnection(dbPath,"root","password");
				//System.out.println("gain the connection");
				return dbconn;
			}
			catch (Exception s)
			{
				System.out.println(s.getStackTrace().toString());
			}
		}
		catch (Exception err)
		{
			System.out.println(err.getStackTrace().toString());
		}
		return null;
	}
	
	public ResultSet selectStatement( String query ) 
	{
		try 
		{

			dbconn=instance.newConnection();
			sql=dbconn.prepareStatement(query);
			//ResultSet results;
			results=sql.executeQuery();
			//System.out.println("query="+query);
	
			dbconn.close();
			return results;
		}
		catch (Exception err) 
		{
			System.out.println(err.getMessage());
			return null;
		}
	}

	public boolean DBentry(String query) 
	{
		try {
			//System.out.println("query="+query);
			instance.newConnection();
			sql=dbconn.prepareStatement(query);
			sql.executeUpdate(query);
			dbconn.close();
			return true;
		}
		catch ( Exception err ) {
			err.getStackTrace();
			return false;
		}
	}
	
	public boolean entry( String user, String pass, String firstName, String lastName) 
	{
		//Explore use of metadata pull out meta data essentially
		//build multiple web pages, to allow user to enter different information
		//encryption
		//hibernate
		
		try 
		{
			
			//System.out.println("UserName & Pass"+user + " " + pass+firstName + " " + lastName);
			pass = PWCrypto.encrypt(pass);
			//System.out.println("Encrypted Pass: "+pass);
			
			instance.DBentry("INSERT INTO Lab6.lab6 (`UserName`, `Password`, `FirstName`, `LastName`) " +
								"VALUES ( '"+user+"' ,'"+pass+"' ,'"+firstName+ "' ,'"+ lastName +"');");	
			
			//System.out.println("After insert statement ");
			return true;
		}
		catch ( Exception err ) 
		{
			err.getStackTrace();
			return false;
		}
	}
	
	public boolean userLookUp(String user, String pass) throws SQLException
	{
		boolean lookUp = false;
		String query = "Select * from Lab6.lab6 where UserName = '" + user+ "';";
		dbconn=instance.newConnection();
		sql=dbconn.prepareStatement(query);

		results=sql.executeQuery();
		
		while(results.next())
		{
			//System.out.println("while loop");
			//System.out.println(results.getString("UserName"));
			if(results.getString("UserName").contains(user) )
			{
				lookUp = true;
				//System.out.println(lookUp);
			}
		}
		
		//System.out.println("query="+query);

		dbconn.close();
		return lookUp;
		
	}
	public static void main(String[] args) 
	{	
		//instance.entry("Golf");
	}
}