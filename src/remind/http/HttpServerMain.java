package remind.http;

import java.io.IOException;

import remind.http.v1.HttpServerV1;
import remind.http.v2.HttpServerV2;

public class HttpServerMain {

    private static final int PORT = 12345;
    public static void main(String[] args) throws IOException, InterruptedException {
        HttpServerV2 httpserver = new HttpServerV2(PORT);
        httpserver.start();
    }
}
