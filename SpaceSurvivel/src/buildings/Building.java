package buildings;

import States.State;
import main.Game;
import processing.core.PApplet;

public class Building {

	private int x;
	private int y;
	private Game game;
	private State state;

	public Building(Game game, int x, int y) {
		this.x = x;
		this.y = y;
		this.game=game;
	}

	public void update() {
		state.update();
	}

	public void draw(PApplet app) {
		app.fill(10);
		app.rect(x * game.gridSize + 5, y * game.gridSize + 5, 40, 40);

	}

}
