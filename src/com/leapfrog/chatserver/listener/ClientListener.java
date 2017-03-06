package com.leapfrog.chatserver.listener;

import com.leapfrog.chatserver.handler.ClientHandler;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class ClientListener extends Thread {

    private Socket client;
    private Client c;
    private ClientHandler handler;

    public ClientListener(Socket client, ClientHandler handler) {
        this.client = client;
        this.handler = handler;
    }

    @Override
    public void run() {

        try {
            PrintStream ps = new PrintStream(client.getOutputStream());
            ps.println("Hello Form Leapfrog Technology :)");

            ps.println("Enter your User Name");
            BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
            String name = reader.readLine();
            System.out.println("Hello " + name + " !");

            c = new Client(name, client);
            handler.addClient(c);
            handler.broadcastMessage(c.getUserName() + " has joined to this chat room.");

            while (!isInterrupted()) {
                String msg = reader.readLine();

                String[] tokens = msg.split(";;");

                if (tokens[0].equalsIgnoreCase("pm")) {
                    if (tokens.length > 2) {

                        handler.privateMessage(tokens[1], " (PM) from " + c.getUserName() + " > " + tokens[2]);

                    }
                } else {
                    handler.broadcastMessage(c.getUserName() + " says >" + msg);
                }
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

}
