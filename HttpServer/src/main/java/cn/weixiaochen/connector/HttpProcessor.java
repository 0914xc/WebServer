package cn.weixiaochen.connector;

import cn.weixiaochen.http.HttpResponse;
import cn.weixiaochen.http.util.HttpRequestParser;
import cn.weixiaochen.http.HttpRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.Socket;
import java.util.Arrays;

public class HttpProcessor {

    private static final Logger logger = LoggerFactory.getLogger(HttpProcessor.class);

    private HttpRequest request;
    private HttpResponse response;

    public HttpProcessor() {

    }

    public void process(Socket socket) {
        try {
            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();

            this.request = new HttpRequest();
            this.response = new HttpResponse(outputStream, request);

            parseRequest(inputStream);

            handleRequest();

            socket.close();

        } catch (Exception e) {
            logger.error("HttpProcessor process failed.", e);
        }
    }

    private void parseRequest(InputStream inputStream) {
        HttpRequestParser requestParser = new HttpRequestParser(inputStream, request);
        requestParser.parse();
    }

    private void handleRequest() {
        logger.info("HttpProcessor handleRequest: {}", request.getRequestURI());
        RequestHandler requestHandler = new RequestHandler(request, response);
        requestHandler.handleRequest();
    }

}
