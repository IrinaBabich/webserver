package com.babich.webserver;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    String webappPath;

    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(3000);
             Socket socket = server.accept();
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
             BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            while (true) {
                String line = reader.readLine();
                // GET /index.html HTTP/1.1
                String uri = split(" ")[1]; // положили index.html
                new FileReader(webappPath + uri);



                if (line.isEmpty()) {
                    break;
                }
                System.out.println("message from client: " + line);
            }

            writer.write("HTTP/1.1 200 OK\n");
            writer.write("\n");
            writer.write("Hello world");
            writer.flush();
        }
    }
}
