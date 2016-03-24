package buildings;

import states.BuildingWait;
import states.Storing;
import main.Game;

public abstract class Machine extends Building {

	protected Storing busy;
	protected BuildingWait broken;
	protected BuildingWait wait;

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
