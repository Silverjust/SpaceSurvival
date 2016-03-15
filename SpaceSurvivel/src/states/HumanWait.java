package states;

import buildings.Entity;
import buildings.Unit;

public class HumanWait extends State {
	@Override
	public void onStart(Entity e) {
		((Unit) e).canMove = true;
	}
}
