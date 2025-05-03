package was.v7.my;

import java.io.IOException;
import java.util.List;

import was.httpserver.HttpServer;
import was.httpserver.ServletManager;
import was.httpserver.servlet.DiscardServlet;
import was.v7.my.annotation.AnnotationServlet;

public class MyServerMainV7 {
    private static int PORT = 12345;

    public static void main(String[] args) throws IOException {
        List<Object> controllers = List.of(new MySiteControllerV7(), new MySearchControllerV7());
        AnnotationServlet annotationServlet = new AnnotationServlet(controllers);

        ServletManager servletManager = new ServletManager();
        servletManager.setDefaultServlet(annotationServlet);
        servletManager.add("/favicon.ico", new DiscardServlet());

        HttpServer server = new HttpServer(PORT, servletManager);
        server.start();
    }
}
