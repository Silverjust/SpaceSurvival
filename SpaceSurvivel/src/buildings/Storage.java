package buildings;

import components.ResNames;
import components.RessourceGroup;
import g4p_controls.GButton;
import g4p_controls.GEvent;
import guiElements.StandardPannel;
import main.Game;
import main.Helper;
import processing.core.PApplet;
import states.BuildingWait;
import states.BuildingWork;
import states.State;
import states.StorageWait;
import states.Storing;

public class Storage extends Building {

	RessourceGroup resHandler;
	private StorageWait wait;
	public State broken;

	public Storage(Game game, int x, int y) {
		super(game, x, y);
		build = new BuildingWork();
		wait = new StorageWait();
		broken = new BuildingWait();
		resHandler = new RessourceGroup();

		RessourceGroup res = new RessourceGroup();
		res.addToRessource(ResNames.METALL, 1000);
		((Storing) wait).getInput().add(res);
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

	@Override
	public void startGui() {
		game.pannel = new Pannel(this);
	}

	public class Pannel extends StandardPannel {
		private GButton repair;

		public Pannel(Entity outer) {
			super(outer.game);
			repair = Helper.createButton(game.app, 0.1f, 0.2f, 0.1f, 0.1f, "repair");
			repair.addEventHandler(this, "handleButtonEvents");
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
			game.app.text(wait.getInput().getText(), 500, 200);
			
		}
	}
}