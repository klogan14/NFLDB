package Lab6_485;
import java.awt.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Scanner;
import java.sql.ResultSet;
import java.sql.SQLException;
 
public class automateImport
{
    public static void main(String[] args) throws FileNotFoundException 
    {
    		File personFile = new File("/Users/be0754kc/Documents/CS485/Person.csv");
        Scanner scan = new Scanner(personFile);
        scan.nextLine();
        while(scan.hasNext())
        {
        		int id,Team_ID,JerseyNum,Height,Weight,Salary,age,years;
        		String Type,FirstName,LastName,College,CollegeState,HomeTown,HomeTownState,PositionGroup;
        		String s = scan.nextLine();
        		String[] personArray =  (s.split(","));
        		id = Integer.parseInt(personArray[0]);
        		FirstName = personArray[1];
        		LastName = personArray[2];
        		age = Integer.parseInt(personArray[3]);
        		years = Integer.parseInt(personArray[4]);
        		College = personArray[5];
        		CollegeState = personArray[6];
        	    Salary = Integer.parseInt(personArray[7]);
        		HomeTown = personArray[8];
        	    HomeTownState = personArray[9];
        	    String query = "INSERT INTO NFLSchema.Person (`ID`, `FirstName`, `LastName`, `Age`, `Years`, `College`, `CollegeState`, `Salary`,`HomeTown`, `HomeTownState`)" 
        	    +"VALUES ( '"+id+"' ,'"+FirstName+"' ,'"+LastName+"' ,'"+age+"' ,'"+years+"' ,'"+College+"' ,'"+CollegeState+"' ,'"+Salary+"' ,'"+HomeTown+"' ,'"+HomeTownState+"');";
        	    //, `Position`, `Height`, `Weight`, `PositionGroup`
        	    //,'"+ Position +"','"+Height+"' ,'"+Weight+"' ,'"+PositionGroup+"'
        	    System.out.println(query);
        		//System.out.println(id + FirstName+LastName + age + years + College + CollegeState + salary + HomeTown + HomeTownState);
        	    DBentry DBentry=new DBentry();
        		boolean flag=DBentry.entry(query);
        }
        
    		//DBase db = new DBase();
        //Connection conn = db.connect("jdbc:mysql://localhost:3306/test","root","password");
        //db.importData(conn,args[0]);
    }
 
}
 
class DBase
{
    public DBase()
    {
    }
 
    public Connection connect(String db_connect_str, 
  String db_userid, String db_password)
    {
        Connection conn;
        try
        {
            Class.forName(  
    "com.mysql.jdbc.Driver").newInstance();
 
            conn = DriverManager.getConnection(db_connect_str, 
    db_userid, db_password);
         
        }
        catch(Exception e)
        {
            e.printStackTrace();
            conn = null;
        }
 
        return conn;    
    }
     
    public void importData(Connection conn,String filename)
    {
        Statement stmt;
        String query;
 
        try
        {
            stmt = conn.createStatement(
    ResultSet.TYPE_SCROLL_SENSITIVE,
    ResultSet.CONCUR_UPDATABLE);
 
            query = "LOAD DATA INFILE '"+filename+
    "' INTO TABLE NFLSchema.Person (text,price);";
 
            stmt.executeUpdate(query);
                 
        }
        catch(Exception e)
        {
            e.printStackTrace();
            stmt = null;
        }
    }
};
