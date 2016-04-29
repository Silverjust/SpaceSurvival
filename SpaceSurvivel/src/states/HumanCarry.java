package states;

import buildings.Entity;
import buildings.Human;
import buildings.Unit;
import components.Ressource;
import components.Slot;
import processing.core.PApplet;

public class HumanCarry extends State implements Storing {
	private Entity target1;
	private boolean isCarrieing;
	private Entity target2;
	private Slot storage;
	private Ressource targetAmount;

	public HumanCarry() {
		storage = new Slot();
	}

	@Override
	public void onStart(Entity e, State old) {
		((Unit) e).canMove = true;
	}

	@Override
	public void update(Entity e) {
		if (isCarrieing) {
			Slot output = ((Storing) target1.getState()).getOutput();
			if (e instanceof Human && PApplet.dist(e.getX(), e.getY(), target1.getX(), target1.getY()) < 1
					&& output.containsPure(targetAmount)) {
				storage.give(output, targetAmount);
				System.out.println("HumanCarry.update()load "+target2+" "+target2.getX());
				isCarrieing = false;
				((Human) e).setXt(target2.getX());
				((Human) e).setYt(target2.getY());
			}
		} else {
			if (target2.getState() instanceof Storing) {
				Slot input = ((Storing) target2.getState()).getInput();
				if (e instanceof Human && PApplet.dist(e.getX(), e.getY(), target2.getX(), target2.getY()) < 1) {
					input.give(storage, targetAmount);
					// System.out.println("HumanCarry.update()unload" +
					// input.getText());
					if (((BuildingWork) target2.getState()).getCarriers().contains(((Entity) e)))
						((BuildingWork) target2.getState()).getCarriers().remove(((Entity) e));
					isCarrieing = true;
					((Human) e).setHasWork(false);
					e.endState();
				}
			}
		}
	}

	public void setTargets(Entity target1, Entity target2, Human human, Ressource targetAmount) {

		human.setHasWork(true);
		this.target1 = target1;
		this.target2 = target2;
		this.targetAmount = targetAmount;
		isCarrieing = true;
		human.setXt(target1.getX());
		human.setYt(target1.getY());
		// System.out.println("HumanCarry.setTargets()" + human.hasWork());
	}

	@Override
	public Slot getInput() {
		return storage;
	}

	@Override
	public Slot getOutput() {
		return storage;
	}
}
