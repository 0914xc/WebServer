package cn.weixiaochen.coyote.http11;

import cn.weixiaochen.coyote.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.Socket;

/**
 *
 * @author 0914xc 2022/3/13
 */
public class Http11Processor extends AbstractProccesor {

    private static final Logger logger = LoggerFactory.getLogger(Http11Processor.class);

    private final Request request;
    private final Response response;

    public Http11Processor() {
        request = new Request();
        response = new Response();
    }

    @Override
    public void service(Socket socket) {
        try {
            InputStream input = socket.getInputStream();
            OutputStream output = socket.getOutputStream();

            parseRequest(input, output);

            // getAdapter().service(request, response);

            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 解析请求
     *
     * @param input  输入流
     * @param output 输出流
     */
    protected void parseRequest(InputStream input, OutputStream output) throws IOException {
        parseRequestLine(input);

        parseHeaders(input);

        parseBody(input);
    }

    /**
     * 解析请求行
     *
     * @param input 输入流
     */
    protected void parseRequestLine(InputStream input) throws IOException {
        String line = readline(input);

        String[] parts = line.split(" ");

        request.setMethod(parts[0]);
        request.setUri(parts[1]);
        request.setProtocol(parts[2]);
    }

    /**
     * 解析请求头
     *
     * @param input 输入流
     */
    protected void parseHeaders(InputStream input) throws IOException {
        String line = readline(input);

        while (!line.isEmpty() && !line.equals("\r")) {
            String[] parts = line.split(": ");

            request.addHeader(parts[0], parts[1]);

            line = readline(input);
        }
    }

    /**
     * 解析请求体
     *
     * @param input 输入流
     */
    protected void parseBody(InputStream input) throws IOException {
        if (!request.getHeaders().containsKey("Content-Length")) {
            return;
        }
        int contentLength = Integer.parseInt(request.getHeader("Content-Length"));
        byte[] bytes = new byte[contentLength];
        input.read(bytes);
        request.setBody(new String(bytes));
    }

    /**
     * 读取一行
     *
     * @param input 输入流
     * @return 读取的一行
     */
    protected String readline(InputStream input) throws IOException {
        StringBuilder builder = new StringBuilder();
        int b = input.read();
        while (b != '\n') {
            builder.append((char) b);
            b = input.read();
        }
        return builder.toString();
    }
}
