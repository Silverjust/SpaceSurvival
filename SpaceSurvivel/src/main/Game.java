package main;

import java.util.ArrayList;


import processing.core.PApplet;

public class Game {
	PApplet app;
	static final int gridW = 64;
	static final int gridH = 64;
	static final float gridSize = 50;
	float zoom = 1;
	float xOffset = 0;
	float yOffset = 0;
	private Building[][] buildings = new Building[gridW][gridH];
	private ArrayList<Entity> entities = new ArrayList<Entity>();
	private Input input;

	public Game(PApplet app) {
		this.app = app;
		buildings[10][10] = new Building(this, 10, 10);
		entities.add(new Entity(this, 15, 15));
		input = new Input(this);

		System.out.println("Game.Game()");
	}

	public void update() {
		input.update();
		app.clear();
		app.background(100);
		app.pushMatrix();
		app.translate(xOffset, yOffset);
		app.scale(zoom);
		// app.stroke(0);
		app.fill(255);
		for (int i = 0; i < gridW; i++) {
			for (int j = 0; j < gridH; j++) {
				app.rect(i * gridSize, j * gridSize, gridSize, gridSize);
			}
		}
		for (int i = 0; i < gridW; i++) {
			for (int j = 0; j < gridH; j++) {
				if (buildings[i][j] != null)
					buildings[i][j].draw(app);
			}
		}
		for (Entity entity : entities) {
			entity.update();
			entity.draw(app);
		}
		app.popMatrix();

	}

	public void build(int i, int j) {
		buildings[i][j] = new Building(this, i, j);
	}
	public void spawn(int i, int j) {
		entities.add(new Entity(this, i, j));
	}
}
