package buildings;

import main.Game;
import processing.core.PApplet;
import states.BuildingWork;
import states.HumanCarry;
import states.HumanGotoWork;
import states.HumanWait;
import states.HumanWork;
import states.State;

public class Human extends Unit {

	protected boolean hasWork;
	public HumanWork work;
	State carry;
	HumanGotoWork gotoWork;
	public HumanWait wait;

	public Human(Game game, int x, int y) {
		super(game, x, y);
		speed = 0.3f;
		wait = new HumanWait();
		work = new HumanWork(game);
		carry = new HumanCarry();
		gotoWork = new HumanGotoWork();
		createRandomTarget();
		setState(wait, this);
	}

	@Override
	public void update() {

		super.update();
		if (canMove) {
			if (PApplet.dist(x, y, xt, yt) < 0.5) {
				createRandomTarget();
			}
		}
	}

	public void draw(PApplet app) {
		app.fill(100);
		app.ellipse(x * Game.gridSize + 25, y * Game.gridSize + 25, 40, 40);
		app.text(getStateName(), x * Game.gridSize + 25, y * Game.gridSize + 25);

	}

	public float getW() {
		// TODO Auto-generated method stub
		return 10;
	}

	public void setTarget(Entity b) {
if(b.getState().needsWorker()){
	((BuildingWork)b.getState()).registerAsWorker(this);
		hasWork = true;
		setState(gotoWork, this);
		gotoWork.setTarget(b);
		xt = b.getX();
		yt = b.getY();}

	}

	private void createRandomTarget() {
		hasWork = false;
		xt = game.app.random(0, 64);
		yt = game.app.random(0, 64);
	}

	public boolean hasWork() {
		return hasWork;
	}

}
