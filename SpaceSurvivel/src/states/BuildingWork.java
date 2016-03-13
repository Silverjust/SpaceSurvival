package states;

import java.util.ArrayList;

import main.Entity;
import main.Unit;

public class BuildingWork extends State {

	ArrayList<Unit> workers = new ArrayList<Unit>();
	private int workerNeeded = 1;
	private int Wmax;
	private float W = 0;

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

	public void addW(float w) {
		W += w;
	}

	@Override
	public boolean needsWorker() {
		return workers.size() < workerNeeded;
	}

	@Override
	public void update(Entity e) {
		if (W >= Wmax) {
			W = 0;
			e.Statefinished(this);
		}
	}

	public void addWorker(Entity e) {
		if (needsWorker())
			workers.add((Unit) e);
	}

	public float getProgress() {
		if (Wmax == 0)
			return 1;
		if (W > Wmax)
			return 1;

		return W / Wmax;
	}

	@Override
	public void onEnd(Entity e) {
		workers.clear();
	}

	public ArrayList<Unit> getWorkers() {
		return workers;
	}
}
