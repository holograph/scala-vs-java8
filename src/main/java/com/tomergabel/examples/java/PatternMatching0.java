package com.tomergabel.examples.java;

import java.util.Collections;
import java.util.Map;

import static java.util.Collections.emptyMap;

public class PatternMatching0 {

    private static class HttpRequest {
        private String method;
        private String path;
        private Map<String, String> headers;

        public String getMethod() { return method; }
        public String getPath() { return path; }
        public Map<String, String> getHeaders() { return headers; }

        public HttpRequest(String method, String path, Map<String, String> headers) {
            this.method = method;
            this.path = path;
            this.headers = headers;
        }
    }

    static class HttpResponse {
        private int status;
        private byte[] body;

        public int getStatus() { return status; }
        public byte[] getBody() { return body; }

        public HttpResponse(int status, byte[] body) {
            this.status = status;
            this.body = body;
        }
    }

    static private HttpResponse execute(HttpRequest req) {
        String index = "<html><body>Hi!</body></html>";
        String json = "{\"response\":\"ok\"}";
        byte[] empty = {};

        switch (req.getMethod()) {
            case "GET":
                if (req.getPath().equals("/"))
                    return new HttpResponse(200, index.getBytes());
                else
                    return new HttpResponse(404, empty);

            case "POST":
                if ("application/json".equals(req.getHeaders().get("Content-type")))
                    return new HttpResponse(200, json.getBytes());
                else
                    return new HttpResponse(415, empty);

            default:
                return new HttpResponse(405, empty);
        }
    }

    static private String render(HttpResponse resp) {
        return "[" + resp.getStatus() + "] " + new String(resp.getBody());
    }

    static public void main(String[] args) {
        Map<String, String> contentTypeHeader = Collections.singletonMap("Content-type", "application/json");

        System.out.println("Index: " + render(execute(new HttpRequest("GET", "/", emptyMap()))));
        System.out.println("Invalid GET: " + render(execute(new HttpRequest("GET", "/test", emptyMap()))));
        System.out.println("Valid POST: " + render(execute(new HttpRequest("POST", "/", contentTypeHeader))));
        System.out.println("Invalid POST: " + render(execute(new HttpRequest("POST", "/", emptyMap()))));
        System.out.println("PUT: " + render(execute(new HttpRequest("PUT", "/", emptyMap()))));
    }
}
