package network.packet;

import common.MessageHandler;

public class LoginSuccess extends MessagePacket {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8659143994600995014L;
	private String name;

	public LoginSuccess(String name) {
		setData(name);
	}

	public void setData(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return "LOGIN SUCCESSFUL: " + name;
	}

	@Override
	public void doAction(MessageHandler handler) {
		handler.log(toString());
	}
}
