package main;

public enum Ressources {

	METALL("Metall"),WASSER("Wasser"),NAHRUNG("Nahrung");

	private String name;

	private Ressources(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
