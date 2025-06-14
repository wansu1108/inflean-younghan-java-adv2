package remind.http;

import java.io.IOException;

import remind.http.v1.HttpServerV1;

public class HttpServerMain {

    private static final int PORT = 12345;
    public static void main(String[] args) throws IOException, InterruptedException {
        HttpServerV1 httpserver = new HttpServerV1(PORT);
        httpserver.start();
    }
}
