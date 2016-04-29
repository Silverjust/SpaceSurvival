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
						game.build(game.contentListHandler.getEntitie(c[1], game).getClass(), Integer.parseInt(c[2]),
								Integer.parseInt(c[3]));
						break;
					case "spawn":
						game.spawn(game.contentListHandler.getEntitie(c[1], game).getClass(), Integer.parseInt(c[2]),
								Integer.parseInt(c[3]));
						break;
					case "info":
						System.out.println("at " + c[1] + " " + c[2] + " is "
								+ game.getBuildings()[Integer.parseInt(c[1])][Integer.parseInt(c[2])].getIngameName());
						break;

					default:
						noError = false;
						System.err.println("command not found");
						break;
					}
				} catch (Exception e) {
					noError = false;
					e.printStackTrace();

				}
			}
			game.app.delay(100);
			if (noError) {
				System.out.println("done");
			}
		}
	}

	public void dispose() {
		scanInput.close();
	}
}
