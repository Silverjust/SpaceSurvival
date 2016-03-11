package main;

import java.util.Scanner;

import processing.core.PApplet;

public class Commands {
	private Game game;
	String command;
	Scanner scanInput;

	public Commands(Game game) {
		this.game = game;
		scanInput = new Scanner(System.in);
	}

	void loop() {
		while (true) {
			boolean noError = true;
			if (scanInput.hasNextLine()) {
				try {
					command = scanInput.nextLine();
					String[] c = PApplet.splitTokens(command, " ");
					switch (c[0]) {
					case "build":
						game.build(Integer.parseInt(c[1]), Integer.parseInt(c[2]));
						break;
					case "spawn":
						game.spawn(Integer.parseInt(c[1]), Integer.parseInt(c[2]));
						break;

					default:
						noError = false;
						System.err.println("command not found");
						break;
					}
				} catch (Exception e) {
					e.printStackTrace();
					noError = false;
				}
			}
			if (noError) {
				System.out.println("done");
			}
		}
	}

	public void dispose() {
		scanInput.close();
	}
}
