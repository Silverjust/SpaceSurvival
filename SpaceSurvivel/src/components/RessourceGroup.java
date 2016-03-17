package components;

import java.util.HashMap;

public class RessourceGroup {
	private HashMap<ResNames, Ressource> ressources;

	public RessourceGroup() {
		this.ressources = new HashMap<ResNames, Ressource>();
	}

	private RessourceGroup(HashMap<ResNames, Ressource> res) {
		this.ressources = res;
	}

	public void addToRessource(ResNames res, float amount) {
		if (!ressources.containsKey(res))
			ressources.put(res, new Ressource(res));
		ressources.get(res).addAmount(amount);
	}

	public void add(RessourceGroup res) {
		for (int i = 0; i < res.getRessources().length; i++) {
			addToRessource(res.getRessources()[i], res.getAmount(res.getRessources()[i]));
		}
	}

	public float getAmount(ResNames resNames) {
		if (ressources.get(resNames) != null)
			return ressources.get(resNames).getAmount();
		return 0;
	}

	public ResNames[] getRessources() {
		return ressources.keySet().toArray(new ResNames[ressources.keySet().size()]);
	}

	public boolean isEmpty() {
		return ressources.isEmpty();
	}

	public RessourceGroup inv() {
		HashMap<ResNames, Ressource> res = new HashMap<ResNames, Ressource>();
		for (int i = 0; i < getRessources().length; i++) {
			ResNames key = getRessources()[i];
			Ressource r = new Ressource(key);
			r.addAmount(-getAmount(key));
			res.put(key, r);
		}
		return new RessourceGroup(res);
	}

}