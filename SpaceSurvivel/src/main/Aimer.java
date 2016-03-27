package main;

import buildings.Building;

public class Aimer {
	Building building;
	public Game game;

	public Aimer(Class<?> c, Game game) {
		this.game = game;
		this.building = game.create(c);
		this.building.endState();
	}

	public void click(int x, int y) {
		System.out.println("Aimer.click()");
		game.build(building.getClass(), x, y);
		game.aimer = null;
	}

	void update() {
		building.drawAt(game.app, game.app.mouseX, game.app.mouseY);
	}

}
