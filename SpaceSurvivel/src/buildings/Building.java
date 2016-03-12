package buildings;

import States.State;
import main.Entity;
import main.Game;
import processing.core.PApplet;

public abstract class Building {

	protected int x;
	protected int y;
	protected Game game;
	protected State state;

	public Building(Game game, int x, int y) {
		this.x = x;
		this.y = y;
		this.game = game;
	}

	public void update() {
		state.update();
		if (state.hasNotEnoughWorker()) {
			System.out.println("Building.update()call");
			callWorker();
		}
	}

	void callWorker() {
		for (Entity e : game.getEntities()) {
			if (!e.hasWork()) {
				System.out.println("Building.callWorker()"+e);
				e.setTarget(this);
			}
		}
	}

	public void draw(PApplet app) {
		app.fill(10);
		app.rect(x * Game.gridSize + 5, y * Game.gridSize + 5, 40, 40);

	}

	protected void setAnimation(State a) {
		state = a;
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

}
