package cn.weixiaochen.coyote;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 0914xc 2021/12/11
 */
public class Request {

    /**
     * 请求方法
     */
    private String method;

    /**
     * 请求路径
     */
    private String uri;

    /**
     * 请求协议
     */
    private String protocol;

    /**
     * 请求头
     */
    private Map<String, Object> headers = new HashMap<>();

    /**
     * 请求参数
     */
    private Map<String, Object> params = new HashMap<>();

    /**
     * 请求体
     */
    private String body;

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getHeader(String key) {
        return (String) headers.get(key);
    }

    public void addHeader(String key, String value) {
        headers.put(key, value);
    }

    public Map<String, Object> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, Object> headers) {
        this.headers = headers;
    }

    public String getParam(String key) {
        return (String) params.get(key);
    }

    public void addParam(String key, String value) {
        params.put(key, value);
    }

    public Map<String, Object> getParams() {
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String toString() {
        return "Request [method=" + method + ", uri=" + uri + ", protocol=" + protocol + ", headers=" + headers + ", params=" + params + ", body=" + body + "]";
    }

}
