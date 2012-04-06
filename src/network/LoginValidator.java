package network;

import java.util.ArrayList;

public class LoginValidator {
	private final ArrayList<String> usernames;
	private final ArrayList<String> passwords;

	public LoginValidator() {
		usernames = new ArrayList<String>();
		passwords = new ArrayList<String>();
		usernames.add("Anonymous");
		passwords.add("pass123");
		// TODO: Load/save?

	}

	public boolean validate(String name, String password) {
		int index = usernames.indexOf(name);
		if (index != -1) {
			if (password.equals(passwords.get(index))) {
				return true;
			}
		}
		return false;
	}
}
