package was.v7.my;

import was.httpserver.HttpRequest;
import was.httpserver.HttpResponse;
import was.v7.my.annotation.GetMapping;

public class MySiteControllerV7 {

    @GetMapping("/")
    public void home(HttpRequest request, HttpResponse response) {
        response.writeBody("<h1>HOME</h1>");
        response.writeBody("<ul>");
        response.writeBody("<li><a href='/site1'>site1</a></li>");
        response.writeBody("<li><a href='/site2'>site2</a></li>");
        response.writeBody("<li><a href='/search?q=hello'>검색</a></li>");
        response.writeBody("</ul>");
    }

    @GetMapping("/site1")
    public void page1(HttpRequest request, HttpResponse response) {
        response.writeBody("<h1>Site1 zzz</h1>");
    }

    @GetMapping("/site2")
    public void page2(HttpRequest request, HttpResponse response) {
        response.writeBody("<h1>Site2 zzz</h1>");
    }
}
