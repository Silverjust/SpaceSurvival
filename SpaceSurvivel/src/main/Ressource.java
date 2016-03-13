package main;

public class Ressource {
	String name;
	private float amount;

	public Ressource(String name) {
		this.name = name;
		this.amount=0;
		
	}

	public float getAmount() {
		return amount;
	}

	public void addAmount(float amount) {
		this.amount+= amount;
	}

}
