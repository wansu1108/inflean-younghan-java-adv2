package remind.http.v5;

import java.io.IOException;

import remind.http.HttpRequest;
import remind.http.HttpResponse;
import remind.http.HttpServlet;

public class HomeServlet implements HttpServlet {

    @Override
    public void service(HttpRequest request, HttpResponse response) throws IOException {
        response.write("<h1>HOME</h1>");
        response.write("<ul>");
        response.write("<li><a href='/site1'>site1</a></li>");
        response.write("<li><a href='/site2'>site2</a></li>");
        response.write("<li><a href='/search?q=hello'>검색</a></li>");
        response.write("</ul>");
    }
    
}
