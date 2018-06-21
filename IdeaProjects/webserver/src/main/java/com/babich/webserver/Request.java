package com.babich.webserver;

import java.util.Map;

public class Request {
    String url;
    HttpMethod httpMethod;
    Map<String, String> headers;

    public HttpMethod getHttpMethod() {
        return httpMethod;
    }

    public void setHttpMethod(HttpMethod httpMethod) {
        this.httpMethod = httpMethod;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setMap(Map<String, String> headers) {
        this.headers = headers;
    }
}


