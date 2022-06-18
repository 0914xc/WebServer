package cn.weixiaochen.http;

import java.util.Map;

/**
 * @author 0914xc 2022/6/18
 */
public interface Request {

    void setRequestURI(String requestURI);

    String getRequestURI();

    void setMethod(String method);

    String getMethod();

    void setProtocol(String protocol);

    String getProtocol();

    void setHeaders(Map<String, String> headers);

    void setHeader(String key, String value);

    Map<String, String> getHeaders();

    String getHeader(String key);

    void setBody(String body);

    String getBody();
}
