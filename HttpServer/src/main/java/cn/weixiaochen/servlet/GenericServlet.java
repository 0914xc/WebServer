package cn.weixiaochen.servlet;

import cn.weixiaochen.http.Response;
import cn.weixiaochen.http.Request;

/**
 * @author 0914xc 2022/6/18
 */
public abstract class GenericServlet implements Servlet {

    @Override
    public void init() {

    }

    @Override
    public abstract void service(Request request, Response response);

    @Override
    public void destroy() {

    }
}
