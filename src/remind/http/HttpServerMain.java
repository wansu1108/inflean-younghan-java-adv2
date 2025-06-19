package remind.http;

import java.io.IOException;

import remind.http.servlet.DiscardServet;
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
        servletManager.add("/", new HomeServlet());
        servletManager.add("/site1", new Site1Servlet());
        servletManager.add("/site2", new Site2Servlet());
        servletManager.add("/search", new SearchServlet());
        servletManager.add("/favicon.ico", new DiscardServet());

        HttpServerV5 httpserver = new HttpServerV5(PORT, servletManager);
        httpserver.start();
    }
}
