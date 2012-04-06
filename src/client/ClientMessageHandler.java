package client;

import network.EventQueue;

import common.Logger;
import common.MessageHandler;

public class ClientMessageHandler extends MessageHandler {
	private final Logger log;

	public ClientMessageHandler(Logger log, EventQueue output) {
		super(output);
		this.log = log;
	}

	@Override
	public void log(String s) {
		log.log(s);
	}

}
