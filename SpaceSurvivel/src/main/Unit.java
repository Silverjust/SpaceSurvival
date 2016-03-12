package main;

import processing.core.PApplet;

public abstract class Unit extends Entity {

	protected float x;
	protected float y;
	private float xt;
	private float yt;
	protected float speed;
	private boolean hasWork;

	public Unit(Game game, int x, int y) {
		super(game);
		this.x = x;
		this.y = y;
		createRandomTarget();

	}

	public void update() {
		x = x + (xt - x) / PApplet.dist(x, y, xt, yt) * speed;
		y = y + (yt - y) / PApplet.dist(x, y, xt, yt) * speed;
		if (PApplet.dist(x, y, xt, yt) < 5) {
			createRandomTarget();
		}
	}

	private void createRandomTarget() {
		hasWork = false;
		xt = game.app.random(0, 64);
		yt = game.app.random(0, 64);
	}

	/** empty */
	public void onSpawn() {

	}

	public boolean hasWork() {
		return hasWork;
	}

	public void setTarget(Entity b) {
		hasWork = true;
		xt = b.getX();
		yt = b.getY();
	}

	@Override
	public float getX() {
		return x;
	}

	@Override
	public float getY() {
		return y;
	}

}
