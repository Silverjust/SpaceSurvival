package components;

public class Ressource {
	String name;
	private float amount;

	public Ressource(ResNames metall) {
		this.name = metall.getName();
		this.amount = 0;

	}

	public float getAmount() {
		return amount;
	}

	public void addAmount(float amount) {
		this.amount += amount;
	}

}
