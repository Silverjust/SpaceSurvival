package guiElements;

import java.util.ArrayList;

import buildings.Building;
import buildings.Farm;
import buildings.Kompostierer;
import buildings.Lager;
import g4p_controls.GButton;
import g4p_controls.GEvent;
import main.Aimer;
import main.Game;
import main.Helper;

public class BuildPannel extends StandardPannel {
	private ArrayList<GButton> buildings = new ArrayList<GButton>();
	private ArrayList<Class<?>> classes = new ArrayList<Class<?>>();
	private Game game;
	ArrayList<Class<?>> buildEntities = new ArrayList<Class<?>>();

	public BuildPannel(Game game) {
		super(game, "Build");
		this.game = game;
		setupBuildEntities();
		float i = 0;
		for (Class<?> c : buildEntities) {
			Building b = game.create(c);
			System.out.println("BuildPannel.BuildPannel()"+b);
			GButton button = Helper.createButton(game.app, this, 0.2f, 0.2f + i, 0.1f, 0.1f, b.getIngameName());
			classes.add(c);
			buildings.add(button);
			i += 0.1f;
		}
	}

	public void setupBuildEntities() {
		buildEntities.add(Farm.class);
		buildEntities.add(Kompostierer.class);
		buildEntities.add(Lager.class);
	}

	public void handleButtonEvents(GButton button, GEvent event) {
		for (GButton b : buildings) {
			if (button == b) {
				game.aimer = new Aimer(classes.get(buildings.indexOf(b)), game);
				game.disposePannel();
			}
		}
		super.handleButtonEvents(button, event);
	}

	@Override
	public void dispose() {
		for (GButton button : buildings) {
			button.dispose();
		}
		super.dispose();
	}

}
