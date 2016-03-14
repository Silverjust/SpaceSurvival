package buildings;

import main.Entity;
import main.Game;
import states.BuildingWork;

public abstract class Building extends Entity {

	protected int x;
	protected int y;
	protected BuildingWork collect;
	protected BuildingWork build;
	public Building(Game game, int x, int y) {
		super(game);
		this.x = x;
		this.y = y;
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
