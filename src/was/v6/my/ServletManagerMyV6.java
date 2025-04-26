package was.v6.my;

import static util.MyLogger.log;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import was.httpserver.HttpRequest;
import was.httpserver.HttpResponse;
import was.httpserver.PageNotFoundException;

public class ServletManagerMyV6 {

    private final Map<String, String> servletMap = new HashMap<>();
    private String notfound = "notFound";
    private String serverError = "serverError";

    public ServletManagerMyV6() {
    }

    public void add(String path, String methodName) {
        servletMap.put(path, methodName);
    }

    public void setNotFound(String notfound) {
        this.notfound = notfound;
    }

    public void setServerError(String serverError) {
        this.serverError = serverError;
    }

    public void excute(HttpRequest request, HttpResponse response) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        ServletConroller servletConroller = new ServletConroller();
        Class<? extends ServletConroller> aClass =  servletConroller.getClass();
        try {
            String methodName = servletMap.getOrDefault(request.getPath(), "notFound");

            if(methodName == null) {
                throw new PageNotFoundException("request url= " + request.getPath());
            }

            Method method = aClass.getMethod(methodName, HttpRequest.class, HttpResponse.class);
            method.invoke(servletConroller, request, response);
        } catch (PageNotFoundException e) {
            log(e);
            Method method = aClass.getMethod(notfound, HttpRequest.class, HttpResponse.class);
            method.invoke(servletConroller, request, response);            
        } catch (Exception e) {
            log(e);
            Method method = aClass.getMethod(serverError, HttpRequest.class, HttpResponse.class);
            method.invoke(servletConroller, request, response);            
        }
    }
}
