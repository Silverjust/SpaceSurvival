package guiElements;

import java.util.ArrayList;

import g4p_controls.GButton;
import g4p_controls.GEvent;
import main.Aimer;
import main.ContentListHandler;
import main.Game;
import main.Helper;

public class BuildPannel extends StandardPannel {
	private ArrayList<GButton> buildings = new ArrayList<GButton>();
	private ArrayList<String> name = new ArrayList<String>();
	private Game game;

	public BuildPannel(Game game) {
		super(game);
		this.game = game;
		float i = 0;
		for (Object o : ContentListHandler.getContent().keys()) {
			GButton button = Helper.createButton(game.app, this, 0.2f, 0.2f + i, 0.1f, 0.1f, (String) o);
			name.add((String) o);
			buildings.add(button);
			i += 0.1f;
		}

	}

	public void handleButtonEvents(GButton button, GEvent event) {
		for (GButton b : buildings) {
			if (button == b) {
				game.aimer = new Aimer(name.get(buildings.indexOf(b)), game);
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
