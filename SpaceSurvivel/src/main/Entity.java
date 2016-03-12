package main;

import processing.core.PApplet;
import states.BuildingWork;
import states.State;

public abstract class Entity {

	protected Game game;
	private State state;

	public Entity(Game game) {
		this.game = game;
	}

	public void update() {
		getState().update(this);
	}

	public abstract void draw(PApplet app);

	public void setState(State a) {
		a.startState(this);
		this.state = a;
	}

	public abstract float getX();

	public abstract float getY();

	/** empty */
	public void Statefinished(BuildingWork work) {

	}

	public State getState() {
		return state;
	}

	/** empty */
	public void onSpawn() {

	}

	protected String getStateName() {
		return state.getClass().getSimpleName();
	}

}
