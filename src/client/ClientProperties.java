package client;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class ClientProperties extends Properties {
	private static final long serialVersionUID = -3673782349062049197L;
	private static final String filename = "prefs.inf";

	public ClientProperties() {
		load();
	}

	public void load() {
		FileInputStream ifs;
		try {
			ifs = new FileInputStream(new File(filename));
			load(ifs);
		} catch (IOException e) {
			setProperty("host", "localhost");
			setProperty("port", "1234");
			setProperty("username", "Anonymous");
			setProperty("password", "pass1234");
			setProperty("displaywidth", "800");
			setProperty("displayheight", "600");
		}
	}

	public void save() {
		try {
			FileOutputStream ofs = new FileOutputStream(new File(filename));
			store(ofs, "Preferences for Colony client");
		} catch (IOException e) {
		}
	}

	public int getIntProperty(String name) {
		return Integer.parseInt(getProperty(name));
	}
}
