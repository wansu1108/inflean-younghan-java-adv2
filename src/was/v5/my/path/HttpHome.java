package was.v5.my.path;

import was.httpserver.HttpRequest;
import was.httpserver.HttpResponse;
import was.v5.my.HttpPath;

public class HttpHome implements HttpPath {

    @Override
    public void path(HttpRequest request, HttpResponse response) {
        response.writeBody("<h1>HOME</h1>");
        response.writeBody("<ul>");
        response.writeBody("<li><a href='/site1'>site1</a></li>");
        response.writeBody("<li><a href='/site2'>site2</a></li>");
        response.writeBody("<li><a href='/search?q=hello'>검색</a></li>");
        response.writeBody("</ul>");
    }
}
