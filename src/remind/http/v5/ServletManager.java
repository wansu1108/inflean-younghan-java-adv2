package remind.http.v5;

import static remind.common.MyLogger.log;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import remind.http.HttpRequest;
import remind.http.HttpResponse;
import remind.http.HttpServlet;
import remind.http.PageNotFoundException;
import remind.http.servlet.InternerServerErrorServlet;
import remind.http.servlet.NotfoundServlet;

public class ServletManager {

    private final Map<String, HttpServlet> servletMap = new HashMap<>();
    private HttpServlet defaultServlet;
    private HttpServlet notFoundServlet = new NotfoundServlet();
    private HttpServlet internerServerErrorServlet = new InternerServerErrorServlet();

    public ServletManager() {
    }

    public void add(String path, HttpServlet servlet) {
        servletMap.put(path, servlet);
    }

    public void setDefaultServlet(HttpServlet defaultServlet) {
        this.defaultServlet = defaultServlet;
    }
    
    public void setNotFoundServlet(HttpServlet notFoundServlet) {
        this.notFoundServlet = notFoundServlet;
    }

    public void setInternerServerErrorServlet(HttpServlet internerServerErrorServlet) {
        this.internerServerErrorServlet = internerServerErrorServlet;
    }

    public void excute(HttpRequest request, HttpResponse response) throws IOException {
        try {
            HttpServlet httpServlet = servletMap.getOrDefault(request.getPath(), defaultServlet);

            if(httpServlet == null) {
                throw new PageNotFoundException("Not Found Page url: " + request.getPath());
            }
        
            httpServlet.service(request, response);
        } catch (PageNotFoundException e) {
            log(e);
            notFoundServlet.service(request, response);
        } catch (Exception e) {
            log(e);
            internerServerErrorServlet.service(request, response);
        }
    }

}
