package StateMachine;

import data.*;//Document;

import java.util.List;
import java.util.Vector;
public class Searcher {

	public Searcher() {
		// TODO Auto-generated constructor stub
	}

	public static List<Document> Search(String fullquery){
		Vector<Document> DocsRelated2Query=new Vector<Document>();	
				
		List<Integer> querykeywordIDs= KeywordDAO.getInstance().canonize(fullquery);
		List<Integer> CommonDocIds=IndexDAO.getInstance().Find(querykeywordIDs);
				
		for (int i=0;i<CommonDocIds.size();i++){
			DocsRelated2Query.add(DocumentDAO.getInstance().getDocumentById(CommonDocIds.get(i)));
		}
		
		return DocsRelated2Query;
		
	} 
	
		

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(Search("terrorism").get(2).getContent());
	
	    System.exit(0);
	
	}
	
	
	
}
