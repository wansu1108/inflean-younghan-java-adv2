package remind.http.v5;

import java.io.IOException;

import remind.http.HttpRequest;
import remind.http.HttpResponse;
import remind.http.HttpServlet;

public class Site1Servlet implements HttpServlet {

    @Override
    public void service(HttpRequest request, HttpResponse response) throws IOException {
        response.write("<h1>Site1</h1>");
    }

}
