package remind.http.servlet.reflection;

import remind.http.HttpRequest;
import remind.http.HttpResponse;

public class SearchController {
    public void search(HttpRequest request, HttpResponse response) {
        String query = request.getParameter("q");

        response.write("<ul>");
        response.write("<li>query: " + query + "</li>");
        response.write("</ul>");
    }
}
