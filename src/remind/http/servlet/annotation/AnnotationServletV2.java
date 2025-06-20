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

public class AnnotationServletV2 implements HttpServlet {

    private final List<Object> controllers;

    public AnnotationServletV2(List<Object> controllers) {
        this.controllers = controllers;
    }

    @Override
    public void service(HttpRequest request, HttpResponse response) throws IOException {
        String path = request.getPath();

        for (Object controller : controllers) {
            Class<?> clazz = controller.getClass();
            for (Method method : clazz.getDeclaredMethods()) {
                if (method.isAnnotationPresent(Mapping.class)) {
                    Mapping annotation = method.getAnnotation(Mapping.class);
                    String value = annotation.value();
                    if (path.equals(value)) {
                        invoke(controller, request, response, method);
                        return;
                    }
                }
            }
        }

        throw new PageNotFoundException("NotFoud page url: " + request.getPath());
    }

    private void invoke(Object controller, HttpRequest request, HttpResponse response, Method method) {
        Class<?>[] parameterTypes = method.getParameterTypes();
        Object[] args = new Object[parameterTypes.length];
        for (int i = 0; i < parameterTypes.length; i++) {
            if (parameterTypes[i] == HttpRequest.class) {
                args[i] = request;
            } else if (parameterTypes[i] == HttpResponse.class) {
                args[i] = response;
            } else {
                throw new IllegalArgumentException("Unsupported ParameterType : " + parameterTypes[i]);
            }
        }

        try {
            method.invoke(controller, args);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

}
