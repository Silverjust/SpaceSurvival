package buildings;

import states.BuildingWait;
import states.BuildingWork;
import main.Entity;
import main.Game;
import main.Human;

public abstract class Machine extends Building {

	protected BuildingWork busy;
	protected BuildingWait broken;
	public Machine(Game game, int x, int y) {
		super(game,x,y);
		
		this.game = game;
	}

	@Override
	public void update() {
		if (getState().needsWorker()) {
			callWorker();
		}
		super.update();
	}

	@Override
	public void Statefinished(BuildingWork work) {
		if (work == build) {
			setState(busy, this);
		}
	}

	public void callWorker() {
		for (Entity e : game.getEntities()) {
			if (e instanceof Human && !((Human) e).hasWork() && getState().needsWorker()) {
				((Human) e).setTarget(this);
			}
		}
	}
}
