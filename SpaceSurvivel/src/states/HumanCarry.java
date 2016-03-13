package states;

import main.Entity;
import main.Unit;

public class HumanCarry extends State {
	@Override
	public void onStart(Entity e) {
		((Unit) e).canMove = true;
	}
}