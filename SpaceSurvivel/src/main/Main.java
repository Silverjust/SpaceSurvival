package main;

import javax.swing.JFrame;

import g4p_controls.G4P;
import processing.core.PApplet;
import processing.core.PFont;

@SuppressWarnings("serial")
public class Main extends PApplet {
	public static void main(String args[]) {
		boolean fullscreen = true;
		fullscreen = true;
		if (fullscreen) {
			PApplet.main(new String[] { "--present", "main.Main" });
		} else {
			PApplet.main(new String[] { "main.Main" });
		}
	}

	PFont font;
	private Game game;
	private Commands commands;

	@Override
	public void setup() {
		size(displayWidth, displayHeight, P2D);
		frame.setExtendedState(frame.getExtendedState() | JFrame.MAXIMIZED_BOTH);
		frame.addWindowListener(new Listener(this));
		frame.setResizable(true);
		frame.setTitle("test");
		// frame.addWindowListener(new Listener());
		// frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		// frame.setVisible(true);
		// frameRate(60);
		font = createFont("Aharoni Fett", 40);
		// System.out.println(font.ascent());
		textFont(font);
		G4P.messagesEnabled(false);
		//noSmooth();

		game = new Game(this);
		commands = new Commands(game);
		thread("commands");
	}

	@Override
	public void draw() {
		game.update();
	}

	@Override
	public void keyPressed() {

	}

	public void commands() {
		commands.loop();
	}

	@Override
	public void dispose() {
		frame.setVisible(false);
		super.dispose();
	}

}
