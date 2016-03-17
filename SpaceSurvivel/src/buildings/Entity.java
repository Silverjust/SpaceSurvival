package buildings;

import main.Game;
import processing.core.PApplet;
import states.BuildingWork;
import states.State;

public abstract class Entity {

	public Game game;
	private State state;

	public Entity(Game game) {
		this.game = game;
	}

	public void update() {
		getState().update(this);
	}

	public abstract void draw(PApplet app);

	public void setState(State a, Object o) {
		if (state != null)
			state.onEnd(this);
		if (a == null)
			System.out.println("Entity.setState()   u stupid? " + o + " tried to setState null");
		a.onStart(this);
		System.out.println("Entity.setState()" + a + o);
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

	/** empty */
	public void startGui() {
		System.out.println(this.getClass().getSimpleName() + " has no Gui");
	}

}
