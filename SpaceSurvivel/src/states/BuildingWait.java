package states;

import buildings.Entity;
import states.State;

public class BuildingWait extends State {

	public BuildingWait() {
	}

	@Override
	public void onStart(Entity e) {
		e.setState(e.getState(), this);
	}

	public void continueState(Entity e) {
		e.endState();
	}
}
