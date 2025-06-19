package remind.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class HttpRequest {

    private String method;
    private String path;
    private Map<String, String> queryParameters = new HashMap<>();
    private Map<String, String> headers = new HashMap<>();

    public HttpRequest(BufferedReader input) throws IOException {
        parseHedaer(input);
        // parseBody(input);
    }

    private void parseHedaer(BufferedReader input) throws IOException {
        String start_line = input.readLine();
        String[] parts = start_line.split(" ");
        if (parts.length != 3) {
            throw new IllegalArgumentException("잘못된 HTTP 형식 입니다: " + start_line);
        }

        this.method = parts[0];
        parseParameter(parts[1]);

        String line;
        while((line = input.readLine()) != null) {
            if(line.isEmpty()) {
                break;
            }
            String[] headerParts = line.split(":");
            String headerValue = (headerParts.length == 1 && headerParts[1].isEmpty()) ? "" : headerParts[1];
            headers.put(headerParts[0], headerValue);
        }
    }

    private void parseParameter(String totalPath) {
        String[] parts = totalPath.split("\\?");
        this.path = parts[0];

        if(parts.length != 1 && parts[1] != null && !parts[1].isEmpty()) {
            for(String queryParameter : parts[1].split("\\&")) {
                String[] queryParameterParts = queryParameter.split("\\=");
                String key = URLDecoder.decode(queryParameterParts[0], StandardCharsets.UTF_8);
                String value = (queryParameterParts.length > 1) ? URLDecoder.decode(queryParameterParts[1], StandardCharsets.UTF_8) : "";
                queryParameters.put(key, value);
            }
        }
    }

    private void parseBody(BufferedReader input) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'parseBody'");
    }
    
    public String getMethod() {
        return method;
    }

    public String getPath() {
        return path;
    }

    public String getParameter(String key) {
        return queryParameters.get(key);
    }

    public Map<String, String> getHeaders() {
        return headers;
    }
}
