package remind.http.servlet.annotation;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import remind.http.HttpRequest;
import remind.http.HttpResponse;
import remind.http.HttpServlet;
import remind.http.PageNotFoundException;
import remind.http.v7.Mapping;

public class AnnotationServletV3 implements HttpServlet {

    private Map<String, ControllerMethod> pathMap = new HashMap<>();

    public AnnotationServletV3(List<Object> controllers) {
        initializePathMapping(controllers);
    }

    private void initializePathMapping(List<Object> controllers) {
        for(Object controller : controllers) {
            Class<?> clazz = controller.getClass();

            for(Method method : clazz.getDeclaredMethods()) {
                if(method.isAnnotationPresent(Mapping.class)) {
                    Mapping annotation = method.getAnnotation(Mapping.class);
                    String path = annotation.value();
                    
                    if(pathMap.containsKey(path)) {
                        throw new IllegalArgumentException("Duplicated Path : " + path);
                    }
                    
                    pathMap.put(path, new ControllerMethod(controller, method));
                }        
            }
        }
    }

    @Override
    public void service(HttpRequest request, HttpResponse response) throws IOException {
        String path = request.getPath();

        ControllerMethod controllerMethod = pathMap.get(path);

        if(controllerMethod == null) {
            throw new PageNotFoundException("NotFoud page url: " + request.getPath());
        }

        controllerMethod.invoke(request, response);
    
    }

    public static class ControllerMethod {
        private Object controller;
        private Method method;
        
        public ControllerMethod(Object controller, Method method) {
            this.controller = controller;
            this.method = method;
        }

        private void invoke(HttpRequest request, HttpResponse response) {
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
}
