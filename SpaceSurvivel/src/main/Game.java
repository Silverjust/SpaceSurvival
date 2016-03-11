package main;

import java.lang.reflect.Constructor;
import java.util.ArrayList;

import buildings.Building;
import processing.core.PApplet;

public class Game {
	PApplet app;
	public static final int gridW = 50;
	public static final int gridH = 40;
	public static final float gridSize = 50;
	float zoom = 1;
	float xOffset = 0;
	float yOffset = 0;
	private Building[][] buildings = new Building[gridW][gridH];
	private boolean[][] isInside = new boolean[gridW][gridH];
	private boolean[][] isUsed = new boolean[gridW][gridH];

	private ArrayList<Entity> entities = new ArrayList<Entity>();
	private ArrayList<Entity> toAdd = new ArrayList<Entity>();
	private ArrayList<Entity> toRemove = new ArrayList<Entity>();
	private Input input;

	public Game(PApplet app) {
		this.app = app;
		for (int i = 0; i < gridW; i++) {
			for (int j = 0; j < gridH; j++) {
				int rim = 10;
				if (rim < i && i < gridW - rim && rim < j && j < gridH - rim)
					isInside[i][j] = true;
			}
		}

		input = new Input(this);
		ContentListHandler.setup(app);
		ContentListHandler.load();

		build("farm", 11, 11);
		entities.add(new Entity(this, 15, 15));

		System.out.println("Game.Game()");
	}

	public void update() {
		input.update();
		app.clear();
		app.background(0, 0, 100);
		app.pushMatrix();
		app.translate(xOffset, yOffset);
		app.scale(zoom);
		// app.stroke(0);
		app.fill(255);
		for (int i = 0; i < gridW; i++) {
			for (int j = 0; j < gridH; j++) {
				if (isInside[i][j])
					if (isUsed[i][j])
						app.fill(150);
					else
						app.fill(200);
				else
					app.fill(255, 0);
				app.rect(i * gridSize, j * gridSize, gridSize, gridSize);
			}
		}
		for (int i = 0; i < gridW; i++) {
			for (int j = 0; j < gridH; j++) {
				if (buildings[i][j] != null)
					buildings[i][j].draw(app);
			}
		}for (int i = 0; i < toAdd.size(); i++) {
			entities.add(toAdd.get(i));
			toAdd.get(i).onSpawn();
			toAdd.remove(i);
		}
		for (int i = 0; i < toRemove.size(); i++) {
			Entity entity = toRemove.get(i);
			if (entity != null) {
				entities.remove(entity);
				toRemove.remove(i);
			}
		}
		for (Entity entity : entities) {
			entity.update();
			entity.draw(app);
		}
		app.popMatrix();

	}

	public void build(String name, int i, int j) {

		try {
			name = ContentListHandler.getContent().getString(name);
			Class<?> clazz = Class.forName(name);
			Constructor<?> ctor = clazz.getConstructor(Game.class, int.class, int.class);
			Building b;
			b = (Building) ctor.newInstance(new Object[] { this, i, j });
			buildings[i][j] = b;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void spawn(String name, int i, int j) {
		try {
			name = ContentListHandler.getContent().getString(name);
			Class<?> clazz = Class.forName(name);
			Constructor<?> ctor = clazz.getConstructor(Game.class, int.class, int.class);
			Entity e;
			e = (Entity) ctor.newInstance(new Object[] { this, i, j });
			toAdd.add(e);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
