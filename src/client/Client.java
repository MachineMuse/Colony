package client;

import ui.UserInterface;

public class Client {
	public static ClientProperties properties;
	private static UserInterface ui;

	public static final int width() {
		return properties.getIntProperty("displaywidth");
	}

	public static final int height() {
		return properties.getIntProperty("displayheight");
	}

	public Client() {
		properties = new ClientProperties();
	}

	public static void setUI(UserInterface ui) {
		Client.ui = ui;
	}

	public static void log(String s) {
		if (ui == null) {
			System.out.print(s);
		} else {
			ui.log(s);
		}
	}

}
