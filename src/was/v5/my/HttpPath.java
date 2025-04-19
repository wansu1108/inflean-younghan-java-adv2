package was.v5.my;

import was.httpserver.HttpRequest;
import was.httpserver.HttpResponse;

public interface HttpPath {
    void path(HttpRequest request, HttpResponse response);
}
