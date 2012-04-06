package server;

import java.net.Socket;

import network.DaemonMaker;
import network.EventQueue;
import network.InputConnection;
import network.OutputConnection;
import network.SocketListener;
import network.packet.LoginAttempt;
import network.packet.LoginSuccess;
import network.packet.MessagePacket;

import common.Logger;

public class ClientHandler implements Logger {
	private final EventQueue inputEvents;
	private final EventQueue outputEvents;
	private String name;
	private final SocketListener thread;
	private final Socket sock;
	private final ServerMessageHandler handler;

	public ClientHandler(Socket sock, SocketListener thread) {
		inputEvents = new EventQueue();
		outputEvents = new EventQueue();
		handler = new ServerMessageHandler(outputEvents);

		this.thread = thread;
		this.sock = sock;
	}

	public void start() {
		Runnable outgoing = new OutputConnection(sock, outputEvents, this);
		Runnable incoming = new InputConnection(sock, inputEvents, this);
		DaemonMaker.startDaemon(outgoing);
		DaemonMaker.startDaemon(incoming);
	}

	public void update() {
		while (!inputEvents.isEmpty()) {
			MessagePacket event = inputEvents.remove();
			event.doAction(handler);
		}
	}

	public void login(LoginAttempt creds) {
		if (thread.alreadyConnected(creds.getName())) {
			System.out.print("Problem: " + name + " already connected\n");
		} else {
			this.name = creds.getName();
			System.out.print("Connected: " + name + "\n");
			outputEvents.add(new LoginSuccess(name));
		}

	}

	public boolean checkForDeath() {
		if (sock.isClosed()) {
			return true;
		}
		return false;
	}

	public void kill() {
		// TODO
	}

	@Override
	public void log(String s) {
		System.out.print(s + "\n");

	}
}
