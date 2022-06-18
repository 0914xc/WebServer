package cn.weixiaochen.servlet;

import cn.weixiaochen.http.Request;
import cn.weixiaochen.http.Response;

/**
 * @author 0914xc 2022/6/18
 */
public interface Servlet {

    void init();

    void service(Request request, Response response);

    void destroy();
}
