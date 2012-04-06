package network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.SocketException;

import network.packet.MessagePacket;

import common.Logger;

public class InputConnection implements Runnable {
	private final Socket sock;
	private final EventQueue events;
	private final Logger log;

	public InputConnection(Socket sock, EventQueue events, Logger log) {
		this.sock = sock;
		this.events = events;
		this.log = log;
	}

	@Override
	public void run() {
		try {

			ObjectInputStream in = new ObjectInputStream(
					sock.getInputStream());
			while (sock.isConnected()) {
				MessagePacket message = (MessagePacket) in.readObject();
				events.put(message);
			}
		} catch (SocketException e) {
			log.log("Socket exception: " + e.getMessage());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		close();
	}

	public void close() {
		try {
			sock.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
