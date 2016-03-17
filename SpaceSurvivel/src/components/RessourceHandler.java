package components;

import java.util.HashMap;

public class RessourceHandler {
	private HashMap<ResNames, Ressource> ressources;

	public RessourceHandler() {
		this.ressources = new HashMap<ResNames, Ressource>();
	}

	public void addToRessource(ResNames res, float amount) {
		if (!ressources.containsKey(res))
			ressources.put(res, new Ressource(res));
		ressources.get(res).addAmount(amount);
	}

	public void add(RessourceHandler res) {
		for (int i = 0; i < res.getRessources().length; i++) {
			addToRessource(res.getRessources()[i], res.getAmount(res.getRessources()[i]));
		} 
	}

	public float getAmount(ResNames resNames) {
		return ressources.get(resNames).getAmount();
	}

	public ResNames[] getRessources() {
		return ressources.keySet().toArray(new ResNames[ressources.keySet().size()]);
	}
}