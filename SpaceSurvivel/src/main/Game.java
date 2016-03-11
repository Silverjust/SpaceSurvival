package main;

import processing.core.PApplet;

public class Game {
	PApplet app;
	public Game(PApplet app) {
		this.app=app;
	}

	public void update() {
	app.ellipse(200, 100, 100, 100);		
	}

}
