package client;

import java.io.IOException;
import java.net.Socket;

import network.DaemonMaker;
import network.EventQueue;
import network.InputConnection;
import network.OutputConnection;
import network.packet.LoginAttempt;
import network.packet.MessagePacket;
import network.packet.NullPacket;
import ui.UserInterface;

import common.MessageHandler;

public class ConnectionThread implements Runnable {
	private InputConnection inc;
	private OutputConnection outc;
	private final EventQueue in;
	private final EventQueue out;
	private final MessageHandler handler;

	public ConnectionThread(UserInterface ui) {
		in = new EventQueue();
		out = new EventQueue();
		this.handler = new ClientMessageHandler(ui, out);
	}

	@Override
	public void run() {

		try {
			Socket socket = new Socket(Client.properties.getProperty("host"),
					Client.properties.getIntProperty("port"));

			inc = new InputConnection(socket, in, handler);
			outc = new OutputConnection(socket, out, handler);

			DaemonMaker.startDaemon(outc);
			DaemonMaker.startDaemon(inc);

			out.add(new LoginAttempt(Client.properties.getProperty("username"),
					Client.properties.getProperty("password")));
			while (socket.isConnected()) {
				MessagePacket event = in.take();
				event.doAction(handler);
			}
		} catch (IOException e) {
			Client.log("Whoops! It didn't work!\n");
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (Exception e) {
			Client.log("Unable to connect: " + e.getMessage());
		}
	}

	public void sendMessage(String message) {
		out.add(new NullPacket(message));
	}
}
