package states;

import buildings.Building;
import buildings.Entity;
import buildings.Human;
import components.ResNames;
import components.Ressource;

public class HumanEat extends State {
	Building target;
	private HumanGetQuest eat;
	private HumanBringQuest shit;
	private float hunger;

	public HumanEat() {
		eat = new HumanGetQuest();
		shit = new HumanBringQuest();
	}

	@Override
	public void onStart(Entity e, State old) {

		if (old == eat) {
			((Human) e).consumeFood((int) -hunger);
			shit.setMin(new Ressource(ResNames.BIOMÜLL, hunger));
			e.insertState(shit, this);
		} else if (old == shit) {
			e.endState();
		} else {
			hunger = ((Human) e).getHunger();
			eat.setMin(new Ressource(ResNames.NAHRUNG, hunger));
			e.insertState(eat, this);
		}
	}

	@Override
	public void update(Entity e) {
	}
}
