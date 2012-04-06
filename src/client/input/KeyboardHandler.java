package client.input;

import org.lwjgl.input.Keyboard;

import render3d.Camera;

public class KeyboardHandler {
	private static final float step = 0.1f;
	private Camera camera;
	private boolean left;
	private boolean right;
	private boolean forward;
	private boolean back;
	private boolean up;
	private boolean down;

	public void update() {
		left = Keyboard.isKeyDown(Keyboard.KEY_A);
		right = Keyboard.isKeyDown(Keyboard.KEY_D);
		forward = Keyboard.isKeyDown(Keyboard.KEY_W);
		back = Keyboard.isKeyDown(Keyboard.KEY_S);
		up = Keyboard.isKeyDown(Keyboard.KEY_SPACE);
		down = Keyboard.isKeyDown(Keyboard.KEY_C);

		updateCamera();
	}

	public void updateCamera() {
		if (left) {
			camera.translate(step, 0, 0);
		}
		if (right) {
			camera.translate(-step, 0, 0);
		}
		if (forward) {
			camera.translate(0, 0, step);
		}
		if (back) {
			camera.translate(0, 0, -step);
		}
		if (up) {
			camera.translate(0, -step, 0);
		}
		if (down) {
			camera.translate(0, step, 0);
		}
	}

	public void addCamera(Camera c) {
		this.camera = c;
	}
}
