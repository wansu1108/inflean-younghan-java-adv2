package remind.http;

import java.io.IOException;

import remind.http.v1.HttpServerV1;
import remind.http.v2.HttpServerV2;
import remind.http.v3.HttpServerV3;
import remind.http.v4.HttpServerV4;

public class HttpServerMain {

    private static final int PORT = 12345;
    public static void main(String[] args) throws IOException, InterruptedException {
        HttpServerV4 httpserver = new HttpServerV4(PORT);
        httpserver.start();
    }
}
