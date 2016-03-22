package main;

import buildings.Building;

public class Aimer {
	public String building;
	public Game game;

	public Aimer(String building, Game game) {
		this.building = building;
		this.game = game;
	}

	public void click(int x, int y) {
		System.out.println("Aimer.click()");
		game.build(building, x, y);
		game.aimer = null;
	}

}
