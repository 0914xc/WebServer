package cn.weixiaochen.http;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class HttpResponse implements Response {

    private static final Logger logger = LoggerFactory.getLogger(HttpResponse.class);

    private String protocol;
    private int statusCode;
    private String statusMessage;
    private Map<String, String> contentHeaders;

    private OutputStream outputStream;
    private HttpRequest httpRequest;

    public HttpResponse(OutputStream outputStream, HttpRequest httpRequest) {
        this.outputStream = outputStream;
        this.httpRequest = httpRequest;
        this.contentHeaders = new HashMap<>();
    }

    public String getProtocol() {
        return httpRequest.getProtocol();
    }

    @Override
    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
        if (statusCode == 200) {
            this.statusMessage = "OK";
        } else if (statusCode == 404) {
            this.statusMessage = "Not Found";
        } else if (statusCode == 500) {
            this.statusMessage = "Internal Server Error";
        }
    }

    @Override
    public void setContentType(String contentType) {
        addContentHeader("Content-Type", contentType);
    }

    @Override
    public void addContentHeader(String name, String value) {
        contentHeaders.put(name, value);
    }

    @Override
    public void setContent(String content) {
        try {
            Writer writer = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8);
            writer.write(getProtocol() + " " + statusCode + " " + statusMessage + "\r\n");
            writer.append("Content-Type: ").append(contentHeaders.get("Content-Type")).append("\r\n");
            writer.append("Content-Length: ").append(String.valueOf(content.length())).append("\r\n");
            contentHeaders.forEach((key, value) -> {
                try {
                    writer.append(key).append(": ").append(value).append("\r\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            writer.append("\r\n");
            writer.append(content);
            writer.flush();
        } catch (IOException e) {
            logger.error("HttpResponse setContent failed.", e);
        }
    }

}
