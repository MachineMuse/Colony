package common;

import network.EventQueue;
import network.packet.MessagePacket;

public abstract class MessageHandler implements Logger {
	public EventQueue output;

	public MessageHandler(EventQueue output) {
		this.output = output;
	}

	public void reply(MessagePacket packet) {
		output.add(packet);
	}
}
