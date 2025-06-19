package remind.http.v3;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HttpServerV3 {
    private final int port;
    private final ExecutorService es = Executors.newFixedThreadPool(20);

    public HttpServerV3(int port) {
        this.port = port;
    }

    public void start() throws IOException {
        ServerSocket serverSocket = new ServerSocket(port);
        while (true) {
            Socket socket = serverSocket.accept();
            HttpRequestHandlerV3 requestHandler = new HttpRequestHandlerV3(socket);
            es.submit(requestHandler);
        }
    }
}
