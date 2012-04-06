package client.input;

import org.lwjgl.input.Mouse;

import render3d.Camera;
import render3d.Point2i;

public class MouseHandler {
	private static int returnx = 200, returny = 200; // Where the mouse anchors
														// during right-clicking

	private int clickx, clicky; // Store mouse coordinates during right-clicking

	private boolean clicking;
	private Camera camera;

	public void update() {
		while (Mouse.next()) {
			switch (Mouse.getEventButton()) { // left = 0; right = 1;
			case 1:
				if (Mouse.getEventButtonState()) {
					clickx = Mouse.getEventX();
					clicky = Mouse.getEventY();
					clicking = true;
					Mouse.setCursorPosition(returnx, returny);
					Mouse.getDX();
					Mouse.getDY();
					Mouse.setGrabbed(clicking);
				} else {
					clicking = false;
					Mouse.setCursorPosition(clickx, clicky);
				}
				Mouse.setGrabbed(clicking);
				break;
			default:
			}
		}
		updateCamera();
	}

	public void updateCamera() {
		if (clicking) {
			camera.rotateh(Mouse.getDX());
			camera.rotatev(-Mouse.getDY());
		}
	}

	public Point2i getClickyDelta() {
		if (clicking) {
			return new Point2i(returnx - Mouse.getX(), returny - Mouse.getY());
		} else {
			return null;
		}
	}

	public void addCamera(Camera c) {
		this.camera = c;
	}
}
