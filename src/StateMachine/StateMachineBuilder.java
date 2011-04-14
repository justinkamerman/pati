package StateMachine;

import java.util.Vector;

import data.Keyword;


public class StateMachineBuilder {

	private static int lastStateId;
	private static State initialState;
	
	public static Vector<Character> CHARACTERS = new Vector<Character>();
	public StateMachineBuilder(){
	
		
		CHARACTERS.add('a');
		CHARACTERS.add('b');
		CHARACTERS.add('c');
		CHARACTERS.add('d');
		CHARACTERS.add('e');
		CHARACTERS.add('f');
		CHARACTERS.add('g');
		CHARACTERS.add('h');
		CHARACTERS.add('i');
		CHARACTERS.add('j');
		CHARACTERS.add('k');
		CHARACTERS.add('l');
		CHARACTERS.add('m');
		CHARACTERS.add('n');
		CHARACTERS.add('o');
		CHARACTERS.add('p');
		CHARACTERS.add('q');
		CHARACTERS.add('r');
		CHARACTERS.add('s');
		CHARACTERS.add('t');
		CHARACTERS.add('u');
		CHARACTERS.add('v');
		CHARACTERS.add('w');
		CHARACTERS.add('x');
		CHARACTERS.add('y');
		CHARACTERS.add('z');
		CHARACTERS.add('-');
		CHARACTERS.add(' ');
		
		
	}

	public State createStateMachine(Vector<Keyword> keywords){
		
		
		State initialState = new State(0);
		lastStateId=0;
		
		/*********************************/
		//Construction of the goToFunction
		/*********************************/
		
		//construction of the goto graph
		for(Keyword keyword : keywords){
			enterKeyword(keyword.getId(),keyword.getCanon(), initialState);
			for(String synonym : keyword.getSynonyms()){
				enterKeyword(keyword.getId(),synonym, initialState);
			}
			
		}
		//construction of the first state transitions
		for(Character charac : CHARACTERS){
			if (initialState.getGoTo().belongsTo(charac)==-1){
				initialState.getGoTo().add(charac, initialState);
			}
		}
		
		
		/************************************/
		//Construction of the failure function
		/************************************/
		
		
		Vector<State> queue = new Vector<State>();
		
		// Computation of the failure function for each state of depth 1
		Vector<State> depth1State = initialState.getGoTo().getNextState();
		for (State s : depth1State){
			if (s.getName()!=0){
				s.setFail(initialState);
				queue.add(s);
			}
		}
		int iteration = 0;
		// Computation of the failure function for other states
		while(queue.size()!=0){
			//paper's notation
			State r = queue.get(0);
			queue.remove(0);
			GoToFunction currentGoTo = r.getGoTo();
			for (int i =0;i<currentGoTo.getNextState().size();i++){
				//paper's notation
				State s= currentGoTo.getNextState(i);
				char a = currentGoTo.getNextCharacter(i);
				queue.add(currentGoTo.getNextState(i));
				//paper's notation
				State state = r.getFail();
				while(state.getGoTo().belongsTo(a)==-1){
					state = state.getFail();
				}
				s.setFail(state.getGoTo().getStateCorrespondingTo(a));
				s.getOutput().addAll(s.getFail().getOutput());	
			}
		}
				
		return initialState;
	}
	
	public void enterKeyword(int keywordId, String keyword, State initialState){
		State state=initialState;
		for (int i=0;i<keyword.length();i++){
			//if the char doesn't belong to the goto transition
			int nextStateIndex=state.getGoTo().belongsTo(keyword.charAt(i));
			if(nextStateIndex==-1){
				State newState= new State(lastStateId +1);
				lastStateId = lastStateId +1;
				state.getGoTo().add(keyword.charAt(i), newState);
				state=newState;
			}else{
				state = state.getGoTo().getNextState(nextStateIndex);
			}
		}
		
		state.addOutput(keywordId);
		
	}
	
	
	public void printGraph(State initialState){
		Vector<State> queue = new Vector<State>();
		queue.add(initialState);
		
		//print for all states from smaller depth to higher depth
		while(queue.size()!=0){
		State currentState= queue.get(0);
		System.out.println("STATE : " + currentState.getName() );
		
		//print goTo function
		System.out.println("\t goTo function :");
		if(currentState.getGoTo().getNextState().size()!=0){
			for(int i=0;i<currentState.getGoTo().getNextState().size();i++){
				if(currentState.getGoTo().getNextState().get(i).getName()!=0){
					queue.add(currentState.getGoTo().getNextState().get(i));
				}
				System.out.println("\t \t" + currentState.getGoTo().getNextCharacter(i)+ " -> " + currentState.getGoTo().getNextState(i).getName() );
			}
		}else{
			System.out.println("\t \t No GoTo");
		}
			
		
		//print outputs
		System.out.println("\t output :");
		if(currentState.getOutput().size()!=0){
			for(int i=0;i<currentState.getOutput().size();i++){
				System.out.println("\t \t" + currentState.getOutput().get(i));
			}
		}else{
			System.out.println("\t \t No output");
		}
		
		//print failure function
		System.out.println("\t failure case :");
			if (currentState.getFail()!=null){
				System.out.println("\t \t" + currentState.getFail().getName());
			}else{
				System.out.println("\t \t no failure function" );
			}
		
		
		queue.remove(0);
		
		
		}
	}
	
	
	
	
	
}
