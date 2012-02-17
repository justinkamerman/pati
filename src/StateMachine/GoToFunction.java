package StateMachine;
import java.util.Vector;

//class that does a mapping between a letter and a state

public class GoToFunction {

	private Vector<Character> nextCharacter;
	private Vector<State> nextState;
	
	public GoToFunction(){
		nextCharacter = new Vector<Character>();
		nextState =new Vector<State>();
	}

	public State getNextState(int index){
		return nextState.get(index);
	}
	
	public Character getNextCharacter(int index){
		return nextCharacter.get(index);
	}

	public void add(char a, State s){
		nextCharacter.add(a);
		nextState.add(s);
	}

	//check if the character a belongs to the list of characters
	public Integer belongsTo(char a){
		if (nextCharacter !=null){
			return nextCharacter.indexOf(a);
		}else{
			return -1;
		}
	}


	public Vector<State> getNextState() {
		return nextState;
	}
	
	public Vector<Character> getNextCharacter() {
		return nextCharacter;
	}

	//return the state corresponding to the transition with character a
	//we consider that such a transitions exists if the function is called
	public State getStateCorrespondingTo(char a) {
		return nextState.get(nextCharacter.indexOf(a));
		
	}
	
}
