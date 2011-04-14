package data;

import java.util.Vector;

public class TemporaryIndex {
	
	private Vector<Keyword> keywordList;
	private int documentId;
	
	public TemporaryIndex(Vector<Keyword> keywordList,int documentId){
		setDocumentId(documentId);
		setKeywordList(keywordList);
		
	}
	
	
	public void setDocumentId(int documentId) {
		this.documentId = documentId;
	}
	public int getDocumentId() {
		return documentId;
	}
	public void setKeywordList(Vector<Keyword> keywordList) {
		this.keywordList = keywordList;
	}
	public Vector<Keyword> getKeywordList() {
		return keywordList;
	}
	
	
	
}
