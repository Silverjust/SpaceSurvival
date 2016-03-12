package main;

import States.State;
import States.Work;
import processing.core.PApplet;

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

	protected void setState(State a) {
		this.state = a;
	}

	public abstract float getX();

	public abstract float getY();

	/** empty */
	public void Statefinished(Work work) {

	}

	public State getState() {
		return state;
	}

}
