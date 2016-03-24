package states;

import components.Slot;

public class StorageWait extends Wait implements Storing {

	private Slot store;

	public StorageWait() {
		store = new Slot();
	}

	@Override
	public Slot getInput() {
		return store;
	}

	@Override
	public Slot getOutput() {
		return store;
	}

}
