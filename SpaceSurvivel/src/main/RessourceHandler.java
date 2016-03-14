package main;

import java.util.HashMap;

public class RessourceHandler {
	private HashMap<ResNames, Ressource> ressources;

	public RessourceHandler() {
		this.ressources = new HashMap<ResNames, Ressource>();
	}

	public void addToRessource(ResNames res, int amount) {
		if (!ressources.containsKey(res))
			ressources.put(res, new Ressource(res));
		ressources.get(res).addAmount(amount);
	}

	public ResNames[] getRessources() {
		return ressources.keySet().toArray(new ResNames[ressources.keySet().size()]);
	}
}