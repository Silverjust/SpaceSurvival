package main;

import java.util.ArrayList;
import processing.core.PApplet;

public class Helper {

	public static byte getDirection(float x, float y, float tx, float ty) {
		float a = (float) Math.toDegrees(Math.atan2(y - ty, x - tx));
		a += 112.5;
		if (a < 0) {
			a += 360;
		}
		byte b = (byte) (a * 8 / 360);
		return (0 <= b && b <= 8) ? b : 0;
	}

	@Deprecated
	public static boolean StringToBoolean(String S) {
		boolean b = false;
		if (S.equals("true")) {
			b = true;
		} else if (S.equals("false")) {
			b = false;
		} else {
			throw new IllegalArgumentException("String is no boolean");
		}
		return b;
	}

	public static boolean isOver(float x, float y, float x1, float y1, float x2, float y2) {
		boolean b = x1 <= x && x <= x2 && y1 <= y && y <= y2;
		return b;
	}

	public static boolean isBetween(float x, float y, float x1, float y1, float x2, float y2) {
		boolean b = (x1 < x2 ? (x1 <= x && x <= x2) : (x2 <= x && x <= x1))
				&& (y1 < y2 ? (y1 <= y && y <= y2) : (y2 <= y && y <= y1));
		return b;
	}

	public static boolean isMouseOver(PApplet app, float x1, float y1, float x2, float y2) {
		boolean b = x1 <= app.mouseX && app.mouseX <= x2 && y1 <= app.mouseY && app.mouseY <= y2;
		return b;
	}

	public static boolean listContainsInstanceOf(Class<?> c, ArrayList<Unit> arrlist) {
		if (c == null) {
			return true;
		}
		for (Unit e : arrlist) {
			if (c.isAssignableFrom(e.getClass())) {
				return true;
			}

		}
		return false;
	}

	public static int listContainsInstancesOf(Class<?> c, ArrayList<Unit> arrlist) {
		if (c == null) {
			return 0;
		}
		int i = 0;
		for (Unit e : arrlist) {
			if (c.isAssignableFrom(e.getClass())) {
				i++;
			}

		}
		return i;
	}

	static public class Timer {
		public int cooldown;
		private int cooldownTimer;

		public Timer() {
		}

		public Timer(int cooldown) {
			this.cooldown = cooldown;
		}

		public void startCooldown() {
			cooldownTimer = GameTime.getMillis() + cooldown;
		}

		public boolean isNotOnCooldown() {
			return cooldownTimer <= GameTime.getMillis();
		}

		public float getCooldownPercent() {
			float f = 1 - (float) (cooldownTimer - GameTime.getMillis()) / cooldown;
			return f > 1 || f < 0 ? 1 : f;
		}

		public float getTimeLeft() {
			return (cooldownTimer - GameTime.getMillis());
		}

	}
}
