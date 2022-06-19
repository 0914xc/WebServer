package cn.weixiaochen.http;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;

/**
 * @author 0914xc 2022/6/18
 */
public interface Response {

    // set status code
    void setStatusCode(int statusCode);

    // set content type
    void setContentType(String contentType);

    // add content header
    void addContentHeader(String name, String value);

    // set content
    void setContent(String content);
}
