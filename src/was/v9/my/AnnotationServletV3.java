package was.v9.my;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import was.httpserver.HttpRequest;
import was.httpserver.HttpResponse;
import was.httpserver.HttpServlet;
import was.httpserver.PageNotFoundException;
import was.httpserver.servlet.annotation.Mapping;

public class AnnotationServletV3 implements HttpServlet{

    private final Map<String, Invoke> pathMap;
    
    public AnnotationServletV3(List<Object> controllers) { 
        pathMap = createPathMap(controllers);
    }
    
    @Override
    public void service(HttpRequest request, HttpResponse response) throws IOException {
        String path = request.getPath();
        
        Invoke invoke = pathMap.get(path);
        if(invoke == null) {
            throw new PageNotFoundException("request : " + request);
        }

        Object[] args = findMethodArgs(invoke.getMethod(), request, response);

        invoke.invoke(args);
    }

    public Map<String, Invoke> createPathMap(List<Object> controllers) {
        Map<String, Invoke> pathMap = new HashMap<>();
        for(Object controller : controllers) {
            Method[] methods = controller.getClass().getDeclaredMethods();
            for(Method method : methods) {
                if(method.isAnnotationPresent(Mapping.class)) {
                    Mapping annotation = method.getAnnotation(Mapping.class);
                    String value = annotation.value();
                    
                    if(pathMap.get(value) != null) {
                        throw new IllegalArgumentException("Duplicated pathmapping : " + value);
                    }
                    
                    pathMap.put(value, new Invoke(controller, method));
                }
            }
        }

        return pathMap;
    }

    public Object[] findMethodArgs(Method method, HttpRequest request, HttpResponse response) {
        Class<?>[] parameterTypes = method.getParameterTypes();
        Object[] args = new Object[parameterTypes.length];

        for(int i = 0; i < parameterTypes.length; i++) {
            if(parameterTypes[i] ==  HttpRequest.class) {
                args[i] = request;
            } else if(parameterTypes[i] ==  HttpResponse.class) {
                args[i] = response;
            } else {
                throw new IllegalArgumentException("Unsupported parametertype : " + parameterTypes[i]);
            }
        }
        
        return args;
    }
}
