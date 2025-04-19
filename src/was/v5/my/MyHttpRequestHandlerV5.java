package was.v5.my;

import static java.nio.charset.StandardCharsets.UTF_8;
import static util.MyLogger.log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

import was.httpserver.HttpRequest;
import was.httpserver.HttpResponse;
import was.v5.my.path.HttpHome;
import was.v5.my.path.HttpNotFound;
import was.v5.my.path.HttpSerach;

public class MyHttpRequestHandlerV5 implements Runnable {

    private final Socket socket;
    private final Map<String, HttpPath> pathMap = new HashMap<>();
    private final HttpNotFound httpNotFound = new HttpNotFound();

    public MyHttpRequestHandlerV5(Socket socket) {
        this.socket = socket;
        pathMap.put("/", new HttpHome());
        pathMap.put("/search", new HttpSerach());
    }

    @Override
    public void run() {
        try {
            process();
        } catch (Exception e) {
            log(e);
        }
    }

    private void process() throws IOException {
        try (
                socket;
                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), UTF_8));
                PrintWriter writer = new PrintWriter(socket.getOutputStream(), false, UTF_8);
            ) {

            HttpRequest request = new HttpRequest(reader);
            HttpResponse response = new HttpResponse(writer);

            if (request.getPath().equals("/favicon.ico")) {
                log("favicon 요청");
                return;
            }

            System.out.println(request);
            
            log("HTTP 응답 생성중...");
            System.out.println(request.getPath());
            HttpPath foundPath = pathMap.getOrDefault(request.getPath(), httpNotFound);
            foundPath.path(request, response);
            response.flush();
            log("HTTP 응답 전달 완료");
        }
    }
}
