package StateMachine;

import data.Document;
import data.TemporaryIndex;


public class Parser {

	/* give a document in input and this function return the temporaryIndex built by 
	 * parsing this document into the state machine
	 */
	public TemporaryIndex parse(Document document){
		TemporaryIndex temporaryIndex = new TemporaryIndex(document.getId());
		
		State currentState = StateMachineBuilder.getStateMachine();
		
		for(int i=0;i<document.getContent().length();i++){
			char a =document.getContent().charAt(i);
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
