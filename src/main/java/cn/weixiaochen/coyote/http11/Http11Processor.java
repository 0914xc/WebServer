package cn.weixiaochen.coyote.http11;

import cn.weixiaochen.coyote.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * @author 0914xc 2022/3/13
 */
public class Http11Processor extends AbstractProccesor {

    private static final Logger logger = LoggerFactory.getLogger(Http11Processor.class);

    protected Adapter adapter;

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

            adapter.service(request, response);

            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void parseRequest(InputStream input, OutputStream output) {

    }
}
