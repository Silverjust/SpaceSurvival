package states;

import main.Entity;
import main.Human;
import main.Unit;
import processing.core.PApplet;

public class HumanGotoWork extends State {

	private Entity target;

	public void setTarget(Entity b) {
		target = b;
	}

	@Override
	public void update(Entity e) {
		if (e instanceof Human && PApplet.dist(e.getX(), e.getY(), target.getX(), target.getY()) < 1) {
			e.setState(((Human) e).work);
		}
	}

	@Override
	public void startState(Entity e) {
		((Unit) e).canMove = true;
	}
}
