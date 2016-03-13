package buildings;

import processing.core.PApplet;
import states.BuildingWait;
import states.BuildingWork;
import main.Game;

public class Farm extends Building {

	public Farm(Game game, int x, int y) {
		super(game, x, y);
		build = new BuildingWork().setWorkers(2).setW(100);
		busy = new BuildingWork().setWorkers(1).setW(110);
		broken = new BuildingWait();
		setState(build, this);
	}

	@Override
	public void update() {
		super.update();
	}

	@Override
	public void draw(PApplet app) {
		if (getState() == build)
			app.fill(10);
		else
			app.fill(100);
		app.rect(x * Game.gridSize + 5, y * Game.gridSize + 5, 40, 40);
		if (getState() instanceof BuildingWork) {
			app.text(getStateName() + ((BuildingWork) getState()).getWorkers().size(), x * Game.gridSize + 5,
					y * Game.gridSize + 5);
			app.fill(180,150);
			app.rect(x*Game.gridSize, y*Game.gridSize-15, Game.gridSize, 5);
			app.fill(0,255,0);
			app.rect(x*Game.gridSize, y*Game.gridSize-15, Game.gridSize*((BuildingWork) getState()).getProgress(), 5);
		}
	}

}
