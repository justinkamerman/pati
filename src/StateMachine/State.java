package StateMachine;

import java.util.Vector;


public class State {

	private GoToFunction goTo;
	private State fail;
	private Vector<Integer> output;
	private int name;
	
	public State(int name){
		setGoTo(new GoToFunction());
		setFail(null);
		setOutput(new Vector<Integer>());
		setName(name);
	}

	public void setGoTo(GoToFunction goTo) {
		this.goTo = goTo;
	}
	public GoToFunction getGoTo() {
		return this.goTo;
	}

	public void setFail(State fail) {
		this.fail = fail;
	}

	public State getFail() {
		return fail;
	}

	public void setOutput(Vector<Integer> output) {
		this.output = output;
	}

	public Vector<Integer> getOutput() {
		return output;
	}
	
	public void addOutput(int o){
		output.add(o);
	}

	public void setName(int name) {
		this.name = name;
	}

	public int getName() {
		return name;
	}

	
	
	
	
	
}
