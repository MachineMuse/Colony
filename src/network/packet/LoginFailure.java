package network.packet;

import common.MessageHandler;

public class LoginFailure extends MessagePacket {
	private final String name;

	public LoginFailure(String name) {
		this.name = name;
	}

	@Override
	public void doAction(MessageHandler handler) {
		// TODO Auto-generated method stub

	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "LOGIN FAILURE: " + name;
	}

}
