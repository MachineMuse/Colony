package test;

import server.ServerApplication;
import client.ClientApplet;

public class ClientServerTester {

	public static void main(String[] args) {
		ServerApplication servapp = new ServerApplication();
		Thread servthread = new Thread(servapp);
		servthread.start();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		ClientApplet capp = new ClientApplet();
		Thread cappthread = new Thread(capp);
		cappthread.start();
	}

	public static void smile() {

	}
}
