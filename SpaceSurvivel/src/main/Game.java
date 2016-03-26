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
import processing.core.PImage;
import states.Storing;
import buildings.Storage;

public class Game {
	public PApplet app;
	public static final int gridW = 50;
	public static final int gridH = 40;
	public static final float gridSize = 50;
	float zoom = 1;
	float xOffset = 0;
	float yOffset = 0;
	public boolean[][] isInside = new boolean[gridW][gridH];
	public boolean[][] isUsed = new boolean[gridW][gridH];

	private RessourceGroup ressourceGroup = new RessourceGroup();
	private PImage img;

	public GUIpannel pannel;
	public Aimer aimer;
	private Input input;
	Updater updater;
	public GameTime gameTime;
	public ImageManager imgManager;

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
		imgManager = new ImageManager(app);
		{
			build("farm", 11, 11);

			build("storage", 20, 11);
			RessourceGroup res = new RessourceGroup();
			res.addToRessource(ResNames.METALL, 10000);
			((Storing) ((Storage) getBuildings()[20][11]).wait).getInput().add(res);

			build("storage", 20, 13);
			RessourceGroup res1 = new RessourceGroup();
			res1.addToRessource(ResNames.BIOMÜLL, 1000);
			((Storing) ((Storage) getBuildings()[20][13]).wait).getInput().add(res1);
		}
		updater.add(new Human(this, 15, 15));
		updater.add(new Human(this, 16, 15));
		updater.add(new Human(this, 17, 15));
		ressourceGroup.addToRessource(ResNames.METALL, 500);
		System.out.println("Game.Game()");

		img = imgManager.load("map/", "background");
	}

	public void update() {
		input.update();
		updater.update();
		{
			app.clear();
			app.background(0, 0, 100);

			app.pushMatrix();
			app.translate(xOffset / 10, yOffset / 10);
			app.scale(zoom * 10);
			app.image(img, 0, 0, app.width / 5, app.height / 5);
			app.popMatrix();

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
						getBuildings()[i][j].draw();
				}
			}
			for (Entity entity : getEntities()) {

				entity.draw();
			}
		}
		app.popMatrix();

		if (pannel != null) {
			pannel.update();
		}
		if (aimer != null) {
			aimer.update();
		}

	}

	/**
	 * only for theoretical use, do not place in game
	 * 
	 * @return
	 */
	public Building create(String name) {
		try {
			name = ContentListHandler.getContent().getString(name);
			Class<?> clazz = Class.forName(name);
			Constructor<?> ctor = clazz.getConstructor(Game.class, int.class, int.class);
			return (Building) ctor.newInstance(new Object[] { this, 0, 0 });

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void build(String name, int x, int y) {
		try {
			name = ContentListHandler.getContent().getString(name);
			System.out.println("build " + name);
			Class<?> clazz = Class.forName(name);
			Constructor<?> ctor = clazz.getConstructor(Game.class, int.class, int.class);
			Building b = (Building) ctor.newInstance(new Object[] { this, x, y });
			if (isSpaceFor(b)) {
				for (int i = 0; i < b.getArea()[0].length; i++) {
					for (int j = 0; j < b.getArea().length; j++) {
						if (!(x + i >= isUsed[0].length || y + j >= isUsed.length) && b.getArea()[i][j]) {
							isUsed[x + i][y + j] = true;
							getBuildings()[x + i][y + j] = b;
						}
					}
				}
				b.onSpawn();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private boolean isSpaceFor(Building b) {
		boolean[][] area = b.getArea();
		int x = (int) b.getX();
		int y = (int) b.getY();
		for (int i = 0; i < area[0].length; i++) {
			for (int j = 0; j < area.length; j++) {
				if ((x + i >= isUsed[0].length || y + j >= isUsed.length) || area[i][j] == isUsed[i + x][j + y])
					return false;
			}
		}
		return true;
	}

	public void destroyBuilding(Building b) {
		boolean[][] area = b.getArea();
		int x = (int) b.getX();
		int y = (int) b.getY();
		for (int i = 0; i < area[0].length; i++) {
			for (int j = 0; j < area.length; j++) {
				if ((x + i < isUsed[0].length && y + j < isUsed.length) && area[i][j]) {
					isUsed[x + i][y + j] = false;
					getBuildings()[x + i][y + j] = null;
				}
			}
		}
		b.onEnd();
		b.dispose();
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
		if (pannel != null)
			pannel.dispose();
		pannel = null;
	}

}
