package buildings;

import States.State;
import main.Entity;
import main.Game;

public class Farm extends Building {

	private State build;
	private State busy;
	private State broken;

	public Farm(Game game, int x, int y) {
		super(game, x, y);
		build = new State();
		busy = new State();
		broken = new State();
		setAnimation(build);
	}

}
