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
import states.Storing;

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
		busy = new BuildingWork().setWorkers(1).setW(200).setRepeat(true).setInput(buildRes).setOutput(resOut);
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
			app.fill(100);
		app.rect(x * Game.gridSize + 5, y * Game.gridSize + 5, 40, 40);
		if (getState() instanceof BuildingWork) {
			app.text(getStateNames(), x * Game.gridSize + 5, y * Game.gridSize + 5);
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

	public class Pannel extends StandardPannel {

		private GButton repair;
		private GButton stop;
		public Pannel(Entity outer) {
			super(outer.game);
			repair = Helper.createButton(game.app, 0.1f, 0.2f, 0.1f, 0.1f, "repair");
			repair.addEventHandler(this, "handleButtonEvents");
			if (getState() != broken) {
				repair.setAlpha(100);
				repair.setEnabled(false);
			}
			stop = Helper.createButton(game.app, 0.1f, 0.3f, 0.1f, 0.1f, "");
			if (getState() != wait)
				stop.setText("stop");
			else
				stop.setText("start");

			stop.addEventHandler(this, "handleButtonEvents");
		}

		@Override
		public void handleButtonEvents(GButton button, GEvent event) {
			if (button == stop) {
				if (getState() == wait) {
					System.out.println("Farm.Pannel.handleButtonEvents()");
					endState();
					stop.setText("stop");
				} else {
					setState(wait, this);
					stop.setText("start");
				}
			} else
				super.handleButtonEvents(button, event);
		}

		

		@Override
		public void dispose() {
			repair.dispose();
			stop.dispose();
			super.dispose();
		}

		@Override
		public void update() {
			super.update();
			if (getState() instanceof Storing) {
				game.app.text(((Storing) getState()).getInput().getText(), 500, 200);
				game.app.text(((Storing) getState()).getOutput().getText(), 1200, 200);
			}
		}
	}
}
