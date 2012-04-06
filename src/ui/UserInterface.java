package ui;

import org.lwjgl.opengl.GL11;

import client.Client;

import common.Logger;

public class UserInterface implements Logger {
	TextBox tb;
	int[] flags = { GL11.GL_TEXTURE_2D };

	int width;
	int height;

	public UserInterface() {
		tb = new TextBox();
		width = Client.properties.getIntProperty("displaywidth");
		height = Client.properties.getIntProperty("displayheight");
	}

	public void draw() {
		enableFlags();
		pushMatrix();

		tb.draw();

		popMatrix();
		disableFlags();

	}

	private void pushMatrix() {
		// GL11.glClear(GL11.GL_DEPTH_BUFFER_BIT);
		GL11.glPushMatrix();
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(0, width, height, 0, 1, -1);
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

	public void log(String str) {
		tb.addLine(str);
	}

	public int width() {
		return width;
	}

	public int height() {
		return height;
	}
}
