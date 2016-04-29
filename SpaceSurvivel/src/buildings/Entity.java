package buildings;

import java.util.ArrayList;

import main.Game;
import processing.core.PApplet;
import states.Wait;
import states.State;

public abstract class Entity {

	private static final boolean showStateChanges = false;
	public Game game;
	private ArrayList<State> states = new ArrayList<State>();
	public Wait wait;
	protected String ingameName;

	public Entity(Game game) {
		this.game = game;
		ingameName = game.contentListHandler.getNameFor(this);
	}

	public void update() {
		getState().update(this);
	}

	public void draw() {
		drawAt(game.app, getX() * Game.gridSize, getY() * Game.gridSize);
	}

	public abstract void drawAt(PApplet app, float x, float y);

	public void addState(State a, Object o) {
		State old = getState();
		if (a == null) {
			System.out.println("Entity.setState()   u stupid? " + o + " tried to setState null");
			return;
		}
		if (showStateChanges)
			System.out.println("Entity.addState()" + a.getSimpleName() + " " + o.getClass().getSimpleName());
		states.add(a);// adds a at end
		if (states.isEmpty())
			a.onStart(this, old);
	}

	public void insertState(State a, Object o) {
		State old = getState();
		if (a == null) {
			System.out.println("Entity.setState() u stupid? " + o + " tried	 to setState null");
			return;
		}
		if (!states.isEmpty())
			old.onEnd(this);
		if (showStateChanges)
			System.out.println("Entity.insertState()" + a.getSimpleName() + " by " + o.getClass().getSimpleName());
		if (states.remove(a))// remove all lower states of a
			System.out.println("Entity.insertState()removed lower state of " + a.getSimpleName());
		states.add(0, a);// adds a at top
		a.onStart(this, old);
	}

	public void endState() {
		State old = getState();
		if (!states.isEmpty())
			getState().onEnd(this);
		states.remove(0);
		if (states.isEmpty())
			states.add(wait);

		if (states.get(0) == null) {
			System.out.println("Entity.endState()");
			return;
		}
		states.get(0).onStart(this, old);
	}

	public State getState() {
		if (states.isEmpty())
			return null;
		return states.get(0);
	}

	public boolean statesContains(State state) {
		return states.contains(state);
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

	public String getIngameName() {
		return ingameName;
	}

}
