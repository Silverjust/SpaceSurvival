package main;

import java.util.ArrayList;

import processing.core.PApplet;

public class Game {
	PApplet app;
	static final  int gridW = 64;
	static final int gridH = 64;
	static final float gridSize = 50;
	float zoom = 1;
	float xOffset = 0;
	float yOffset = 0;
	private Block[][] blocks = new Block[gridW][gridH];
	private ArrayList<Entity> entities = new ArrayList<Entity>();
	private Input input;

	public Game(PApplet app) {
		this.app = app;
		blocks[10][10] = new Block(this, 10, 10);
		entities.add(new Entity(this, 15, 15));
		input = new Input(this, app);
		System.out.println("Game.Game()");
	}

	public void update() {
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
				if (blocks[i][j] != null)
					blocks[i][j].draw(app);
			}
		}
		for (Entity entity : entities) {
			entity.update();
			entity.draw(app);
		}
		app.popMatrix();
	}

}
