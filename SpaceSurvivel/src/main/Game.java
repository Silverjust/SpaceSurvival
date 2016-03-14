package main;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;

import processing.core.PApplet;
//commentar
public class Game {
	public PApplet app;
	public static final int gridW = 50;
	public static final int gridH = 40;
	public static final float gridSize = 50;
	float zoom = 1;
	float xOffset = 0;
	float yOffset = 0;
	Entity[][] buildings = new Entity[gridW][gridH];
	private boolean[][] isInside = new boolean[gridW][gridH];
	private boolean[][] isUsed = new boolean[gridW][gridH];

	private ArrayList<Unit> entities = new ArrayList<Unit>();
	ArrayList<Unit> toAdd = new ArrayList<Unit>();
	ArrayList<Unit> toRemove = new ArrayList<Unit>();
	private HashMap<Ressources, Ressource> ressources = new HashMap<Ressources, Ressource>();
	private Input input;
	Updater updater;
	public GameTime gameTime;

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
		updater = new Updater(this);
		ContentListHandler.setup(app);
		ContentListHandler.load();
		gameTime = new GameTime(this);

		build("farm", 11, 11);
		getEntities().add(new Human(this, 15, 15));
		getEntities().add(new Human(this, 16, 15));
		getEntities().add(new Human(this, 17, 15));
		addToRessource(Ressources.METALL, 500);
		System.out.println("Game.Game()");
	}

	public void update() {
		input.update();
		updater.update();

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
		}
		for (Entity entity : getEntities()) {

			entity.draw(app);
		}
		app.popMatrix();

	}

	public void build(String name, int i, int j) {

		try {
			name = ContentListHandler.getContent().getString(name);
			Class<?> clazz = Class.forName(name);
			Constructor<?> ctor = clazz.getConstructor(Game.class, int.class, int.class);
			Entity b;
			b = (Entity) ctor.newInstance(new Object[] { this, i, j });
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
			Unit e;
			e = (Unit) ctor.newInstance(new Object[] { this, i, j });
			toAdd.add(e);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addToRessource(Ressources res, int amount) {
		if (!ressources.containsKey(res))
			ressources.put(res, new Ressource(res));
		ressources.get(res).addAmount(amount);
	}

	public ArrayList<Unit> getEntities() {
		return entities;
	}
}
