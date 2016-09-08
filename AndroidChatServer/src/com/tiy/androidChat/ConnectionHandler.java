package com.tiy.androidChat;

import com.sun.deploy.util.SessionState;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ConnectionHandler implements Runnable {

	Socket connection;

	public ConnectionHandler(Socket incomingConnection) {
		this.connection = incomingConnection;
	}

	public void run() {
		handleIncomingConnections(connection);
	}


	private void handleIncomingConnections(Socket inputSocket) {
		try {
			System.out.println("Incoming connection from " + inputSocket.getInetAddress().getHostAddress());

			BufferedReader inputFromClient = new BufferedReader(new InputStreamReader(inputSocket.getInputStream()));
			PrintWriter outputToClient = new PrintWriter(inputSocket.getOutputStream(), true);

//			String firstInput;
//			String clientName;
//			firstInput = inputFromClient.readLine();
//			String[] nameArray = firstInput.split("=");
//			clientName = nameArray[1];
//			outputToClient.println("Thank you, " + clientName);



			String inputLine;
			while ((inputLine = inputFromClient.readLine()) != null) {
				System.out.println(inputLine);
//				System.out.println("Received message: " + inputLine + " from " + inputSocket.toString());
				outputToClient.println("Roger roger");
			}
		}catch (IOException exception){
			exception.printStackTrace();
		}
	}
}

