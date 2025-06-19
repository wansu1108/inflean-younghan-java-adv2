package remind.http.v5;

import java.io.IOException;

import remind.http.HttpRequest;
import remind.http.HttpResponse;
import remind.http.HttpServlet;

public class SearchServlet implements HttpServlet {

    @Override
    public void service(HttpRequest request, HttpResponse response) throws IOException {
        String query = request.getParameter("q");

        response.write("<ul>");
        response.write("<li>query: " + query + "</li>");
        response.write("</ul>");
    }
    
}
