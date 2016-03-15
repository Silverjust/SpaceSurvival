package states;

import buildings.Entity;
import buildings.Unit;

public class HumanCarry extends State {
	@Override
	public void onStart(Entity e) {
		((Unit) e).canMove = true;
	}
}
