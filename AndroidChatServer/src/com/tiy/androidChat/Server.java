package com.tiy.androidChat;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Brett on 9/7/16.
 */
public class Server {

	public static void main(String[] args) {
		Server myServer = new Server();
		myServer.startServer();
	}


	public void startServer() {
		try {
			ServerSocket serverListener = new ServerSocket(8005);
			System.out.println("Ready to accept incoming connections!");

			while(true){
				Socket clientSocket = serverListener.accept();
				ConnectionHandler myHandler = new ConnectionHandler(clientSocket);
				Thread myThread = new Thread(myHandler);
				myThread.start();
			}



		}catch(IOException serverException){
			serverException.printStackTrace();
		}

	}

}
