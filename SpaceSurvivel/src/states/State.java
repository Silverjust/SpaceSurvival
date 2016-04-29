package states;

import buildings.Entity;

public class State {

	protected boolean repeat;

	/** empty */
	public void onStart(Entity e, State old) {
	}

	/** empty */
	public void onEnd(Entity e) {
	}

	/** empty */
	public void update(Entity e) {
	}

	public String getSimpleName() {
		return this.getClass().getSimpleName();
	}

}
