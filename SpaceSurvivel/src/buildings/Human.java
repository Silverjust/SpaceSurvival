package buildings;

import main.Game;
import processing.core.PApplet;
import states.HumanCarry;
import states.HumanGotoWork;
import states.HumanWait;
import states.HumanWork;
import states.State;

public class Human extends Unit {

	private boolean hasWork;
	public HumanWork work;
	public State carry;
	public HumanGotoWork gotoWork;
	public HumanWait wait;
	private int number;
	static int totalNumber;

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
	public void onSpawn() {
		totalNumber++;
		number = totalNumber;
	}

	@Override
	public void update() {
		super.update();
		if (getState() == wait) {
			if (PApplet.dist(x, y, xt, yt) < 0.5) {
				createRandomTarget();
			}
		}
	}

	public void draw(PApplet app) {
		app.fill(100);
		app.ellipse(x * Game.gridSize + 25, y * Game.gridSize + 25, 40, 40);
		app.line(x * Game.gridSize + 25, y * Game.gridSize + 25, xt * Game.gridSize + 25, yt * Game.gridSize + 25);
		app.text(getStateName() + " " + number, x * Game.gridSize + 25, y * Game.gridSize + 25);

	}

	public float getW() {
		// TODO calculate W
		return 10;
	}

	private void createRandomTarget() {
		setHasWork(false);
		setXt(game.app.random(0, 64));
		setYt(game.app.random(0, 64));
	}

	public boolean hasWork() {
		return hasWork;
	}

	public void setHasWork(boolean hasWork) {
		this.hasWork = hasWork;
	}

}
