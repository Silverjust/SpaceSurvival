package buildings;

import states.Storing;
import states.Wait;
import states.BuildingWork;
import states.State;
import g4p_controls.GButton;
import g4p_controls.GEvent;
import guiElements.StandardPannel;
import main.Game;
import main.Helper;

public abstract class Machine extends Building {

	protected State busy;
	protected Wait broken;

	public Machine(Game game, int x, int y) {
		super(game, x, y);

		this.game = game;
	}

	@Override
	public void update() {
		super.update();
	}

	/** empty */
	public void addOutput() {

	}

	public class MachinePannel extends StandardPannel {

		private GButton repair;
		private GButton stop;
		private GButton destroy;
		private Entity outer;

		public MachinePannel(Entity outer) {
			super(outer.game);
			this.outer = outer;
			repair = Helper.createButton(game.app, this, 0.1f, 0.2f, 0.1f, 0.1f, "repair");
			if (getState() != broken) {
				repair.setAlpha(100);
				repair.setEnabled(false);
			}
			stop = Helper.createButton(game.app, this, 0.1f, 0.3f, 0.1f, 0.1f, "");
			if (getState() != wait)
				stop.setText("stop");
			else
				stop.setText("start");

			destroy = Helper.createButton(game.app, this, 0.1f, 0.4f, 0.1f, 0.1f, "destroy");
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
			} else if (button == destroy) {
				System.out.println("Machine.MachinePannel.handleButtonEvents()");
				game.destroyBuilding((Building) outer);
				game.disposePannel();
			} else
				super.handleButtonEvents(button, event);
		}

		@Override
		public void dispose() {
			repair.dispose();
			stop.dispose();
			destroy.dispose();
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
