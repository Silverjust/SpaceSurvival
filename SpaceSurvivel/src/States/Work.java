package States;

import java.util.ArrayList;

import main.Entity;
import main.Unit;

public class Work extends State {

	ArrayList<Unit> workers = new ArrayList<Unit>();
	private int workerNeeded = 1;
	private int Wmax, W = 0;

	public Work() {
		super();
	}

	public Work setWorkers(int n) {
		workerNeeded = n;
		return this;
	}

	public Work setW(int i) {
		Wmax = i;
		return this;
	}

	@Override
	public boolean needsWorker() {
		return workers.size() < workerNeeded;
	}

	@Override
	public void update(Entity e) {
		if (W >= Wmax)
			e.Statefinished(this);
	}

}
