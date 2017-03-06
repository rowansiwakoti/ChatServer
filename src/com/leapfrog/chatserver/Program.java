
package com.leapfrog.chatserver;

import com.leapfrog.chatserver.handler.ClientHandler;
import com.leapfrog.chatserver.listener.ClientListener;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Program {

    public static void main(String[] args) {

        //Each port is identified by a number between 1 and 65535.
        int port = 9000;
        try {
            ServerSocket socket = new ServerSocket(port);
            System.out.println("Server is running at " + port);
            ClientHandler handler = new ClientHandler();
            while (true) {
                Socket client = socket.accept();
                System.out.println("Got connection from " + client.getInetAddress().getHostAddress());

                ClientListener listener = new ClientListener(client,handler);
                listener.start();

            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
