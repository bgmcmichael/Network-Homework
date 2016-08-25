package com.tiy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by fenji on 8/25/2016.
 */
public class Client {
    public static void main(String[] args) {
        try {
            // connect to the server on the target port

            Socket client = new Socket("10.0.0.127", 8005);

            // once we connect to the server, we also have an input and output stream
            PrintWriter out = new PrintWriter(client.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));

            // send the server an arbitrary message
            out.println("Marvin says hello!");
            // read what the server returns
            String serverResponse = in.readLine();

            // close the connection
            client.close();
        } catch (IOException ioEx){
            ioEx.printStackTrace();
        }

    }
}
