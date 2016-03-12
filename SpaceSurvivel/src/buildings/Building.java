package buildings;

import States.Animation;
import main.Game;
import processing.core.PApplet;

public abstract class Building {

	protected int x;
	protected int y;
	protected Game game;
	protected Animation state;

	public Building(Game game, int x, int y) {
		this.x = x;
		this.y = y;
		this.game = game;
	}

	public void update() {
		state.update();
	}

	public void draw(PApplet app) {
		app.fill(10);
		app.rect(x * Game.gridSize + 5, y * Game.gridSize + 5, 40, 40);

	}

	protected void setAnimation(Animation a) {
		state = a;
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

}
