package remind.http.servlet.reflection;

import remind.http.HttpResponse;
import remind.http.v7.Mapping;

public class SiteController {

    @Mapping("/site1")
    public void site1(HttpResponse response) {
        response.write("<h1>Site1</h1>");
    }

    @Mapping("/site2")
    public void site2(HttpResponse response) {
        response.write("<h1>Site2</h1>");
    }
}
