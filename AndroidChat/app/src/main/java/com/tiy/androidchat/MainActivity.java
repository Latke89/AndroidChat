package com.tiy.androidchat;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemLongClickListener{
    ArrayAdapter<String> items;

    ListView list;
    EditText text;
    Button sendButton;
    Client myClient = null;
    String chatString = null;
    String response = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = (ListView) findViewById(R.id.listView);
        text = (EditText) findViewById(R.id.editText);
        sendButton = (Button) findViewById(R.id.button);

        items = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        list.setAdapter(items);

        sendButton.setOnClickListener(this);
        list.setOnItemLongClickListener(this);




    }

    @Override
    public void onClick(View v) {
        String item = text.getText().toString();
        items.add(item);
        text.setText("");
        startClient(item);
        items.add(response);
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        String item = items.getItem(position);
        items.remove(item);
        return true;
    }

    public void startClient(String chatString) {

//        Scanner inputScanner = new Scanner(System.in);
        try {
            // connect to the server on the target port
            Socket clientSocket = new Socket("10.0.0.192", 8005);

            // once we connect to the server, we also have an input and output stream
            PrintWriter outputToServer = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader inputFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

//            System.out.println("Enter your message to send: ");
            String userMessage = chatString;
            outputToServer.println(userMessage);
            response = ("Server says: " + inputFromServer.readLine());
            System.out.println("server says:" + response);

            // close the connection
            clientSocket.close();


        } catch (IOException clientException) {
            clientException.printStackTrace();
        }

    }

}
