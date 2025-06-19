package remind.http.servlet;

import java.io.IOException;

import remind.http.HttpRequest;
import remind.http.HttpResponse;
import remind.http.HttpServlet;

public class InternerServerErrorServlet implements HttpServlet {

    @Override
    public void service(HttpRequest request, HttpResponse response) throws IOException {
        response.setStatusCode(500);
        response.write("<h1>500 알 수 없는 오류가 발생했습니다.</h1>");
    }
}
