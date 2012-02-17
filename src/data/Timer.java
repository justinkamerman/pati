package data;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

import snaq.db.ConnectionPoolManager;

public class Timer {
	
	
	private static Logger log = Logger.getLogger (Timer.class.getName());
	private static Timer __instance = new Timer ();
    private ConnectionPoolManager __cpm = null;
    private String __connectionPoolName = "mysql";

	public Timer() {
		// TODO Auto-generated constructor stub
		try 
		 {
	            __cpm = ConnectionPoolManager.getInstance ();
	      
	        }
	        catch (IOException ex)
	        {
	            log.severe ("Exception creating connection pool: " + ex.toString());
	        }        
	
	}
	
	
	
	
	public static Timer getInstance ()
    {
        return __instance;
    }

	
	public void UpdateTime(int ID){
		
		String sqlquery="Update timer set LatestTime= NOW() where ID="+ ID +";";
		
		try 
        {
            Connection con = __cpm.getConnection (__connectionPoolName);
            PreparedStatement stmt = con.prepareStatement(sqlquery);
            stmt.execute();
            
            con.close();
            
        } 
        catch(SQLException ex) 
        {
            log.severe ("Error in Updating Temporary Index");
        } 
		
		}
	
	
	
	public int GetTime(){
		int time=-1;
		String sqlquery="Select LatestTime from timer Where ID=1;";
		
		try 
        {
            Connection con = __cpm.getConnection (__connectionPoolName);
            PreparedStatement stmt = con.prepareStatement(sqlquery);
            stmt.execute();
            ResultSet rs = stmt.executeQuery ();
            while (rs.next()) 
            {
              time=rs.getInt(1);  
            }
            con.close();
            
        } 
        catch(SQLException ex) 
        {
            log.severe ("Error in Updating Temporary Index");
        } 
		return time;
		}
	
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
