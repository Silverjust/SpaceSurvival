package states;

import main.Entity;
import main.Unit;

public class HumanWork extends State {
	@Override
	public void startState(Entity e) {
		((Unit) e).canMove = false;
	}
}
