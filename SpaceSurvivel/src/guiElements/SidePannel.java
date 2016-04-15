package guiElements;

import g4p_controls.GButton;
import g4p_controls.GEvent;
import main.Game;
import main.Helper;

public class SidePannel extends GUIpannel {
	public boolean closed;

	private Game game;
	private byte slider;

	private GButton close;
	private GButton open;
	private GButton buildings;

	public SidePannel(Game game) {
		this.game = game;
		open = Helper.createButton(game.app, this, 0.95f, 0.0f, 0.05f, 0.07f, "<");
		open.setVisible(false);
		close = Helper.createButton(game.app, this, 0.875f, 0.0f, 0.05f, 0.07f, ">");
		buildings = Helper.createButton(game.app, this, 0.875f, 0.2f, 0.1f, 0.1f, "buildings");

	}

	public void toggle() {
		closed = !closed;
		if (closed)
			setButtonsVisible(false);

	}

	public void handleButtonEvents(GButton button, GEvent event) {
		if (button == open || button == close) {
			toggle();
		} else if (button == buildings) {
			game.setPannel(new BuildPannel(game));
		}
	}

	@Override
	public void update() {
		game.app.fill(200, 150);
		game.app.rect(game.app.width * (0.85f + 0.1f * (1.0f - slider / 100.0f)), 0,
				game.app.width * (0.15f + 0.1f * (slider / 100.0f)), game.app.height);
		if (closed && slider > 0) {
			slider -= 5;
		} else if (!closed && slider < 100) {
			slider += 5;
		}
		if (!closed && !buildings.isVisible() && slider == 100) {
			setButtonsVisible(true);
		}
	}

	public void setButtonsVisible(boolean b) {
		buildings.setVisible(b);
		close.setVisible(b);
		open.setVisible(!b);
	}

	@Override
	public void dispose() {
		buildings.dispose();
	}

}
