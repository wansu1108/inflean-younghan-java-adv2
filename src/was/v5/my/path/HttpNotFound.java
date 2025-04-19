package was.v5.my.path;

import was.httpserver.HttpRequest;
import was.httpserver.HttpResponse;
import was.v5.my.HttpPath;

public class HttpNotFound implements HttpPath{

    @Override
    public void path(HttpRequest request, HttpResponse response) {
        response.setStatusCode(404);
        response.writeBody("<h1>404 호출하신 페이지를 찾을 수 없습니다.</h1>");
    }
}
