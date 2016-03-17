package components;

public class Slot {
	RessourceGroup amount, min;
	float max;

	public Slot() {
		amount = new RessourceGroup();
		min = new RessourceGroup();
	}

	public boolean hasMin() {
		boolean hasMin = false;
		if (!min.isEmpty()) {
			ResNames[] res = min.getRessources();
			for (int i = 0; i < res.length; i++) {
				if (min.getAmount(res[i]) < amount.getAmount(res[i]))
					hasMin = true;
			}
		} else
			hasMin = true;
		return hasMin;
	}

	public void add(RessourceGroup res) {
		amount.add(res);

	}

	public RessourceGroup getMin() {
		return min;
	}

	public void setMin(RessourceGroup res) {
		min = res;
	}

	public boolean contains(RessourceGroup testAmount) {
		boolean contains = true;
		if (!amount.isEmpty()) {
			ResNames[] res = min.getRessources();
			for (int i = 0; i < res.length; i++) {
				if (amount.getAmount(res[i]) < testAmount.getAmount(res[i]))
					contains = false;
			}
		}
		System.out.println("Slot.contains()" + contains);
		return contains;
	}

	public void give(Slot from, RessourceGroup amount) {
		from.add(amount.inv());
		add(amount);

	}
}
