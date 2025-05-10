package webservice;

import java.io.IOException;
import java.util.List;

import io.member.MemberRepository;
import io.member.impl.FileMemberRepository;
import was.httpserver.HttpServer;
import was.httpserver.HttpServlet;
import was.httpserver.ServletManager;
import was.httpserver.servlet.DiscardServlet;
import was.httpserver.servlet.annotation.AnnotationServletV3;

public class MemberServiceMain {

    private static final int PORT = 12345;

    public static void main(String[] args) throws IOException {
        MemberRepository memberRepository = new FileMemberRepository();
        HttpServlet defaultServlet = new AnnotationServletV3(List.of(new MemberController(memberRepository)));
        ServletManager servletManager = new ServletManager();
        servletManager.add("/favicon.ico", new DiscardServlet());
        servletManager.setDefaultServlet(defaultServlet);
    
        HttpServer httpserver = new HttpServer(PORT, servletManager);
        httpserver.start();
    }
}
