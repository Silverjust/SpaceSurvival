package guiElements;

import g4p_controls.GButton;
import g4p_controls.GEvent;
import main.Game;
import main.Helper;

public class SidePannel extends GUIpannel {
	public boolean closed;
	private GButton buildings;
	private Game game;
	private byte slider;

	public SidePannel(Game game) {
		this.game = game;
		buildings = Helper.createButton(game.app, this, 0.875f, 0.2f, 0.1f, 0.1f, "buildings");

	}

	public void toggle() {
		closed = !closed;
		if (closed)
			setButtonsVisible(false);

	}

	public void handleButtonEvents(GButton button, GEvent event) {
		if (button == buildings) {
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
	}

	@Override
	public void dispose() {
		buildings.dispose();
	}

}
