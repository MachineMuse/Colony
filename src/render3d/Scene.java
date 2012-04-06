package render3d;

import org.lwjgl.opengl.GL11;

public class Scene {
	private Camera camera;
	private final Mesh quad;
	private final Mesh octo;
	private final int flags[] = { GL11.GL_DEPTH_TEST };

	public Scene() {
		camera = new Camera();
		camera.setPosition(new Point3f(0, 0, 0));
		quad = new Mesh();
		quad.MakeBox();
		octo = new Mesh();
		octo.MakeOctahedron();
		octo.translate(new Point3f(0, 0, 20));
	}

	public void draw() {
		enableFlags();
		pushMatrix();

		camera.applyTransform();
		quad.draw();
		octo.draw();
		// samplemesh.draw();

		popMatrix();
		disableFlags();
	}

	private void pushMatrix() {
		GL11.glPushMatrix();
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		camera.applyPerspective();
		GL11.glMatrixMode(GL11.GL_MODELVIEW);

	}

	private void popMatrix() {
		GL11.glPopMatrix();

	}

	public void enableFlags() {
		for (int flag : flags) {
			GL11.glEnable(flag);
		}
	}

	public void disableFlags() {
		for (int flag : flags) {
			GL11.glDisable(flag);
		}
	}

	public Camera getCamera() {
		return camera;
	}

	public void setCamera(Camera camera) {
		this.camera = camera;
	}

}
