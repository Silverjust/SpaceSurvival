package states;

import buildings.Entity;
import buildings.Unit;

public class HumanWait extends Wait {
	@Override
	public void onStart(Entity e) {
		((Unit) e).canMove = true;
	}
}
