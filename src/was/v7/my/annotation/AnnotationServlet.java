package was.v7.my.annotation;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import was.httpserver.HttpRequest;
import was.httpserver.HttpResponse;
import was.httpserver.HttpServlet;

public class AnnotationServlet implements HttpServlet {

    List<Object> controllers = new ArrayList<>();

    public AnnotationServlet(List<Object> controllers) {
        this.controllers = controllers;
    }

    @Override
    public void service(HttpRequest request, HttpResponse response) throws IOException {
        String path = request.getPath();

        for(Object controller : controllers) {
            Method[] methods = controller.getClass().getDeclaredMethods();
            for(Method method : methods) {
                if(method.isAnnotationPresent(GetMapping.class)) {
                    GetMapping annotation = method.getAnnotation(GetMapping.class);
                    if(path.equals(annotation.value())) {
                        try {
                            method.invoke(controller, request, response);
                        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                            throw new RuntimeException("실패했습니다.");
                        }
                    }
                }
            }
        }
    }
}
