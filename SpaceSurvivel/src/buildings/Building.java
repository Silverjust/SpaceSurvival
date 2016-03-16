package buildings;

import main.Game;
import states.BuildingWork;

public abstract class Building extends Entity {

	protected int x;
	protected int y;
	protected BuildingWork build;
	private boolean[][] area;

	public Building(Game game, int x, int y) {
		super(game);
		this.x = x;
		this.y = y;
		setAreaSqare(1, 1);
	}

	@Override
	public float getX() {
		return x;
	}

	@Override
	public float getY() {
		return y;
	}

	public boolean[][] getArea() {
		return area;
	}

	public void setArea(boolean[][] area) {
		this.area = area;
	}

	public void setAreaSqare(int w, int h) {
		boolean[][] area = new boolean[w][h];
		for (int i = 0; i < w; i++) {
			for (int j = 0; j < h; j++) {
				area[i][j] = true;
			}
		}
		this.setArea(area);
	}

}
