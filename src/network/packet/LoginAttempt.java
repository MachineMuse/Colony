package network.packet;

import network.LoginValidator;

import common.MessageHandler;

public class LoginAttempt extends MessagePacket {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4806781163858067211L;
	private String name;
	private String password;

	public LoginAttempt(String name, String password) {
		setData(name, password);
	}

	public void setData(String name, String password) {
		this.name = name;
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public String getPass() {
		// TODO: implement md5 somewhere along the way here
		return password;
	}

	@Override
	public String toString() {
		return "LOGIN ATTEMPT: " + name + " // " + password;
	}

	@Override
	public void doAction(MessageHandler handler) {
		if (new LoginValidator().validate(name, password)) {
			handler.log("Login successful for " + name);
			handler.reply(new LoginSuccess(name));
			// TODO: do more login stuff
		} else {
			handler.log("Failed login from " + name);
			handler.reply(new LoginFailure(name));
		}
	}
}
