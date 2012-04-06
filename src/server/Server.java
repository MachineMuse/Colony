package server;

import java.util.ArrayList;
import java.util.concurrent.LinkedBlockingQueue;

import network.DaemonMaker;
import network.SocketListener;

public class Server {
	private static SocketListener socketlistener;
	private final ArrayList<ClientHandler> clients;

	private final LinkedBlockingQueue<ClientHandler> connections;

	public Server() {
		connections = new LinkedBlockingQueue<ClientHandler>();
		clients = new ArrayList<ClientHandler>();
		ServerData.loadWorld();
	}

	public int doCommand(String command) {
		if (command.equals("q")) {
			return 0;
		} else if (command.equals("start")) {
			startServer();
		}
		return 1;
	}

	public void update() {
		while (!connections.isEmpty()) {
			System.out.print("Connection received\n");
			ClientHandler client = connections.remove();
			client.start();
			clients.add(client);
		}
		for (ClientHandler client : clients) {
			client.update();
		}
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void startServer() {
		if (socketlistener == null) {
			log("Starting server...");
			socketlistener = new SocketListener(connections);
			log("Giving server its own thread...");
			DaemonMaker.startDaemon(socketlistener);
		} else {
			log("Server already started!");
		}
	}

	public static void log(String s) {
		System.out.print(s + "\n");
	}

}
