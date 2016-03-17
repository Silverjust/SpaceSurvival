package states;

import buildings.Entity;
import buildings.Human;
import buildings.Unit;
import components.Slot;
import processing.core.PApplet;

public class HumanCarry extends State implements Storing {
	private Entity target1;
	private boolean isCarrieing;
	private Entity target2;
	private Slot storage;

	@Override
	public void onStart(Entity e) {
		((Unit) e).canMove = true;
	}

	@Override
	public void update(Entity e) {
		if (isCarrieing) {
			if (e instanceof Human && PApplet.dist(e.getX(), e.getY(), target1.getX(), target1.getY()) < 1
					&& ((Storing) target1.getState()).getOutput()
							.contains(((Storing) target2.getState()).getInput().getMin())) {
				storage.give(((Storing) target1.getState()).getOutput(),
						((Storing) target2.getState()).getInput().getMin());
			}
		}
	}

	public void setTargets(Entity target1, Entity target2, Human human) {
		human.hasWork = true;
		this.target1 = target1;
		this.target2 = target2;
		human.xt = target1.getX();
		human.yt = target1.getY();

	}

	@Override
	public Slot getInput() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Slot getOutput() {
		// TODO Auto-generated method stub
		return null;
	}
}
