package was.v9.my;

import was.httpserver.HttpRequest;
import was.httpserver.HttpResponse;
import was.httpserver.servlet.annotation.Mapping;

public class SearchControllerV9 {
    @Mapping("/search")
    public void search1(HttpRequest request, HttpResponse response) {
        String query = request.getParameter("q");

        response.writeBody("<ul>");
        response.writeBody("<li>query: " + query + "</li>");
        response.writeBody("</ul>");
    }

}
