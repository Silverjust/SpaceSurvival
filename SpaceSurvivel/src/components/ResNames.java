package components;

public enum ResNames {

	METALL("Metall"),WASSER("Wasser"),NAHRUNG("Nahrung"),BIOM�LL("Biom�ll"),D�NGER("D�nger");

	private String name;

	private ResNames(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
