package was.v5;

import java.io.IOException;

public class ServerMainV5 {
    public static void main(String[] args) throws IOException {
        HttpServerV5 server = new HttpServerV5(12345);
        server.start();
    }
}
