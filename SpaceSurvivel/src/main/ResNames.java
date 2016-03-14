package main;

public enum ResNames {

	METALL("Metall");

	private String name;

	private ResNames(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
