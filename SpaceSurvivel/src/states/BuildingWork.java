package states;

import java.util.ArrayList;

import buildings.Entity;
import buildings.Human;
import buildings.Unit;
import components.ResNames;
import components.RessourceGroup;
import components.Slot;
import main.Game;

public class BuildingWork extends State implements Storing {

	ArrayList<Unit> workers = new ArrayList<Unit>();
	private int workerNeeded = 1;
	private int Wmax;
	private float W = 0;
	Slot in = new Slot(), out = new Slot();

	public BuildingWork() {
		super();
	}

	public BuildingWork setWorkers(int n) {
		workerNeeded = n;
		return this;
	}

	public BuildingWork setW(int i) {
		Wmax = i;
		return this;
	}

	public BuildingWork setInput(RessourceGroup res) {
		in.setMin(res);
		return this;
	}

	public BuildingWork setOutput(RessourceGroup res) {
		out.setMin(res);
		return this;
	}

	@Override
	public void onStart(Entity e) {
		if (!inputHasMin())
			callGetter(e);
		else if (needsWorker())
			callWorker(e);

	}

	@Override
	public void onEnd(Entity e) {
		for (Unit unit : workers) {
			unit.setState(((Human) unit).wait, this);
		}
		workers.clear();
	}

	@Override
	public void update(Entity e) {
		// System.out.println("BuildingWork.onStart()"+in.getText());
		if (!inputHasMin())
			callGetter(e);
		else if (needsWorker())
			callWorker(e);
		if (W >= Wmax) {
			W = 0;
			in.add(in.getMin().inv());
			out.add(out.getMin());
			callTaker(out);

			e.Statefinished(this);
		}
	}

	public void addW(Entity target, float w) {
		if (inputHasMin())
			W += w;
		else
			callGetter(target);
	}

	/** test if this has enough ressources to produce output */
	private boolean inputHasMin() {
		return in.hasMin();
	}

	public boolean needsWorker() {
		return workers.size() < workerNeeded;
	}

	private void callWorker(Entity e) {
		for (Entity entity : e.game.getEntities()) {
			if (entity instanceof Human && !((Human) entity).hasWork() && needsWorker()) {
				((Human) entity).gotoWork.setTarget(e, this, ((Human) entity));
				((Human) entity).setState(((Human) entity).gotoWork, this);
			}
		}
	}

	private void callGetter(Entity e) {
		ResNames[] res = in.getMin().getRessources();
		for (int n = 0; n < res.length; n++) {
			// System.out.println("BuildingWork.callGetter()");
			for (Entity entity : e.game.getEntities()) {
				if (entity instanceof Human && !((Human) entity).hasWork()) {
					Human human = (Human) entity;
					for (int i = 0; i < Game.gridW; i++) {
						for (int j = 0; j < Game.gridH; j++) {
							Entity building = e.game.getBuildings()[i][j];
							if (building != null && building.getState() instanceof Storing
									&& ((Storing) building.getState()).getOutput()
											.containsPure(in.getMin().get(res[n]))) {
								((HumanCarry) human.carry).setTargets(building, e, human, in.getMin().get(res[n]));
								entity.setState(human.carry, this);
							}
						}
					}
				}
			}
		}
	}

	private void callTaker(Slot out2) {
		// TODO Auto-generated method stub

	}

	@Deprecated
	public void addWorker(Entity e) {

	}

	public float getProgress() {
		if (Wmax == 0)
			return 1;
		if (W > Wmax)
			return 1;

		return W / Wmax;
	}

	@Override
	public Slot getInput() {
		return in;
	}

	@Override
	public Slot getOutput() {
		return out;
	}

	public ArrayList<Unit> getWorkers() {
		return workers;
	}

	public void registerAsWorker(Unit u) {
		if (needsWorker())
			workers.add((Unit) u);
	}
}
