package main;

import buildings.Building;
import processing.core.PApplet;

public class Entity {

	private Game game;
	private float x;
	private float y;
	private float xt;
	private float yt;
	private float speed=0.3f;
	private boolean hasWork;
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
		hasWork=false;
		xt=game.app.random(0, 64);
		yt=game.app.random(0, 64);
	}

	public void draw(PApplet app) {
		app.fill(100);
		app.ellipse(x * game.gridSize + 25, y * game.gridSize + 25, 40, 40);

	}

	public void onSpawn() {
		// TODO Auto-generated method stub
		
	}

	public boolean hasWork() {
		return hasWork;
	}

	public void setTarget(Building b) {
		hasWork=true;
		xt=b.getX();
		yt=b.getY();
	}

}
