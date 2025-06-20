package remind.http;

import java.io.IOException;
import java.util.List;

import remind.http.servlet.DiscardServet;
import remind.http.servlet.annotation.AnnotationServletV1;
import remind.http.servlet.annotation.AnnotationServletV2;
import remind.http.servlet.annotation.AnnotationServletV3;
import remind.http.servlet.reflection.ReflectionServlet;
import remind.http.servlet.reflection.SearchController;
import remind.http.servlet.reflection.SiteController;
import remind.http.v1.HttpServerV1;
import remind.http.v2.HttpServerV2;
import remind.http.v3.HttpServerV3;
import remind.http.v4.HttpServerV4;
import remind.http.v5.HomeServlet;
import remind.http.v5.HttpServerV5;
import remind.http.v5.SearchServlet;
import remind.http.v5.ServletManager;
import remind.http.v5.Site1Servlet;
import remind.http.v5.Site2Servlet;

public class HttpServerMain {

    private static final int PORT = 12345;
    public static void main(String[] args) throws IOException, InterruptedException {
        ServletManager servletManager = new ServletManager();
        // servletManager.add("/", new HomeServlet());
        // servletManager.add("/site1", new Site1Servlet());
        // servletManager.add("/site2", new Site2Servlet());
        // servletManager.add("/search", new SearchServlet());
        List<Object> controllers = List.of(new SiteController(), new SearchController());

        servletManager.add("/favicon.ico", new DiscardServet());
        servletManager.setDefaultServlet(new AnnotationServletV3(controllers));

        HttpServerV5 httpserver = new HttpServerV5(PORT, servletManager);
        httpserver.start();
    }
}
