package server;

import common.Area;

public abstract class ServerData {
	private static Area area;

	public static Area getArea() {
		if (area == null) {
			area = new Area(128, 128, 128);
			area.randomize(1);
		}
		return area;
	}

	public static void loadWorld() {
		// if (worldfound == true) {
		// // TODO
		// } else {
		// }
	}
}
