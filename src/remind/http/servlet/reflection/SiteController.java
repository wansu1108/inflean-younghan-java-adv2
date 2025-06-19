package remind.http.servlet.reflection;

import remind.http.HttpRequest;
import remind.http.HttpResponse;

public class SiteController {

    public void site1(HttpRequest request, HttpResponse response) {
        response.write("<h1>Site1</h1>");
    }

    public void site2(HttpRequest request, HttpResponse response) {
        response.write("<h1>Site2</h1>");
    }
}
