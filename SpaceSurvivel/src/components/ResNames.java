package components;

public enum ResNames {

	METALL("Metall"),WASSER("Wasser"),NAHRUNG("Nahrung"),BIOMÜLL("Biomüll"),DÜNGER("Dünger");

	private String name;

	private ResNames(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
