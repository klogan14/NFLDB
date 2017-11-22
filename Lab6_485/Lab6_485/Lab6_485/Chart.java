package Lab6_485;
import java.sql.*;
import java.io.*;
import org.jfree.ui.*;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.PieSectionLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.*;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.jdbc.JDBCPieDataset;

public class Chart {
	public static void main(String[] args) throws Exception {
		PreparedStatement sql;
		String query = "select * from NFLSchema.PlayerStateTotals;";
		
		Connection dbconn  = null;
		      /* Create MySQL Database Connection */
		try 
		{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			
			try 
			{			
				dbconn = DriverManager.getConnection("jdbc:mysql://localhost:3306","root","password");
				//System.out.println("gain the connection");
				 
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
			
		      sql = dbconn.prepareStatement(query);
			  // Statement statement = dbconn.createStatement( );
		      ResultSet resultSet = sql.executeQuery(query );
		      DefaultPieDataset dataset = new DefaultPieDataset( );
		      
		      while( resultSet.next( ) ) {
		         dataset.setValue( 
		         resultSet.getString( "HomeTownState" ) ,
		         Double.parseDouble( resultSet.getString("PlayersFromState")));
		      }
		      JFreeChart chart = ChartFactory.createPieChart("Players From States ", dataset, true, true, false );
				ChartPanel chartPanel = new ChartPanel(chart);
				//chartPanel.set
				chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
				ApplicationFrame f = new ApplicationFrame("Chart");
				PiePlot plot = (PiePlot) chart.getPlot();
				PieSectionLabelGenerator generator = new StandardPieSectionLabelGenerator("{0} = {1}")  ;
				plot.setLabelGenerator(generator);
				f.setContentPane(chartPanel);
				f.pack();
				f.setVisible(true);
		      int width = 560;    /* Width of the image */
		      int height = 370;   /* Height of the image */ 
		      File pieChart = new File( "PlayerStates1.jpg" );
		      ChartUtilities.saveChartAsJPEG( pieChart , chart , width , height );
	}
}