package network;

public class DaemonMaker {
	public static void startDaemon(Runnable task) {
		Thread thread = new Thread(task);
		thread.setDaemon(true);
		thread.start();
	}
}
