package remind.http.servlet.annotation;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import remind.http.HttpRequest;
import remind.http.HttpResponse;
import remind.http.HttpServlet;
import remind.http.PageNotFoundException;
import remind.http.v7.Mapping;

public class AnnotationServlet implements HttpServlet {

    private final List<Object> controllers;

    public AnnotationServlet(List<Object> controllers) {this.controllers = controllers;}

    @Override
    public void service(HttpRequest request, HttpResponse response) throws IOException {
        String path = request.getPath();

        for(Object controller : controllers) {
            Class<?> clazz = controller.getClass();
            for(Method method : clazz.getDeclaredMethods()) {
                if(method.isAnnotationPresent(Mapping.class)) {
                    Mapping annotation = method.getAnnotation(Mapping.class);
                    String value = annotation.value();
                    if(path.equals(value)) {
                        invoke(controller, request, response, method);
                        return;
                    }
                }
            }
        }
        
        throw new PageNotFoundException("NotFoud page url: " + request.getPath());
    }

    private void invoke(Object controller, HttpRequest request, HttpResponse response, Method method) {
        try {
            method.invoke(controller, request, response);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
    
}
