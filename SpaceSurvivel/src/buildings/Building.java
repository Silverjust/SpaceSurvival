package buildings;

import main.Unit;
import States.Work;
import main.Entity;
import main.Game;

public abstract class Building extends Entity {

	public int x;
	public int y;
	protected Work build;
	protected Work busy;
	protected Work broken;

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
	public void Statefinished(Work work) {
		if (work == build) {
			setState(busy);
		}
	}

	public void callWorker() {
		for (Unit e : game.getEntities()) {
			if (!e.hasWork()) {
				System.out.println("Building.callWorker()" + e);
				e.setTarget(this);
			}
		}
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
