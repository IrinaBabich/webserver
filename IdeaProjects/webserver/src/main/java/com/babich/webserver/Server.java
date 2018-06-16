package com.babich.webserver;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(3001);
             Socket socket = server.accept();
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
             BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            String message = reader.readLine();
            System.out.println("message from client: " + message);

            writer.write("echo: " + message);
            writer.newLine();
            writer.flush();
        }
    }
}
