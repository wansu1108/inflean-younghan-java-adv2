package remind.http;

import java.io.PrintWriter;
import static java.nio.charset.StandardCharsets.*;

public class HttpResponse {
    private final PrintWriter writer;
    private int statusCode = 200;
    private final StringBuilder bodyBuilder = new StringBuilder();
    private String contentType = "text/html; charset=UTF-8";

    public HttpResponse(PrintWriter writer) {
        this.writer = writer;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public StringBuilder getBodyBuilder() {
        return bodyBuilder;
    }

    public void write(String line) {
        bodyBuilder.append(line);
    }

    public void flush() {
        int length = bodyBuilder.toString().getBytes(UTF_8).length;
        writer.println("HTTP1.1 " + statusCode + " " + getReasonPhrase(statusCode));
        writer.println("Content-Type: " + contentType);
        writer.println("Content-Length: " + length + ";");
        writer.println();
        writer.println(bodyBuilder);
        writer.flush();
    }

    public String getReasonPhrase(int statusCode) {
        switch (statusCode) {
            case 200:
                return "OK";
            case 404:
                return "Not Found";
            case 500:
                return "Internal Server Error";
            default:
                return "Unknown Status";
        }
    }
}
