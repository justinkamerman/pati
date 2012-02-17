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
            PreparedStatement stmt = con.prepareStatement 
                ("SELECT ID, BlogContent, Author, Title, Link FROM blog WHERE ID = ?");
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
     * Return up to 'count' unprocessed documents and mark them as
     * 'processed'.
     */
    public List<Document> getDocuments (int count)
    {
        ArrayList<Document> documents = new ArrayList<Document>();
        Connection con = null;
        try 
        {
            con = __cpm.getConnection (__connectionPoolName);
            con.setAutoCommit (false);
            PreparedStatement selectStmt = con.prepareStatement 
                ("SELECT Id, Content, Author, Title, Link, Processed " + 
                 "FROM Document WHERE Processed IS NULL LIMIT 0,? FOR UPDATE");
            PreparedStatement insertStmt = con.prepareStatement 
                ("INSERT INTO DocumentProcessed (DocumentId, Processed) VALUES ( ?, 1)");
            selectStmt.setInt (1, count);
            ResultSet rs = selectStmt.executeQuery ();
            
            while (rs.next()) 
            {
                Document document = new Document (rs.getInt ("Id"),
                                                  rs.getString ("Content"),
                                                  rs.getString ("Author"),
                                                  rs.getString ("Title"),
                                                  rs.getString ("Link"));
                documents.add (document);
                insertStmt.setInt (1, document.getId());
                insertStmt.executeUpdate ();
            }
            
            con.commit();
        } 
        catch(SQLException ex) 
        {
            log.severe ("Exception retrieving unprocessed documents: " + ex.toString());
            try
            {
                con.rollback ();
            }
            catch (SQLException ex2)
            {
                log.severe ("Exception rolling back transaction: " + ex2.toString());
            }
        } 
        finally 
        {
            try
            {
                con.setAutoCommit (true);
                con.close ();
            }
            catch (SQLException ex)
            {
                log.severe ("Exception closing database connection: " + ex.toString ());
            }
        }
  
        return documents; 
    }
}

