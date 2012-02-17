package StateMachine;

import data.Document;
import data.TemporaryIndex;


public class Parser1 {
	
	
	public Parser1(){
		
	}
	/* give a document in input and this function return the temporaryIndex built by 
	 * parsing this document into the state machine
	 */
	public static TemporaryIndex parse(Document document){
		TemporaryIndex temporaryIndex = new TemporaryIndex(document.getId());
		
		State currentState = StateMachineBuilder.getStateMachine();
		String content = document.getContent().toLowerCase();
		content=content.replaceAll("[^a-zA-Z]", " ");
		
		for(int i=0;i<content.length();i++){
			char a =content.charAt(i);
			while( currentState.getGoTo().belongsTo(a)==-1){
				currentState= currentState.getFail();
			}
			currentState= currentState.getGoTo().getStateCorrespondingTo(a);
			//System.out.println(currentState.getNumber());
			if (currentState.getOutput().size()!=0){
				for(int j=0;j<currentState.getOutput().size();j++){
					temporaryIndex.addKeyword(currentState.getOutput().get(j));
				}
			}
		}
		
		
		
		
		return temporaryIndex;
		
	}
	
	
}
