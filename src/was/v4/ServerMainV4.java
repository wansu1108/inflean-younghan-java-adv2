package was.v4;

import java.io.IOException;

public class ServerMainV4 {
    public static void main(String[] args) throws IOException {
        HttpServerV4 server = new HttpServerV4(12345);
        server.start();
    }
}
