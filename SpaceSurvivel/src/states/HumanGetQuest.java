package states;

import buildings.Building;
import buildings.Entity;
import buildings.Human;
import components.Ressource;
import components.Slot;
import main.Game;
import processing.core.PApplet;

public class HumanGetQuest extends State {

	private Ressource min;
	private Building target;
	private Slot slot;

	public HumanGetQuest() {
		slot = new Slot();
	}

	public void update(Entity e) {
		if (target != null) {
			if (PApplet.dist(e.getX(), e.getY(), target.getX(), target.getY()) < 1) {
				((Storing) target.getState()).getOutput().give(slot, min);
				e.endState();
			}
		} else
			findTarget(e);
	}

	public void findTarget(Entity e) {
		// System.out.println("HumanGetQuest.findTarget()");
		for (int i = 0; i < Game.gridW; i++) {
			for (int j = 0; j < Game.gridH; j++) {
				Building b = e.game.getBuildings()[i][j];
				if (b != null && ((Storing) b.getState()).getOutput().containsPure(min)) {
					((Human) e).setXt(b.getX());
					((Human) e).setYt(b.getY());
					((Human) e).canMove = true;
					target = b;
				}
			}
		}
	}

	public Ressource getMin() {
		return min;
	}

	public void setMin(Ressource min) {
		this.min = min;
	}

	@Override
	public void onEnd(Entity e) {

		((Human) e).canMove = false;
	}

}
