package com.babich.webserver;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private String webAppPath;
    private int port;
    private String unknownResource = "HTTP/1.1 404 not found\n";
    private String returnPage = "Bad request";
    private String OK = "HTTP/1.1 200 OK\n";

    public void start() throws IOException {
        try (ServerSocket server = new ServerSocket(port);
             // accept говорит серверу об ожидании клиента, ждем подключения
             Socket socket = server.accept();
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
             BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            while (true) {
                String line = reader.readLine();
                // пустая строка, если request закончился
                if (line.isEmpty()) {
                    break;
                }
                System.out.println("message from client: " + line);

                // GET /index.html HTTP/1.1
                String uri = line.split(" ")[1]; // положили index.html

                String resourcePath = webAppPath+uri;

                // если файла не существует, то на веб-страницу выдает ошибку
                File file = new File(resourcePath);
                if (!file.exists()) {
                    writer.write(unknownResource);
                }

//                try (BufferedReader fileReader = new BufferedReader(new FileReader(resourcePath));

            }

            writer.write("HTTP/1.1 200 OK\n");
            writer.write("\n");
//            writer.write(content);
            writer.flush();
        }
    }

    public void setPort(int port) {
        this.port = port;
    }

    public void setWebAppPath(String webAppPath) {
        this.webAppPath = webAppPath;
    }
}
