package was.v1;

import java.io.IOException;

import was.my.v2.MyHttpServerV2;

public class HttpServerMain {
    
    private static final int PORT = 12345;
    public static void main(String[] args) throws IOException {
        // HttpServerV1 server = new HttpServerV1(PORT);
        MyHttpServerV2 server = new MyHttpServerV2(PORT);
        server.start();
    }
}
