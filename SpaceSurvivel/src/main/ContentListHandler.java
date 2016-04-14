package main;

import java.util.ArrayList;
import buildings.Building;
import buildings.Entity;
import buildings.Farm;
import buildings.Human;
import buildings.Kompostierer;
import buildings.Lager;

public class ContentListHandler {
	public String path = "data/content.json";
	ArrayList<Class<?>> entityList = new ArrayList<Class<?>>();

	public ContentListHandler() {
	}

	public void load() {
		entityList.add(Farm.class);
		entityList.add(Kompostierer.class);
		entityList.add(Lager.class);
		entityList.add(Human.class);

	}

	public ArrayList<Class<?>> getEntities() {
		return entityList;
	}

	public Building getEntitie(String key, Game game) {
		for (Class<?> c : entityList) {
			Building e = game.create(c);
			if (e.getIngameName() == key)
				return e;
		}
		return null;

	}

	public String getNameFor(Entity entity) {
		// TODO Auto-generated method stub
		return null;
	}

}
