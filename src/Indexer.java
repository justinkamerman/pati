/**
 * $Id$ 
 *
 * $LastChangedDate$ 
 * 
 * $LastChangedBy$
 */


import java.util.logging.Logger;
import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import snaq.db.ConnectionPoolManager;
import java.io.IOException;
import java.util.List;

import data.Document;
import data.DocumentDAO;


public class Indexer extends Thread
{
    private static Logger log = Logger.getLogger (Indexer.class.getName());
    private Options __opt;
    private CommandLine __cl;
    private int __docBatchSize = 10;
    private ConnectionPoolManager __cpm = null;
    private boolean __shutdown = false;
    private Thread __main;


    private Indexer ()
    {
        __opt = new Options(); 
        __opt.addOption("h", false, "Print help");
        __opt.addOption("c", true, "document batch size (default " + __docBatchSize + ")");
    }

     
    public static void main (String[] args)
    {
        new Indexer().execute (args);
    }

    
    private void printUsage (String message, int rc)
    {
        HelpFormatter hf = new HelpFormatter();
        hf.printHelp (message, __opt);
        System.exit (rc);
    }

    
    private void execute (String[] args)
    {
        __main = Thread.currentThread();
        Runtime.getRuntime().addShutdownHook (this);
        try
        {        
            __cl = (new BasicParser()).parse (__opt, args); 
            if ( __cl.hasOption ('h') ) printUsage ("help", 0);
            if ( __cl.hasOption ('c') ) __docBatchSize = Integer.parseInt (__cl.getOptionValue ('c'));
        }
        catch (ParseException ex)
        {
            printUsage (ex.getMessage(), 1);
            System.exit (1);
        }
        
        log.info ("Indexer starting. Document batch size set to " + __docBatchSize);

        while ( ! shutdown() )
        {
            List<Document> documents = DocumentDAO.getInstance().getDocuments (__docBatchSize);
            log.info ("Retrieved " + documents.size() + " unprocessed documents.");

            for (Document document : documents)
            {
                log.info (document.toString());
            }

            // Index documents - just sleep for now
            log.info ("Indexing documents");
            try
            {
                Thread.currentThread().sleep (5000);
            }
            catch (InterruptedException ex)
            {
                log.severe ("Interrupted sleep!");
            }
        }

        log.info ("Indexer shutting down");
        try
        {
            ConnectionPoolManager.getInstance().release ();
        }
        catch (IOException ex)
        {
            log.severe ("Exception releasing connection pool manager: " + ex.getMessage());
        }
    }


    /**
     * Shutdown hook
     */
    public void run ()
    {
        log.info ("Running shutdown hook");
        shutdownNotify ();
        try 
        {
            __main.join ();
        }
        catch (InterruptedException ex)
        {
            log.info ("Interrupted running shutdown hook.");
        }
    }

    
    private synchronized boolean shutdown ()
    {
        return __shutdown;
    }

    
    private synchronized void shutdownNotify ()
    {
        __shutdown = true;
    }
}




