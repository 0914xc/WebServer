package cn.weixiaochen.servlet;

import cn.weixiaochen.http.Request;
import cn.weixiaochen.http.Response;

/**
 * @author 0914xc 2022/6/18
 */
public abstract class AbstractServlet implements Servlet {

    @Override
    public void init() {

    }

    @Override
    public void service(Request request, Response response) {
        if (request.getMethod().equals("GET")) {
            doGet(request, response);
        } else if (request.getMethod().equals("POST")) {
            doPost(request, response);
        }
    }

    @Override
    public void destroy() {

    }

    abstract public void doGet(Request request, Response response);

    abstract public void doPost(Request request, Response response);
}
