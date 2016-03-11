package main;

import processing.core.PApplet;

public class Entity {

	private Game game;
	private float x;
	private float y;
	private float xt;
	private float yt;
	private float speed=0.3f;
	public Entity(Game game,int x, int y) {
		this.game=game;
		this.x = x;
		this.y = y;
		createRandomTarget();
		
	}

	public void update() {
		x= x + (xt - x) / PApplet.dist(x, y, xt, yt) * speed;
		y= y + (yt - y) / PApplet.dist(x, y, xt, yt) * speed;
		if(PApplet.dist(x, y, xt, yt)<5){
			createRandomTarget();
		}
			
	}

	private void createRandomTarget() {
		xt=game.app.random(0, 64);
		yt=game.app.random(0, 64);
	}

	public void draw(PApplet app) {
		app.fill(100);
		app.ellipse(x * game.gridSize + 25, y * game.gridSize + 25, 40, 40);

	}

}
