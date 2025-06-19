package remind.http.v4;

import static remind.common.MyLogger.log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URLDecoder;

import remind.http.HttpRequest;
import remind.http.HttpResponse;
import remind.http.SocketCloseUtil;

import static java.nio.charset.StandardCharsets.*;

public class HttpRequestHandlerV4 implements Runnable {

    private final Socket socket;
    private final BufferedReader input;
    private final PrintWriter output;

    private final HttpRequest request;
    private final HttpResponse response;

    private boolean closed = false;

    public HttpRequestHandlerV4(Socket socket) throws IOException {
        this.socket = socket;
        input = new BufferedReader(new InputStreamReader(socket.getInputStream(), UTF_8));
        output = new PrintWriter(socket.getOutputStream(), false, UTF_8);

        this.request = new HttpRequest(input);
        this.response = new HttpResponse(output);
    }

    @Override
    public void run() {
        try {
            // 파비콘 요청 처리 X
            if (request.getPath().contains("/favicon.ico")) {
                log("파비콘 요청");
                return;
            }
            log("[" + request.getMethod() + "][" + request.getPath() + "]");

            log("HTTP 응답 생성중...");
            if (request.getPath().equals("/site1")) {
                goToSite1();
            } else if (request.getPath().equals("/site2")) {
                goToSite2();
            } else if (request.getPath().equals("/search")) {
                goToSearch();
            } else if (request.getPath().equals("/")) {
                goToHome();
            } else {
                goTo404Page();
            }
            log("HTTP 응답 전달 완료");
        } catch (Exception e) {
            log(e);
        } finally {
            close();
        }
    }

    private void goToHome() {
        response.write("<h1>Home</h1>");
        response.write("<ul>");
        response.write("<li><a href='/site1'>site1</a></li>");
        response.write("<li><a href='/site2'>site1</a></li>");
        response.write("<li><a href='/search?p=hello'>search</a></li>");
        response.write("</ul>");
        response.flush();
    }

    private void goToSite1() {
        response.write("<h1>site1</h1>");
        response.flush();
    }

    private void goToSite2() {
        response.write("<h1>site2</h1>");
        response.flush();
    }

    private void goToSearch() {
        String queryParameter = request.getQueryParameters().get("p");
        String encoeded = URLDecoder.decode(queryParameter, UTF_8);

        response.write("<h3>Search Page</h3>");
        response.write("<ui>");
        response.write("<li>query: " + queryParameter + "</li>");
        response.write("<li>decode: " + encoeded + "</li>");
        response.write("</ui>");
        response.flush();
    }

    private void goTo404Page() {
        response.write("<h1>404 페이지를 찾을 수 없습니다.</h1>");
        response.flush();
    }

    private synchronized void close() {
        if (closed) {
            return;
        }

        SocketCloseUtil.closeAll(socket, input, output);
        closed = true;
    }
}
