package states;

import buildings.Entity;
import buildings.Human;
import buildings.Unit;
import processing.core.PApplet;

public class HumanGotoWork extends State {

	private Entity target;

	@Override
	public void update(Entity e) {
		if (e instanceof Human && PApplet.dist(e.getX(), e.getY(), target.getX(), target.getY()) < 1) {
			if (((BuildingWork) target.getState()).getWorkers().contains(e)) {
				((Human) e).work.setTarget(target);
				e.setState(((Human) e).work, this);

			} else {
				e.setState(((Human) e).wait, this);
			}
		}
	}

	@Override
	public void onStart(Entity e) {
		((Unit) e).canMove = true;
	}

	public void setTarget(Entity target,BuildingWork work, Human human) {
		work.registerAsWorker(human);
		human.setHasWork(true);
		this.target = target;
		human.setXt(target.getX());
		human.setYt(target.getY());

	}
}
