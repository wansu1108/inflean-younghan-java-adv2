package was.v6.my;

import java.io.IOException;

public class ServerMainMyV6 {
    
    private static final int PORT = 12345;
    public static void main(String[] args) throws IOException {
        ServletManagerMyV6 servletManager = new ServletManagerMyV6();

        servletManager.add("/", "home");
        servletManager.add("/site1", "site1");
        servletManager.add("/site2", "site2");
        servletManager.add("/search", "search");
        servletManager.add("/favicon.ico", "discard");

        HttpServerMyV6 server = new HttpServerMyV6(PORT, servletManager);
        server.start();
    }
}
