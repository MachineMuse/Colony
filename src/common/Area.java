package common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class Area implements Serializable {
	private static final long serialVersionUID = 4640422969062147923L;
	private final ArrayList<Block> blocks;
	private final int width;
	private final int height;
	private final int depth;

	public Area(int w, int h, int d) {
		width = w;
		height = h;
		depth = d;
		blocks = new ArrayList<Block>(volume());

	}

	public int volume() {
		return width * height * depth;
	}

	public int convertCoords(int x, int y, int z) {
		// x coordinate is the maximum stride, y is the middle
		return x * width * height + y * width + z;
	}

	public Block getBlock(int x, int y, int z) {
		return blocks.get(convertCoords(x, y, z));
	}

	public void randomize(int mode) {
		Random generator = new Random();
		for (int i = 0; i < volume(); i++) {
			blocks.add(i, randomBlock(generator.nextInt()));
		}
	}

	public Block randomBlock(int t) {
		Block b = new Block();
		b.setType(t);
		return b;
	}
}
