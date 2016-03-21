package states;

import buildings.Building;
import buildings.Entity;
import buildings.Human;
import buildings.Unit;
import main.Game;
import main.Helper;
import main.Helper.Timer;

public class HumanWork extends State {
	private Timer timer;
	private Entity target;

	public HumanWork(Game game) {
		timer = new Helper.Timer(game.gameTime, 500);
	}

	@Override
	public void update(Entity e) {
		if (timer.isNotOnCooldown()) {
			((BuildingWork) ((Building) target).getState()).addW(e, target, ((Human) e).getW());
			timer.startCooldown();
		}
	}

	public void setTarget(Entity b) {
		target = b;
	}

	@Override
	public void onStart(Entity e) {
		((Unit) e).canMove = false;
		timer.startCooldown();
	}
}
