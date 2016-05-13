package states;

import java.util.ArrayList;

import buildings.Building;
import buildings.Entity;
import buildings.Human;
import buildings.Unit;
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

	@Override
	public void onStart(Entity e, State old) {
		((Human) e).setHasWork(true);
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

	public void requestMin(Ressource ressource, Entity e, ArrayList<Unit> carriers) {
		this.min = ressource;
		Unit carrier = null;
		for (Unit unit : e.game.getEntities()) {
			for (int i = 0; i < Game.gridW; i++) {
				for (int j = 0; j < Game.gridH; j++) {
					Building b = e.game.getBuildings()[i][j];
					if (b != null && ((Storing) b.getState()).getOutput().containsPure(min)
							&& !((Human) unit).hasWork()) {
						if (target != null && carrier != null) {
							if (PApplet.dist(target.getX(), target.getY(), carrier.getX(), carrier.getY()) > PApplet
									.dist(b.getX(), b.getY(), unit.getX(), b.getY())) {
								target = b;
								carrier = unit;
							}
						} else {
							target = b;
							carrier = unit;
						}
					}
				}
			}
		}
		if (carrier != null) {
			carrier.insertState(this, this);
			((Human) carrier).setXt(target.getX());
			((Human) carrier).setYt(target.getY());
			((Human) carrier).canMove = true;
			carriers.add(carrier);
		}
	}
}
