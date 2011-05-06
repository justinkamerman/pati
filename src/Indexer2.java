import java.util.List;
import StateMachine.Parser1;
import StateMachine.StateMachineBuilder;
import data.Document;
import data.DocumentDAO;
import data.IndexDAO;
import data.Keyword;
import data.KeywordDAO;
import data.KeywordDAO2;


public class Indexer2 {
	private static int docBatchSize=100;
	private static int maxDocs=1000;
    private static int totalKeywords=100;	
	
	public Indexer2() {
		// TODO Auto-generated constructor stub
	}

	public static void IndexDocuments(){
		        
        int count =0;
		System.out.println("Indexer starting. Document batch size set to " + docBatchSize);
		while ( count <maxDocs){
			if (count<=maxDocs){
            	count++;
            }else{break;}
	            List<Document> documents = DocumentDAO.getInstance().getDocuments (docBatchSize);
	            System.out.println("Retrieved " + documents.size() + " unprocessed documents.");
           
	            for (Document document : documents){
	            count++;
	            System.out.println(document.toString());
	            System.out.println("Indexing Document ID: "+ document.getId());	
	                // update indexx for a given document
	                IndexDAO.getInstance().UpdateIndex(Parser1.parse(document));
	            }
	            
	        }

	        
	      
	}
	 public static void buildMachine(){
		 System.out.println("Loading keywords");
			KeywordDAO2 Key= new KeywordDAO2(totalKeywords);
			List<Keyword> keywords = Key.getKeywords();
			//List<Keyword> keywords = KeywordDAO.getInstance().getKeywords(totalKeywords);
	        System.out.println("Fetched "+ totalKeywords+" Keywords");
	     
		// Build state machine
	        StateMachineBuilder sm=new StateMachineBuilder();
	        sm.createStateMachine(keywords);
	        
	 }
	
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		double start, end , time;
		start=System.currentTimeMillis();
		buildMachine();
		end=System.currentTimeMillis();
		time= end-start;
		//System.out.println("Time taken to BuildState Machine with "+ totalKeywords + " Keywords is "+ time+ " ms");
		//System.out.println("");
		
		double start1, end1 , time1;
		start1=System.currentTimeMillis();
		IndexDocuments();
		end1=System.currentTimeMillis();
		time1= end1-start1;
		System.out.println("");
		System.out.println("***** Indexing Complete *****");
		System.out.println("");
		System.out.println("Time taken to BuildState Machine with "+ totalKeywords + " Keywords is "+ time+ " ms");
		System.out.println("");
		System.out.println("");
		System.out.println("Time taken to Index "+ maxDocs +" Documents is : "+ time1+ " ms");
		System.exit(0);// terminate the JVM
	
	
	}

}
