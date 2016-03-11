package main;

import processing.core.PApplet;

public class GameTime {
private static PApplet app;

	public GameTime(PApplet app) {
		this.app=app;
}
	public static int getMillis() {
		return app.millis();
	}

}
