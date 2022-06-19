package cn.weixiaochen.connector;

import cn.weixiaochen.http.Response;
import cn.weixiaochen.servlet.Servlet;
import cn.weixiaochen.http.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author 0914xc 2022/6/18
 */
public class RequestHandler {

    private static final Logger logger = LoggerFactory.getLogger(RequestHandler.class);

    private Request request;
    private Response response;

    public RequestHandler(Request request, Response response) {
        this.request = request;
        this.response = response;
    }

    public void handleRequest() {
        logger.info("RequestHandler handleRequest: {}", request.getRequestURI());
        Servlet servlet;
        try {
            servlet = (Servlet) Class.forName("example.weixiaochen.servlet" + request.getRequestURI().replace("/", ".")).newInstance();
            servlet.service(request, response);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
