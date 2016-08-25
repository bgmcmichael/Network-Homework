package com.tiy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by fenji on 8/25/2016.
 */
public class Server implements Runnable{
    Socket client = null;


    public  Server(){

    }

    public Server(Socket client) {
        this.client = client;
    }

    public void startServer() {
        System.out.println("starting server...");
        try {
            ServerSocket server = new ServerSocket(8005);
            System.out.println("listener is ready to connect.");
            while (true) {
                Socket client = server.accept();
                Server newServer = new Server(client);
                Thread serverThread = new Thread(newServer);
                serverThread.start();
            }


        } catch (IOException ioEx) {
            ioEx.printStackTrace();
        }
    }

    public void handleClientInput(){
        try {

            // display information about who just connected to our server

            System.out.println("Incoming connection from " + client.getInetAddress().getHostAddress());

            // this is how we read from the client
            BufferedReader clientInput = new BufferedReader(new InputStreamReader(client.getInputStream()));
            // this is how we write back to the client
            PrintWriter outputToClient = new PrintWriter(client.getOutputStream(), true);
            // read from the input until the client disconnects
            String inputLine;
            while ((inputLine = clientInput.readLine()) != null) {
                System.out.println("Received message: " + inputLine + " from " + client.toString());
                outputToClient.println("Message received loud and clear");

            }
        }catch (IOException ioEx){
            ioEx.printStackTrace();
        }
    }

    @Override
    public void run() {
        handleClientInput();
    }
}
