package main;

import processing.core.PApplet;
import states.HumanCarry;
import states.HumanGotoWork;
import states.HumanWork;
import states.State;

public class Human extends Unit {

	protected boolean hasWork;
	public State work;
	State carry;
	HumanGotoWork gotoWork;
	public Human(Game game, int x, int y) {
		super(game, x, y);
		speed = 0.3f;
		work = new HumanWork();
		carry = new HumanCarry();
		gotoWork = new HumanGotoWork();
		createRandomTarget();
	}

	@Override
	public void update() {
		super.update();
		System.out.println("Human.update()"+canMove);
		if (canMove) {
			if (PApplet.dist(x, y, xt, yt) < 5) {
				createRandomTarget();
			}
		}
	}

	public void draw(PApplet app) {
		app.fill(100);
		app.ellipse(x * Game.gridSize + 25, y * Game.gridSize + 25, 40, 40);
		app.text(getStateName(), x * Game.gridSize + 25, y * Game.gridSize + 25);

	}

	public void setTarget(Entity b) {
		hasWork = true;
		setState(gotoWork);
		gotoWork.setTarget(b);
		xt = b.getX();
		yt = b.getY();
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
