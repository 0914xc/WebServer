package cn.weixiaochen.servlet;

import cn.weixiaochen.http.Response;
import cn.weixiaochen.http.Request;
import cn.weixiaochen.servlet.GenericServlet;

/**
 * @author 0914xc 2022/6/18
 */
public abstract class HttpServlet extends GenericServlet {

    public static final String METHOD_GET = "GET";

    public static final String METHOD_POST = "POST";

    @Override
    public void service(Request request, Response response) {
        if (request.getMethod().equals(METHOD_GET)) {
            doGet(request, response);
        } else if (request.getMethod().equals(METHOD_POST)) {
            doPost(request, response);
        }
    }

    public void doGet(Request request, Response response) {

    }

    public void doPost(Request request, Response response) {

    }
}
