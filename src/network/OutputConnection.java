package network;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;

import network.packet.MessagePacket;

import common.Logger;

public class OutputConnection implements Runnable {
	Socket sock;
	PrintWriter out;
	EventQueue events;
	Logger log;

	public OutputConnection(Socket sock, EventQueue events, Logger log) {
		this.sock = sock;
		this.events = events;
		this.log = log;
	}

	@Override
	public void run() {
		try {
			ObjectOutputStream out = new ObjectOutputStream(
					sock.getOutputStream());
			while (sock.isConnected()) {
				MessagePacket message = events.take();
				out.writeObject(message);
				out.flush();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		close();
		out.close();
	}

	public void close() {
		try {
			out.close();
			sock.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
