package main;

import java.util.ArrayList;

import buildings.Building;
import buildings.Entity;
import buildings.Unit;

public class Updater {

	private Game game;
	public boolean pause;
	private ArrayList<Unit> entities = new ArrayList<Unit>();
	private ArrayList<Unit> toAdd = new ArrayList<Unit>();
	private ArrayList<Unit> toRemove = new ArrayList<Unit>();
	private Building[][] buildings = new Building[Game.gridW][Game.gridH];

	public Updater(Game game) {
		this.game = game;
	}

	public void update() {
		if (!pause) {
			for (int i = 0; i < Game.gridW; i++) {
				for (int j = 0; j < Game.gridH; j++) {
					if (game.getBuildings()[i][j] != null)
						game.getBuildings()[i][j].update();
				}
			}
			for (int i = 0; i < toAdd.size(); i++) {
				game.getEntities().add(toAdd.get(i));
				toAdd.get(i).onSpawn();
				toAdd.remove(i);
			}
			for (int i = 0; i < toRemove.size(); i++) {
				Entity entity = toRemove.get(i);
				if (entity != null) {
					game.getEntities().remove(entity);
					toRemove.remove(i);
				}
			}
			for (Entity entity : game.getEntities()) {
				entity.update();

			}
		}

	}

	public ArrayList<Unit> getEntities() {
		return entities;
	}

	public void add(Unit e) {
		toAdd.add(e);
	}

	public void remove(Unit e) {
		toRemove.add(e);
	}

	public Building[][] getBuildings() {
		return buildings;
	}

}
