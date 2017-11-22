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
    		File personFile = new File("/Users/be0754kc/Documents/CS485/D_Player_Stats.csv");
        Scanner scan = new Scanner(personFile);
        scan.nextLine();
        while(scan.hasNext())
        {
        		int id,PassingYards,Carries,RushingYards,Receptions,RYds,TDs,FGMade,FGAttempt,GameStarts,FGAtt,SB_Appearances,SB_Wins,Championships,
        		Stadium_ID,Capacity;
        		double Tackles,Sacks,INTS, ForcedFumbles;
        		String Name,City, State,LastName,College,CollegeState,HomeTown,HomeTownState,PositionGroup,Playoff_Record;
        		String s = scan.nextLine();
        		String[] personArray =  (s.split(","));
        		id = Integer.parseInt(personArray[0]);
        		Tackles = Double.parseDouble(personArray[1]);
        		Sacks = Double.parseDouble(personArray[2]);
        		INTS = Double.parseDouble(personArray[3]);
        		ForcedFumbles = Double.parseDouble(personArray[4]);
//        		RYds = Integer.parseInt(personArray[5]);
//        		TDs = Integer.parseInt(personArray[6]);
//        	    GameStarts = Integer.parseInt(personArray[7]);
//        		FGMade = Integer.parseInt(personArray[8]);
//        	    FGAttempt = Integer.parseInt(personArray[9]);
        	    String query = "INSERT INTO NFLSchema.D_Player_Stats (`P_ID`, `Tackles`, `Sacks`, `INTS`, `ForcedFumbles`)" 
        	    +"VALUES ( '"+id+"' ,'"+Tackles+"' ,'"+Sacks+"' ,'"+INTS+"' ,'"+ForcedFumbles+"');";
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
