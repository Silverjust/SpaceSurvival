package buildings;

import components.ResNames;
import components.RessourceHandler;
import main.Game;
import processing.core.PApplet;
import states.BuildingWait;
import states.BuildingWork;

public class Storage extends Building {

	RessourceHandler resHandler;
	private BuildingWait wait;

	public Storage(Game game, int x, int y) {
		super(game, x, y);
		build = new BuildingWork();
		wait = new BuildingWait();
		resHandler = new RessourceHandler();

		setState(wait, this);
	}

	@Override
	public void draw(PApplet app) {
		app.fill(200, 200, 0);
		app.rect(x * Game.gridSize + 5, y * Game.gridSize + 5, 40, 40);
	}

	public void addToRessource(ResNames res, int amount) {
		this.resHandler.addToRessource(res, amount);
	}

	ResNames[] getStoredRessources() {
		return resHandler.getRessources();
	}
}