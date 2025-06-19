package remind.http.v5;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HttpServerV5 {
    private final int port;
    private final ServletManager servletManager;
    private final ExecutorService es = Executors.newFixedThreadPool(20);

    public HttpServerV5(int port, ServletManager servletManager) {
        this.port = port;
        this.servletManager = servletManager;
    }

    public void start() throws IOException {
        ServerSocket serverSocket = new ServerSocket(port);
        while (true) {
            Socket socket = serverSocket.accept();
            HttpRequestHandlerV5 requestHandler = new HttpRequestHandlerV5(socket, servletManager);
            es.submit(requestHandler);
        }
    }
}
