package main;

import buildings.Building;

public class Aimer {
	public String name;
	Building building;
	public Game game;

	public Aimer(String building, Game game) {
		this.name = building;
		this.game = game;
		this.building = game.create(name);
		this.building.endState();
	}

	public void click(int x, int y) {
		System.out.println("Aimer.click()");
		game.build(name, x, y);
		game.aimer = null;
	}

	void update() {
		building.drawAt(game.app, game.app.mouseX, game.app.mouseY);
	}

}
