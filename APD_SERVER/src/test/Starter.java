package test;

import java.rmi.RemoteException;

import server.Server;

public class Starter {
	public static void main(String args[]) {
		try {
			new Server();
			System.out.println("SERVER: ARRANCADO!");
		} catch (RemoteException e) {
			System.out.println("SERVER: ERROR! "+e.getMessage());
		}
	}
}
