package states;

import buildings.Entity;
import buildings.Human;
import buildings.Unit;
import processing.core.PApplet;

public class HumanGotoWork extends State {

	private Entity target;

	@Override
	public void update(Entity e) {
		if (e instanceof Human && target.getState() instanceof BuildingWork
				&& PApplet.dist(e.getX(), e.getY(), target.getX(), target.getY()) < 1) {
			if (((BuildingWork) target.getState()).getWorkers().contains(e)) {
				((Human) e).work.setTarget(target);
				e.endState();
				e.insertState(((Human) e).work, this);
			} else {
				e.endState();
			}
		}
	}

	@Override
	public void onStart(Entity e, State old) {
		((Unit) e).canMove = true;
	}

	public void setTarget(Entity target, Human human) {

		human.setHasWork(true);
		this.target = target;
		human.setXt(target.getX());
		human.setYt(target.getY());

	}
}
