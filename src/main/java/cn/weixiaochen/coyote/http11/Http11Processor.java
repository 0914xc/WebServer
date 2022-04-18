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
            // get input and output stream from socket
            InputStream input = socket.getInputStream();
            OutputStream output = socket.getOutputStream();

            // parse request
            parseRequest(input, output);

            // process request
            getAdapter().service(request, response);

            // close socket
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * parse request
     *
     * @param input  input stream
     * @param output output stream
     */
    protected void parseRequest(InputStream input, OutputStream output) throws IOException {
        parseRequestLine(input);

        parseHeaders(input);

        parseBody(input);
    }

    /**
     * parse request line
     *
     * @param input input stream
     */
    protected void parseRequestLine(InputStream input) throws IOException {
        String line = readline(input);

        String[] parts = line.split(" ");

        request.setMethod(parts[0]);
        request.setUri(parts[1]);
        request.setProtocol(parts[2]);
    }

    /**
     * parse headers
     *
     * @param input input stream
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
     * parse body
     *
     * @param input input stream
     */
    protected void parseBody(InputStream input) throws IOException {
        if (!request.getHeaders().containsKey("Content-Length")) {
            return;
        }
        int contentLength = Integer.parseInt(request.getHeader("Content-Length").replace("\r", ""));
        byte[] bytes = new byte[contentLength];
        input.read(bytes);
        request.setBody(new String(bytes));
    }

    /**
     * read one line from input stream
     *
     * @param input input stream
     * @return the one line which read from input stream
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
