package network.packet;

import common.MessageHandler;

public class NullPacket extends MessagePacket {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2404722308647212441L;
	private final String comment;

	public NullPacket(String comment) {
		this.comment = comment;
	}

	public String getComment() {
		return comment;
	}

	@Override
	public String toString() {
		return "NULLPACKET: " + comment;
	}

	@Override
	public void doAction(MessageHandler handler) {
		handler.log(comment);

	}
}
