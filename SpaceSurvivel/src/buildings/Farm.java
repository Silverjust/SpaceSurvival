package buildings;

import components.ResNames;
import components.RessourceGroup;
import main.Game;
import processing.core.PApplet;
import states.Wait;
import states.BuildingWork;

public class Farm extends Machine {

	public Farm(Game game, int x, int y) {
		super(game, x, y);
		RessourceGroup buildRes = new RessourceGroup();
		buildRes.addToRessource(ResNames.METALL, 100);
		RessourceGroup resInput = new RessourceGroup();
		resInput.addToRessource(ResNames.DÜNGER, 100);
		RessourceGroup resOut = new RessourceGroup();
		resOut.addToRessource(ResNames.NAHRUNG, 100);

		build = new BuildingWork().setWorkers(2).setW(100).setInput(buildRes);
		busy = new BuildingWork().setWorkers(1).setW(200).setRepeat(true).setInput(resInput).setOutput(resOut);
		broken = new Wait();
		wait = new Wait();
		setAreaSqare(2, 2);
		setState(build, this);
		addState(busy, this);
	}

	@Override
	public void update() {
		super.update();
	}

	@Override
	public void draw() {
		super.draw();
		if (getState() instanceof BuildingWork) {
			game.app.fill(10);
			game.app.text(getStateNames(), x * Game.gridSize + 5, y * Game.gridSize + 5);
			game.app.fill(180, 150);
			game.app.rect(x * Game.gridSize, y * Game.gridSize - 15, Game.gridSize, 5);
			game.app.fill(0, 255, 0);
			game.app.rect(x * Game.gridSize, y * Game.gridSize - 15,
					Game.gridSize * ((BuildingWork) getState()).getProgress(), 5);
		}
	}

	@Override
	public void drawAt(PApplet app, float x, float y) {
		if (getState() == build)
			app.fill(10);
		else
			app.fill(100);
		app.rect(x + 5, y + 5, 90, 90);
	}

	@Override
	public void startGui() {
		game.pannel = new MachinePannel(this);
	}

}
