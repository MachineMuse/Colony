package server;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ServerApplication implements Runnable {

	private static boolean done;

	@Override
	public void run() {
		BufferedReader cin = new BufferedReader(
				new InputStreamReader(System.in));
		Server server = new Server();

		log("Colony server application, by Claire Semple.\n");
		done = false;
		server.startServer();

		while (!done) {
			try {
				if (cin.ready()) {
					String command = cin.readLine();
					done = (server.doCommand(command) == 0);
					System.out.print("> ");
				}

				server.update();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void log(String str) {
		System.out.print(str);
	}
}
