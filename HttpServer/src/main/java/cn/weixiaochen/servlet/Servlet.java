package cn.weixiaochen.servlet;

import cn.weixiaochen.http.Response;
import cn.weixiaochen.http.Request;

/**
 * @author 0914xc 2022/6/18
 */
public interface Servlet {

    void init();

    void service(Request request, Response response);

    void destroy();
}
