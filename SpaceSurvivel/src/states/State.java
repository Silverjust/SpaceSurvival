package states;

import main.Entity;

public class State {
	public boolean needsWorker() {

		return false;
	}

	/** empty 
	 * @param e TODO*/
	public void startState(Entity e) {
	}

	/** empty */
	public void update(Entity e) {
	}

}
