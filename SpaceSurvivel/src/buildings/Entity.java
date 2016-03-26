package buildings;

import java.util.ArrayList;

import main.Game;
import processing.core.PApplet;
import states.Wait;
import states.State;

public abstract class Entity {

	public Game game;
	private ArrayList<State> states = new ArrayList<State>();
	public Wait wait;

	public Entity(Game game) {
		this.game = game;
	}

	public void update() {
		getState().update(this);
	}

	public void draw() {
		drawAt(game.app, getX() * Game.gridSize, getY() * Game.gridSize);
	}

	public abstract void drawAt(PApplet app, float x, float y);

	public void addState(State a, Object o) {
		if (a == null) {
			System.out.println("Entity.setState()   u stupid? " + o + " tried to setState null");
			return;
		}
		// System.out.println("Entity.addState()" + a.getClass().getSimpleName()
		// + " "+o.getClass().getSimpleName());
		if (states.isEmpty())
			states.get(0).onStart(this);
		states.add(a);
	}

	public void setState(State a, Object o) {
		if (!states.isEmpty())
			getState().onEnd(this);
		if (a == null) {
			System.out.println("Entity.setState()   u stupid? " + o + " tried to setState null");
			return;
		}
		a.onStart(this);
		// System.out.println("Entity.setState()" + a.getClass().getSimpleName()
		// + " "+o.getClass().getSimpleName());
		states.add(0, a);
	}

	public State getState() {
		if (states.isEmpty())
			return null;
		return states.get(0);
	}

	public void endState() {
		if (!states.isEmpty())
			getState().onEnd(this);
		states.remove(0);
		if (states.isEmpty())
			states.add(wait);

		if (states.get(0) == null) {
			System.out.println("Entity.endState()");
			return;
		}
		states.get(0).onStart(this);
	}

	public abstract float getX();

	public abstract float getY();

	/** empty */
	public void onSpawn() {

	}

	protected String getStateName() {
		return getState().getClass().getSimpleName();
	}

	protected String getStateNames() {
		String s = "";
		for (State state : states) {
			s += state.getClass().getSimpleName() + "\n";
		}
		return s;
	}

	/** empty */
	public void startGui() {
		System.out.println(this.getClass().getSimpleName() + " has no Gui");
	}

	public void dispose() {

	}

	public void onEnd() {
		endState();
	}

}
