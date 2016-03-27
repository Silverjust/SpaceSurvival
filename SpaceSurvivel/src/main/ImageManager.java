package main;

import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PImage;

public class ImageManager {
	String dataPath = "data/";
	public ArrayList<PImage> imagesToLoad = new ArrayList<PImage>();

	public int nImagesToLoad;

	public ArrayList<Class<?>> classes = new ArrayList<Class<?>>();
	private boolean dispose = false;
	private Game game;

	public ImageManager(Game game) {
		this.game = game;
	}

	public boolean requestAllImages() {
		try {
			classes.addAll(game.contentListHandler.getEntities());
			
			for (Class<?> c : classes) {
				Method m = c.getDeclaredMethod("loadImages");
				m.invoke(null, this);
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public float stateOfLoading() {
		int loadedImages = 0;
		boolean error = false;
		for (PImage i : imagesToLoad) {
			if (i.width == -1) {
				System.err.println("Image error");
				error = true;
			}
			if (i.width > 0) {
				loadedImages++;
			}

		}
		if (error)
			loadedImages = -1;
		return (float) (loadedImages) / nImagesToLoad;
	}

	public PImage[] load(String path, String name, char animation, byte iterations) {
		if (dispose)
			return null;
		PImage[] imageArray = new PImage[iterations];
		for (int i = 0; i < iterations || iterations == 0 && i == 0; i++) {
			nImagesToLoad++;
			String s = dataPath + path + name + (animation != 0 ? "_" + animation : "")
					+ (iterations != 0 ? "_" + PApplet.nf(i, 4) : "") + ".png";
			System.out.println(s);
			s = getPath(s);
			imageArray[i] = game.app.requestImage(s);
			imagesToLoad.add(imageArray[i]);
		}

		return imageArray;
	}

	public PImage load(String path, String name, char animation) {
		if (dispose)
			return null;
		PImage image;
		nImagesToLoad++;
		String s = dataPath + path + name + (animation != 0 ? "_" + animation : "") + ".png";
		System.out.println(s);
		s = getPath(s);
		image = game.app.requestImage(s);
		imagesToLoad.add(image);
		return image;
	}

	public PImage load(String path, String name) {
		if (dispose)
			return null;
		PImage image;
		nImagesToLoad++;
		String s = dataPath + path + name + ".png";
		System.out.println(s);
		s = getPath(s);
		image = game.app.requestImage(s);
		imagesToLoad.add(image);
		return image;
	}

	private String getPath(String path) {

		try {
			path = game.app.getClass().getClassLoader().getResource(path).getFile();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return path;
	}

	String getClassPath(Object o) {
		String pkg = o.getClass().getEnclosingClass().getCanonicalName();

		int pos = pkg.lastIndexOf("."); // Slash before the class name
		if (pos == -1)
			return ""; // No package
		pkg = pkg.substring(0, pos + 1); // Keep the ending dot
		String cp = pkg.replaceAll("\\.", "/");
		return cp;

	}

	public String getBinaryPath() {
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		File classpathRoot = new File(classLoader.getResource("").getPath());
		return classpathRoot.getPath() + "\\";
	}

	public void dispose() {
		/*
		 * for (PImage img : imagesToLoad) { Object cache =
		 * ref.app.getCache(img); if (cache instanceof Texture) ((Texture)
		 * cache).disposeSourceBuffer(); ref.app.removeCache(img); } dispose =
		 * true; requestAllImages(); dispose = false;
		 * 
		 * // for (int i = 0; i < imagesToLoad.size(); i++) { imagesToLoad }
		 * 
		 * imagesToLoad.clear();
		 */
	}

	public void drawImage(PApplet pApplet, PImage img, float a, float b, float c, float d) {
		try {
			pApplet.image(img, a, b, c, d);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
