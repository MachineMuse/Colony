package client;

import network.DaemonMaker;

import org.lwjgl.opengl.Display;

import render3d.RenderContext;
import render3d.Scene;
import ui.UserInterface;
import client.input.KeyboardHandler;
import client.input.MouseHandler;

public class ClientApplet implements Runnable {
	public ClientApplet() {
		new Client();
	}

	@Override
	public void run() {

		RenderContext.init();
		Scene scene = new Scene();

		MouseHandler mouse = new MouseHandler();
		mouse.addCamera(scene.getCamera());

		KeyboardHandler keyboard = new KeyboardHandler();
		keyboard.addCamera(scene.getCamera());

		UserInterface ui = new UserInterface();
		Client.setUI(ui);

		ConnectionThread connection = new ConnectionThread(ui);
		DaemonMaker.startDaemon(connection);

		while (!Display.isCloseRequested()) {
			mouse.update();
			keyboard.update();

			RenderContext.clear();
			ui.draw();
			scene.draw();

			Display.update();
			Display.sync(60);
		}

		Display.destroy();
	}
}