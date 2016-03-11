package main;

import javax.swing.JFrame;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PFont;

@SuppressWarnings("serial")
public class Main extends PApplet {
	public static void main(String args[]) {
		boolean fullscreen = false;
		fullscreen = true;
		if (fullscreen) {
			PApplet.main(new String[] { "--present", "main.Main" });
		} else {
			PApplet.main(new String[] { "main.Main" });
		}
	}

	PFont font;
	private Game game;

	@Override
	public void setup() {
		size(displayWidth, displayHeight, P2D);
		frame.setExtendedState(frame.getExtendedState() | JFrame.MAXIMIZED_BOTH);
		frame.setResizable(true);
		frame.setTitle("test");
		// frame.addWindowListener(new Listener());
		// frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		// frame.setVisible(true);
		// frameRate(60);
		font = createFont("Aharoni Fett", 40);
		// System.out.println(font.ascent());
		textFont(font);
		noSmooth();

		game = new Game(this);

	}

	@Override
	public void draw() {
		game.update();
	}

	@Override
	public void keyPressed() {

	}

	@Override
	public void dispose() {
		frame.setVisible(false);
		super.dispose();
	}

}
