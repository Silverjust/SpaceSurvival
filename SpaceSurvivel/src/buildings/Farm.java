package buildings;

import States.Work;
import processing.core.PApplet;
import main.Game;

public class Farm extends Building {

	public Farm(Game game, int x, int y) {
		super(game, x, y);
		build = new Work().setWorkers(1).setW(100);
		busy = new Work();
		broken = new Work();
		setState(build);
	}

	@Override
	public void draw(PApplet app) {
		app.fill(10);
		app.rect(x * Game.gridSize + 5, y * Game.gridSize + 5, 40, 40);
	}

	

}
