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

	@Override
	public void onStart(Entity e) {
		((Human) e).canMove = true;
		goToFood(e);
	}

	public void goToFood(Entity e) {
		System.out.println("HumanEat.goToFood()");
		for (int i = 0; i < Game.gridW; i++) {
			for (int j = 0; j < Game.gridH; j++) {
				Building b = e.game.getBuildings()[i][j];
				if (b != null
						&& ((Storing) b.getState()).getOutput().containsPure(new Ressource(ResNames.NAHRUNG, 100))) {
					System.out.println("HumanEat.onStart()" + ((Storing) b.getState()).getOutput().getText());
					((Human) e).setXt(b.getX());
					((Human) e).setYt(b.getY());
					target = b;
				}
			}
		}
	}

	@Override
	public void update(Entity e) {
		if (target != null){
			if (PApplet.dist(e.getX(), e.getY(), target.getX(), target.getY()) < 1) {
				((Storing) target.getState()).getOutput().give(null, new Ressource(ResNames.NAHRUNG, -100));
				e.endState();
			}
		} else
			goToFood(e);
	}
}
