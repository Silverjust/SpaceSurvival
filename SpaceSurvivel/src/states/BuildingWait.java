package states;

import buildings.Entity;
import states.State;

public class BuildingWait extends State {

	private State previousState;

	public BuildingWait() {
	}

	@Override
	public void onStart(Entity e) {
		previousState = e.getState();
	}

	public void continueState(Entity e) {
		e.setState(previousState, this);
	}
}
