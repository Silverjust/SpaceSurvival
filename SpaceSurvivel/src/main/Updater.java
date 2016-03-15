package main;

import buildings.Entity;

public class Updater {

	private Game game;
	public boolean pause;

	public Updater(Game game) {
		this.game = game;
	}

	public void update() {
		if (!pause) {
			for (int i = 0; i < Game.gridW; i++) {
				for (int j = 0; j < Game.gridH; j++) {
					if (game.buildings[i][j] != null)
						game.buildings[i][j].update();
				}
			}
			for (int i = 0; i < game.toAdd.size(); i++) {
				game.getEntities().add(game.toAdd.get(i));
				game.toAdd.get(i).onSpawn();
				game.toAdd.remove(i);
			}
			for (int i = 0; i < game.toRemove.size(); i++) {
				Entity entity = game.toRemove.get(i);
				if (entity != null) {
					game.getEntities().remove(entity);
					game.toRemove.remove(i);
				}
			}
			for (Entity entity : game.getEntities()) {
				entity.update();

			} 
		}

	}

	


}
