package buildings;

import java.util.ArrayList;

import main.Game;
import processing.core.PApplet;
import states.BuildingWork;
import states.State;

public abstract class Entity {

	public Game game;
	private ArrayList<State> states = new ArrayList<State>();

	public Entity(Game game) {
		this.game = game;
	}

	public void update() {
		getState().update(this);
	}

	public abstract void draw(PApplet app);

	public void setState(State a, Object o) {
		if (!states.isEmpty())
			getState().onEnd(this);
		if (a == null) {
			System.out.println("Entity.setState()   u stupid? " + o + " tried to setState null");
			return;
		}
		a.onStart(this);
		// System.out.println("Entity.setState()" + a + o);
		this.states.add(a);
	}

	public abstract float getX();

	public abstract float getY();

	public State getState() {
		if (states.isEmpty())
			return null;
		return states.get(0);
	}

	public State endState() {
		return states.remove(0);
	}

	/** empty */
	public void onSpawn() {

	}

	protected String getStateName() {
		return states.getClass().getSimpleName();
	}

	/** empty */
	public void startGui() {
		System.out.println(this.getClass().getSimpleName() + " has no Gui");
	}

}
