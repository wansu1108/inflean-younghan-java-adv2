package was.v3;

import static java.nio.charset.StandardCharsets.UTF_8;
import static util.MyLogger.log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URLDecoder;

public class HttpRequestHandlerV3 implements Runnable {

    private final Socket socket;

    public HttpRequestHandlerV3(Socket socket) {
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
                PrintWriter writer = new PrintWriter(socket.getOutputStream(), false, UTF_8);) {
            String requestString = requestToString(reader);
            if (requestString.contains("/favicon.ico")) {
                log("favicon 요청");
                return;
            }

            log("HTTP 응답 생성중...");

            /**
             * GET /site1 HTTP1.1
             * /site1
             * /site2
             * /search?q=hello
             * /
             * 404 Not Found
             */
            if (requestString.startsWith("GET /site1")) {
                site1(writer);
            } else if (requestString.startsWith("GET /site2")) {
                site2(writer);
            } else if (requestString.startsWith("GET /search")) {
                search(requestString, writer);
            } else if (requestString.startsWith("GET / ")) { // '/' 다음에 space 필수!
                home(writer);
            } else {
                // 404 Not Found
                notFound(writer);
            }

            log("HTTP 응답 전달 완료");
        }
    }
    
    private void home(PrintWriter writer) {
        // 원칙적으로 Content-Length를 계산해서 전달해야 하지만, 예제를 단순하게 설명하기 위해 생략하겠다.
        writer.println("HTTP/1.1 200 OK");
        writer.println("Content-Type: text/html; charset=UTF-8");
        writer.println(); // CTRF
        writer.println("<h1>HOME</h1>");
        writer.println("<ul>");
        writer.println("<li><a href='/site1'>site1</a></li>");
        writer.println("<li><a href='/site2'>site2</a></li>");
        writer.println("<li><a href='/search?q=hello'>검색</a></li>");
        writer.println("</ul>");
        writer.flush();
    }
    
    private void notFound(PrintWriter writer) {
        writer.println("HTTP/1.1 404 Not Found");
        writer.println("Content-Type: text/html; charset=UTF-8");
        writer.println(); // CTRF
        writer.println("<h1>404 호출하신 페이지를 찾을 수 없습니다.</h1>");
        writer.flush();
    }
    
    private void site1(PrintWriter writer) {
        writer.println("HTTP/1.1 200 OK");
        writer.println("Content-Type: text/html; charset=UTF-8");
        writer.println(); // CTRF
        writer.println("<h1>Site1</h1>");
        writer.flush();
    }
    
    private void site2(PrintWriter writer) {
        writer.println("HTTP/1.1 200 OK");
        writer.println("Content-Type: text/html; charset=UTF-8");
        writer.println(); // CTRF
        writer.println("<h1>Site2</h1>");
        writer.flush();
    }
    
    private void search(String requestString, PrintWriter writer) {
        int startIndex = requestString.indexOf("q=");
        int endIndex = requestString.indexOf(" ", startIndex + 2);
        String query = requestString.substring(startIndex + 2, endIndex);
        String decode = URLDecoder.decode(query, UTF_8);

        writer.println("HTTP/1.1 200 OK");
        writer.println("Content-Type: text/html; charset=UTF-8");
        writer.println(); // CTRF
        writer.println("<ul>");
        writer.println("<li>query: " + query + "</li>");
        writer.println("<li>decode: " + decode + "</li>");
        writer.println("</ul>");
        writer.flush();
    }

    private String requestToString(BufferedReader reader) throws IOException {
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            if (line.isEmpty()) {
                break; // 우선 GET만 지원 -> body는 읽지 않는다. CTRF
            }
            sb.append(line).append("\r\n");
        }

        log("HTTP 요청 정보 출력");
        System.out.println(sb.toString());

        return sb.toString();
    }
}
