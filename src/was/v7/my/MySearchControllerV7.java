package was.v7.my;

import was.httpserver.HttpRequest;
import was.httpserver.HttpResponse;
import was.v7.my.annotation.GetMapping;

public class MySearchControllerV7 {
    @GetMapping("/search")
    public void search1(HttpRequest request, HttpResponse response) {
        String query = request.getParameter("q");

        response.writeBody("<ul>");
        response.writeBody("<li>query: " + query + "</li>");
        response.writeBody("</ul>");
    }

}
