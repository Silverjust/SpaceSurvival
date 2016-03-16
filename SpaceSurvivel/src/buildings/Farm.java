package buildings;

import processing.core.PApplet;
import states.BuildingWait;
import states.BuildingWork;
import main.Game;
import g4p_controls.GButton;
import g4p_controls.GCheckbox;
import g4p_controls.GEvent;
import g4p_controls.GTextArea;
import guiElements.GUIpannel;

public class Farm extends Machine {

	public Farm(Game game, int x, int y) {
		super(game, x, y);
		build = new BuildingWork().setWorkers(2).setW(100);
		busy = new BuildingWork().setWorkers(1).setW(200);
		broken = new BuildingWait();
		wait = new BuildingWait();
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
		game.pannel = new Pannel();
	}

	public class Pannel extends GUIpannel {

		private GButton close;
		private GButton repair;
		private GButton stop;

		public Pannel() {
			close = new GButton(game.app, 100, 100, 200, 100, "close");
			close.addEventHandler(this, "handleButtonEvents");
			repair = new GButton(game.app, 100, 200, 200, 100, "repair");
			repair.addEventHandler(this, "handleButtonEvents");
			stop = new GButton(game.app, 100, 300, 200, 100, "stop");
			stop.addEventHandler(this, "handleButtonEvents");
		}

		public void handleButtonEvents(GButton button, GEvent event) {
			if (button == close)
				game.disposePannel();
			if (button == stop) {

				if (getState() == wait) {
					setState(busy, this);
					stop.setText("stop");
				} else {
					setState(wait, this);
					stop.setText("start");
				}
			}
		}

		@Override
		public void dispose() {
			close.dispose();
			repair.dispose();
			stop.dispose();
		}

		@Override
		public void update() {
			// game.app.rect(500, 500, 500, 200);
		}
	}
}
