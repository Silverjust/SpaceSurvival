package buildings;

import states.BuildingWait;
import states.BuildingWork;
import main.Entity;
import main.Game;
import main.Human;
import main.Unit;

public abstract class Building extends Entity {

	public int x;
	public int y;
	protected BuildingWork build;
	protected BuildingWork busy;
	protected BuildingWait broken;

	public Building(Game game, int x, int y) {
		super(game);
		this.x = x;
		this.y = y;
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

	public boolean registerAsWorker(Unit u) {
		if (((BuildingWork) getState()).needsWorker()) {
			((BuildingWork) getState()).addWorker(u);
			return true;
		}
		return false;
	}

	@Override
	public float getX() {
		return x;
	}

	@Override
	public float getY() {
		return y;
	}
}
