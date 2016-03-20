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

	public RessourceGroup getMin() {
		return min;
	}

	public void setMin(RessourceGroup res) {
		min = res;
	}

	public void add(RessourceGroup res) {
		amount.add(res);
	}

	public void give(Slot from, RessourceGroup amount) {
		from.add(amount.inv());
		add(amount);
		System.out.println("Slot.give()");

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
		// System.out.println("Slot.contains()" + contains);
		return contains;
	}

	public boolean containsPure(Ressource ressource) {
		if (!amount.isEmpty())
			if (amount.getAmount(ressource.getName()) > ressource.getAmount())
				return true;
		return false;

	}

	public String getText() {
		String s = "Ressources:\n";
		ResNames[] res = min.getRessources();
		if (res.length > 0) {
			for (int i = 0; i < res.length; i++) {
				s += res[i].getName() + ": " + amount.getAmount(res[i]) + "/" + min.getAmount(res[i]) + "\n";
			}
		} else {
			ResNames[] res2 = amount.getRessources();
			for (int i = 0; i < res2.length; i++) {
				s += res2[i].getName() + ": " + amount.getAmount(res2[i]) + "\n";
			}
		}
		return s;

	}

	public void give(Slot output, Ressource res) {
		output.getRes().addToRessource(res.getName(), -res.getAmount());
		amount.addToRessource(res.getName(), res.getAmount());
		//System.out.println("Slot.give()" + output.getRes().getAmount(res.getName()));
		//System.out.println("Slot.give()" + amount.getAmount(res.getName()));
	}

	public RessourceGroup getRes() {
		return amount;
	}
}
