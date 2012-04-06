package render3d;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;

import client.Client;

import common.MathUtil;

public class Camera {
	private Point3f position;
	private float yaw;
	private float pitch;

	public Camera() {
	}

	public Point3f getPosition() {
		return position;
	}

	public void setPosition(Point3f position) {
		this.position = position;
	}

	public void applyTransform() {
		GL11.glRotatef(-pitch, 1, 0, 0);
		GL11.glRotatef(-yaw, 0, 1, 0);
		GL11.glTranslatef(position.x(), position.y(), position.z());
	}

	public void applyPerspective() {
		GLU.gluPerspective(45.0f,
				(float) (Client.width()) / (float) (Client.height()),
				0.01f, 1000.0f);
	}

	public void rotateh(float r) {
		this.yaw += r;
		yaw = MathUtil.modbase(yaw, 360);
	}

	public void rotatev(float r) {
		this.pitch += r;
		pitch = MathUtil.clamp(pitch, -90, 90);
	}

	public void translate(Point3f v) {
		v.rotatePYR(pitch, yaw, 0);
		this.position.add(v);
	}

	public void translate(float x, float y, float z) {
		this.translate(new Point3f(x, y, z));
	}

	public float getRotationh() {
		return yaw;
	}

	public void setRotationh(float rotationh) {
		this.yaw = rotationh;
	}

	public float getRotationv() {
		return pitch;
	}

	public void setRotationv(float rotationv) {
		this.pitch = rotationv;
	}
}
