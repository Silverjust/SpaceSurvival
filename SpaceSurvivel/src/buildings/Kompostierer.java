package buildings;

import components.ResNames;
import components.RessourceGroup;
import main.Game;
import processing.core.PApplet;
import states.BuildingWork;
import states.Wait;

public class Kompostierer extends Machine {

	public Kompostierer(Game game, int x, int y) {
		super(game, x, y);
		RessourceGroup buildRes = new RessourceGroup();
		buildRes.addToRessource(ResNames.METALL, 100);
		RessourceGroup resInput = new RessourceGroup();
		resInput.addToRessource(ResNames.BIOM�LL, 100);
		RessourceGroup resOut = new RessourceGroup();
		resOut.addToRessource(ResNames.D�NGER, 100);

		build = new BuildingWork().setWorkers(2).setW(100).setInput(buildRes);
		busy = new BuildingWork().setW(200).setRepeat(true).setInput(resInput).setOutput(resOut);
		broken = new Wait();
		wait = new Wait();
		setState(build, this);
		addState(busy, this);
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
			app.fill(100, 50, 0);
		app.rect(x * Game.gridSize + 5, y * Game.gridSize + 5, 40, 40);
		if (getState() instanceof BuildingWork) {
			app.text(getStateName() + ((BuildingWork) getState()).getProgress(), x * Game.gridSize + 5,
					y * Game.gridSize + 5);
			app.fill(180, 150);
			app.rect(x * Game.gridSize, y * Game.gridSize - 15, Game.gridSize, 5);
			app.fill(0, 255, 0);
			app.rect(x * Game.gridSize, y * Game.gridSize - 15,
					Game.gridSize * ((BuildingWork) getState()).getProgress(), 5);
		}
	}

	@Override
	public void startGui() {
		game.pannel = new Pannel(this);
	}

}
