package remind.http.servlet.reflection;

import remind.http.HttpRequest;
import remind.http.HttpResponse;
import remind.http.v7.Mapping;

public class SearchController {

    @Mapping("/")
    public void home(HttpRequest request, HttpResponse response) {
        response.write("<h1>HOME_</h1>");
        response.write("<ul>");
        response.write("<li><a href='/site1'>site1</a></li>");
        response.write("<li><a href='/site2'>site2</a></li>");
        response.write("<li><a href='/search?q=hello'>검색</a></li>");
        response.write("</ul>");
    }

    @Mapping("/search")
    public void search(HttpRequest request, HttpResponse response) {
        String query = request.getParameter("q");

        response.write("<ul>");
        response.write("<li>query: " + query + "</li>");
        response.write("</ul>");
    }
}
