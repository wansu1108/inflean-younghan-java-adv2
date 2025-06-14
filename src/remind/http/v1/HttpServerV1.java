package remind.http.v1;

import static remind.common.MyLogger.log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import static java.nio.charset.StandardCharsets.*;

public class HttpServerV1 {
    private final int port;

    public HttpServerV1(int port) {
        this.port = port;
    }

    public void start() throws IOException {
        ServerSocket serverSocket = new ServerSocket(port);
        while (true) {
            Socket socket = serverSocket.accept();
            process(socket);
        }
    }

    private void process(Socket socket) {
        try (
                socket;
                BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream(), UTF_8));) {
                PrintWriter output = new PrintWriter(socket.getOutputStream(), false, UTF_8);
            {
                String requestString = requestToString(input);
                // 파비콘 요청 처리 X
                if (requestString.contains("/favicon.ico")) {
                    log("파비콘 요청");
                    return;
                }
                log("HTTP 요청 정보 출력");
                System.out.println(requestString);

                log("HTTP 응답 생성중...");
                Thread.sleep(1000);
                responseToClient(output);
                log("HTTP 응답 전달 완료");
            }
        } catch (Exception e) {
            log(e);
        }
    }

    private String requestToString(BufferedReader input) throws IOException {
        StringBuilder sb = new StringBuilder();
        String line;
        while((line = input.readLine()) != null) {
            if(line.isEmpty()) {
                break;
            }

            sb.append(line).append("\n");
        }

        return sb.toString();
    }

    private void responseToClient(PrintWriter output) {
        String body = "<h1>Hello world</h1>";
        int length = body.getBytes(UTF_8).length;

        StringBuilder sb = new StringBuilder();
        sb.append("HTTP1.1 200 OK").append("\r\n");
        sb.append("Conten-Type: text/html;").append("\r\n");
        sb.append("Conteng-Length: " + length + ";").append("\r\n");
        sb.append("\r\n"); //CRLF
        sb.append(body);

        output.println(sb);
        output.flush();
    }
}
