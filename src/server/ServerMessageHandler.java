package server;

import network.EventQueue;

import common.MessageHandler;

public class ServerMessageHandler extends MessageHandler {

	public ServerMessageHandler(EventQueue output) {
		super(output);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void log(String s) {
		System.out.println(s);
	}

}
