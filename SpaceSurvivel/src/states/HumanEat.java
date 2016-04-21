package states;

import buildings.Building;
import buildings.Entity;
import buildings.Human;
import components.ResNames;
import components.Ressource;
import main.Game;
import processing.core.PApplet;

public class HumanEat extends State {
	private Building target;
	private int foodEaten;
	private boolean needsToShit;

	@Override
	public void onStart(Entity e) {
		((Human) e).canMove = true;
		needsToShit = true;
		goToFood(e);
	}

	@Override
	public void update(Entity e) {
		if (needsToShit) {
			if (((Human) e).isSatt()) {
				if (target != null) {
					if (PApplet.dist(e.getX(), e.getY(), target.getX(), target.getY()) < 1) {
						((Storing) target.getState()).getOutput().give(null,
								new Ressource(ResNames.BIOMÜLL, foodEaten));
						foodEaten = 0;
						needsToShit = false;
					}
				} else
					goToLoo(e);
			} else {
				if (target != null) {
					if (PApplet.dist(e.getX(), e.getY(), target.getX(), target.getY()) < 1) {
						((Storing) target.getState()).getInput().give(null, new Ressource(ResNames.NAHRUNG, -10));
						((Human) e).consumeFood(-10);
						foodEaten += 10;
					}
				} else
					goToFood(e);
			}
		} else {
			if (((Human) e).isSatt()) {
				e.endState();System.out.println("HumanEat.update()end");
			}
		}
	}

	public void goToFood(Entity e) {
		for (int i = 0; i < Game.gridW; i++) {
			for (int j = 0; j < Game.gridH; j++) {
				Building b = e.game.getBuildings()[i][j];
				if (b != null
						&& ((Storing) b.getState()).getOutput().containsPure(new Ressource(ResNames.NAHRUNG, 10))) {
					((Human) e).setXt(b.getX());
					((Human) e).setYt(b.getY());
					target = b;
				}
			}
		}
	}

	private void goToLoo(Entity e) {
		for (int i = 0; i < Game.gridW; i++) {
			for (int j = 0; j < Game.gridH; j++) {
				Building b = e.game.getBuildings()[i][j];
				if (b != null && ((Storing) b.getState()).getInput().containsPure(new Ressource(ResNames.BIOMÜLL, 0))) {
					((Human) e).setXt(b.getX());
					((Human) e).setYt(b.getY());
					target = b;
				} else
					System.out.println("HumanEat.goToLoo()no loo");
			}
		}

	}
}
