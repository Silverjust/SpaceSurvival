package guiElements;

import g4p_controls.GButton;
import g4p_controls.GEvent;
import main.Game;
import main.Helper;
import processing.core.PApplet;

public class StandardPannel extends GUIpannel {
	private GButton close;
	protected Game game;
	private String name;

	public StandardPannel(Game game, String name) {
		PApplet app = game.app;
		this.game = game;
		this.name = name;
		close = Helper.createButton(app, this, 0.1f, 0.1f, 0.1f, 0.1f, "close");
	}

	@Override
	public void update() {
		game.app.fill(200, 150);
		game.app.rect(game.app.width * 0.1f, game.app.height * 0.1f, game.app.width * 0.7f, game.app.height * 0.8f);
		game.app.fill(0);
		game.app.text(name, game.app.width * 0.2f, game.app.height * 0.15f);
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
