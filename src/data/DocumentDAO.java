/**
 * $Id$ 
 *
 * $LastChangedDate$ 
 * 
 * $LastChangedBy$
 */
package data;

import java.io.IOException;
import java.util.logging.Logger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import snaq.db.ConnectionPoolManager;


public class DocumentDAO
{
    private static Logger log = Logger.getLogger (DocumentDAO.class.getName());
    private static DocumentDAO __instance = new DocumentDAO (); //singleton instance
    private ConnectionPoolManager __cpm = null;
    private String __connectionPoolName = "mysql";


    private DocumentDAO ()
    {
        try
        {
            __cpm = ConnectionPoolManager.getInstance ();
        }
        catch (IOException ex)
        {
            log.severe ("Exception creating connection pool: " + ex.toString());
        }
    }
    
    
    public static DocumentDAO getInstance ()
    {
        return __instance;
    }


    public Document getDocumentById (int id)
    {
        Document document = null;
        try 
        {
            Connection con = __cpm.getConnection (__connectionPoolName);
            PreparedStatement stmt = con.prepareStatement ("SELECT ID, BlogContent, Author, Title, Link FROM blog WHERE ID = ?");
            stmt.setInt (1, id);
            ResultSet rs = stmt.executeQuery ();
            
            if (rs.next()) 
            {
                document = new Document (rs.getInt ("ID"),
                                         rs.getString ("BlogContent"),
                                         rs.getString ("Author"),
                                         rs.getString ("Title"),
                                         rs.getString ("Link"));
            }
            
            con.close();
        } 
        catch(SQLException ex) 
        {
            log.severe ("Exception retrieving document with ID " + id + ": " + ex.toString());
        } 
  
        return document;
    }


    /**
     * Return count documents and mark them as 'processed' so that
     * they will not be returned agian by another call to this method.
     */
    public List<Document> getDocuments (int count)
    {

        return new ArrayList<Document> ();
    }
}