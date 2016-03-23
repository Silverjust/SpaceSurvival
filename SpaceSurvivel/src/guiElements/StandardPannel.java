package guiElements;

import g4p_controls.GButton;
import g4p_controls.GEvent;
import main.Game;
import main.Helper;
import processing.core.PApplet;

public class StandardPannel extends GUIpannel {
	private GButton close;
	private Game game;

	public StandardPannel(Game game) {
		PApplet app = game.app;
		this.game = game;
		close = Helper.createButton(app, 0.1f, 0.1f, 0.1f, 0.1f, "close");
		close.addEventHandler(this, "handleButtonEvents");
	}

	@Override
	public void update() {
		game.app.fill(255, 100);
		game.app.rect(game.app.width * 0.1f, game.app.height * 0.1f, game.app.width * 0.8f, game.app.height * 0.8f);
	}

	@Override
	public void dispose() {
		close.dispose();

	}

	public void handleButtonEvents(GButton button, GEvent event) {
		if (button == close)
			game.disposePannel();
	}

}
