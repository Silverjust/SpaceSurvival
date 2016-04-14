package main;

import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;

import processing.core.PApplet;
import processing.core.PImage;

public class ImageManager {
	String dataPath = "data/";
	public HashMap<ImgTag, PImage> images = new HashMap<ImgTag, PImage>();

	public int nImagesToLoad;

	public ArrayList<Class<?>> classes = new ArrayList<Class<?>>();
	private boolean dispose = false;
	private Main main;

	public ImageManager(Main main) {
		this.main = main;
		classes.addAll(main.contentListHandler.getEntities());
		classes.add(Game.class);
	}

	public boolean requestAllImages() {
		try {
			for (Class<?> c : classes) {
				try {
					Method m = c.getDeclaredMethod("loadImages", ImageManager.class);
					m.invoke(null, this);
				} catch (NoSuchMethodException nme) {
					System.out.println("ImageManager.requestAllImages(): " + c.getSimpleName()
							+ " is missing   public static void loadImages(ImageManager manager){}");
				}
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
		for (ImgTag s : images.keySet()) {
			PImage i = images.get(s);
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

	public PImage[] load(String path, String name, char animation, byte iterations, ImgTag tag) {
		if (dispose)
			return null;
		PImage[] imageArray = new PImage[iterations];
		for (int i = 0; i < iterations || iterations == 0 && i == 0; i++) {
			nImagesToLoad++;
			String s = dataPath + path + name + (animation != 0 ? "_" + animation : "")
					+ (iterations != 0 ? "_" + PApplet.nf(i, 4) : "") + ".png";
			imageArray[i] = request(s, tag);

		}

		return imageArray;
	}

	public PImage load(String path, String name, char animation, ImgTag tag) {
		if (dispose)
			return null;
		PImage image;
		nImagesToLoad++;
		String s = dataPath + path + name + (animation != 0 ? "_" + animation : "") + ".png";
		image = request(s, tag);
		return image;
	}

	public PImage load(String path, String name, ImgTag tag) {
		if (dispose)
			return null;
		PImage image;
		nImagesToLoad++;
		String s = dataPath + path + name + ".png";
		image = request(s, tag);
		return image;
	}

	public PImage load(String path, ImgTag tag) {
		if (dispose)
			return null;
		PImage image;
		nImagesToLoad++;
		String s = dataPath + path + ".png";
		image = request(s, tag);
		return image;
	}

	private PImage request(String s, ImgTag tag) {
		PImage image;
		System.out.println(s);
		s = getPath(s);
		image = main.requestImage(s);
		images.put(tag, image);
		return image;
	}

	private String getPath(String path) {

		try {
			path = main.getClass().getClassLoader().getResource(path).getFile();
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

	public PImage get(ImgTag key) {
		PImage img = images.get(key);
		if (img == null)
			System.out.println("ImageManager.get(): Warning img is null");
		return img;
	}
}
