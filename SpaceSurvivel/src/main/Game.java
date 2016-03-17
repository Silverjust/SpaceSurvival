package main;

import java.lang.reflect.Constructor;
import java.util.ArrayList;

import buildings.Building;
import buildings.Entity;
import buildings.Human;
import buildings.Unit;
import components.ResNames;
import components.RessourceGroup;
import guiElements.GUIpannel;
import processing.core.PApplet;

public class Game {
	public PApplet app;
	public static final int gridW = 50;
	public static final int gridH = 40;
	public static final float gridSize = 50;
	float zoom = 1;
	float xOffset = 0;
	float yOffset = 0;
	private boolean[][] isInside = new boolean[gridW][gridH];
	private boolean[][] isUsed = new boolean[gridW][gridH];

	private RessourceGroup ressourceHandler = new RessourceGroup();
	private Input input;
	Updater updater;
	public GameTime gameTime;
	public GUIpannel pannel;

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
		build("storage", 20, 11);
		getEntities().add(new Human(this, 15, 15));
		getEntities().add(new Human(this, 16, 15));
		getEntities().add(new Human(this, 17, 15));
		ressourceHandler.addToRessource(ResNames.METALL, 500);
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
				if (getBuildings()[i][j] != null)
					getBuildings()[i][j].draw(app);
			}
		}
		for (Entity entity : getEntities()) {

			entity.draw(app);
		}
		app.popMatrix();

		if (pannel != null) {
			pannel.update();
		}

	}

	public void build(String name, int i, int j) {

		try {
			name = ContentListHandler.getContent().getString(name);
			System.out.println("build " + name);
			Class<?> clazz = Class.forName(name);
			Constructor<?> ctor = clazz.getConstructor(Game.class, int.class, int.class);
			Building b;
			b = (Building) ctor.newInstance(new Object[] { this, i, j });
			getBuildings()[i][j] = b;
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
			updater.add(e);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Unit> getEntities() {
		return updater.getEntities();
	}

	public Building[][] getBuildings() {
		return updater.getBuildings();
	}

	public void disposePannel() {
		pannel.dispose();
		pannel = null;
	}

}
