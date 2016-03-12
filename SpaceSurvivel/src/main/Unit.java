package main;

import processing.core.PApplet;

public abstract class Unit extends Entity {

	protected float x;
	protected float y;
	protected float xt;
	protected float yt;
	protected float speed;
	public boolean canMove;

	public Unit(Game game, int x, int y) {
		super(game);
		this.x = x;
		this.y = y;

	}

	public void update() {
		if (canMove) {
			x = x + (xt - x) / PApplet.dist(x, y, xt, yt) * speed;
			y = y + (yt - y) / PApplet.dist(x, y, xt, yt) * speed;
		}
		super.update();
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
