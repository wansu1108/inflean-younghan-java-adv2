package was.v4;

import static java.nio.charset.StandardCharsets.UTF_8;
import static util.MyLogger.log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import was.httpserver.HttpRequest;
import was.httpserver.HttpResponse;

public class HttpRequestHandlerV4 implements Runnable {

    private final Socket socket;

    public HttpRequestHandlerV4(Socket socket) {
        this.socket = socket;
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
            if (request.getPath().equals("/site1")) {
                site1(response);
            } else if (request.getPath().equals("/site2")) {
                site2(response);
            } else if (request.getPath().equals("/search")) {
                search(request, response);
            } else if (request.getPath().equals("/")) { // '/' 다음에 space 필수!
                home(response);
            } else {
                // 404 Not Found
                notFound(response);
            }
            response.flush();
            log("HTTP 응답 전달 완료");
        }
    }
    
    private void home(HttpResponse response) {
            // 원칙적으로 Content-Length를 계산해서 전달해야 하지만, 예제를 단순하게 설명하기 위해 생략하겠다.
            response.writeBody("<h1>HOME</h1>");
            response.writeBody("<ul>");
            response.writeBody("<li><a href='/site1'>site1</a></li>");
            response.writeBody("<li><a href='/site2'>site2</a></li>");
            response.writeBody("<li><a href='/search?q=hello'>검색</a></li>");
            response.writeBody("</ul>");
    }
    
    
    private void site1(HttpResponse response) {
        response.writeBody("<h1>Site1</h1>");
    }
    
    private void site2(HttpResponse response) {
        response.writeBody("<h1>Site2</h1>");
    }
    
    private void search(HttpRequest request, HttpResponse response) {
        String query = request.getParameter("q");

        response.writeBody("<ul>");
        response.writeBody("<li>query: " + query + "</li>");
        response.writeBody("</ul>");
    }
    
    private void notFound(HttpResponse response) {
        response.setStatusCode(404);
        response.writeBody("<h1>404 호출하신 페이지를 찾을 수 없습니다.</h1>");
    }
}
