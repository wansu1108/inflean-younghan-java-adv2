package was.v6.my;

import was.httpserver.HttpRequest;
import was.httpserver.HttpResponse;

public class ServletConroller {
    
    public void home(HttpRequest request, HttpResponse response) {
        response.writeBody("<h1>HOME</h1>");
        response.writeBody("<ul>");
        response.writeBody("<li><a href='/site1'>site1</a></li>");
        response.writeBody("<li><a href='/site2'>site2</a></li>");
        response.writeBody("<li><a href='/search?q=hello'>검색</a></li>");
        response.writeBody("</ul>");
    }

    public void site1(HttpRequest request, HttpResponse response) {
        response.writeBody("<h1>Site1</h1>");
    }

    public void site2(HttpRequest request, HttpResponse response) {
        response.writeBody("<h1>Site2</h1>");
    }

    public void search(HttpRequest request, HttpResponse response) {
        String query = request.getParameter("q");

        response.writeBody("<ul>");
        response.writeBody("<li>query: " + query + "</li>");
        response.writeBody("</ul>");
    }

    public void discard(HttpRequest request, HttpResponse response) {
        // empty
    }

    public void notFound(HttpRequest request, HttpResponse response) {
        response.setStatusCode(404);
        response.writeBody("<h1>404 호출하신 페이지를 찾을 수 없습니다.</h1>");
    }

    public void serverError(HttpRequest request, HttpResponse response) {
        response.setStatusCode(500);
        response.writeBody("<h1>Internal Error</h1>");        
    }
}
