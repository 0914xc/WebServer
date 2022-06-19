package cn.weixiaochen.http;

import java.util.HashMap;
import java.util.Map;

public class HttpRequest implements Request {

    private String requestURI;

    private String method;

    private String protocol;

    private Map<String, String> headers;

    private String body;

    public HttpRequest() {
        this.headers = new HashMap<>();
    }

    @Override
    public void setRequestURI(String requestURI) {
        this.requestURI = requestURI;
    }

    @Override
    public String getRequestURI() {
        return requestURI;
    }

    @Override
    public void setMethod(String method) {
        this.method = method;
    }

    @Override
    public String getMethod() {
        return method;
    }

    @Override
    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    @Override
    public String getProtocol() {
        return protocol;
    }

    @Override
    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    @Override
    public void setHeader(String key, String value) {

    }

    @Override
    public Map<String, String> getHeaders() {
        return headers;
    }

    @Override
    public String getHeader(String key) {
        return null;
    }

    @Override
    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public String getBody() {
        return body;
    }
}
