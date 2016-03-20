package components;

public class Ressource {
	private ResNames name;
	private float amount;

	public Ressource(ResNames name) {
		this.name = name;
		this.amount = 0;
	}

	public float getAmount() {
		return amount;
	}

	public void addAmount(float amount) {
		this.amount += amount;
	}

	public ResNames getName() {
		return name;
	}

}
