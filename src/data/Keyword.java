package data;

import java.util.List;
import java.util.Vector;


public class Keyword {
	
	private int id;
	private String canon;
	private Vector<String> synonyms;
	
	
   public Keyword (String canon)
   {
       this.setCanon (canon);
       this.synonyms = new Vector<String> ();
   }
	

	public Keyword (String canon, List<String> synonyms){
		this.setCanon(canon);
		this.setSynonyms(synonyms);
	}
	
	public int getId() {
       return canon.hashCode();
	}
	public void setSynonyms(List<String> synonyms) {
       this.synonyms = new Vector (synonyms);
	}
	public List<String> getSynonyms() {
		return synonyms;
	}
	public void setCanon(String canon) {
		this.canon = canon;
	}
	public String getCanon() {
		return canon;
	}

    public void addSynonym (String synonym)
    {
        this.synonyms.add (synonym);
    }

    
    public String toString ()
    {
        StringBuffer sb = new StringBuffer ();
        sb.append ("[canon=" + canon + "][synonyms=[");
        for (String synonym : synonyms)
        {
            sb.append (synonym + ", ");
        }
        sb.append ("]]");
        return sb.toString();
    }
}
