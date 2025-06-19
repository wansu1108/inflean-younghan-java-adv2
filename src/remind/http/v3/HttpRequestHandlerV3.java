package remind.http.v3;

import static remind.common.MyLogger.log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import remind.http.SocketCloseUtil;

import static java.nio.charset.StandardCharsets.*;

public class HttpRequestHandlerV3 implements Runnable {

    private final Socket socket;
    private final BufferedReader input;
    private final PrintWriter output;
    private boolean closed = false;

    public HttpRequestHandlerV3(Socket socket) throws IOException {
        this.socket = socket;
        input = new BufferedReader(new InputStreamReader(socket.getInputStream(), UTF_8));
        output = new PrintWriter(socket.getOutputStream(), false, UTF_8);
    }

    @Override
    public void run() {
        try {
            String requestString = requestToString(input);
            // 파비콘 요청 처리 X
            if (requestString.contains("/favicon.ico")) {
                log("파비콘 요청");
                return;
            }
            log("HTTP 요청 정보 출력");
            System.out.println(requestString);

            log("HTTP 응답 생성중...");
            if (requestString.contains("/site1")) {
                goToSite1();
            } else if (requestString.contains("/site2")) {
                goToSite2();
            } else if (requestString.contains("/search")) {
                goToSearch();
            } else if (requestString.contains("/")) {
                goToHome();
            } else {
                goTo404Page();
            }
            log("HTTP 응답 전달 완료");
        } catch (Exception e) {
            log(e);
            close();
        }
    }

    private void goToHome() {
        StringBuilder body = new StringBuilder();
        body.append("<h1>Home>/h1>");
        body.append("<ul>");
        body.append("<li><a href='/site1'>site1</a></li>");
        body.append("<li><a href='/site2'>site1</a></li>");
        body.append("<li><a href='/search?p=hello'>search</a></li>");
        body.append("</ul>");

        write(body);
    }

    private void goToSite1() {
        StringBuilder body = new StringBuilder();
        body.append("<h1>site1</h1>");
        write(body);
    }

    private void goToSite2() {
        StringBuilder body = new StringBuilder();
        body.append("<h1>site2</h1>");
        write(body);
    }

    private void goToSearch() {
        StringBuilder body = new StringBuilder();
        body.append("<h1>site2</h1>");
        write(body);
        
    }

    private void goTo404Page() {
        StringBuilder body = new StringBuilder();
        body.append("<h1>404 페이지를 찾을 수 없습니다.</h1>");
        write(body);
    }

    private void write(StringBuilder body) {
        int length = body.toString().getBytes(UTF_8).length;

        StringBuilder sb = new StringBuilder();
        sb.append("HTTP1.1 200 OK").append("\r\n");
        sb.append("Conten-Type: text/html;").append("\r\n");
        sb.append("Conteng-Length: " + length + ";").append("\r\n");
        sb.append("\r\n"); // CRLF
        sb.append(body);

        output.println(sb);
        output.flush();
        close();
    }

    private synchronized void close() {
        if(closed) {
            return;
        }
        
        SocketCloseUtil.closeAll(socket, input, output);
        closed = true;
    }

    private String requestToString(BufferedReader input) throws IOException {
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = input.readLine()) != null) {
            if (line.isEmpty()) {
                break;
            }

            sb.append(line).append("\n");
        }

        return sb.toString();
    }
}
