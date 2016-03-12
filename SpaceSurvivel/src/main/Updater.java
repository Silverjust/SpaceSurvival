package main;

public class Updater {

	private Game game;

	public Updater(Game game) {
		this.game = game;
	}

	public void update() {
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
			Unit entity = game.toRemove.get(i);
			if (entity != null) {
				game.getEntities().remove(entity);
				game.toRemove.remove(i);
			}
		}
		for (Unit entity : game.getEntities()) {
			entity.update();

		}

	}

	


}
