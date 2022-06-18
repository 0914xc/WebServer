package cn.weixiaochen.http.util;


import cn.weixiaochen.http.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;

/**
 * parse GET/POST http request
 *
 * @author 0914xc 2022/6/15
 */
public class HttpRequestParser {

    private static final Logger logger = LoggerFactory.getLogger(HttpRequestParser.class);

    public static final byte SPACE = ' ';
    public static final byte CR = '\r';
    public static final byte LF = '\n';
    public static final byte[] CRLF = {CR, LF};
    public static final byte[] CRLFCRLF = {CR, LF, CR, LF};

    public static final String GET = "GET";
    public static final String POST = "POST";

    public static final String CONTENT_LENGTH = "Content-Length";
    public static final String CONTENT_TYPE = "Content-Type";
    public static final String CONTENT_TYPE_TEXT_HTML = "text/html";
    public static final String CONTENT_TYPE_TEXT_PLAIN = "text/plain";
    public static final String CONTENT_TYPE_APPLICATION_JSON = "application/json";
    public static final String CONTENT_TYPE_APPLICATION_X_WWW_FORM_URLENCODED = "application/x-www-form-urlencoded";


    private static HttpRequestParser parser;

    private InputStream inputStream;
    private Request request;

    private int parsingRequestLine = 0;


    public HttpRequestParser(InputStream inputStream, Request request) {
        this.inputStream = inputStream;
        this.request = request;
    }

    public void parse() {
        String requestLine;
        while (parsingRequestLine < 3) {
            try {
                requestLine = readLine();
                if (logger.isDebugEnabled()) {
                    logger.debug("requestLine: {}", requestLine);
                }
                if (requestLine.isEmpty()) {
                    parsingRequestLine++;
                } else {
                    if (parsingRequestLine < 1) {
                        parseRequestLine(requestLine);
                        parsingRequestLine++;
                    } else if (parsingRequestLine < 2) {
                        parseRequestHeader(requestLine);
                    } else if (parsingRequestLine < 3) {
                        parseRequestBody(requestLine);
                        parsingRequestLine++;
                    }
                }
            } catch (IOException e) {
                logger.error("HttpRequestParser doParse failed.", e);
                break;
            }
        }
    }

    private String readLine() throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        int i;
        while ((i = inputStream.read()) != -1) {
            if (i == CR) {
                i = inputStream.read();
                if (i == LF) {
                    break;
                }
            }
            stringBuilder.append((char) i);
        }
        return stringBuilder.toString();
    }


    public void parseRequestLine(String requestLine) throws IOException {
        String[] requestLineArray = requestLine.split(" ");
        if (requestLineArray.length != 3) {
            throw new IllegalArgumentException("Invalid request line: " + requestLine);
        }

        // parse request method
        String method = requestLineArray[0];
        if (!method.equals(GET) && !method.equals(POST)) {
            throw new IllegalArgumentException("Invalid request method: " + method);
        }
        request.setMethod(method);

        // parse request uri
        String path = requestLineArray[1];
        if (path.isEmpty()) {
            throw new IllegalArgumentException("Invalid request uri: " + path);
        }
        request.setRequestURI(path);

        // parse request protocol
        String protocol = requestLineArray[2];
        if (!protocol.equals("HTTP/1.1")) {
            throw new IllegalArgumentException("Invalid protocol: " + protocol);
        }
        request.setProtocol(protocol);
    }

    public void parseRequestHeader(String requestHeader) throws IOException {
        String[] requestHeaderArray = requestHeader.split(": ");
        if (requestHeaderArray.length != 2) {
            throw new IllegalArgumentException("Invalid request header: " + requestHeader);
        }

        // parse request header
        String headerName = requestHeaderArray[0];
        String headerValue = requestHeaderArray[1];
        if (headerName.isEmpty() || headerValue.isEmpty()) {
            throw new IllegalArgumentException("Invalid request header: " + requestHeader);
        }
        request.setHeader(headerName, headerValue);
    }

    public void parseRequestBody(String requestBody) throws IOException {
        if (requestBody.isEmpty()) {
            return;
        }
        request.setBody(requestBody);
    }
}
