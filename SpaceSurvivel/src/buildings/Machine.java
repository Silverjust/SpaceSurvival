package buildings;

import states.Wait;
import states.State;
import main.Game;

public abstract class Machine extends Building {

	protected State busy;
	protected Wait broken;
	public Machine(Game game, int x, int y) {
		super(game, x, y);

		this.game = game;
	}

	@Override
	public void update() {
		super.update();
	}

	/** empty */
	public void addOutput() {

	}
}
