package remind.http.servlet.reflection;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import remind.http.HttpRequest;
import remind.http.HttpResponse;
import remind.http.HttpServlet;
import remind.http.PageNotFoundException;

public class ReflectionServlet implements HttpServlet {

    public final List<Object> controllers;

    public ReflectionServlet(List<Object> controllers) {
        this.controllers = controllers;
    }

    @Override
    public void service(HttpRequest request, HttpResponse response) throws IOException {
        String path = request.getPath();

        for (Object controller : controllers) {
            Class<?> clazz = controller.getClass();
            for (Method method : clazz.getDeclaredMethods()) {
                String methodName = method.getName();
                if (path.equals("/" + methodName)) {
                    invoke(controller, method, request, response);
                    return;
                }
            }
        }

        throw new PageNotFoundException("NotFoud page url: " + request.getPath());
    }

    private void invoke(Object target, Method method, HttpRequest request, HttpResponse response) {
        try {
            method.invoke(target, request, response);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
