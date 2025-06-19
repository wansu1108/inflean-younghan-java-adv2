package remind.http.v4;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HttpServerV4 {
    private final int port;
    private final ExecutorService es = Executors.newFixedThreadPool(20);

    public HttpServerV4(int port) {
        this.port = port;
    }

    public void start() throws IOException {
        ServerSocket serverSocket = new ServerSocket(port);
        while (true) {
            Socket socket = serverSocket.accept();
            HttpRequestHandlerV4 requestHandler = new HttpRequestHandlerV4(socket);
            es.submit(requestHandler);
        }
    }
}
