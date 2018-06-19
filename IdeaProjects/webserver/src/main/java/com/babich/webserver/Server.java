package com.babich.webserver;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private String webAppPath;
    private int port;
    private String unknownResource = "HTTP/1.1 404 Not found\n";
    private String badRequest = "HTTP/1.1 400 Bad Request\n";
    private String OK = "HTTP/1.1 200 OK\n";

    public void start() throws IOException {
        try (ServerSocket server = new ServerSocket(port);
             // когда клиент пытаеться присоединиться, метод аксепт возвращает сокет -> соединение с клиентом
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
                String resourcePath = webAppPath + uri;

                // если файла не существует, то на веб-страницу выдает ошибку
                File file = new File(resourcePath);
                if (!file.exists()) {
                    writer.write(unknownResource);
                } else {
                    writer.write(OK);

                    try (BufferedReader fileReader = new BufferedReader(new FileReader(resourcePath))) {
                        while ((line = fileReader.readLine()) != null) {
                            writer.write(line);
                        }
                        writer.newLine();
                    }
                }
            }

            writer.write("HTTP/1.1 200 OK\n");
            writer.newLine();
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
