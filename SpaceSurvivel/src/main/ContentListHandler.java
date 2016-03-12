package main;

import processing.core.PApplet;
import processing.data.JSONObject;

public class ContentListHandler {
	public static String path = "data/content.json";
	static JSONObject contentList;
	static JSONObject entityList;

	private static PApplet app;

	public static void setup(PApplet app_) {
		app = app_;
	}

	public static void load() {
		contentList = app.loadJSONObject(path);
		entityList = contentList.getJSONObject("entities");
	}

	public static JSONObject getContent() {
		return entityList;
	}

}
