package was.v2;

import java.io.IOException;

public class ServerMainV2 {
    public static void main(String[] args) throws IOException {
        HttpServerV2 server = new HttpServerV2(12345);
        server.start();
    }
}
