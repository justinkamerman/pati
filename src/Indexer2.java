import java.util.List;
import java.util.Vector;

import StateMachine.Parser1;
import StateMachine.StateMachineBuilder;
import data.Document;
import data.DocumentDAO;
import data.IndexDAO;
import data.Keyword;
import data.KeywordDAO2;
import data.TemporaryIndex;
import data.Timer;


public class Indexer2 {
	private static int docBatchSize=100;
	private static int totalKeywords=1000;	
	   
    
	public Indexer2() {
		// TODO Auto-generated constructor stub
	}

	public static void buildMachine(){
		 //System.out.println("Loading keywords");
			KeywordDAO2 Key= new KeywordDAO2(totalKeywords);
			List<Keyword> keywords = Key.getKeywords();
			        
	     
		// Building state machine
	        StateMachineBuilder sm=new StateMachineBuilder();
	        sm.createStateMachine(keywords);
	        
	 }
	
 	public static void IndexDocuments(int maxDocs){
 		//List<Document> documents=null;
		List<Document> documents =null;      
		
		if (Timer.getInstance().GetTime()==0){
			Timer.getInstance().UpdateTime(1);			
		}	
		
		int count =0;
		//System.out.println("Indexer starting. Document batch size set to " + docBatchSize);
		while ( count <maxDocs){
			System.out.println(count);
			if (count<=maxDocs){
            	count++;
            
			}else{break;}
			
				
			
	            ///List<Document> 
				documents = DocumentDAO.getInstance().getDocuments (docBatchSize);
				//System.out.println("Retrieved " + documents.size() + " unprocessed documents.");
			if (documents.size()==docBatchSize){	
						
				Vector<TemporaryIndex> TempIndexVector= new Vector<TemporaryIndex>();
	            
				for (Document document : documents){
	            	count++;
	            	TempIndexVector.add(Parser1.parse(document));
	            	// IndexDAO.getInstance().UpdateIndex(Parser1.parse(document))   ;         
				}
	          
	            IndexDAO.getInstance().UpdateIndex(TempIndexVector);// update TempIndex vector in db
	       		
	            TempIndexVector.clear();
	            documents.clear();
	         
			}else{
				
	        	   
	        	   	Vector<TemporaryIndex> TempIndexVector= new Vector<TemporaryIndex>();
		            for (Document document : documents){
		            	count++;
		                TempIndexVector.add(Parser1.parse(document));
		                //IndexDAO.getInstance().UpdateIndex(Parser1.parse(document))   ;         
		            }
		          
		            IndexDAO.getInstance().UpdateIndex(TempIndexVector);
		       		TempIndexVector.clear();
		            documents.clear();
	        	   
		         		                  	 
		 		   //update Time and date in db
		 		    Timer.getInstance().UpdateTime(2);
		 		   
		 		   
	        	   System.exit(0);
	        	   
	           }
	          
	            
	        }//while

	        
	      
	}
	
	public static void IndexDocuments(){
 		   
	if (Timer.getInstance().GetTime()==0){
		Timer.getInstance().UpdateTime(1);			
	}	
	int count =0;
	while (count<10000){
				
	    List<Document> documents = DocumentDAO.getInstance().getDocuments (docBatchSize);
				
				
			if (documents.size()==docBatchSize){	
						
				Vector<TemporaryIndex> TempIndexVector= new Vector<TemporaryIndex>();
	            
				for (Document document : documents){
					count	++;
					System.out.println("Document ID: "+ document.getId()+" Indexed");
					TempIndexVector.add(Parser1.parse(document));
					         
				}
	          
				IndexDAO.getInstance().UpdateIndex(TempIndexVector);// update TempIndex vector in db
	       		TempIndexVector.clear();
				documents.clear();
	         
			}else{
				
	        	Vector<TemporaryIndex> TempIndexVector= new Vector<TemporaryIndex>();
		            
	        	for (Document document : documents){
	        		count	++;
	        		System.out.println("Document ID: "+ document.getId()+" Indexed");
	        		TempIndexVector.add(Parser1.parse(document));
		           	//IndexDAO.getInstance().UpdateIndex(Parser1.parse(document))   ;         
		        }
		          
		        IndexDAO.getInstance().UpdateIndex(TempIndexVector);
		        TempIndexVector.clear();
		        documents.clear();
	        	         		                  	 
		 		//update Time and date in db
		 		Timer.getInstance().UpdateTime(2);
		 		 System.out.println("Terminating Process: No more unprocessed Documents");
		 		System.exit(0);// Terminate the process as there are no more unprocessed documents
	        	   
	           }// end else
	          
	            
	 }//end while
	System.out.println("Killing the process and starting again");
	System.exit(0);
  
}
	
	public static void Run(){
		
		buildMachine();//build state machine
		
		IndexDocuments();// start indexing documents
				
	}
	
	
	public static void main(String[] args) {
			
				Run();
		
	}

}
