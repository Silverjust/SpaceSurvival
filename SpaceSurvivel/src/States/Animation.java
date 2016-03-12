package States;

import java.util.ArrayList;

import main.Entity;

public class Animation {
	ArrayList<Entity> workers =new ArrayList<Entity>();
	private int workerNeeded;

	public void update() {
	}

	public boolean hasNotEnoughWorker() {
		
		return workers.size()<workerNeeded;
	}

}
