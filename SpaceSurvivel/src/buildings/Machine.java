package buildings;

import states.BuildingWait;
import states.BuildingWork;
import main.Game;

public abstract class Machine extends Building {

	protected BuildingWork busy;
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

	@Override
	public void Statefinished(BuildingWork work) {
		if (work == build) {
			setState(busy, this);
		}
	}

	/** empty */
	public void addOutput() {

	}
}
