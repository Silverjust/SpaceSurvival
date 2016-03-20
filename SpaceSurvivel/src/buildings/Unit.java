package buildings;

import main.Game;
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
			x = x + (getXt() - x) / PApplet.dist(x, y, getXt(), getYt()) * speed;
			y = y + (getYt() - y) / PApplet.dist(x, y, getXt(), getYt()) * speed;
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

	public float getXt() {
		return xt;
	}

	public void setXt(float xt) {
		this.xt = xt;
	}

	public float getYt() {
		return yt;
	}

	public void setYt(float yt) {
		this.yt = yt;
	}

}
