package states;

import buildings.Entity;
import buildings.Unit;

public class HumanWait extends Wait {
	@Override
	public void onStart(Entity e, State old) {
		((Unit) e).canMove = true;
	}
}
