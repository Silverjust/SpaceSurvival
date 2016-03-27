package buildings;

import components.ResNames;
import components.RessourceGroup;
import g4p_controls.GButton;
import g4p_controls.GEvent;
import guiElements.StandardPannel;
import main.Game;
import main.Helper;
import processing.core.PApplet;
import states.Wait;
import states.BuildingWork;
import states.State;
import states.StorageWait;
import states.Storing;

public class Lager extends Building {

	RessourceGroup resHandler;
	public State broken;

	public Lager(Game game, int x, int y) {
		super(game, x, y);
		ingameName="lager";
		build = new BuildingWork();
		wait = new StorageWait();
		broken = new Wait();
		resHandler = new RessourceGroup();

		// RessourceGroup res = new RessourceGroup();
		// res.addToRessource(ResNames.METALL, 1000);
		// ((Storing) wait).getInput().add(res);
		setState(wait, this);
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
		app.fill(200, 200, 0);
		app.rect(x  + 5, y  + 5, 40, 40);
	}

	public void addToRessource(ResNames res, int amount) {
		this.resHandler.addToRessource(res, amount);
	}

	ResNames[] getStoredRessources() {
		return resHandler.getRessources();
	}

	@Override
	public void startGui() {
		game.pannel = new Pannel(this);
	}

	public class Pannel extends StandardPannel {
		private GButton repair;

		public Pannel(Entity outer) {
			super(outer.game);
			repair = Helper.createButton(game.app, this, 0.1f, 0.2f, 0.1f, 0.1f, "repair");
			if (getState() != broken) {
				repair.setAlpha(100);
				repair.setEnabled(false);
			}
		}

		public void handleButtonEvents(GButton button, GEvent event) {
			if (button == repair)
				game.disposePannel();
			else
				super.handleButtonEvents(button, event);

		}

		@Override
		public void dispose() {
			repair.dispose();
			super.dispose();
		}

		@Override
		public void update() {
			super.update();
			game.app.text(((Storing) wait).getInput().getText(), 500, 200);

		}
	}
}