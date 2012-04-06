package network.packet;

import java.io.Serializable;

import common.MessageHandler;

public abstract class MessagePacket implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 869195196744542317L;

	public abstract void doAction(MessageHandler handler);

	@Override
	public abstract String toString();
}
