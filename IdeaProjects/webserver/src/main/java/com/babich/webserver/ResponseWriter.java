package com.babich.webserver;

import java.io.BufferedWriter;
import java.io.IOException;

public class ResponseWriter {

    BufferedWriter writer;

    public void writeSuccessResponse(String content) throws IOException {
        writer.write("HTTP/1.1 200 OK\n");
        writer.write("\n");
        writer.write(content);
        writer.flush();
    }

    public void writeNotFoundResponse(String content) throws IOException{
        writer.write("HTTP/1.1 400 Bad Request\n");
        writer.write("\n");
        writer.flush();
    }
}
