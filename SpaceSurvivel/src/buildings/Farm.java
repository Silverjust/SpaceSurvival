package buildings;

import States.Animation;
import main.Entity;
import main.Game;

public class Farm extends Building {

	private Animation build;
	private Animation busy;
	private Animation broken;

	public Farm(Game game, int x, int y) {
		super(game, x, y);
		build = new Animation();
		busy = new Animation();
		broken = new Animation();
		setAnimation(build);
	}
	@Override
	public void update() {
	if(state.hasNotEnoughWorker())
		callWorker();
		super.update();
	}
	private void callWorker() {
		for (Entity e : game.getEntities()) {
			if(!e.hasWork())e.setTarget(this);
		}
	}

}
