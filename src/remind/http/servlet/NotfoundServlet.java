package remind.http.servlet;

import java.io.IOException;

import remind.http.HttpRequest;
import remind.http.HttpResponse;
import remind.http.HttpServlet;

public class NotfoundServlet implements HttpServlet {

    @Override
    public void service(HttpRequest request, HttpResponse response) throws IOException {
        response.setStatusCode(404);
        response.write("<h1>404 호출하신 페이지를 찾을 수 없습니다.</h1>");
    }
}
