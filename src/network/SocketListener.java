package network;

import java.net.ServerSocket;
import java.util.concurrent.LinkedBlockingQueue;

import server.ClientHandler;
import server.ServerApplication;

public class SocketListener implements Runnable {
	private static final int SOCKET = 1234;
	private final LinkedBlockingQueue<ClientHandler> clients;

	public SocketListener(LinkedBlockingQueue<ClientHandler> clients) {
		this.clients = clients;
	}

	@Override
	public void run() {
		try {
			ServerApplication.log("Opening port " + SOCKET + ".\n");
			ServerSocket serversock = new ServerSocket(SOCKET);
			ServerApplication.log("Port opened! Listening for connections.\n");
			boolean done = false;
			while (done == false) {
				ClientHandler ch = new ClientHandler(serversock.accept(), this);
				clients.put(ch);
			}
			serversock.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean alreadyConnected(String name) {
		// TODO
		return false;
	}
}
