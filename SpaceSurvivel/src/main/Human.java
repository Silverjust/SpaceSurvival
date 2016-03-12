package main;

import processing.core.PApplet;

public class Human extends Unit {

	public Human(Game game, int x, int y) {
		super(game, x, y);
		speed = 0.3f;
	}

	public void draw(PApplet app) {
		app.fill(100);
		app.ellipse(x * Game.gridSize + 25, y * Game.gridSize + 25, 40, 40);
	
	}

}
