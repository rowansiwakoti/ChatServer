/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.leapfrog.chatserver.listener;

import java.net.Socket;

/**
 *
 * @author dell
 */
public class Client {
    private String userName;
    private Socket client;

    public Client() {
    }

    public Client(String userName, Socket client) {
        this.userName = userName;
        this.client = client;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Socket getClient() {
        return client;
    }

    public void setClient(Socket client) {
        this.client = client;
    }
   
}
