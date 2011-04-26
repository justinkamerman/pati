/**
 * $Id$ 
 *
 * $LastChangedDate$ 
 * 
 * $LastChangedBy$
 */
package data;

import java.util.List;
import java.util.Vector;


public class Keyword {
	
    private int id = 0;
    private String canon;
    private Vector<String> synonyms;
	
    
    /**
     * Create a Keyword instance given an id, canon, and
     * comma-separated list of synonyms.
     */
    public Keyword (int id, String canon, String synonyms)
    {
        this.id = id;
        this.setCanon (canon);
        this.synonyms = new Vector<String> ();
        this.synonyms.add (canon);
        String[] tokens = synonyms.split (",");
        for (int i = 0; i < tokens.length; i++)
        {
            this.synonyms.add (tokens[i].trim());
        }
    }
	

    public Keyword (String canon, List<String> synonyms)
    {
        this.setCanon(canon);
        this.setSynonyms(synonyms);
    }
	

    public int getId() 
    {
        return this.id;
    }

    public void setSynonyms(List<String> synonyms) 
    {
        this.synonyms = new Vector (synonyms);
    }


    public List<String> getSynonyms()
    {
        return synonyms;
    }


    public void setCanon(String canon) 
    {
        this.canon = canon;
    }


    public String getCanon() 
    {
        return canon;
    }


    public void addSynonym (String synonym)
    {
        this.synonyms.add (synonym);
    }

    
    public String toString ()
    {
        StringBuffer sb = new StringBuffer ();
        sb.append ("[id=" + getId() + "][canon=" + canon + "][synonyms=[");
        for (String synonym : synonyms)
        {
            sb.append (synonym + ", ");
        }
        sb.append ("]]");
        return sb.toString();
    }
}
