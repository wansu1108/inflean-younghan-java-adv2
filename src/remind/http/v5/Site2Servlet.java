package remind.http.v5;

import java.io.IOException;

import remind.http.HttpRequest;
import remind.http.HttpResponse;
import remind.http.HttpServlet;

public class Site2Servlet implements HttpServlet  {

    @Override
    public void service(HttpRequest request, HttpResponse response) throws IOException {
        response.write("<h1>Site2</h1>");
    }
    
}
