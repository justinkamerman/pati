import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

import snaq.db.ConnectionPoolManager;
import data.IndexDAO;


public class ClearDatabase {

	

	    private static ConnectionPoolManager __cpm = null;
	    private static String __connectionPoolName = "mysql";
		

		public ClearDatabase() {
			 
			try 
			 {
		            __cpm = ConnectionPoolManager.getInstance ();
		      
		        }
		        catch (IOException ex)
		        {
		            
		        }        
		
		}
		
		
		public static int Cleardb(){
	int IndexxRows=0;
	try 
     {
		__cpm = ConnectionPoolManager.getInstance ();
         Connection con = __cpm.getConnection (__connectionPoolName);
        
         
         
         PreparedStatement stmt1 = con.prepareStatement("Truncate Indexx;");
         PreparedStatement stm2 = con.prepareStatement("Truncate DocumentProcessed;");
         PreparedStatement stm3 = con.prepareStatement("Update timer set LatestTime= 0 where ID= 1 and 2;");
         
         stmt1.executeQuery ();
         stm2.executeQuery ();
         stm3.executeQuery();
             
        
         PreparedStatement stm4 = con.prepareStatement("Select count(*) from Indexx;");
         ResultSet rs=stm4.executeQuery ();
         while (rs.next()) 
         {
           IndexxRows= rs.getInt(1);  
         } 
         
         con.close();
 
     
     } 
     catch(SQLException ex) 
     {
         
     } catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
     
     //__cpm.release();
     return IndexxRows;
}


 
 
		public static void main (String[] args){
	System.out.println(Cleardb());
	
 }
 
 
 
 }