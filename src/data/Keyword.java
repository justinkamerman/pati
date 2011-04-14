package data;
import java.util.Vector;


public class Keyword {
	
	private int id;
	private String canon;
	private Vector<String> synonyms;
	
	
	
	public Keyword(String canon, int id, Vector<String> synonyms){
		this.setCanon(canon);
		this.setId(id);
		this.setSynonyms(synonyms);
		
	}
	

	public void setId(int id) {
		this.id = id;
	}
	public int getId() {
		return id;
	}
	public void setSynonyms(Vector<String> synonyms) {
		this.synonyms = synonyms;
	}
	public Vector<String> getSynonyms() {
		return synonyms;
	}
	public void setCanon(String canon) {
		this.canon = canon;
	}
	public String getCanon() {
		return canon;
	}

	
}
