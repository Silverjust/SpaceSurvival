package guiElements;

import buildings.GUIpannel;
import g4p_controls.GButton;
import g4p_controls.GEvent;
import main.Game;

public class BuildPannel extends GUIpannel {
	private GButton close;
	private GButton farm;
	private Game game;
	public BuildPannel(Game game) {
		this.game = game;
		farm = new GButton(game.app, 1400, 100, 100, 100, "Farm");
		farm.addEventHandler(this, "handleButtonEvents");
		close= new GButton(game.app, 1400, 200, 200, 100, "close");
		close.addEventHandler(this, "handleButtonEvents");
	}
	public void handleButtonEvents(GButton button, GEvent event) {
		if (button == farm) {
			game.build("farm", 15, 15);
	}
		if (button == close) {
			game.disposePannel();
	}
}
	@Override
	public void dispose() {
		close.dispose();
		farm.dispose();
	}

}
