package com.a1arick.spbsu.homework4.try2.server.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientSocketWrapper {
    private Socket socket;
    private String clientName;
    private int  clientId;
    private BufferedReader reader;
    private PrintWriter writer;


    public ClientSocketWrapper(Socket socket) throws IOException {
        this.socket = socket;
        this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.writer = new PrintWriter(socket.getOutputStream());
    }

    public void init(int clientId) {
        this.clientId = clientId;
        this.clientName = clientId + "[" + socket + "]";
    }

    public String getClientName() {
        return clientName;
    }


}
