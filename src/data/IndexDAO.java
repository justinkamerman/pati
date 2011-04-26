package data;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.logging.Logger;
import snaq.db.ConnectionPoolManager;

/**
 * @author raman
 *
 */
public class IndexDAO {

	private static Logger log = Logger.getLogger (IndexDAO.class.getName());
	private static IndexDAO __instance = new IndexDAO ();
    private ConnectionPoolManager __cpm = null;
    private String __connectionPoolName = "mysql";
	

	public IndexDAO() {
		 
		try 
		 {
	            __cpm = ConnectionPoolManager.getInstance ();
	      
	        }
	        catch (IOException ex)
	        {
	            log.severe ("Exception creating connection pool: " + ex.toString());
	        }        
	
	}

	public static IndexDAO getInstance ()
    {
        return __instance;
    }
	
	// This method is used to find list of document ids for given list of query keywords by looking in Index table in db
	public  List<Integer> Find(List<Integer> QueryKeywordIds){
		ArrayList<Integer> CommonDocIds=new ArrayList<Integer>();
		//Building sql query 
		String sqlquery= "SELECT DocumentId from Indexx where KeywordId in (";
		for (int i=0;i<QueryKeywordIds.size();i++)
      {
          if (i != 0) sqlquery += ",";
          sqlquery= sqlquery + QueryKeywordIds.get(i);
		}
		sqlquery=sqlquery+")";
	
		// end of building sql string query.
		
		 
	    try 
        {
            Connection con = __cpm.getConnection (__connectionPoolName);
            PreparedStatement stmt = con.prepareStatement(sqlquery);
            ResultSet rs = stmt.executeQuery ();
            while (rs.next()) 
            {
              CommonDocIds.add(rs.getInt(1));  
            }
            
            con.close();
        } 
        catch(SQLException ex) 
        {
            log.severe ("Error retrieving Document IDs for given Keyword IDs: " + ex);
        } 
  	
				
		return CommonDocIds;
	}
	
	
	
	// This method takes DeltaIndex of type TemporaryIndex(containing DocId and list<keywordIds>) 
	//  and update sql table called Index in db.  
	public void UpdateIndex(TemporaryIndex DeltaIndex){
		int DocumentId=DeltaIndex.getDocumentId();
		Vector<Integer>keywordIdlist=DeltaIndex.getKeywordList();// list of keyword ids to be added for given Document id.
		Vector<Integer> ExistingKeywordIds= getKeywordsList(DocumentId);// returns the list of keyword ids in Index table for given Document id.
		if (!keywordIdlist.isEmpty()){
		String sqlquery= "INSERT INTO Indexx (DocumentId,KeywordId)VALUES";
		for (int i=0;i<keywordIdlist.size();i++){
			if (!ExistingKeywordIds.contains(keywordIdlist.get(i))){
			
				if (i!=keywordIdlist.size()-1){
				sqlquery=sqlquery+"("+DocumentId+","+ keywordIdlist.get(i)+")"+",";
				}else {
					sqlquery=sqlquery+"("+DocumentId+","+ keywordIdlist.get(i)+")";
				}
				
				
				}
			}
		
		sqlquery=sqlquery+";";
		// at the end "sqlquery" is of the form
		//INSERT INTO test.Index (DocumentId,KeywordId)VALUES (7, 2),(7,3);
		System.out.println(sqlquery);
						 
	    try 
        {
            Connection con = __cpm.getConnection (__connectionPoolName);
            PreparedStatement stmt = con.prepareStatement(sqlquery);
            con.close();
        } 
        catch(SQLException ex) 
        {
            log.severe ("Error in Updating Temporary Index");
        } 
		
		}
		
	}
	
	// To avoid duplicate entries of documentid and keyword id while updating Index table
	// used in method UpdateIndex
	public Vector<Integer> getKeywordsList(int DocId){
		
		Vector<Integer> ExistingKeywordIds=new Vector<Integer>();
		try 
		 {
	            __cpm = ConnectionPoolManager.getInstance ();
	      
	        }
	        catch (IOException ex)
	        {
	            log.severe ("Exception creating connection pool: " + ex.toString());
	        }
	    try 
       {
           Connection con = __cpm.getConnection (__connectionPoolName);
           PreparedStatement stmt = con.prepareStatement("Select KeywordId from Indexx where DocumentId=?;");
           stmt.setInt (1, DocId);
           ResultSet rs = stmt.executeQuery ();
           while (rs.next()) 
           {
        	   ExistingKeywordIds.add(rs.getInt(1));  
           }
           
           con.close();
       } 
       catch(SQLException ex) 
       {
           log.severe ("Error in retrieving Keyword Ids");
       } 
		
			
		return ExistingKeywordIds;
		
		
		
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
    ArrayList<Integer> r=new ArrayList<Integer>();
    r.add(1);
    //r.add(2);
    System.out.println(getInstance().Find(r));
	
	
	}

}
