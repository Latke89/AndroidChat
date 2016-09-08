package com.tiy.androidchat;

/**
 * Created by Brett on 9/7/16.
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    String chatString = null;

    public Client(String chatString) {
        this.chatString = chatString;
    }

    public static void main(String[] args) {
//        Client myClient = new Client();
    }


        public void startClient() {

        Scanner inputScanner = new Scanner(System.in);
        try {
            // connect to the server on the target port
            Socket clientSocket = new Socket("10.0.0.134", 8005);

            // once we connect to the server, we also have an input and output stream
            PrintWriter outputToServer = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader inputFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            System.out.println("Enter your message to send: ");
            String userMessage = chatString;
            outputToServer.println(userMessage);
            inputFromServer.read();

            // close the connection
            clientSocket.close();


        } catch (IOException clientException) {
            clientException.printStackTrace();
        }

    }
}

