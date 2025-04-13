package was.v3;

import java.io.IOException;

public class ServerMainV3 {
    public static void main(String[] args) throws IOException {
        HttpServerV3 server = new HttpServerV3(12345);
        server.start();
    }
}
