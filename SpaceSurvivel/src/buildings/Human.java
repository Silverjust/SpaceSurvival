package buildings;

import main.Game;
import main.Helper.Timer;
import processing.core.PApplet;
import states.HumanCarry;
import states.HumanEat;
import states.HumanGotoWork;
import states.HumanWait;
import states.HumanWork;
import states.State;

public class Human extends Unit {

	private static final float foodOverTime = 0.1f;
	public static final float foodOnWork = 0.1f;
	private boolean hasWork;
	public HumanWork work;
	public State carry;
	public HumanGotoWork gotoWork;
	private int number;
	static int totalNumber;
	private float foodMax;
	public float food;
	private State eat;
	private Timer foodTimer;

	public Human(Game game, int x, int y) {
		super(game, x, y);
		ingameName = "human";
		speed = 0.3f;
		wait = new HumanWait();
		work = new HumanWork(game);
		carry = new HumanCarry();
		gotoWork = new HumanGotoWork();
		eat = new HumanEat();
		foodTimer = new Timer(game.gameTime, 500);
		food = 19;
		foodMax = 20;
		createRandomTarget();
		insertState(wait, this);
	}

	@Override
	public void onSpawn() {
		totalNumber++;
		number = totalNumber;
	}

	@Override
	public void update() {
		super.update();
		if (getState() == wait) {
			if (PApplet.dist(x, y, xt, yt) < 0.5) {
				createRandomTarget();
			}
		}
		if (foodTimer.isNotOnCooldown()) {
			food -= foodOverTime;
			foodTimer.startCooldown();
		}
		if (food < foodMax * 0.1 && !statesContains(eat)) {
			insertState(eat, this);
		}

	}

	public boolean isSatt() {
		return food >= foodMax;
	}

	@Override
	public void draw() {
		super.draw();
		game.app.line(x * Game.gridSize + 25, y * Game.gridSize + 25, xt * Game.gridSize + 25, yt * Game.gridSize + 25);
		game.app.text(getStateNames() + " " + number, x * Game.gridSize + 25, y * Game.gridSize + 25);
		game.app.fill(180, 150);
		game.app.rect(x * Game.gridSize, y * Game.gridSize - 15, Game.gridSize, 5);
		game.app.fill(0, 255, 0);
		game.app.rect(x * Game.gridSize, y * Game.gridSize - 15, Game.gridSize * (food / foodMax), 5);
	}

	@Override
	public void drawAt(PApplet app, float x, float y) {
		app.fill(100);
		app.ellipse(x + 25, y + 25, 40, 40);
	}

	public float getW() {
		// TODO calculate W
		return 10;
	}

	private void createRandomTarget() {
		setHasWork(false);
		float x = 0, y = 0;
		boolean b = true;
		while (b) {
			x = game.app.random(0, Game.gridW);
			y = game.app.random(0, Game.gridH);
			if (x >= game.isInside.length || y >= game.isInside[0].length)
				b = true;
			else {
				b = !game.isInside[(int) x][(int) y];
			}
		} // System.out.println("Human.createRandomTarget()"+x+" "+y);
		setXt(x);
		setYt(y);
	}

	public boolean hasWork() {
		return hasWork;
	}

	public void setHasWork(boolean hasWork) {
		this.hasWork = hasWork;
	}

	public void consumeFood(float amount) {
		food -= amount;
	}

	public float getHunger() {
		return foodMax - food;
	}

}
