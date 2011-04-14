package data;

import java.util.Vector;

public class TemporaryIndex {
	
	private Vector<Integer> keywordList;
	private int documentId;
	
	public TemporaryIndex(int documentId){
		setDocumentId(documentId);
		setKeywordList(new Vector<Integer>());
		
	}
	
	
	public void setDocumentId(int documentId) {
		this.documentId = documentId;
	}
	public int getDocumentId() {
		return documentId;
	}
	public void setKeywordList(Vector<Integer> keywordList) {
		this.keywordList = keywordList;
	}
	public Vector<Integer> getKeywordList() {
		return keywordList;
	}
	
	//add keyword to the list of keyword
	public void addKeyword(int keywordId){
		if(!keywordList.contains(keywordId)) this.keywordList.add(keywordId);
	}
	
}
