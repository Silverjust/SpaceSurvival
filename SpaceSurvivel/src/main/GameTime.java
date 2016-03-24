package main;

public class GameTime {
	private Game game;

	public GameTime(Game game) {
		this.game = game;
	}

	private int pauseStart;
	private int pauseTime;

	public void startPause() {
		if (game.updater.pause == false) {
			game.updater.pause = true;
			pauseStart = game.app.millis();
		}
	}

	public void endPause() {
		if (game.updater.pause == true) {
			game.updater.pause = false;
			pauseTime += game.app.millis() - pauseStart;
			pauseStart = 0;
		}
	}

	public int getMillis() {
		if (pauseStart == 0)
			return game.app.millis() - pauseTime;
		else
			return game.app.millis() - (pauseTime + game.app.millis() - pauseStart);
	}

}
