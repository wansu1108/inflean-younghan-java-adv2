package was.v5.my.path;

import was.httpserver.HttpRequest;
import was.httpserver.HttpResponse;
import was.v5.my.HttpPath;

public class HttpSerach implements HttpPath{

    @Override
    public void path(HttpRequest request, HttpResponse response) {
        String query = request.getParameter("q");

        response.writeBody("<ul>");
        response.writeBody("<li>query: " + query + "</li>");
        response.writeBody("</ul>");
    }
}
