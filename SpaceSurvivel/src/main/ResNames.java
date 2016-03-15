package main;

public enum ResNames {

	METALL("Metall"),WASSER("Wasser"),NAHRUNG("Nahrung");

	private String name;

	private ResNames(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
